package com.lms.service;

import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespLoginDto;
import com.lms.entity.PrincipalUser;
import com.lms.entity.User;
import com.lms.mapper.LoginLogMapper;
import com.lms.mapper.UserMapper;
import com.lms.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private RefreshTokenService refreshTokenService;
    @Mock
    private LoginLogMapper loginLogMapper;
    @Mock
    private HttpServletRequest request;

    @Test
    @DisplayName("로그인 성공 테스트")
    void signIn() {
        /* 1. Given: 상황 설정 */
        String userId = "20260001";
        String password = "Qwer1234!";
        User user = new User(1, userId, "encoded_pw", "김유진", "ROLE_STUDENT", 101);

        when(userMapper.selectUserInfoByUserId(userId)).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0...");
        when(jwtTokenProvider.createAccessToken(anyString(), anyString())).thenReturn("access_token");
        when(jwtTokenProvider.createRefreshToken(anyString())).thenReturn("refresh_token");

        /* 2. When: 서비스 호출 */
        ReqLoginDto loginDto = new ReqLoginDto(userId, password);
        RespLoginDto result = authService.signIn(loginDto, request);

        /* 3. Then: 검증 */
        assertNotNull(result);
        assertEquals("access_token", result.getAccessToken());
        verify(refreshTokenService, times(1)).saveRefreshToken(any());
        verify(loginLogMapper, times(1)).insertLoginLog(any());
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void signIn_fail_password() {
        /* 1. Given */
        String userId = "20260001";
        User user = new User(1, userId, "encoded_pw", "김유진", "ROLE_STUDENT", 101);

        when(userMapper.selectUserInfoByUserId(userId)).thenReturn(user);
        /* 핵심: 비번이 틀렸다고 가정 */
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        /* 2. When & 3. Then: 예외가 터지는지 확인 */
        assertThrows(RuntimeException.class, () -> {
            authService.signIn(new ReqLoginDto(userId, "wrong_pw"), request);
        });

        /* 비번이 틀렸으니 토큰 생성은 절대 호출되면 안 됨 */
        verify(jwtTokenProvider, never()).createAccessToken(anyString(), anyString());
    }

    @Test
    @DisplayName("토큰 재발급 성공 테스트")
    void refresh_success() {
        /* 1. Given */
        String refreshToken = "valid_rt";
        String userId = "20260001";
        User user = new User(1, userId, "encoded_pw", "김유진", "ROLE_STUDENT", 101);

        when(jwtTokenProvider.validateToken(refreshToken)).thenReturn(true);
        when(jwtTokenProvider.getUserId(refreshToken)).thenReturn(userId);
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0...");
        when(refreshTokenService.findRefreshToken(eq(userId), anyString())).thenReturn(refreshToken);
        when(userMapper.selectUserInfoByUserId(userId)).thenReturn(user);
        when(jwtTokenProvider.createAccessToken(userId, "ROLE_STUDENT")).thenReturn("new_at");

        /* 2. When */
        RespLoginDto result = authService.refresh(refreshToken, request);

        /* 3. Then */
        assertNotNull(result);
        assertEquals("new_at", result.getAccessToken());
        verify(jwtTokenProvider).createAccessToken(eq(userId), anyString());
    }
}