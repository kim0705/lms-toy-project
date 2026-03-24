package com.lms.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Redis 저장용 리프레시 토큰 DTO
 * @description 사용자별, 기기별(WEB/MOBILE) 리프레시 토큰 정보를 Redis에 저장하기 위한 객체입니다.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {
    private String userId;
    private String refreshToken;
    private String deviceType;
    private Long refreshExpirationTime;

    public static RefreshTokenDto of(String userId, String refreshToken, String deviceType, Long refreshExpirationTime) {
        return RefreshTokenDto.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .deviceType(deviceType)
                .refreshExpirationTime(refreshExpirationTime)
                .build();
    }
}
