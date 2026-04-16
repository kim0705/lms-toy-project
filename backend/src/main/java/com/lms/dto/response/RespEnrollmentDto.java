package com.lms.dto.response;

import com.lms.entity.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/** 수강 과목 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "수강 과목 응답 객체")
public class RespEnrollmentDto {
    @Schema(description = "과목 ID")
    private int courseId;

    @Schema(description = "과목명")
    private String courseTitle;

    /** Entity -> DTO 변환 메서드 */
    public static List<RespEnrollmentDto> fromEntity(List<Course> courses) {
        return courses.stream()
                .map(course -> RespEnrollmentDto.builder()
                        .courseId(course.getId())
                        .courseTitle(course.getTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
