import { instance } from "./util/instance";

export const getEnrollmentList = async(studentId) => {
    const response = await instance.get(`/enrollments/${studentId}`);
    return response.data;
}