package com.lms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Access Token 재발급을 위한 Refresh Token ReqDTO
 * @description 만료된 Access Token을 대신하여 본인 인증을 수행할 Refresh Token을 전달합니다.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqRefreshTokenDto {
    @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;
}
