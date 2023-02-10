package com.zhashut.smartcityclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.adapter.NewsListAdapter;
import com.zhashut.smartcityclient.bean.News;
import com.zhashut.smartcityclient.bean.PressCategory;
import com.zhashut.smartcityclient.utils.HttpUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.zhashut.smartcityclient.common.RequestUrl.NEWS_LIST;
import static com.zhashut.smartcityclient.common.RequestUrl.PRESS_CATEGORY_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    private final String TYPE = "type";
    private View view;
    private LinearLayout ll_news;
    private TextView[] textViews;
    private NewsListAdapter newsListAdapter;
    HttpUtil httpUtil = new HttpUtil();

    // 查询新闻分类回调
    private Handler categoryHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PressCategory pressCategory = (PressCategory) msg.obj;
            pressSuccess(pressCategory.pressLists);
        }
    };

    // 查询新闻列表
    private Handler pressListHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            News newsList = (News) msg.obj;
            initPressListAdapter(newsList.newsLists);
        }
    };

    // 新闻列表适配器
    private void initPressListAdapter(List<News.NewsList> newsLists) {
        ListView lvNews = view.findViewById(R.id.lv_news);
        newsListAdapter = new NewsListAdapter(getContext(), newsLists);
        lvNews.setAdapter(newsListAdapter);
    }

    // 获取新闻分类成功
    private void pressSuccess(List<PressCategory.PressList> pressLists) {
        int count = pressLists.size();
        textViews = new TextView[count];
        for (int i = 0; i < count; i++) {
            PressCategory.PressList pressList = pressLists.get(i);
            textViews[i] = new TextView(getContext());
            textViews[i].setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            textViews[i].setGravity(Gravity.CENTER);
            textViews[i].setTextColor(Color.BLACK);
            textViews[i].setTextSize(17.0f);
            textViews[i].setText(pressList.name);
            ll_news.addView(textViews[i]);
            setTextViewOnClick(i, pressList.id);
        }
    }

    private void setTextViewOnClick(int i, int id) {
        TextView tv_news = (TextView) ll_news.getChildAt(i);
        String idStr = String.valueOf(id);
        tv_news.setOnClickListener(v -> {
            httpUtil.NewsListWithKeyword(NEWS_LIST, TYPE, idStr, pressListHandler, News.class);
        });
    }

    public static NewsFragment newInstance(News news) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 加载网络请求
    private void initLoading() {
        httpUtil.CommonList(PRESS_CATEGORY_URL, categoryHandler, PressCategory.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        ll_news = view.findViewById(R.id.ll_news);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLoading();
    }

}