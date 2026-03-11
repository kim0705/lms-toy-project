package com.lms.service;

import com.lms.dto.request.ReqLoginDto;
import com.lms.entity.User;
import com.lms.mapper.StudentMapper;
import com.lms.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String signIn(ReqLoginDto reqLoginDto) {
        /* 1. DB 조회 */
        User user = studentMapper.selectStudentInfoByStudentNo(reqLoginDto.getUserId());

        if(user == null) {
            throw new RuntimeException("등록되지 않은 아이디입니다.");
        }

        /* 2. 비밀번호 확인 */
        if(!passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호를 확인해주세요.");
        }

        /* 3. 토큰 발행 */
        return jwtTokenProvider.createToken(user.getUserId(), user.getRole());
    }
}
