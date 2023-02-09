package com.zhashut.smartcityclient.common;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class ReqResult {
    // context: 当前 activity， cls: 要跳转的 activity
    public static Handler ResultHandler(Context context, Class<?> cls) {
        return new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                ResultEntity result = (ResultEntity) msg.obj;
                if (result.code.equals("200")) {
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, cls);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    // context: 当前 activity， cls: 要跳转的 activity, successMsg: 成功的消息
    public static Handler ResultHandler(Context context, Class<?> cls, String successMsg) {
        return new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                ResultEntity result = (ResultEntity) msg.obj;
                if (result.code.equals("200")) {
                    // 注册成功
                    Toast.makeText(context, successMsg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, cls);
                    context.startActivity(intent);
                } else {
                    // 注册失败
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
