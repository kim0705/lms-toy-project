package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 페이징 공통 응답 DTO
 * 페이징 처리가 필요한 모든 목록 조회에서 공통으로 사용됩니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "페이징 공통 응답 객체")
public class RespPageDto<T> {
    @Schema(description = "조회 데이터 목록")
    private List<T> content;

    @Schema(description = "전체 데이터 수")
    private Long totalCount;

    @Schema(description = "현재 페이지 번호")
    private int page;

    @Schema(description = "페이지 당 데이터 수")
    private int size;
}
