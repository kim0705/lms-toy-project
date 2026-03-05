package com.lms.controller;

import com.lms.dto.response.RespNoticeDto;
import com.lms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * [주차별 공지 정보 조회]
     * GET http://localhost:8080/api/notice/{courseId}?week={week}
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<List<RespNoticeDto>> getNoticeInfoByWeek(@PathVariable int courseId, @RequestParam(required = true) int week) {

        int studentId = 1; // 임시 하드 코딩

        return ResponseEntity.ok(noticeService.findNoticeInfoByWeek(courseId, studentId, week));

    }
}
