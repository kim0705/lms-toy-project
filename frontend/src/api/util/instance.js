import axios from "axios";

export const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        'Content-Type': 'application/json'
    }
});

/**
 * [요청 인터셉터]
 * 모든 요청에 로컬 스토리지의 Access Token을 Authorization 헤더에 자동으로 추가합니다.
 */
instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("accessToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

/**
 * [응답 인터셉터]
 * 성공 응답 시 서버 응답 구조(status, message, data)에서 data만 추출하여 반환합니다.
 * 401 응답 시 Refresh Token으로 Access Token 재발급 후 원래 요청을 재시도합니다.
 * 재발급 실패 시 토큰을 삭제하고 로그인 페이지로 이동합니다.
 */
instance.interceptors.response.use(
    (response) => {
        if (response?.data && response?.data?.status === 200) {
            return response?.data?.data;
        }
        return Promise.reject(response.data);
    },
    async (error) => {
        const originalRequest = error.config;

        if (error?.response?.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;

            try {
                const refreshToken = localStorage.getItem("refreshToken");
                const response = await axios.post("http://localhost:8080/api/auth/refresh", { refreshToken });

                if (response?.data?.status === 200) {
                    const { accessToken, refreshToken: newRefreshToken } = response.data.data;
                    localStorage.setItem("accessToken", accessToken);
                    localStorage.setItem("refreshToken", newRefreshToken);

                    originalRequest.headers.Authorization = `Bearer ${accessToken}`;
                    return instance(originalRequest);
                }
            } catch {
                localStorage.removeItem("accessToken");
                localStorage.removeItem("refreshToken");
                window.location.href = "/login";
            }
        }

        return Promise.reject(error);
    }
);
