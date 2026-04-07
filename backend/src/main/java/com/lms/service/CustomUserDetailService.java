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

    /**
     * 사용자 ID로 인증 객체를 로드합니다.
     * @param userId 조회할 사용자 ID (학번)
     * @return 인증된 사용자 정보 (PrincipalUser)
     * @throws UsernameNotFoundException 사용자를 찾을 수 없는 경우
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userMapper.selectUserInfoByUserId(userId);

        if(user == null) {
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다: " + userId);
        }

        return new PrincipalUser(user);
    }
}
