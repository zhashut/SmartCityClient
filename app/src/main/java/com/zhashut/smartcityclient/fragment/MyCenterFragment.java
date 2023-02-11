package com.zhashut.smartcityclient.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.activity.LoginActivity;
import com.zhashut.smartcityclient.adapter.SystemAdapter;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyCenterFragment extends Fragment implements View.OnClickListener {

    private ListView lvSystem;
    private String[] systems = {"个人信息", "修改密码", "意见反馈", "订单列表"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_center, container, false);
        TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText("炸薯条");
        lvSystem = view.findViewById(R.id.lv_system);
        Button btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        initAdapter();
        return view;
    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        SystemAdapter systemAdapter = new SystemAdapter(getContext(), systems);
        lvSystem.setAdapter(systemAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("退出登录");
                dialog.setMessage("你确定要退出登录吗");
                dialog.setPositiveButton("确定", (a, b) -> {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                });
                dialog.setNegativeButton("取消", (a, b) -> {
                });
                dialog.create();
                dialog.show();
                break;
        }
    }
}