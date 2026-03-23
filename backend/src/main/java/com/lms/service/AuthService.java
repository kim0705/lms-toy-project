package com.lms.service;

import com.lms.dto.auth.RefreshTokenDto;
import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespLoginDto;
import com.lms.entity.User;
import com.lms.mapper.LoginLogMapper;
import com.lms.mapper.StudentMapper;
import com.lms.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        String ua = request.getHeader("User-Agent");
        String clientIp = getClientIp(request);
        String deviceInfo = extractDeviceInfo(ua);

        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId());

        loginLogMapper.insertLoginLog(user.getUserId(), deviceInfo, ua, clientIp);

        refreshTokenService.saveRefreshToken(RefreshTokenDto.of(user.getUserId(), refreshToken, deviceInfo, refreshExpirationTime));

        return new RespLoginDto(accessToken, refreshToken);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        return (ip == null || ip.isEmpty()) ? request.getRemoteAddr() : ip;
    }

    private String extractDeviceInfo(String ua) {
        if (ua == null) return "Unknown";
        if (ua.toLowerCase().contains("mobi")) return "Mobile";

        return "PC";
    }
}
