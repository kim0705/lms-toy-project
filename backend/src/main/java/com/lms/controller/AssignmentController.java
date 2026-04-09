package com.lms.controller;

import com.lms.dto.response.RespAssignmentDto;
import com.lms.dto.response.RespCommonInfo;
import com.lms.entity.PrincipalUser;
import com.lms.service.AssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "과제 API", description = "과제 조회를 담당하는 컨트롤러입니다.")
@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "과제 조회 성공"),
        @ApiResponse(responseCode = "401", description = "인증 실패 (토큰 오류)"),
        @ApiResponse(responseCode = "404", description = "과제 정보를 찾을 수 없음")
})
@RequiredArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {

    private final AssignmentService assignmentService;

    /**
     * [주차별 과제 정보 조회]
     * 과목 ID와 주차를 기반으로 해당 사용자의 과제 목록을 조회합니다.
     * [Endpoint] GET /api/assignment/{courseId}?week={week}
     * @param courseId 조회할 과목 ID
     * @param week 조회할 주차
     * @param principalUser 인증된 사용자 정보 (학번 추출)
     * @return 성공 시 200 코드, 메세지와 함께 과제 목록 반환
     */
    @Operation(summary = "주차별 과제 조회", description = "과목 ID와 주차를 이용해 과제 목록을 가져옵니다.")
    @GetMapping("/{courseId}")
    public ResponseEntity<RespCommonInfo<List<RespAssignmentDto>>> getAssignmentInfoByWeek(@PathVariable int courseId, @RequestParam int week, @AuthenticationPrincipal PrincipalUser principalUser) {

        List<RespAssignmentDto> assignmentList = assignmentService.findAssignmentInfoByWeek(courseId, principalUser.getUser().getUserId(), week);

        return ResponseEntity.ok(new RespCommonInfo<>(200,"과제 정보 조회 성공", assignmentList));

    }
}
