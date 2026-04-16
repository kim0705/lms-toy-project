package com.lms.service;

import com.lms.dto.request.ReqPageDto;
import com.lms.dto.response.RespLectureNoticeDto;
import com.lms.dto.response.RespNoticeDto;
import com.lms.dto.response.RespPageDto;
import com.lms.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    /**
     * 과목 ID, 사용자 ID, 주차를 기반으로 공지사항 목록을 조회합니다.
     * @param courseId 조회할 과목 ID
     * @param userId 조회할 사용자 ID (학번)
     * @param week 조회할 주차
     * @return 공지사항 목록
     */
    public List<RespLectureNoticeDto> findNoticeInfoByWeek(int courseId, String userId, int week) {
        List<RespLectureNoticeDto> result = noticeMapper.selectNoitceInfoByWeek(courseId, userId, week);

        log.info("===== [Service] 주차별 공지 조회 완료 - {}건 =====", result.size());

        return result;
    }

    /**
     * 과목 ID를 기반으로 공지사항 목록을 페이징/검색하여 조회합니다.
     * @param courseId 조회할 과목 ID
     * @param req 페이징 및 검색 조건
     * @return 페이징된 공지사항 목록
     */
    public RespPageDto<RespNoticeDto> findNoticeByCourse(int courseId, ReqPageDto req) {
        List<RespNoticeDto> notices = noticeMapper.selectNoticeListByCourse(courseId, req);
        Long totalCount = noticeMapper.countNoticeListByCourse(courseId, req);
        log.info("===== [Service] 과목별 공지 조회 완료 - {}건 =====", notices.size());
        return new RespPageDto<>(notices, totalCount, req.getPage(), req.getSize());
    }
}
