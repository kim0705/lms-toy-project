package com.lms.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 로그 저장 DTO
 * @description 로그인 인증 완료 후 로그인 로그 테이블에 저장할 데이터를 담는 객체입니다.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenDto {
    private String userId;
    private String refreshToken;
    private String deviceInfo;
    private Long refreshExpirationTime;

    public static RefreshTokenDto of(String userId, String refreshToken, String deviceInfo, Long refreshExpirationTime) {
        return RefreshTokenDto.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .deviceInfo(deviceInfo)
                .refreshExpirationTime(refreshExpirationTime)
                .build();
    }
}
