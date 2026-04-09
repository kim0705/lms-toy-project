package com.lms.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 인증/인가
    UNAUTHORIZED(401, "인증이 필요합니다."),
    INVALID_CREDENTIALS(401, "아이디 또는 비밀번호가 올바르지 않습니다."),
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    FORBIDDEN(403, "접근 권한이 없습니다."),

    // 리소스
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    ASSIGNMENT_NOT_FOUND(404, "과제를 찾을 수 없습니다."),
    LECTURE_NOT_FOUND(404, "강의를 찾을 수 없습니다."),
    NOTICE_NOT_FOUND(404, "공지사항을 찾을 수 없습니다."),
    DEPARTMENT_NOT_FOUND(404, "학과 정보를 찾을 수 없습니다."),

    // 입력값 검증
    INVALID_INPUT(400, "요청값이 올바르지 않습니다."),
    MISSING_PARAMETER(400, "필수 파라미터가 누락되었습니다."),

    // 서버
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    private final int status;
    private final String message;
}
