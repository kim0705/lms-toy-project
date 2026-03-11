package com.lms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/* OncePerRequestFilter를 상속받으면, 사용자의 한 요청에 대해 딱 한 번만 실행됨을 보장 */
@RequiredArgsConstructor
public class JwtAuthenthicationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /* [STEP 1] 헤더에서 토큰 꺼내기 */
        String token = jwtTokenProvider.getBearerToken(request);

        /* [STEP 2] 토큰이 유효한지 검사하기 */
        if(token != null && jwtTokenProvider.validateToken(token)) {

            /* [STEP 3] 유효하다면, 토큰을 신분증(Authentication)으로 교환하기 */
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            /* [STEP 4] 시큐리티 지갑(SecurityContext)에 신분증 넣어두기 */
            SecurityContextHolder.getContext().setAuthentication(auth);

        }

        /* [STEP 5] 다음 필터로 통과시키기 */
        filterChain.doFilter(request, response);
    }
}
