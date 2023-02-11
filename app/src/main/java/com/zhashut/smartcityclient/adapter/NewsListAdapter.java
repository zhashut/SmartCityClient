 package com.zhashut.smartcityclient.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.bean.News;
import com.zhashut.smartcityclient.utils.ImageUtil;

import java.util.List;

import static com.zhashut.smartcityclient.common.RequestUrl.REQUEST_URL;

public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private List<News.NewsList> newsLists;
    private String contentStr;

    public NewsListAdapter(Context context, List<News.NewsList> newsLists) {
        this.context = context;
        this.newsLists = newsLists;
    }

    @Override
    public int getCount() {
        return newsLists.size();
    }

    @Override
    public Object getItem(int position) {
        return newsLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_press_list_view, null);
            holder = new ViewHolder();
            holder.ivNewsImg = convertView.findViewById(R.id.iv_news_img);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvContent = convertView.findViewById(R.id.tv_content);
            holder.readNum = convertView.findViewById(R.id.tv_read);
            holder.commentNum = convertView.findViewById(R.id.tv_comment);
            holder.likeNum = convertView.findViewById(R.id.tv_like);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        News.NewsList info = newsLists.get(position);
        ImageUtil.UploadImage(context, holder.ivNewsImg, REQUEST_URL + info.cover, 0, 0);
        holder.tvTitle.setText("【" + info.title + "】");
        holder.tvTime.setText("编辑于:" + info.createTime);
        if (Html.fromHtml(info.content).toString().length() > 150) {
            contentStr = Html.fromHtml(info.content).toString().substring(0, 150) + ".....";
        } else {
            contentStr = Html.fromHtml(info.content).toString();
        }
        holder.tvContent.setText(contentStr);
        holder.readNum.setText(info.readNum + "");
        holder.commentNum.setText(info.commentNum + "");
        holder.likeNum.setText(info.likeNum + "");

        return convertView;
    }

    public class ViewHolder {
        public ImageView ivNewsImg;
        public TextView tvTitle;
        public TextView tvTime;
        public TextView tvContent;
        public TextView readNum;
        public TextView commentNum;
        public TextView likeNum;
    }
}
