package com.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
/* * Redis 연결 및 데이터 처리를 위한 설정 클래스입니다.
 * Lettuce 라이브러리를 사용하여 비동기로 레디스와 통신합니다.
 */
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        /* application.yml에서 읽어온 host와 port를 사용하여 연결 팩토리를 만듭니다. */
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        /* RedisTemplate은 레디스 데이터를 조작하는 메인 도구입니다. */
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        /* * 레디스에 저장될 때 Key와 Value가 깨지지 않도록 설정합니다.
         * 보통 Key는 String으로, Value는 범용성을 위해 String이나 JSON 형태로 저장합니다.
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        /* Hash 구조를 사용할 경우의 시리얼라이저 설정 */
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
