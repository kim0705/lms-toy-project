import { instance } from "./util/instance";

export const getEnrollmentList = async() => {
    const response = await instance.get(`/enrollments`);
    return response;
}