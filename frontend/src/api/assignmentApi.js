import { instance } from "./util/instance";

export const getAssignmentInfo = async(courseId, week) => {
    const response = await instance.get(`/assignment/${courseId}`, {
        params: {
            week: week || 1
        }
    });
    return response;
}