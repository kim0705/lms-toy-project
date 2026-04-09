package com.lms.service;

import com.lms.dto.response.RespAssignmentDto;
import com.lms.mapper.AssignmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentMapper assignmentMapper;

    /**
     * 과목 ID, 사용자 ID, 주차를 기반으로 과제 목록을 조회합니다.
     * @param courseId 조회할 과목 ID
     * @param userId 조회할 사용자 ID (학번)
     * @param week 조회할 주차
     * @return 과제 정보 목록
     */
    public List<RespAssignmentDto> findAssignmentInfoByWeek(int courseId, String userId, int week) {

        return assignmentMapper.selectAssignmentInfoByWeek(courseId, userId, week);

    }
}
