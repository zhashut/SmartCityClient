package com.zhashut.smartcityclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.adapter.NewsListAdapter;
import com.zhashut.smartcityclient.bean.News;

import java.util.List;

public class SearchNewsActivity extends AppCompatActivity {

    private List<News.NewsList> newsLists;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        count = 0;
        onResult();
        intiView();
    }

    private void intiView() {
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(v -> {
            if (count == 0) {
                ++count;
                Toast.makeText(this, "再按一次返回主页面", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("搜索结果");
        ListView lvPress = findViewById(R.id.lv_press);
        NewsListAdapter newsListAdapter = new NewsListAdapter(this, newsLists);
        lvPress.setAdapter(newsListAdapter);
    }

    private void onResult() {
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("news");
        newsLists = news.newsLists;
    }
}