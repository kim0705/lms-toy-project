package com.lms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 페이징/검색 공통 요청 DTO
 * 페이징 및 검색 조건이 필요한 모든 목록 조회에서 공통으로 사용됩니다.
 */
@Data
@Schema(description = "페이징/검색 공통 요청 객체")
public class ReqPageDto {
    @Schema(description = "페이지 번호 (0부터 시작)", defaultValue = "0")
    private int page = 0;

    @Schema(description = "페이지 당 데이터 수", defaultValue = "10")
    private int size = 10;

    @Schema(description = "검색 키워드")
    private String keyword;

    @Schema(description = "검색 유형 (title / content / writer / all)")
    private String searchType;

    public int getOffset() {
        return page * size;
    }
}
