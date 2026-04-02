package com.lms.controller;

import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.response.RespCommonInfo;
import com.lms.dto.response.RespLoginDto;
import com.lms.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "인증 API", description = "로그인 및 토큰 발급을 담당하는 컨트롤러입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(examples = @ExampleObject(value = "{\"status\": 200, \"message\": \"성공!\"}"))),
        @ApiResponse(responseCode = "403", content = @Content(examples = @ExampleObject(value = "{\"status\": 403, \"message\": \"아이디/비밀번호 불일치\"}"))),
        @ApiResponse(responseCode = "404", content = @Content(examples = @ExampleObject(value = "{\"status\": 404, \"message\": \"존재하지 않는 사용자\"}")))
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * [로그인]
     * Post http://localhost:8080/api/auth/login
     */
    @Operation(summary = "로그인 시도", description = "학번(userId)과 비밀번호를 받아 JWT 토큰을 반환합니다.")
    @PostMapping("/login")
    public ResponseEntity<RespCommonInfo<RespLoginDto>> signIn(@RequestBody ReqLoginDto reqLoginDto, HttpServletRequest request) {

        RespLoginDto tokens = authService.signIn(reqLoginDto, request);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "성공", tokens));

    }
}
