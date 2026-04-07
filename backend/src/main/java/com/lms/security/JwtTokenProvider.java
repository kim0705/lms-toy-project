package com.lms.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    /* 비밀키 */
    @Value("${jwt.secret}")
    private String salt;
    /* Access Token 만료 시간 */
    @Value("${jwt.expiration-time}")
    private Long expirationTime;
    /* Refresh Token 만료 시간 */
    @Value("${jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;

    private SecretKey secretKey;

    private final UserDetailsService userDetailsService;

    /* 객체 생성 후 비밀키 초기화 */
    @PostConstruct
    protected void init() {
        /* 설정 파일에서 가져온 'salt' 문자열을 컴퓨터가 이해하는 바이트 배열로 쪼갠 뒤
           HMAC-SHA 알고리즘 규격에 맞는 진짜 열쇠(SecretKey)로 변환 */
        this.secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Access Token을 생성합니다.
     * @param userId 토큰에 담을 사용자 ID
     * @param role 토큰에 담을 사용자 권한
     * @return 생성된 Access Token
     */
    public String createAccessToken(String userId, String role) {
        Date now = new Date();

        return Jwts.builder()
                .subject(userId) /* 유저 아이디 넣기 */
                .claim("role", role) /* 유저 롤 넣기 */
                .issuedAt(now) /* 언제 만들었는지 기록 */
                .expiration(new Date(now.getTime() + expirationTime)) /* 언제까지 유효한지 기록 */
                .signWith(secretKey) /* 위조 방지 처리 */
                .compact(); /* 문자열로 압축 */
    }

    /**
     * Refresh Token을 생성합니다.
     * @param userId 토큰에 담을 사용자 ID
     * @return 생성된 Refresh Token
     */
    public String createRefreshToken(String userId) {
        Date now = new Date();

        return Jwts.builder()
                .subject(userId) /* 유저 아이디 넣기 */
                .issuedAt(now) /* 언제 만들었는지 기록 */
                .expiration(new Date(now.getTime() + refreshExpirationTime)) /* 언제까지 유효한지 기록 */
                .signWith(secretKey) /* 위조 방지 처리 */
                .compact(); /* 문자열로 압축 */
    }

    /**
     * 토큰을 기반으로 Spring Security 인증 객체를 반환합니다.
     * @param token 검증할 JWT 토큰
     * @return 인증 객체 (Authentication)
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰의 유효성 및 만료 여부를 확인합니다.
     * @param jwtToken 검증할 JWT 토큰
     * @return 유효하면 true, 만료 또는 잘못된 토큰이면 false
     */
    public boolean validateToken(String jwtToken) {
        try{
            /* parseSignedClaims는 서명 검증과 만료일자 체크를 동시에 수행 */
            /* 만약 만료되었다면 여기서 바로 ExpiredJwtException이 터짐 */
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwtToken);

            return true;

        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            System.out.println("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    /**
     * 토큰에서 사용자 ID를 추출합니다.
     * @param token 파싱할 JWT 토큰
     * @return 토큰에 담긴 사용자 ID
     */
    public String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Authorization 헤더에서 Bearer 토큰을 추출합니다.
     * @param httpServletRequest HTTP 요청 객체
     * @return Bearer 제거 후 순수 토큰 값, 없으면 null
     */
    public String getBearerToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            /* "Bearer " 뒤의 7번째 인덱스부터 끝까지 잘라냄 */
            return bearerToken.substring(7);
        }
        return null;
    }

}
