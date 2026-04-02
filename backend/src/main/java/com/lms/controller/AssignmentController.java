package com.lms.controller;

import com.lms.dto.response.RespAssignmentDto;
import com.lms.dto.response.RespCommonInfo;
import com.lms.entity.PrincipalUser;
import com.lms.service.AssignmentService;
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
        @ApiResponse(responseCode = "200", description = "과제 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패"),
        @ApiResponse(responseCode = "403", description = "권한 없음")
})
@RequiredArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    /**
     * [주차별 과제 정보 조회]
     * GET http://localhost:8080/api/assignment/{courseId}?week={week}
     */

    @Operation(summary = "주차별 과제 조회", description = "과목 ID와 주차를 이용해 과제 목록을 가져옵니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespAssignmentDto>>> getAssignmentInfoByWeek(@PathVariable int courseId, @RequestParam(required = true) int week, @AuthenticationPrincipal PrincipalUser principalUser) {

        List<RespAssignmentDto> assignmentList = assignmentService.findAssignmentInfoByWeek(courseId, principalUser.getUser().getId(), week);

        return ResponseEntity.ok(new RespCommonInfo<>(200,"과제 정보 조회 성공", assignmentList));

    }
}
