package com.lms.controller;

import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespEnrollmentDto;
import com.lms.entity.PrincipalUser;
import com.lms.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "수강 목록 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "404", description = "수강 내역을 찾을 수 없음")
})
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * [수강 목록 정보 조회]
     * GET http://localhost:8080/api/enrollments
     */

    @Operation(summary = "수강 목록 조회", description = "학생 ID를 통해 수강 중인 강의 목록을 가져옵니다.")
    @GetMapping()
    public ResponseEntity<RespCommonInfo<List<RespEnrollmentDto>>> getEnrollmentInfo(@AuthenticationPrincipal PrincipalUser principalUser) {

        List<RespEnrollmentDto> enrollmentList = enrollmentService.findEnrollmentById(principalUser.getUser().getId());

        return ResponseEntity.ok(new RespCommonInfo<>(200, "수강 목록 조회 성공", enrollmentList));

    }
}
