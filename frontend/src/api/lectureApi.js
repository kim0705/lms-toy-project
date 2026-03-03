import { instance } from "./util/instance";

export const getLectureInfo = async(courseId, week) => {
    const response = await instance.get(`/lecture/${courseId}`, {
        params: {
            week: week || null
        }
    });
    return response.data;
}