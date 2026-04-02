package com.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * [인증 유저 엔티티]
 * 인증 객체로 사용
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalUser implements UserDetails {

    private User user;

    /* 시큐리티 권한 반환 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    /* 시큐리티 비밀번호 반환 */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /* 시큐리티 아이디 반환 (학번) */
    @Override
    public String getUsername() {
        return user.getUserId();
    }

    /* 계정 상태 관리 시작 */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
    /* 계정 상태 관리 끝 */
}
