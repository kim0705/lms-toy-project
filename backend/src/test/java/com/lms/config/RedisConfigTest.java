package com.lms.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import static org.assertj.core.api.Assertions.assertThat;

/* *
 * Redis 연결이 정상적으로 이루어지는지 확인하는 테스트 클래스입니다.
 */
@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("Redis 연결 확인 테스트")
    void redisConnectionTest() {
        /* Given: 테스트를 위한 키와 값을 준비 */
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String key = "testKey";
        String value = "hello world";

        /* When: 레디스에 데이터를 저장 */
        valueOperations.set(key, value);

        /* Then: 저장된 데이터를 다시 가져와서 비교 */
        String result = (String) valueOperations.get(key);
        System.out.println("Redis에서 가져온 값: " + result);

        assertThat(result).isEqualTo(value);
    }
}