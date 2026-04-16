import { instance } from "./util/instance";

/* 로그인 요청: 아이디/비밀번호로 Access/Refresh Token 발급 */
export const signIn = async (userId, password) => {
    const response = await instance.post('/auth/signin', { userId, password });
    return response;
};

/* 토큰 재발급 요청: Refresh Token으로 Access Token 재발급 */
export const refresh = async (refreshToken) => {
    const response = await instance.post('/auth/refresh', { refreshToken });
    return response;
};
