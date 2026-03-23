package com.lms.service;

import com.lms.entity.User;
import com.lms.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String studentNo) throws UsernameNotFoundException {

        User user = studentMapper.selectStudentInfoByStudentNo(studentNo);

        if(user == null) {
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다: " + studentNo);
        }

        return user;
    }
}
