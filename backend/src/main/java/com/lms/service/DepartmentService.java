package com.lms.service;

import com.lms.dto.response.RespDepartmentDto;
import com.lms.entity.Department;
import com.lms.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public RespDepartmentDto findDepartmentById(int studentId) {

        Department department = departmentMapper.selectDepartmentById(studentId);

        return RespDepartmentDto.fromEntity(department);
    }
}
