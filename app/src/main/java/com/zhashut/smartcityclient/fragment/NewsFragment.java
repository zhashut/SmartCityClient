package com.zhashut.smartcityclient.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.TypedValue;
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
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.adapter.NewsAdapter;
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
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    private View view;
    private ViewPager vpNews;
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

    // 获取新闻分类成功
    private void pressSuccess(List<PressCategory.PressList> pressLists) {
        NewsAdapter newsAdapter = new NewsAdapter(getFragmentManager(), pressLists);
        vpNews.setAdapter(newsAdapter);
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
        PagerTabStrip ptsNews = view.findViewById(R.id.pts_news);
        ptsNews.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
        vpNews = view.findViewById(R.id.vp_news);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLoading();
    }

}