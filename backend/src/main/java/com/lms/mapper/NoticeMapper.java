package com.lms.mapper;

import com.lms.dto.response.RespNoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /* 학습 주차별 공지사항 정보 조회 */
    List<RespNoticeDto> selectNoitceInfoByWeek(int courseId, int studentId, int week);
}
