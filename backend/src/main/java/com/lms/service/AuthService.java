package com.lms.service;

import com.lms.dto.auth.LoginLogDto;
import com.lms.dto.auth.RefreshTokenDto;
import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespLoginDto;
import com.lms.entity.User;
import com.lms.exception.CustomException;
import com.lms.exception.ErrorCode;
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
        /* DB 조회 */
        User user = userMapper.selectUserInfoByUserId(reqLoginDto.getUserId());

        if(user == null) {
            log.warn("===== [Service] 로그인 실패 - 존재하지 않는 아이디: {} =====", reqLoginDto.getUserId());
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        /* 비밀번호 확인 */
        if(!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            log.warn("===== [Service] 로그인 실패 - 비밀번호 불일치: {} =====", reqLoginDto.getUserId());
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        /* 정보 추출 */
        String userAgent = request.getHeader("User-Agent");
        Map<String, String> userAgentDetails = UserAgentUtil.extractUserAgentInfo(userAgent);
        String clientIp = UserAgentUtil.extractClientIp(request);

        /* User-Agent에서 추출한 값 변수에 담기 */
        String deviceType = userAgentDetails.get("deviceType");
        String osName = userAgentDetails.get("osName");
        String browserName = userAgentDetails.get("browserName");

        /* 토큰 생성 */
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

        log.info("===== [Service] 로그인 완료 - User: {}, Device: {}, IP: {} =====", user.getUserId(), deviceType, clientIp);

        /* 로그인 이력 생성 */
        loginLogMapper.insertLoginLog(LoginLogDto.of(user.getUserId(), deviceType, osName, browserName, userAgent, clientIp));

        /* Refresh Token 저장 */
        refreshTokenService.saveRefreshToken(RefreshTokenDto.of(user.getUserId(), refreshToken, deviceType, refreshExpirationTime));

        return new RespLoginDto(accessToken, refreshToken);
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
        /* 1. JWT 자체 유효성 및 만료 여부 확인 */
        if(!jwtTokenProvider.validateToken(refreshToken)) {
            log.warn("===== [Service] 토큰 재발급 실패 - 유효하지 않은 리프레시 토큰 =====");
            throw new CustomException(ErrorCode.INVALID_TOKEN);
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
            log.warn("===== [Service] 토큰 재발급 실패 - 토큰 불일치: User: {}, Device: {} =====", userId, deviceType);
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        /* 6. 새로운 Access Token 발급 */
        User user = userMapper.selectUserInfoByUserId(userId);
        if(user == null) {
            log.error("===== [Service] 토큰 재발급 실패 - DB에 유저 없음: {} =====", userId);
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());

        log.info("===== [Service] 토큰 재발급 완료 - User: {} =====", userId);

        /* 7. 응답 반환 */
        return new RespLoginDto(newAccessToken, refreshToken);
    }
}
