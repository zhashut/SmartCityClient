package com.zhashut.smartcityclient.common;

public class RequestUrl {
    // 接口地址
    public final static String REQUEST_URL = "http://124.93.196.45:10001";

    // 用户登录 - 请求类型：application/json
    public final static String LOGIN_URL = REQUEST_URL + "/prod-api/api/login";

    // 用户注册 - 请求类型：application/json
    public final static String REGISTER_URL = REQUEST_URL + "/prod-api/api/register";

    // 查询新闻列表
    public final static String NEWS_LIST = REQUEST_URL + "/prod-api/press/press/list";

    // 查询主页轮播图
    public final static String ROTATION_URL = REQUEST_URL + "/prod-api/api/rotation/list";

    // 查询所有服务
    public final static String SERVICE_URL = REQUEST_URL + "/prod-api/api/service/list";

    // 获取新闻分类
    public final static String PRESS_CATEGORY_URL = REQUEST_URL + "/prod-api/press/category/list";
}
