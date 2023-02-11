package com.zhashut.smartcityclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.activity.RegisterActivity;

import java.util.HashMap;

public class SystemAdapter extends BaseAdapter {

    private Context context;
    private String[] systems;

    public SystemAdapter(Context context, String[] systems) {
        this.context = context;
        this.systems = systems;
    }

    @Override
    public int getCount() {
        return systems.length;
    }

    @Override
    public Object getItem(int position) {
        return systems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_system_line, null);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.iv_icon);
            holder.name = convertView.findViewById(R.id.tv_name);
            holder.item = convertView.findViewById(R.id.ll_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = systems[position];
        holder.name.setText(name);
        holder.icon.setImageResource(R.drawable.logo);
        holder.item.setOnClickListener(v -> {
            switch (name) {
                case "个人信息":
                    Intent personInfoIntent = new Intent(context, RegisterActivity.class);
                    context.startActivity(personInfoIntent);
                    break;
                case "修改密码":

                    break;
                case "意见反馈":

                    break;
                case "订单列表":

                    break;
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public ImageView icon;
        public TextView name;
        public LinearLayout item;
    }
}
