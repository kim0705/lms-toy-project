package com.lms.service;

import com.lms.dto.response.RespDepartmentDto;
import com.lms.entity.Department;
import com.lms.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentMapper departmentMapper;

    /**
     * 학과 ID를 기반으로 학과 정보를 조회합니다.
     * @param deptId 조회할 학과 ID
     * @return 학과 정보
     */
    public RespDepartmentDto findDepartmentById(int deptId) {

        Department department = departmentMapper.selectDepartmentByDeptId(deptId);

        return RespDepartmentDto.fromEntity(department);
    }
}
