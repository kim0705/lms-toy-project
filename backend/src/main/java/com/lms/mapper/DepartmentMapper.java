package com.lms.mapper;

import com.lms.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    Department selectDepartmentById(int studentId);
}
