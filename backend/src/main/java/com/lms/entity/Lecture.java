package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * [강의 상세 정보 엔티티]
 * 실제 DB 테이블 'lectures'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends CommonInfo{
    private int id;
    private int courseId;
    private String title;
    private int week;
    private int chapter;
    private String videoUrl;
    private int reqTime;
    private Date startDt;
    private Date endDt;
}
