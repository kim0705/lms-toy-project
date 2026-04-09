package com.lms.service;

import com.lms.dto.response.RespLectureDto;
import com.lms.entity.Lecture;
import com.lms.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureMapper lectureMapper;

    /**
     * 과목 ID와 주차를 기반으로 강의 목록을 조회합니다. 주차 미입력 시 전체 반환.
     * @param courseId 조회할 과목 ID
     * @param week 조회할 주차 (null 허용, null 시 전체 조회)
     * @return 강의 정보 목록
     */
    public List<RespLectureDto> findLectureInfoByWeek(int courseId, Integer week) {
        log.info("[Lecture] 강의 조회 - courseId: {}, week: {}", courseId, week);
        List<Lecture> lectureList = lectureMapper.selectLectureInfoByWeek(courseId, week);
        log.info("[Lecture] 조회 결과: {}건", lectureList.size());
        return RespLectureDto.fromEntity(lectureList);
    }
}
