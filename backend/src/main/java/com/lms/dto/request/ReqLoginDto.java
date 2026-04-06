package com.lms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "로그인 요청 정보")
public class ReqLoginDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Schema(description = "사용자 아이디(학번)", example = "20260001")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
    @Schema(description = "비밀번호 (8자 이상, 영문/숫자/특수문자 포함)", example = "Qwer1234!")
    private String password;
}
