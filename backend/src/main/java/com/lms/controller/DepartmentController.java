package com.lms.controller;

import com.lms.annotation.CurrentUser;
import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespDepartmentDto;
import com.lms.entity.User;
import com.lms.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "학과 API", description = "학과 정보 조회를 담당하는 컨트롤러입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "학과 정보 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 오류)"),
        @ApiResponse(responseCode = "404", description = "학생 정보를 찾을 수 없음")
})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * [학과 조회]
     * 로그인된 사용자의 학과 정보를 조회합니다.
     * [Endpoint] GET /api/departments
     * @param user 인증된 사용자 정보 (학과 ID 추출)
     * @return 성공 시 200 코드, 메세지와 함께 학과 정보 반환
     */
    @Operation(summary = "학생 학과 조회", description = "로그인된 사용자의 토큰을 통해 학과 정보를 조회합니다.")
    @GetMapping()
    public ResponseEntity<RespCommonInfo<RespDepartmentDto>> getDepartmentInfo(@CurrentUser User user) {
        log.debug("===== [Controller] 학과 정보 조회 요청 =====");

        RespDepartmentDto response = departmentService.findDepartmentById(user.getDeptId());

        return ResponseEntity.ok(new RespCommonInfo<>(200, "학과 정보 조회 성공", response));
    }
}
