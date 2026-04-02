package com.lms.mapper;

import com.lms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /* PK(숫자)로 유저 찾기 (내 정보 조회, 학과 조회 등 내부 로직용) */
    User selectUserInfoById(int id);
    /* 로그인 ID(문자열)로 유저 찾기 (로그인 처리, 토큰 재발급용) */
    User selectUserInfoByUserId(String userId);
}
