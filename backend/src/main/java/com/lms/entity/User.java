package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [유저 엔티티]
 * 실제 DB 테이블 'users'와 연결
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User extends CommonInfo{
    private int id;
    private String userId;
    private String password;
    private String name;
    private String role;
    private int deptId;
}
