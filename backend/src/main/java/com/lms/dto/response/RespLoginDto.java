package com.lms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 로그인 성공 응답 DTO
 * @description 로그인 인증 완료 후 클라이언트에게 발급되는 Access Token과 Refresh Token을 담는 객체입니다.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespLoginDto {
    private String accessToken;
    private String refreshToken;
}
