package com.lms.service;

import com.lms.dto.response.RespNoticeDto;
import com.lms.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<RespNoticeDto> findNoticeInfoByWeek(int courseId, String userId, int week) {

        return noticeMapper.selectNoitceInfoByWeek(courseId, userId, week);

    }
}
