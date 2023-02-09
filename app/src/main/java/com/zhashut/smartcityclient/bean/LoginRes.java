package com.zhashut.smartcityclient.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/16
 * Time: 22:04
 * Description: 用户登录响应参数
 */
public class LoginRes implements Serializable {
    public String code;

    @SerializedName("msg")
    public String message;

    public String token;
}
