package com.lms.mapper;

import com.lms.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    /* PK(숫자)를 이용한 수강 목록 정보 조회 */
    List<Course> selectEnrollmentById(int id);
}
