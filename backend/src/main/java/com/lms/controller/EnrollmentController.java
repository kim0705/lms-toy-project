package com.lms.controller;

import com.lms.annotation.CurrentUser;
import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespEnrollmentDto;
import com.lms.entity.User;
import com.lms.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "수강 API", description = "수강 목록 조회를 담당하는 컨트롤러입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수강 목록 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자")
})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * [수강 목록 정보 조회]
     * 로그인된 사용자의 수강 중인 강의 목록을 조회합니다.
     * [Endpoint] GET /api/enrollments
     * @param user 인증된 사용자 정보 (학번 추출)
     * @return 성공 시 200 코드, 메세지와 함께 수강 목록 반환
     */
    @Operation(summary = "수강 목록 조회", description = "유저 ID를 통해 수강 중인 강의 목록을 가져옵니다.")
    @GetMapping()
    public ResponseEntity<RespCommonInfo<List<RespEnrollmentDto>>> getEnrollmentInfo(@CurrentUser User user) {
        log.debug("===== [Controller] 수강 목록 조회 요청 =====");

        List<RespEnrollmentDto> enrollmentList = enrollmentService.findEnrollmentById(user.getUserId());

        return ResponseEntity.ok(new RespCommonInfo<>(200, "수강 목록 조회 성공", enrollmentList));
    }
}
