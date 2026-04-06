package com.lms.controller;

import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespDepartmentDto;
import com.lms.entity.PrincipalUser;
import com.lms.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "학과 정보 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 오류)"),
        @ApiResponse(responseCode = "404", description = "학생 정보를 찾을 수 없음")
})
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * [학과 조회]
     * GET http://localhost:8080/api/departments
     */

    @Operation(summary = "학생 학과 조회", description = "로그인된 사용자의 토큰을 통해 학과 정보를 조회합니다.")
    @GetMapping()
    public ResponseEntity<RespCommonInfo<RespDepartmentDto>> getDepartmentInfo(@AuthenticationPrincipal PrincipalUser principalUser) {

        RespDepartmentDto response = departmentService.findDepartmentById(principalUser.getUser().getDeptId());

        return ResponseEntity.ok(new RespCommonInfo<>(200, "학과 정보 조회 성공", response));
    }
}
