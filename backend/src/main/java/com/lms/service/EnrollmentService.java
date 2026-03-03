package com.lms.service;

import com.lms.dto.response.RespEnrollmentDto;
import com.lms.entity.Course;
import com.lms.mapper.EnrollmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    public List<RespEnrollmentDto> findEnrollmentById(int studentId) {

        List<Course> couseList = enrollmentMapper.selectEnrollmentById(studentId);

        return RespEnrollmentDto.fromEntity(couseList);
    }
}
