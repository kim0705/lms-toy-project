package com.lms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [학과 엔티티]
 * 실제 DB 테이블 'departments'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Department extends CommonInfo{
    private int id;
    private String deptName;
}
