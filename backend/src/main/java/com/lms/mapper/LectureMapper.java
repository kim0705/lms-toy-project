package com.lms.mapper;

import com.lms.entity.Lecture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LectureMapper {
    public List<Lecture> selectLectureInfoByWeek(int courseId, int week);
}
