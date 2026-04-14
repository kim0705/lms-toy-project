import { instance } from "./util/instance";

export const getLectureInfo = async(courseId, week) => {
    const response = await instance.get(`/lectures/${courseId}`, {
        params: {
            week: week || null
        }
    });
    return response;
}