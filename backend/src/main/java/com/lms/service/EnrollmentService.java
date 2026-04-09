package com.lms.service;

import com.lms.dto.response.RespEnrollmentDto;
import com.lms.entity.Course;
import com.lms.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;

    /**
     * 사용자 ID를 기반으로 수강 중인 강의 목록을 조회합니다.
     * @param userId 조회할 사용자 ID (학번)
     * @return 수강 중인 강의 목록
     */
    public List<RespEnrollmentDto> findEnrollmentById(String userId) {
        log.info("[Enrollment] 수강 목록 조회 - userId: {}", userId);
        List<Course> courseList = enrollmentMapper.selectEnrollmentById(userId);
        log.info("[Enrollment] 조회 결과: {}건", courseList.size());
        return RespEnrollmentDto.fromEntity(courseList);
    }
}