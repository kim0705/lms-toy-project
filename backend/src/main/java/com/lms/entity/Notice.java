package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [공지 엔티티]
 * 실제 DB 테이블 'notices'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends CommonInfo {
    private int id;
    private String title;
    private String content;
}
