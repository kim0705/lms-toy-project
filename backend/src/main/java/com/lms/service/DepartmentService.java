package com.lms.service;

import com.lms.dto.response.RespDepartmentDto;
import com.lms.entity.Department;
import com.lms.exception.CustomException;
import com.lms.exception.ErrorCode;
import com.lms.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        if (department == null) {
            log.warn("===== [Service] 학과 없음 - deptId: {} =====", deptId);
            throw new CustomException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }

        log.info("===== [Service] 학과 조회 완료 - {} =====", department.getDeptName());
        return RespDepartmentDto.fromEntity(department);
    }
}
