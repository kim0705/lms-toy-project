package com.lms.service;

import com.lms.dto.auth.RefreshTokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RefreshTokenServiceTest {

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Test
    void saveRefreshToken() {

        String userId = "user1";
        String token = "refresh123";
        String deviceInfo = "PC";
        Long expire = 5000L;

        RefreshTokenDto dto = RefreshTokenDto.of(userId, token, deviceInfo, expire);

        refreshTokenService.saveRefreshToken(dto);

        String savedToken = refreshTokenService.findRefreshToken(userId, deviceInfo);
        assertEquals(token, savedToken);

    }

    @Test
    void findRefreshToken() {
    }

    @Test
    void deleteRefreshToken() {
    }
}