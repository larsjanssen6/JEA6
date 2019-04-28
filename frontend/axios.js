import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/1',
    timeout: 10000,
    params: {}
});


instance.interceptors.request.use(function (config) {
    let token = localStorage.token;

    if (token) {
        config.headers.common['Authorization'] = 'Bearer '+localStorage.getItem('token');
    }

    return config;
}, function (error) {
    return Promise.reject(error);
});

export default instance;