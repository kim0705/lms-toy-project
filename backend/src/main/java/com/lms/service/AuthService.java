package com.lms.service;

import com.lms.dto.auth.LoginLogDto;
import com.lms.dto.auth.RefreshTokenDto;
import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespLoginDto;
import com.lms.entity.User;
import com.lms.mapper.LoginLogMapper;
import com.lms.mapper.StudentMapper;
import com.lms.security.JwtTokenProvider;
import com.lms.util.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 사용자 인증 및 권한 관리 서비스
 * @description 로그인 처리, 토큰 발급, 접속 이력 저장 등 인증과 관련된 핵심 비즈니스 로직을 수행합니다.
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    /* Refresh Token 만료 시간 */
    @Value("${jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;

    private final StudentMapper studentMapper;
    private final LoginLogMapper loginLogMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    /**
     * 통합 로그인 처리
     * @description 아이디/비밀번호 검증 후 Access/Refresh 토큰을 발급하고 로그인 로그를 기록합니다.
     * @param reqLoginDto 로그인 요청 정보 (userId, password)
     * @param request 접속 정보 추출을 위한 HttpServletRequest
     * @return 발급된 AccessToken 및 RefreshToken
     */
    @Transactional
    public RespLoginDto signIn(ReqLoginDto reqLoginDto, HttpServletRequest request) {

        /* DB 조회 */
        User user = studentMapper.selectStudentInfoByStudentNo(reqLoginDto.getUserId());

        if(user == null) {
            throw new RuntimeException("등록되지 않은 아이디입니다.");
        }

        /* 비밀번호 확인 */
        if(!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
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

        /* 로그인 이력 생성 */
        loginLogMapper.insertLoginLog(LoginLogDto.of(user.getUserId(), deviceType, osName, browserName, userAgent, clientIp));

        /* Refresh Token 저장 */
        refreshTokenService.saveRefreshToken(RefreshTokenDto.of(user.getUserId(), refreshToken, deviceType, refreshExpirationTime));

        return new RespLoginDto(accessToken, refreshToken);
    }

    /**
     * 클라이언트 실제 IP 추출
     * @description 프록시 서버 등을 거칠 경우를 대비하여 헤더를 순차적으로 확인합니다.
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
}
