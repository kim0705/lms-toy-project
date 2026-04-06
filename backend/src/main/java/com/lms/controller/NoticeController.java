package com.lms.controller;

import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespNoticeDto;
import com.lms.entity.PrincipalUser;
import com.lms.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공지사항 조회 성공"),
        @ApiResponse(responseCode = "404", description = "공지사항을 찾을 수 없음")
})
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * [주차별 공지 정보 조회]
     * GET http://localhost:8080/api/notice/{courseId}?week={week}
     */
    @Operation(summary = "주차별 공지 조회", description = "해당 주차의 공지사항 목록을 확인합니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespNoticeDto>>> getNoticeInfoByWeek(@PathVariable int courseId, @RequestParam(required = true) int week, @AuthenticationPrincipal PrincipalUser principalUser) {

        List<RespNoticeDto> noticeList = noticeService.findNoticeInfoByWeek(courseId, principalUser.getUser().getUserId(), week);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "공지 정보 조회 성공", noticeList));

    }
}
