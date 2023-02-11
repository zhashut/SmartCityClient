package com.zhashut.smartcityclient.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.common.ReqResult;
import com.zhashut.smartcityclient.common.ResultEntity;
import com.zhashut.smartcityclient.utils.HttpUtil;

import org.json.JSONObject;

import static com.zhashut.smartcityclient.common.RequestUrl.REGISTER_URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_psw;
    private String username;
    private String password;
    private EditText et_phone;
    private String phone;
    public ImageView iv_rotate;
    public TextView tv_wait;
    private RadioGroup rg_sex;
    private int sex;
    private HttpUtil httpUtil = new HttpUtil();
    private Handler handler = ReqResult.ResultHandler(RegisterActivity.this, LoginActivity.class, "注册成功");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_psw = findViewById(R.id.et_psw);
        et_phone = findViewById(R.id.et_phone);
        iv_rotate = findViewById(R.id.iv_rotate);
        tv_wait = findViewById(R.id.tv_wait);
        rg_sex = findViewById(R.id.rg_sex); // 0-男 1-女
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    /**
     * 控件绑定事件
     *
     * @param v 视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                username = et_name.getText().toString().trim();
                password = et_psw.getText().toString().trim();
                phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "注册失败，账号密码不得为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (phone.length() != 11) {
                    Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                sex = rg_sex.getCheckedRadioButtonId() == R.id.rb_male ? 0 : 1;
                registerLoading(username, password, phone, sex);
                break;
        }
    }

    /**
     * 注册加载回调
     */
    private void registerLoading(String username, String password, String phone, int sex) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userName", username);
            jsonObject.put("password", password);
            jsonObject.put("phonenumber", phone);
            jsonObject.put("sex", sex);
            // 回调方法
            httpUtil.JsonReqCallBack(REGISTER_URL, jsonObject.toString(), handler, ResultEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}