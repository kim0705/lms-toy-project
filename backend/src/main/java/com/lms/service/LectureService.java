package com.lms.service;

import com.lms.dto.response.RespLectureDto;
import com.lms.entity.Lecture;
import com.lms.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureMapper lectureMapper;

    public List<RespLectureDto> findLectureInfoByWeek(int courseId, Integer week) {

        List<Lecture> lectureList = lectureMapper.selectLectureInfoByWeek(courseId, week);

        return RespLectureDto.fromEntity(lectureList);
    }
}
