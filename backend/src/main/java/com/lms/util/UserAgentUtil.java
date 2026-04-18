package com.lms.util;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 접속 기기 및 브라우저 정보 추출 유틸리티
 * HttpServletRequest의 User-Agent 헤더를 분석하여 OS, 브라우저, 기기 유형(PC/MOBILE)을 판별합니다.
 */

public class UserAgentUtil {

    /**
     * User-Agent 문자열 분석
     * @param userAgent HttpServletRequest에서 추출한 User-Agent 헤더 값
     * @return OS, Browser, Device 정보를 담은 Map 객체
     */
    public static Map<String, String> extractUserAgentInfo(String userAgent) {
        Map<String, String> result = new HashMap<>();

        /* OS 판별 */
        String os = "Unknown OS";
        if (userAgent.contains("iPhone") || userAgent.contains("iPad")) os = "iOS";
        else if (userAgent.contains("Android")) os = "Android";
        else if (userAgent.contains("Windows")) os = "Windows";
        else if (userAgent.contains("Mac")) os = "Mac";

        /* 브라우저 판별 (순서: Edge -> Chrome -> Safari) */
        String browser = "Unknown Browser";
        if (userAgent.contains("Edg")) browser = "Edge";
        else if (userAgent.contains("Chrome")) browser = "Chrome";
        else if (userAgent.contains("Safari")) browser = "Safari";
        else if (userAgent.contains("Firefox")) browser = "Firefox";

        /* 기기 타입 판별 */
        String device = (userAgent.contains("Mobi") || userAgent.contains("Android")) ? "MOBILE" : "PC";

        result.put("osName", os);
        result.put("browserName", browser);
        result.put("deviceType", device);

        return result;
    }

    /**
     * 클라이언트 실제 IP 추출
     * 프록시 환경에서 X-Forwarded-For 등의 헤더를 순차 확인하여 실제 IP를 반환합니다.
     * @param request HttpServletRequest
     * @return 클라이언트 실제 IP 주소
     */
    public static String extractClientIp(HttpServletRequest request) {
        String[] headers = {
            "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
        };

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                return ip.split(",")[0].trim();
            }
        }

        return request.getRemoteAddr();
    }

}
