package com.lms.dto.response;

import com.lms.dto.CommonInfoDto;
import com.lms.entity.Course;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RespEnrollmentDto extends CommonInfoDto {
    private int courseId;
    private String courseTitle;

    /* Entity -> DTO 변환 메서드 */
    public static List<RespEnrollmentDto> fromEntity(List<Course> courses) {

        return courses.stream()
                .map(course -> RespEnrollmentDto.builder()
                        .courseId(course.getId())
                        .courseTitle(course.getTitle())
                        .build())
                .collect(Collectors.toList());

    }
}
