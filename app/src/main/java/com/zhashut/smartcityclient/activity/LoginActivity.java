package com.zhashut.smartcityclient.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.bean.LoginRes;
import com.zhashut.smartcityclient.utils.AnimationUtil;
import com.zhashut.smartcityclient.utils.HttpUtil;
import com.zhashut.smartcityclient.utils.TextUtil;

import org.json.JSONException;
import org.json.JSONObject;

import static com.zhashut.smartcityclient.common.RequestUrl.LOGIN_URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText et_name;
    private EditText et_psw;
    private CheckBox ck_remember;
    private ImageView iv_rotate;
    private TextView tv_wait;
    private String username;
    private String password;
    private int count;
    private SharedPreferences preferences;
    private LoginRes userInfo;
    private HttpUtil httpUtil = new HttpUtil();
    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            userInfo = (LoginRes) msg.obj;
            if (userInfo.code.equals("200")) {
                // 登录成功
                loginSuccess(userInfo);
            } else {
                // 登录失败
                loginFailed(userInfo);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        isFirst();
        initView();
        reload();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        preferences = getSharedPreferences("cfg", MODE_PRIVATE);
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        et_name.addTextChangedListener(this);
        et_psw.addTextChangedListener(this);
        ck_remember = findViewById(R.id.ck_remember);
        iv_rotate = findViewById(R.id.iv_rotate);
        tv_wait = findViewById(R.id.tv_wait);
        ck_remember.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isRemember", isChecked);
            edit.commit();
        });
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    /**
     * 登录成功
     */
    private void loginSuccess(LoginRes user) {
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences.Editor edit = preferences.edit();
        String token = user.token;
        edit.putString("token", token);
        edit.putString("username", username);
        edit.putString("password", password);
        edit.apply();
        startActivity(intent);
        AnimationUtil.StopAnim(this, iv_rotate, tv_wait);
    }

    /**
     * 登录失败
     */
    private void loginFailed(LoginRes userInfo) {
        et_psw.setText("");
        Toast.makeText(this, userInfo.message, Toast.LENGTH_LONG).show();
        AnimationUtil.StopAnim(this, iv_rotate, tv_wait);
    }

    /**
     * 控件绑定事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                username = et_name.getText().toString().trim();
                password = et_psw.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "登录失败，账号密码不得为空", Toast.LENGTH_SHORT).show();
                    et_psw.setText("");
                    return;
                }

                // 加载动画效果
                AnimationUtil.LoadingAnim(this, iv_rotate, tv_wait);

                // 登录加载回调
                loginLoading(username, password);

                break;
            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 记住密码
     */
    private void reload() {
        boolean isRemember = preferences.getBoolean("isRemember", false);
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        if (isRemember) {
            et_name.setText(username);
            et_psw.setText(password);
            ck_remember.setChecked(true);
        }
    }

    /**
     * 登录加载回调
     */
    private void loginLoading(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            Log.i("log", jsonObject.toString());
            // 回调类实例
            // 回调方法
            httpUtil.JsonReqCallBack(LOGIN_URL, jsonObject.toString(), handler, LoginRes.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否是第一次进入登录界面
     */
    private void isFirst() {
        count = preferences.getInt("count", 0);
        if (count != 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt("count", ++count);
        edit.apply();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        TextUtil.limitInput(et_name);
        TextUtil.limitInput(et_psw);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}