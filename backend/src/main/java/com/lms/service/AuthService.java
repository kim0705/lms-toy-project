package com.lms.service;

import com.lms.dto.auth.LoginLogDto;
import com.lms.dto.auth.RefreshTokenDto;
import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespLoginDto;
import com.lms.entity.User;
import com.lms.mapper.LoginLogMapper;
import com.lms.mapper.UserMapper;
import com.lms.security.JwtTokenProvider;
import com.lms.util.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 사용자 인증 및 권한 관리 서비스
 * 로그인 처리, 토큰 발급, 접속 이력 저장 등 인증과 관련된 핵심 비즈니스 로직을 수행합니다.
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    /* Refresh Token 만료 시간 */
    @Value("${jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;

    private final UserMapper userMapper;
    private final LoginLogMapper loginLogMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    /**
     * 통합 로그인 처리
     * 아이디/비밀번호 검증 후 Access/Refresh 토큰을 발급하고 로그인 로그를 기록합니다.
     * @param reqLoginDto 로그인 요청 정보 (userId, password)
     * @param request 접속 정보 추출을 위한 HttpServletRequest
     * @return 발급된 AccessToken 및 RefreshToken
     */
    @Transactional
    public RespLoginDto signIn(ReqLoginDto reqLoginDto, HttpServletRequest request) {
        log.info("[Login Request] UserID: {}", reqLoginDto.getUserId());

        /* DB 조회 */
        User user = userMapper.selectUserInfoByUserId(reqLoginDto.getUserId());

        if(user == null) {
            log.warn("[Login Fail] 존재하지 않는 아이디: {}", reqLoginDto.getUserId());
            throw new RuntimeException("등록되지 않은 아이디입니다.");
        }

        /* 비밀번호 확인 */
        if(!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            log.warn("[Login Fail] 비밀번호 불일치: {}", reqLoginDto.getUserId());
            throw new RuntimeException("비밀번호를 확인해주세요.");
        }

        /* 정보 추출 */
        String userAgent = request.getHeader("User-Agent");
        Map<String, String> userAgentDetails = UserAgentUtil.extractUserAgentInfo(userAgent);
        String clientIp = getClientIp(request);

        /* User-Agent에서 추출한 값 변수에 담기 */
        String deviceType = userAgentDetails.get("deviceType");
        String osName = userAgentDetails.get("osName");
        String browserName = userAgentDetails.get("browserName");

        /* 토큰 생성 */
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

        log.info("[Login Success] User: {}, Device: {}, IP: {}", user.getUserId(), deviceType, getClientIp(request));

        /* 로그인 이력 생성 */
        loginLogMapper.insertLoginLog(LoginLogDto.of(user.getUserId(), deviceType, osName, browserName, userAgent, clientIp));

        /* Refresh Token 저장 */
        refreshTokenService.saveRefreshToken(RefreshTokenDto.of(user.getUserId(), refreshToken, deviceType, refreshExpirationTime));

        return new RespLoginDto(accessToken, refreshToken);
    }

    /**
     * 클라이언트 실제 IP 추출
     * 프록시 서버 등을 거칠 경우를 대비하여 헤더를 순차적으로 확인합니다.
     * - 직접 접속 시: request.getRemoteAddr() 사용
     * - 프록시(Nginx, AWS ALB 등) 거칠 시: 'X-Forwarded-For' 등의 헤더에서 실제 IP 추출
     * - 보안 및 운영 환경(L4, Proxy) 대응을 위한 방어 코드입니다.
     * @param request HttpServletRequest
     * @return 클라이언트의 실제 IP 주소
     */
    private String getClientIp(HttpServletRequest request) {
        /* 1. 프록시 서버를 거칠 경우 실제 IP가 담기는 표준 헤더 확인 */
        String ip = request.getHeader("X-Forwarded-For");

        /* 2. 각 제조사별/설정별 특이 헤더 순차 확인 (null, 빈값, unknown 방어) */
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP"); /* 웹로직 등 특정 WAS용 */
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        /* 3. 모든 헤더에 정보가 없다면 최종적으로 호출자의 IP를 가져옴 */
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else {
            /* X-Forwarded-For에 IP가 여러 개일 경우 첫 번째 IP가 진짜 클라이언트 IP */
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    /**
     * 토큰 재발급 처리
     * Refresh Token의 유효성을 검증하고 Redis에 저장된 토큰과 비교하여 새로운 Access Token을 발급합니다.
     * @param refreshToken 클라이언트로부터 전달받은 Refresh Token
     * @param request 접속 기기 정보 추출을 위한 HttpServletRequest
     * @return 새로운 Access Token 및 기존(혹은 갱신된) Refresh Token
     */
    @Transactional
    public RespLoginDto refresh(String refreshToken,HttpServletRequest request) {
        log.info("[Reissue Request] 리프레시 토큰 재발급 시도");

        /* 1. JWT 자체 유효성 및 만료 여부 확인 */
        if(!jwtTokenProvider.validateToken(refreshToken)) {
            log.warn("[Reissue Fail] 유효하지 않은 리프레시 토큰");
            throw new RuntimeException("유효하지 않거나 만료된 리프레시 토큰입니다. 다시 로그인해주세요.");
        }

        /* 2. 토큰에서 유저 식별자(학번 등) 추출 */
        String userId = jwtTokenProvider.getUserId(refreshToken);

        /* 3. 기기 정보 추출 (signIn 때와 동일하게 기기별 토큰 관리를 위함) */
        String userAgent = request.getHeader("User-Agent");
        String deviceType = UserAgentUtil.extractUserAgentInfo(userAgent).get("deviceType");

        /* 4. Redis(또는 DB)에 저장된 해당 유저+기기의 Refresh Token 조회 */
        String savedToken = refreshTokenService.findRefreshToken(userId, deviceType);

        /* 5. 클라이언트가 보낸 토큰과 저장된 토큰이 일치하는지 비교 */
        if (savedToken == null || !savedToken.equals(refreshToken)) {
            log.warn("[Reissue Fail] 토큰 불일치 혹은 만료된 토큰 - User: {}, Device: {}", userId, deviceType);
            throw new RuntimeException("토큰 정보가 일치하지 않습니다. 보안 위험이 있으니 다시 로그인해주세요.");
        }

        /* 6. 새로운 Access Token 발급 */
        User user = userMapper.selectUserInfoByUserId(userId);
        if(user == null) {
            log.error("[Reissue Fail] 유효한 토큰이나 DB에 유저 없음 - UserID: {}", userId);
            throw new RuntimeException("사용자 정보를 찾을 수 없습니다.");
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());

        log.info("[Reissue Success] Access Token 재발급 완료 - User: {}", userId);

        /* 7. 응답 반환 */
        return new RespLoginDto(newAccessToken, refreshToken);
    }
}
