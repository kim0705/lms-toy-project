package com.lms.mapper;

import com.lms.dto.response.RespAssignmentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssignmentMapper {
    /* 학습 주차별 과제 정보 조회 */
    List<RespAssignmentDto> selectAssignmentInfoByWeek(int courseId, int id, int week);
}
