package com.zhashut.smartcityclient.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.adapter.NewsListAdapter;
import com.zhashut.smartcityclient.bean.News;
import com.zhashut.smartcityclient.utils.HttpUtil;

import java.util.List;

import static com.zhashut.smartcityclient.common.RequestUrl.NEWS_LIST;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsChildFragment extends Fragment {
    private final String TYPE = "type";
    private String id;
    private ListView lvNews;
    HttpUtil httpUtil = new HttpUtil();

    private Handler newsHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            News news = (News) msg.obj;
            searchNewsSuccess(news.newsLists);
        }
    };

    // 查询新闻列表成功
    private void searchNewsSuccess(List<News.NewsList> newsLists) {
        NewsListAdapter newsListAdapter = new NewsListAdapter(getContext(), newsLists);
        lvNews.setAdapter(newsListAdapter);
    }

    public static NewsChildFragment newInstance(String id) {
        NewsChildFragment fragment = new NewsChildFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
        initLoading();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_child, container, false);
        lvNews = view.findViewById(R.id.lv_news);
        return view;
    }

    // 加载网络请求
    private void initLoading() {
        httpUtil.NewsListWithKeyword(NEWS_LIST, TYPE, id, newsHandler, News.class);
    }
}