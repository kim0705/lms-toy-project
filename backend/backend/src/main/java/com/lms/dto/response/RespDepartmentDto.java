package com.lms.dto.response;

import com.lms.dto.CommonInfoDto;
import com.lms.entity.Department;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RespDepartmentDto extends CommonInfoDto {
    private int id;
    private String deptName;

    /* Entity -> DTO 변환 메서드 */
    public static RespDepartmentDto fromEntity(Department department) {
        return RespDepartmentDto.builder()
                .id(department.getId())
                .deptName(department.getDeptName())
                .build();

    }
}
