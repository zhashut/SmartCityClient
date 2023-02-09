package com.zhashut.smartcityclient.common;

public class RequestUrl {
    // 接口地址
    public final static String REQUEST_URL = "http://124.93.196.45:10001";

    // 用户登录 - 请求类型：application/json
    public final static String LOGIN_URL = REQUEST_URL + "/prod-api/api/login";
    // 用户注册 - 请求类型：application/json
    public final static String REGISTER_URL = REQUEST_URL + "/prod-api/api/register";
}
