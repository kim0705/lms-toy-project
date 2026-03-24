package com.lms.service;

import com.lms.dto.auth.RefreshTokenDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 리프레시 토큰 생성/조회/삭제 통합 테스트 클래스입니다.
 */

@SpringBootTest
class RefreshTokenServiceTest {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Test
    @DisplayName("리프레시 토큰 저장 및 조회 테스트")
    void saveRefreshToken() {

        String userId = "user1";
        String token = "refresh123";
        String deviceInfo = "PC";
        Long expire = 5000L;

        RefreshTokenDto dto = RefreshTokenDto.of(userId, token, deviceInfo, expire);

        refreshTokenService.saveRefreshToken(dto);

        String savedToken = refreshTokenService.findRefreshToken(userId, deviceInfo);

        assertEquals(token, savedToken, "저장된 토큰이 조회한 토큰과 일치해야 합니다.");

    }

    @Test
    @DisplayName("존재하지 않는 토큰 조회 시 null 반환 테스트")
    void findRefreshToken() {

        String result = refreshTokenService.findRefreshToken("non_exist_user", "MOBILE");

        assertNull(result, "존재하지 않는 정보로 조회 시 null을 반환해야 합니다.");

    }

    @Test
    @DisplayName("리프레시 토큰 삭제 테스트")
    void deleteRefreshToken() {

        String userId = "user2";
        String deviceInfo = "MOBILE";
        RefreshTokenDto dto = RefreshTokenDto.of(userId, "token_to_delete", deviceInfo, 60000L);
        refreshTokenService.saveRefreshToken(dto);

        refreshTokenService.deleteRefreshToken(userId, deviceInfo);
        String result = refreshTokenService.findRefreshToken(userId, deviceInfo);

        assertNull(result, "삭제 후 조회 시 결과는 null이어야 합니다.");

    }
}