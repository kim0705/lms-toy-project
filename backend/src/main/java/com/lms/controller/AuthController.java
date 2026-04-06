package com.lms.controller;

import com.lms.dto.request.ReqLoginDto;
import com.lms.dto.request.ReqRefreshTokenDto;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "인증 API", description = "로그인 및 토큰 발급을 담당하는 컨트롤러입니다.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                content = @Content(examples = @ExampleObject(value = "{\"status\": 200, \"message\": \"성공!\"}"))),
        @ApiResponse(responseCode = "400", description = "잘못된 입력",
                content = @Content(examples = @ExampleObject(value = "{\"status\": 400, \"message\": \"비밀번호는 8자 이상이어야 합니다.\"}"))),
        @ApiResponse(responseCode = "401", description = "인증 실패",
                content = @Content(examples = @ExampleObject(value = "{\"status\": 401, \"message\": \"아이디 또는 비밀번호가 일치하지 않습니다.\"}"))),
})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * [로그인 처리]
     * @description 사용자 학번(userId)과 비밀번호를 검증하여 Access/Refresh 토큰을 발급합니다.
     * 접속 기기(User-Agent)를 분석하여 중복 로그인을 관리하고 이력을 기록합니다.
     * * [Endpoint] POST /api/auth/signin
     * @param reqLoginDto 로그인 요청 정보 (학번, 비밀번호)
     * @param request 접속 환경(IP, User-Agent) 추출을 위한 객체
     * @return 성공 시 200 코드, 메세지와 함께 AccessToken, RefreshToken 반환
     */
    @Operation(summary = "로그인 시도", description = "학번(userId)과 비밀번호를 받아 JWT 토큰을 반환합니다.")
    @PostMapping("/signin")
    public ResponseEntity<RespCommonInfo<RespLoginDto>> signIn(@Valid @RequestBody ReqLoginDto reqLoginDto, HttpServletRequest request) {

        RespLoginDto tokens = authService.signIn(reqLoginDto, request);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "성공", tokens));

    }

    /**
     * [토큰 재발급]
     * @description Access Token 만료 시, Refresh Token을 사용하여 새로운 Access Token을 발급합니다.
     * * [Endpoint] POST /api/auth/refresh
     * @param refreshTokenDto Refresh Token 정보
     * @param request 접속 환경(IP, User-Agent) 추출을 위한 객체
     * @return 성공 시 200 코드, 메세지와 함께 갱신된 AccessToken 및 기존/갱신된 RefreshToken
     */
    @Operation(summary = "토큰 재발급", description = "Refresh Token을 포함한 요청 정보를 받아 Access Token을 재발급 하여 JWT 토큰을 반환합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<RespCommonInfo<RespLoginDto>> refresh(@RequestBody ReqRefreshTokenDto refreshTokenDto, HttpServletRequest request) {

        RespLoginDto tokens = authService.refresh(refreshTokenDto.getRefreshToken(), request);

        return ResponseEntity.ok(new RespCommonInfo<>(200, "성공", tokens));

    }
}
