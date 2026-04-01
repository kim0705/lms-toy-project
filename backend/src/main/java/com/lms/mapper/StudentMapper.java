package com.lms.mapper;

import com.lms.entity.PrincipalUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    /* 아이디로 학생 개인정보 조회 */
    PrincipalUser selectStudentInfoByStudentNo(String studentNo);
}
