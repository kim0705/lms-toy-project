package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [강의정보 엔티티]
 * 실제 DB 테이블 'courses'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Course extends CommonInfo{
    private int id;
    private String title;
}
