package com.zhashut.smartcityclient.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.adapter.LaunchAdapter;

public class LaunchActivity extends AppCompatActivity {
    private int[] images = {R.drawable.splash1, R.drawable.splash2, R.drawable.splash3};
    private SharedPreferences preferences;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        isFirst();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        ViewPager mViewPager = findViewById(R.id.view_page);
        LaunchAdapter adapter = new LaunchAdapter(getSupportFragmentManager(), images);
        mViewPager.setAdapter(adapter);
    }

    /**
     * 判断是否是第一次进入app
     */
    private void isFirst() {
        preferences = getSharedPreferences("cfg", MODE_PRIVATE);
        count = preferences.getInt("count", 0);
        // 如果是第一次进入app就转到引导页
        if (count == 0) {
            initView();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt("count", ++count);
        edit.apply();
    }
}