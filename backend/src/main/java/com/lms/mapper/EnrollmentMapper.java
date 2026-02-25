package com.lms.mapper;

import com.lms.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnrollmentMapper {
    public List<Course> selectEnrollmentById(int studentId);
}
