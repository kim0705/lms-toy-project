package com.lms.mapper;

import com.lms.dto.response.RespAssignmentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    List<RespAssignmentDto> selectAssignmentInfoByWeek(int courseId, int studentId, int week);
}
