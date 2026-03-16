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
    @Value(("${jwt.refresh-expiration-time}"))
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

    /* 토큰 생성: JWT 발급 */
    public String createToken(String userId, String role) {
        Date now = new Date();

        return Jwts.builder()
                .subject(userId) /* 유저 아이디 넣기 */
                .claim("role", role) /* 유저 롤 넣기 */
                .issuedAt(now) /* 언제 만들었는지 기록 */
                .expiration(new Date(now.getTime() + expirationTime)) /* 언제까지 유효한지 기록 */
                .signWith(secretKey) /* 위조 방지 처리 */
                .compact(); /* 문자열로 압축 */
    }

    /* 신분증(Authentication) 발급 */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /* 토큰 유효성 및 만료일자 확인 */
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

    /* 토큰에서 유저 ID(Subject) 추출 */
    public String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /* 토큰에서 Bearer 제거 */
    public String getBearerToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            /* "Bearer " 뒤의 7번째 인덱스부터 끝까지 잘라냄 */
            return bearerToken.substring(7);
        }
        return null;
    }

}
