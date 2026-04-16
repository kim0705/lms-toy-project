import axios from "axios";

export const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        'Content-Type': 'application/json'
    }
});

/* 요청 인터셉터: Access Token을 Authorization 헤더에 자동 추가 */
instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("accessToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

/* 응답 인터셉터: 성공 시 data 추출, 401 시 토큰 재발급 시도 */
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

        const serverMessage = error?.response?.data?.message;
        return Promise.reject(new Error(serverMessage || '서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.'));
    }
);
