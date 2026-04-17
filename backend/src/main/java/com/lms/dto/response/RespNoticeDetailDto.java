package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/** 공지사항 상세 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공지사항 상세 응답 객체")
public class RespNoticeDetailDto {
    @Schema(description = "공지사항 ID")
    private int noticeId;

    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "공지사항 내용")
    private String content;

    @Schema(description = "작성자 ID")
    private String writer;

    @Schema(description = "작성일시")
    private LocalDateTime createdAt;
}
