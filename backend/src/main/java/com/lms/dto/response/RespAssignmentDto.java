package com.lms.dto.response;

import com.lms.dto.CommonInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RespAssignmentDto extends CommonInfoDto {
    private int courseId;
    private int assignmentId;
    private int week;
    private String title;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private String isPeriod;
    private String studentId;
    private String subStatus;
}
