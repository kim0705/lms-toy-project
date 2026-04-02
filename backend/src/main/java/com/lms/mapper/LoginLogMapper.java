package com.lms.mapper;

import com.lms.dto.auth.LoginLogDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
    /* 로그인 로그 저장 */
    int insertLoginLog(LoginLogDto loginLogDto);
}
