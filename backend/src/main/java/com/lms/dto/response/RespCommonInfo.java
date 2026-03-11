package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 공통 응답 형식 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공통 응답 객체")
public class RespCommonInfo<T> {
    @Schema(description = "상태 코드")
    private int status;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "응답 데이터 (토큰 등)")
    private T data;
}
