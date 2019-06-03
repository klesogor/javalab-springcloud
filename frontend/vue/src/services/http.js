import axios from "axios"
import auth from "./auth"

const http = axios.create({baseURL:"http://localhost/api/v1"})
http.interceptors.request.use(config => {
    if (auth.getToken()) {
        config.headers['Authorization'] = 'Bearer ' + auth.getToken();
    }

    return Promise.resolve(config);
},
error => {
    return Promise.reject(error);
});

export default http