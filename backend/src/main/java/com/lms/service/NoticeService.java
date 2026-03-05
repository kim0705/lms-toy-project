package com.lms.service;

import com.lms.dto.response.RespNoticeDto;
import com.lms.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public List<RespNoticeDto> findNoticeInfoByWeek(int courseId, int studentId, int week) {

        return noticeMapper.selectNoitceInfoByWeek(courseId, studentId, week);
    }
}
