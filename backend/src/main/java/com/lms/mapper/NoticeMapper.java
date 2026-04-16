package com.lms.mapper;

import com.lms.dto.request.ReqPageDto;
import com.lms.dto.response.RespLectureNoticeDto;
import com.lms.dto.response.RespNoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /* 학습 주차별 공지사항 정보 조회 */
    List<RespLectureNoticeDto> selectNoitceInfoByWeek(int courseId, String userId, int week);

    /* 과목별 공지사항 목록 조회 (페이징/검색) */
    List<RespNoticeDto> selectNoticeListByCourse(int courseId, ReqPageDto req);

    /* 과목별 공지사항 총 개수 조회 (페이징용) */
    Long countNoticeListByCourse(int courseId, ReqPageDto req);
}
