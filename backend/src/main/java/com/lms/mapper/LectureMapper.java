package com.lms.mapper;

import com.lms.entity.Lecture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LectureMapper {
    /* 주차별 강의 정보 조회 */
    List<Lecture> selectLectureInfoByWeek(int courseId, Integer week);
}
