package com.lms.mapper;

import com.lms.dto.response.RespNoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<RespNoticeDto> selectNoitceInfoByWeek(int courseId, int studentId, int week);
}
