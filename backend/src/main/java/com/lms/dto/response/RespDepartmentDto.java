package com.lms.dto.response;

import com.lms.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/** 학과 응답 DTO */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "학과 응답 객체")
public class RespDepartmentDto {
    @Schema(description = "학과 ID")
    private int id;

    @Schema(description = "학과명")
    private String deptName;

    /** Entity -> DTO 변환 메서드 */
    public static RespDepartmentDto fromEntity(Department department) {
        return RespDepartmentDto.builder()
                .id(department.getId())
                .deptName(department.getDeptName())
                .build();
    }
}
