package com.zhashut.smartcityclient.common;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.zhashut.smartcityclient.utils.HttpUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

public class ReqCallback {
    // 带json参数的回调
    public <T> void CallBack(String url, String json, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, json, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 带token的 POST请求 回调
    public <T> void CallBack(String url, String token, String json, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, token, json, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 带token的 GET请求 回调
    public <T> void CallBackWithToken(String url, String token, Handler handler, Class<T> classOfT) {
        HttpUtil.ReqWithToken(url, token, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 不带任何参数的回调
    public <T> void CallBack(String url, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReq(url, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 不带任何参数的回调
//    public void CallBackTest(String url, Handler handler, Class<AdvertList> baseEntity) {
//        HttpUtil.JsonReq(url, new okhttp3.Callback() {
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                Message msg = Message.obtain();
//                if (response.isSuccessful()) {
//                    String respJson = response.body().string();
//                    Gson gson = new Gson();
//                    Object info = gson.fromJson(respJson, baseEntity);
//                    msg.obj = info;
//                    handler.sendMessage(msg);
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//        });
//    }

    // 带id的回调请求 且带token get方法
    // 带id的回调请求 且带token
    public <T> void CallBackByIDWithToken(String url, int id, String token, Handler handler, Class<T> classOfT) {
        HttpUtil.ReqWithTokenById(url, token, id, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 带id的回调请求
    public <T> void CallBackByID(String url, int id, Handler handler, Class<T> classOfT) {
        HttpUtil.JsonReqByID(url, id, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }

    // 带token和id的 POST请求 回调
    public <T> void CallBackPostByIDWithToken(String url, String token, int id, JSONObject json, Handler handler, Class<T> classOfT) {
        HttpUtil.ReqPostWithTokenById(url, token, id, json, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Message msg = Message.obtain();
                if (response.isSuccessful()) {
                    String userJSON = response.body().string();
                    Gson gson = new Gson();
                    Object info = gson.fromJson(userJSON, (Type) classOfT);
                    msg.obj = info;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
}
