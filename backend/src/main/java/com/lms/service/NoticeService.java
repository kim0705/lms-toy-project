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

    public List<RespNoticeDto> findNoticeInfoByWeek(int courseId, String userId, int week) {

        return noticeMapper.selectNoitceInfoByWeek(courseId, userId, week);

    }
}
