import { instance } from "./util/instance"

export const getNoticeInfo = async(courseId, week) => {
    const response = await instance.get(`/notice/${courseId}`, {
        params: {
            week: week || 1
        }
    }) 
    return response.data;
}