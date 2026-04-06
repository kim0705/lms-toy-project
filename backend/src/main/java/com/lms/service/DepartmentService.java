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

    public RespDepartmentDto findDepartmentById(int deptId) {

        Department department = departmentMapper.selectDepartmentByDeptId(deptId);

        return RespDepartmentDto.fromEntity(department);
    }
}
