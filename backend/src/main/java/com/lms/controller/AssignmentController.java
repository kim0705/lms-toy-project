package com.lms.controller;

import com.lms.dto.response.RespAssignmentDto;
import com.lms.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    /**
     * [주차별 과제 정보 조회]
     * GET http://localhost:8080/api/assignment/{courseId}?week={week}
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<List<RespAssignmentDto>> getAssignmentInfoByWeek(@PathVariable int courseId, @RequestParam(required = false) Integer week) {

        String studentId = "user01"; // 임시 하드코딩
        System.out.println("courseId 확인: " + courseId );
        System.out.println("week 확인: " + week );

        return ResponseEntity.ok(assignmentService.findAssignmentInfoByWeek(courseId, studentId, week));

    }
}
