import axios from 'axios'
import { Message } from 'element-ui';

axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    if (response.status && response.status==200&&response.data.status==500) {
        Message.error(response.data.msg);
        return;
    }
    if (response.data.msg) {
        Message.success(response.data.msg);
    }
    return response.data;
}, function (error) {
    // 对响应错误做点什么
    if (error.status==504 || error.status==404) {
        Message.error("not found")

    } else  if (error.status==403) {
        Message.error("no access right")
    } else  if (error.status==401) {
        Message.error("not login")
    } else {
        if (error.response.data.msg){
            Message.error(error.response.data.msg);
        } else {
            Message.error("unknow error!");
        }
    }
    return Promise.reject(error);
});

//let baseURL = 'http://localhost:8081';
let baseURL = '';
//login now only support key-value in server side

    export const postKeyValueRequest = (url, params) => {
    return axios(
        {
            method: 'post',
            url: `${baseURL}${url}`,
            data: params,
            transformRequest: [function (data, headers) {
                // 对 data 进行任意转换处理
                let ret = '';
                for (let i in data) {
                    //convert to key value format
                    ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&';
                    console.log(ret);
                }
                return ret;
            }],
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        }
    );
}

export const postRequest = (url, params) => {
    return axios(
        {
            method: 'post',
            url: `${baseURL}${url}`,
            data: params
        }
    );
}

export const getRequest = (url) => {
    return axios(
        {
            method: 'get',
            url: `${baseURL}${url}`
        }
    );
}
