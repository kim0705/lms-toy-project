import { instance } from "./util/instance";

/**
 * [로그인 요청]
 * 아이디와 비밀번호를 서버에 전달하여 Access/Refresh Token을 발급받습니다.
 * @param {string} userId - 사용자 아이디
 * @param {string} password - 사용자 비밀번호
 * @returns {Promise<{accessToken: string, refreshToken: string}>} 발급된 토큰 정보
 */
export const signIn = async (userId, password) => {
    const response = await instance.post('/auth/signin', { userId, password });
    return response;
};

/**
 * [토큰 재발급 요청]
 * 만료된 Access Token을 Refresh Token을 사용하여 재발급받습니다.
 * @param {string} refreshToken - 로컬 스토리지에 저장된 Refresh Token
 * @returns {Promise<{accessToken: string, refreshToken: string}>} 갱신된 토큰 정보
 */
export const refresh = async (refreshToken) => {
    const response = await instance.post('/auth/refresh', { refreshToken });
    return response;
};
