package com.lms.controller;

import com.lms.annotation.CurrentUser;
import com.lms.dto.request.ReqPageDto;
import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespLectureNoticeDto;
import com.lms.dto.response.RespNoticeDetailDto;
import com.lms.dto.response.RespNoticeDto;
import com.lms.dto.response.RespPageDto;
import com.lms.entity.User;
import com.lms.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "공지 API", description = "공지사항 조회를 담당하는 컨트롤러입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "공지사항 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 오류)")
})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * [주차별 공지 정보 조회]
     * 과목 ID와 주차를 기반으로 해당 사용자의 공지사항 목록을 조회합니다.
     * [Endpoint] GET /api/notices/{courseId}?week={week}
     * @param courseId 조회할 과목 ID
     * @param week 조회할 주차
     * @param user 인증된 사용자 정보 (학번 추출)
     * @return 성공 시 200 코드, 메세지와 함께 공지 목록 반환
     */
    @Operation(summary = "주차별 공지 조회", description = "해당 주차의 공지사항 목록을 확인합니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespLectureNoticeDto>>> getNoticeInfoByWeek(@PathVariable int courseId, @RequestParam int week, @CurrentUser User user) {
        log.debug("===== [Controller] 주차별 공지 조회 - courseId: {}, week: {} =====", courseId, week);

        List<RespLectureNoticeDto> noticeList = noticeService.findNoticeInfoByWeek(courseId, user.getUserId(), week);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "공지 정보 조회 성공", noticeList));
    }

    /**
     * [과목별 공지 목록 조회 (페이징/검색)]
     * 과목 ID를 기반으로 공지사항 목록을 페이징 및 검색하여 조회합니다.
     * [Endpoint] GET /api/notices/{courseId}/list
     * @param courseId 조회할 과목 ID
     * @param req 페이징 및 검색 조건 (page, size, keyword, searchType)
     * @return 성공 시 200 코드, 메세지와 함께 페이징된 공지 목록 반환
     */
    @Operation(summary = "과목별 공지 목록 조회", description = "페이징 및 검색 조건으로 공지사항 목록을 조회합니다.")
    @GetMapping("/{courseId}/list")
    public ResponseEntity<RespCommonInfo<RespPageDto<RespNoticeDto>>> getNoticeByCourse(@PathVariable int courseId, @ModelAttribute ReqPageDto req) {
        log.debug("===== [Controller] 과목별 공지 조회 - courseId: {}, page: {}, size: {}, keyword: {} =====", courseId, req.getPage(), req.getSize(), req.getKeyword());

        RespPageDto<RespNoticeDto> result = noticeService.findNoticeByCourse(courseId, req);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "공지 목록 조회 성공", result));
    }

    /**
     * [공지사항 상세 조회]
     * 공지사항 ID를 기반으로 상세 내용을 조회합니다.
     * [Endpoint] GET /api/notices/{courseId}/{noticeId}
     * @param courseId 과목 ID
     * @param noticeId 공지사항 ID
     * @return 성공 시 200 코드, 메세지와 함께 공지사항 상세 정보 반환
     */
    @Operation(summary = "공지사항 상세 조회", description = "공지사항 ID로 상세 내용을 조회합니다.")
    @GetMapping("/{courseId}/{noticeId}")
    public ResponseEntity<RespCommonInfo<RespNoticeDetailDto>> getNoticeDetail(@PathVariable int courseId, @PathVariable int noticeId) {
        log.debug("===== [Controller] 공지사항 상세 조회 - courseId: {}, noticeId: {} =====", courseId, noticeId);

        RespNoticeDetailDto result = noticeService.findNoticeDetail(courseId, noticeId);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "공지사항 상세 조회 성공", result));
    }
}
