package com.lms.mapper;

import com.lms.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    /* 학과 고유 번호(PK)로 학과 상세 정보 조회 */
    Department selectDepartmentByDeptId(int deptId);
}
