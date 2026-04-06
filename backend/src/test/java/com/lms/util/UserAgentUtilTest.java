package com.lms.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * User-Agent 데이터 추출 확인 통합 테스트 클래스입니다.
 */

@SpringBootTest
class UserAgentUtilTest {

    @Test
    @DisplayName("크롬 브라우저와 윈도우 OS 정보 추출 확인")
    void parse_Chrome_Windows_Success() {

        String chromeUa = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/146.0.0.0 Safari/537.36";

        Map<String, String> result = UserAgentUtil.extractUserAgentInfo(chromeUa);

        assertThat(result.get("osName")).isEqualTo("Windows");
        assertThat(result.get("browserName")).isEqualTo("Chrome");
        assertThat(result.get("deviceType")).isEqualTo("PC");
    }

    @Test
    @DisplayName("아이폰 사파리 브라우저 정보 추출 확인")
    void parse_Safari_iOS_Success() {

        String iPhoneUa = "Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1";

        Map<String, String> result = UserAgentUtil.extractUserAgentInfo(iPhoneUa);

        assertThat(result.get("osName")).isEqualTo("iOS");
        assertThat(result.get("browserName")).isEqualTo("Safari");
        assertThat(result.get("deviceType")).isEqualTo("MOBILE");
    }

    @Test
    @DisplayName("엣지 브라우저가 크롬보다 먼저 판별 되는지 확인")
    void parse_Edge_Success() {
        String edgeUa = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0";

        Map<String, String> result = UserAgentUtil.extractUserAgentInfo(edgeUa);

        assertThat(result.get("browserName")).isEqualTo("Edge");
    }

}