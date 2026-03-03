import axios from "axios";

export const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        // Authorization: localStorage.getItem("accessToken"),
        'Content-Type': 'application/json'
    }
});