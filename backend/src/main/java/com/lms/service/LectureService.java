package com.lms.service;

import com.lms.dto.response.RespLectureDto;
import com.lms.entity.Lecture;
import com.lms.mapper.LectureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureMapper lectureMapper;

    public List<RespLectureDto> findLectureInfoByWeek(int courseId, int week) {

        List<Lecture> lectureList = lectureMapper.selectLectureInfoByWeek(courseId, week);

        return RespLectureDto.fromEntity(lectureList);
    }
}
