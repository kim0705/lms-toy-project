package com.lms.controller;

import com.lms.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lecture")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    /**
     * [강의 정보 조회]
     * GET http://localhost:8080/api/lecture/{courseId}?week={week}
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<?> getLectureInfo(@PathVariable int courseId, @RequestParam(value = "week", required = false) Integer week) {

        return ResponseEntity.ok(lectureService.findLectureInfoByWeek(courseId, week));

    }
}
