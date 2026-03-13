package com.lms.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/* *
 * JWT 토큰 생성 및 검증 통합 테스트 클래스입니다.
 */
@SpringBootTest
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("JWT 토큰 생성 및 검증 통합 테스트")
    void jwtTest() {
        /* 1. 토큰 생성 */
        String userId = "testUser";
        String role = "ROLE_STUDENT";
        String token = jwtTokenProvider.createToken(userId, role);
        System.out.println("발급된 토큰: " + token);

        /* 2. 유효성 검사 */
        boolean isValid = jwtTokenProvider.validateToken(token);
        assertTrue(isValid, "유효하지 않은 토큰입니다.");

        /* 3. 정보 추출 테스트 */
        String extractedId = jwtTokenProvider.getUserId(token);
        assertEquals(userId, extractedId, "추출된 ID가 일치하지 않습니다.");
    }
}
