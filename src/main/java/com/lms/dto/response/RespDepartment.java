package com.lms.dto.response;

import com.lms.dto.CommonInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RespDepartment extends CommonInfoDto {
    private int id;
    private String deptName;
}
