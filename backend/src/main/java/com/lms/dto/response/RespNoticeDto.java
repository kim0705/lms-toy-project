package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/** 공지사항 목록 응답 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "공지사항 목록 응답 객체")
public class RespNoticeDto {
    @Schema(description = "공지사항 ID")
    private int noticeId;

    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "작성자 ID")
    private String writer;

    @Schema(description = "작성일시")
    private LocalDateTime createdAt;

    @Schema(description = "조회수")
    private int viewCount;
}
