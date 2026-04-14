package com.lms.controller;

import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespLectureDto;
import com.lms.service.LectureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "강의 API", description = "강의 정보 조회를 담당하는 컨트롤러입니다.")
@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "강의 정보 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 오류)")
})
@RequiredArgsConstructor
@RequestMapping("/api/lectures")
public class LectureController {

    private final LectureService lectureService;

    /**
     * [주차별 강의 정보 조회(전체/주차별)]
     * 과목 ID와 주차를 기반으로 강의 정보를 조회합니다.
     * 주차(week) 미입력 시 해당 과목의 전체 강의 목록을 반환합니다.
     * [Endpoint] GET /api/lecture/{courseId}?week={week}
     * @param courseId 조회할 과목 ID
     * @param week 조회할 주차 (선택, 미입력 시 전체 조회)
     * @return 성공 시 200 코드, 메세지와 함께 강의 목록 반환
     */
    @Operation(summary = "강의 정보 조회", description = "과목 ID와 주차를 이용해 강의 리스트를 가져옵니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespLectureDto>>> getLectureInfo(@PathVariable int courseId, @RequestParam(value = "week", required = false) Integer week) {

        List<RespLectureDto> lectureList = lectureService.findLectureInfoByWeek(courseId, week);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "강의 정보 조회가 완료되었습니다.", lectureList));

    }
}
