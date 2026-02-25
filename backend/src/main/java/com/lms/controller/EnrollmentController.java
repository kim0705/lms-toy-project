package com.lms.controller;

import com.lms.dto.response.RespEnrollmentDto;
import com.lms.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    /**
     * [수강 목록 정보 조회]
     * GET http://localhost:8080/api/enrollments/{studentId}
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<List<RespEnrollmentDto>> getEnrollmentInfo(@PathVariable int studentId) {

        return ResponseEntity.ok(enrollmentService.findEnrollmentById(studentId));

    }
}
