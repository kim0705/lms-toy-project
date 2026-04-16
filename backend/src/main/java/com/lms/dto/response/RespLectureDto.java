package com.lms.dto.response;

import com.lms.entity.Lecture;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/** 강의 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "강의 응답 객체")
public class RespLectureDto {
    @Schema(description = "강의 ID")
    private int id;

    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "강의 제목")
    private String title;

    @Schema(description = "학습 주차")
    private int week;

    @Schema(description = "차시")
    private int chapter;

    @Schema(description = "영상 URL")
    private String videoUrl;

    @Schema(description = "수강 필요 시간 (분)")
    private int reqTime;

    @Schema(description = "강의 시작일")
    private Date startDt;

    @Schema(description = "강의 종료일")
    private Date endDt;

    /** Entity -> DTO 변환 메서드 */
    public static List<RespLectureDto> fromEntity(List<Lecture> lectures) {
        return lectures.stream()
                .map(lecture -> RespLectureDto.builder()
                        .id(lecture.getId())
                        .title(lecture.getTitle())
                        .week(lecture.getWeek())
                        .chapter(lecture.getChapter())
                        .videoUrl(lecture.getVideoUrl())
                        .reqTime(lecture.getReqTime())
                        .startDt(lecture.getStartDt())
                        .endDt(lecture.getEndDt())
                        .build())
                .collect(Collectors.toList());
    }
}
