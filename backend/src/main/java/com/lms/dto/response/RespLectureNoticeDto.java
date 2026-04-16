package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 주차별 공지사항 응답 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "주차별 공지사항 응답 객체")
public class RespLectureNoticeDto {
    @Schema(description = "공지사항 ID")
    private int noticeId;

    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "공지사항 제목")
    private String title;

    @Schema(description = "읽음 여부 (Y/N)")
    private String readStatus;
}
