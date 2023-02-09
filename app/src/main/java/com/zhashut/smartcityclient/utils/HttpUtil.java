package com.zhashut.smartcityclient.utils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    private static final MediaType mediaType = MediaType.Companion.parse("application/json;charset=utf-8");

    public <T> void JsonReqCallBack(String url, String object, Handler handler, Class<T> tClass) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Message msg = Message.obtain();
                String respJson = response.body().string();
                Gson gson = new Gson();
                Object info = gson.fromJson(respJson, (Type) tClass);
                msg.obj = info;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }

    // 请求类型：application/json，带json数据
    public static void JsonReq(String url, String object, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    // 请求类型：application/json，带token
    // 请求类型：application/json，带token和json参数
    public static void JsonReq(String url, String token, String object, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object, mediaType);
        Request request = new Request.Builder().url(url).header("Authorization", token).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，带token和json参数
    public static void ReqWithToken(String url, String token, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).header("Authorization", token).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，不带任何参数
    public static void JsonReq(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：带id的请求
    public static void JsonReqByID(String url, int id, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url + "/" + id).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，带token和json参数 请求类型：带id的请求
    // 请求类型：application/json，带token和json参数
    public static void ReqWithTokenById(String url, String token, Integer id, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url + "/" + id).header("Authorization", token).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    // 请求类型：application/json，带token和json参数
    public static void ReqPostWithTokenById(String url, String token, int id, JSONObject object, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.Companion.create(object.toString(), mediaType);
        Request request = new Request.Builder().url(url + "/" + id).header("Authorization", token).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
