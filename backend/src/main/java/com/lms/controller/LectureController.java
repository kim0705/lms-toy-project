package com.lms.controller;

import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespLectureDto;
import com.lms.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "강의 정보 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패"),
        @ApiResponse(responseCode = "500", description = "서버 에러")
})
@RequiredArgsConstructor
@RequestMapping("/api/lecture")
public class LectureController {

    private final LectureService lectureService;

    /**
     * [주차별 강의 정보 조회(전체/주차별)]
     * GET http://localhost:8080/api/lecture/{courseId}?week={week}
     */
    @Operation(summary = "강의 정보 조회", description = "과목 ID와 주차를 이용해 강의 리스트를 가져옵니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespLectureDto>>> getLectureInfo(@PathVariable int courseId, @RequestParam(value = "week", required = false) Integer week) {

        List<RespLectureDto> lectureList = lectureService.findLectureInfoByWeek(courseId, week);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "강의 정보 조회가 완료되었습니다.", lectureList));

    }
}
