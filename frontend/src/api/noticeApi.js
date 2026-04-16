import { instance } from "./util/instance"

/* 주차별 공지 조회 */
export const getNoticeInfo = async(courseId, week) => {
    const response = await instance.get(`/notices/${courseId}`, {
        params: { week: week || 1 }
    });
    return response;
}

/* 과목별 공지 목록 조회 (페이징/검색) */
export const getNoticeList = async(courseId, params) => {
    const response = await instance.get(`/notices/${courseId}/list`, { params });
    return response;
}
