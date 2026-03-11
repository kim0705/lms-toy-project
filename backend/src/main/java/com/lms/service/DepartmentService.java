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

    public RespDepartmentDto findDepartmentById(int studentId) {

        Department department = departmentMapper.selectDepartmentById(studentId);

        return RespDepartmentDto.fromEntity(department);
    }
}
