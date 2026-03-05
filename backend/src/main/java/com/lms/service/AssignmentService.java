package com.lms.service;

import com.lms.dto.response.RespAssignmentDto;
import com.lms.mapper.AssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;

    public List<RespAssignmentDto> findAssignmentInfoByWeek(int courseId, int studentId, int week) {

        return assignmentMapper.selectAssignmentInfoByWeek(courseId, studentId, week);

    }
}
