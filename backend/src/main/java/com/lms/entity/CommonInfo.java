package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * [공통 정보 클래스]
 * 모든 데이터(Entity)가 공통으로 가져야 할 관리 필드.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonInfo {
    private String insIp;
    private String insId;
    private LocalDateTime insDt;
    private String uptIp;
    private String uptId;
    private LocalDateTime uptDt;
}
