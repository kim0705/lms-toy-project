package com.lms.mapper;

import com.lms.dto.request.ReqPageDto;
import com.lms.dto.response.RespLectureNoticeDto;
import com.lms.dto.response.RespNoticeDto;
import com.lms.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    /* 학습 주차별 공지사항 정보 조회 */
    List<RespLectureNoticeDto> selectNoitceInfoByWeek(int courseId, String userId, int week);

    /* 과목별 공지사항 목록 조회 (페이징/검색) */
    List<RespNoticeDto> selectNoticeListByCourse(int courseId, ReqPageDto req);

    /* 과목별 공지사항 총 개수 조회 (페이징용) */
    Long countNoticeListByCourse(int courseId, ReqPageDto req);

    /* 공지사항 단건 상세 조회 */
    Notice selectNoticeDetail(int courseId, int noticeId);

    /* 공지사항 조회수 +1 */
    void updateViewCount(int noticeId);

    /* 공지사항 읽음 로그 기록 (중복 시 무시) - 실제 insert된 경우 1 반환 */
    int insertNoticeLog(@Param("noticeId") int noticeId, @Param("userId") String userId, @Param("clientIp") String clientIp);
}
