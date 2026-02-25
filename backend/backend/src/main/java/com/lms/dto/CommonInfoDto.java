package com.lms.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommonInfoDto {
    private String insIp;
    private String insId;
    private LocalDateTime insDt;
    private String uptIp;
    private String uptId;
    private LocalDateTime uptDt;
}
