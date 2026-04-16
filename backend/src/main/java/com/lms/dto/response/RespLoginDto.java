package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 성공 응답 DTO
 * 로그인 인증 완료 후 클라이언트에게 발급되는 Access Token과 Refresh Token을 담는 객체입니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "로그인 성공 응답 객체")
public class RespLoginDto {
    @Schema(description = "Access Token")
    private String accessToken;

    @Schema(description = "Refresh Token")
    private String refreshToken;
}
