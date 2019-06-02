import axios from "axios"
import auth from "./auth"

const http = axios.create({baseURL:"/api/v1"})
http.interceptors.request.use(config => {
    if (auth.getToken()) {
        config.headers['Authorization'] = 'Bearer ' + storageService.getToken();
        config.headers['X-Socket-ID'] = Echo.socketId();
    }

    return Promise.resolve(config);
},
error => {
    return Promise.reject(error);
});