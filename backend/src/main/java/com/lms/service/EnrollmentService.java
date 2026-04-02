package com.lms.service;

import com.lms.dto.response.RespEnrollmentDto;
import com.lms.entity.Course;
import com.lms.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;

    public List<RespEnrollmentDto> findEnrollmentById(int id) {

        List<Course> couseList = enrollmentMapper.selectEnrollmentById(id);

        return RespEnrollmentDto.fromEntity(couseList);
    }
}