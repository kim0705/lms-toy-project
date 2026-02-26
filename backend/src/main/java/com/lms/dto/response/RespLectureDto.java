package com.lms.dto.response;

import com.lms.entity.CommonInfo;
import com.lms.entity.Lecture;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RespLectureDto extends CommonInfo {
    private int id;
    private int courseId;
    private String title;
    private int week;
    private int chapter;
    private String videoUrl;
    private int reqTime;
    private Date startDt;
    private Date endDt;

    /* Entity -> DTO 변환 메서드 */
    public static List<RespLectureDto> fromEntity(List<Lecture> lectures) {

        return lectures.stream()
                .map(lecture -> RespLectureDto.builder()
                        .id(lecture.getId())
                        .title(lecture.getTitle())
                        .week(lecture.getWeek())
                        .chapter(lecture.getChapter())
                        .videoUrl(lecture.getVideoUrl())
                        .startDt(lecture.getStartDt())
                        .endDt(lecture.getEndDt())
                        .build())
                .collect(Collectors.toList());
    }
}
