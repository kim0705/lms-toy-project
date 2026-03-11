import axios from "axios";

export const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        // Authorization: localStorage.getItem("accessToken"),
        'Content-Type': 'application/json'
    }
});

instance.interceptors.response.use(
    (response) => {
        /* 성공 응답 처리 
           서버 응답 구조: { status: 200, message: "...", data: {...} }
        */
        if (response?.data && response?.data?.status === 200) {
            return response?.data?.data; 
        }
        
        /* 만약 status가 200이 아닌 에러 메시지가 왔을 경우 */
        return Promise.reject(response.data);
    },
    (error) => {
        /* HTTP 에러 처리 (401, 404, 500 등) */
        if (error?.response && error?.response.status === 401) {
            console.error("인증 실패! 로그인 페이지로 이동합니다.");
        }
        return Promise.reject(error);
    }
);