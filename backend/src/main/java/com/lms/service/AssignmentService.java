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

    public List<RespAssignmentDto> findAssignmentInfoByWeek(int courseId, int id, int week) {

        return assignmentMapper.selectAssignmentInfoByWeek(courseId, id, week);

    }
}
