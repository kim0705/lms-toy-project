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

    public List<RespEnrollmentDto> findEnrollmentById(String userId) {

        List<Course> couseList = enrollmentMapper.selectEnrollmentById(userId);

        return RespEnrollmentDto.fromEntity(couseList);
    }
}