package com.lms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/** 과제 응답 DTO */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "과제 응답 객체")
public class RespAssignmentDto {
    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "과제 ID")
    private int assignmentId;

    @Schema(description = "학습 주차")
    private int week;

    @Schema(description = "과제 제목")
    private String title;

    @Schema(description = "과제 시작일시")
    private LocalDateTime startDt;

    @Schema(description = "과제 종료일시")
    private LocalDateTime endDt;

    @Schema(description = "기간 내 여부 (Y/N)")
    private String isPeriod;

    @Schema(description = "제출 상태")
    private String subStatus;
}
