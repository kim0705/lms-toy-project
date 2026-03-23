package com.lms.mapper;

import com.lms.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    /* 학생ID를 이용한 학과 조회 */
    Department selectDepartmentById(int studentId);
}
