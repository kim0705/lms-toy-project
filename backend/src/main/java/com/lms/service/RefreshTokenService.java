package com.lms.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, String> redisTemplate;

    /* 리프레시 토큰 저장: Key(유저ID) - Value(토큰값) */
    public void saveRefreshToken(String userId, String refreshToken, Long expirationTime) {

        /* 유효성 검사: 유저 ID가 없거나 비어있는 경우 예외를 발생 */
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("유저 ID는 필수 항목입니다.");
        }

        /* 유효성 검사: refreshToken이 없거나 비어있는 경우 예외를 발생 */
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("리프레시 토큰은 필수 항목입니다.");
        }

        /* 유효성 검사: expirationTime이 0보다 커야 정상적인 저장이 가능 */
        if (expirationTime == null || expirationTime <= 0) {
            throw new IllegalArgumentException("만료 시간은 0보다 커야 합니다.");
        }

        /* 레디스에 저장할 때 유효기간(TTL)을 함께 설정해서 자동으로 삭제 */
        redisTemplate.opsForValue().set(
                userId,
                refreshToken,
                expirationTime,
                TimeUnit.MILLISECONDS
        );

        log.info("[Redis Success] 리프레시 토큰 저장 완료 - 유저: {}", userId);
    }

    /* 저장된 토큰 가져오기 */
    public String findRefreshToken(String userId) {

        /* 유효성 검사: 유저 ID가 없거나 비어있는 경우 예외를 발생 */
        if(userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("조회할 유저 ID가 없습니다.");
        }

        String token = redisTemplate.opsForValue().get(userId);

        if (token != null) {
            log.info("[Redis Success] 리프레시 토큰 조회 완료 - 유저: {}", userId);
        }

        return token;
    }

    /* 토큰 삭제 (로그아웃 시 사용) */
    public void deleteRefreshToken(String userId) {

        /* 유효성 검사: 유저 ID가 없거나 비어있는 경우 예외를 발생 */
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("삭제할 유저 ID가 없습니다.");
        }

        redisTemplate.delete(userId);

        log.info("[Redis Success] 리프레시 토큰 삭제 완료 - 유저: {}", userId);
    }
}
