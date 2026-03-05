package com.lms.dto.response;

import com.lms.dto.CommonInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespNoticeDto {
    private int noticeId;
    private int courseId;
    private String title;
    private String readStatus;
}
