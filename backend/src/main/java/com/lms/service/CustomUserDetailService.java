package com.lms.service;

import com.lms.entity.PrincipalUser;
import com.lms.entity.User;
import com.lms.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userMapper.selectUserInfoByUserId(userId);

        if(user == null) {
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다: " + userId);
        }

        return new PrincipalUser(user);
    }
}
