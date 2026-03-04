package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * [과제 엔티티]
 * 실제 DB 테이블 'assignments'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Assignment extends CommonInfo{
    private int id;
    private String title;
    private String content;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
