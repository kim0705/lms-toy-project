package com.lms.entity;

import lombok.*;

/**
 * [학과 엔티티]
 * 실제 DB 테이블 'departments'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Department extends CommonInfo{
    private int id;
    private String deptName;
}
