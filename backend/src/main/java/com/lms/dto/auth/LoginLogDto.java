package com.lms.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DB 저장용 로그인 로그 DTO
 * @description 로그인 인증 완료 후 로그인 로그 테이블에 저장할 데이터를 담는 객체입니다.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginLogDto {
    private String userId;
    private String deviceType;
    private String osName;
    private String browserName;
    private String userAgent;
    private String clientIp;

    public static LoginLogDto of(String userId, String deviceType, String osName, String browserName, String userAgent, String clientIp) {
        return LoginLogDto.builder()
                .userId(userId)
                .deviceType(deviceType)
                .osName(osName)
                .browserName(browserName)
                .userAgent(userAgent)
                .clientIp(clientIp)
                .build();
    }
}
