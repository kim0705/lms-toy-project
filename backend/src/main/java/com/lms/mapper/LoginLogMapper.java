package com.lms.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper {
    /* 로그인 로그 저장 */
    int insertLoginLog(String userId, String deviceInfo, String ua, String clientIp);
}
