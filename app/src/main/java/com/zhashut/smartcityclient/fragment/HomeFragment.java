package com.zhashut.smartcityclient.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.activity.LoginActivity;
import com.zhashut.smartcityclient.activity.SearchNewsActivity;
import com.zhashut.smartcityclient.adapter.SliderAdapter;
import com.zhashut.smartcityclient.bean.News;
import com.zhashut.smartcityclient.bean.Rotation;
import com.zhashut.smartcityclient.bean.Service;
import com.zhashut.smartcityclient.utils.HttpUtil;
import com.zhashut.smartcityclient.utils.ImageUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.PropertyResourceBundle;

import static com.zhashut.smartcityclient.common.RequestUrl.NEWS_LIST;
import static com.zhashut.smartcityclient.common.RequestUrl.REQUEST_URL;
import static com.zhashut.smartcityclient.common.RequestUrl.ROTATION_URL;
import static com.zhashut.smartcityclient.common.RequestUrl.SERVICE_URL;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements TextView.OnEditorActionListener {
    private final String TYPE = "title";
    private View view;
    private int childCount;
    private ImageView iv_icon;
    private TextView tv_name;
    private FragmentManager fm;
    HttpUtil httpUtil = new HttpUtil();

    // TODO 查询新闻列表回调
    private Handler newsHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            News news = (News) msg.obj;
            if (news.code.equals("200")) {
                searchNewsSuccess(news);
            }
        }
    };

    // 查询轮播图回调
    private Handler rotationHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Rotation rotation = (Rotation) msg.obj;
            rotationSuccess(rotation.rotationLists);
        }
    };

    // 查询所有服务回调
    private Handler serviceHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Service service = (Service) msg.obj;
            initViewGridLayout(service.serviceLists);
        }
    };
    private EditText etSearch;

    // 首页轮播图加载成功
    private void rotationSuccess(List<Rotation.RotationList> rotationLists) {
        ViewPager pagerSlider = view.findViewById(R.id.pager_slider);
        SliderAdapter sliderAdapter = new SliderAdapter(getChildFragmentManager(), rotationLists);
        pagerSlider.setAdapter(sliderAdapter);
    }

    //  查询新闻列表成功跳转列表页
    private void searchNewsSuccess(News news) {
        Intent intent = new Intent(getActivity(), SearchNewsActivity.class);
        intent.putExtra("news", news);
        etSearch.setText("");
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        etSearch = view.findViewById(R.id.et_search);
        etSearch.setOnEditorActionListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        initRequestLoading(); // 加载首页资源
    }

    private void initRequestLoading() {
        // 加载轮播图
        httpUtil.CommonList(ROTATION_URL, rotationHandler, Rotation.class);
        // 查询所有服务
        httpUtil.CommonList(SERVICE_URL, serviceHandler, Service.class);
    }


    //  TODO 初始化所有服务
    private void initViewGridLayout(List<Service.ServiceList> serviceLists) {
        GridLayout glService = view.findViewById(R.id.gl_service);
        childCount = glService.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Service.ServiceList serviceList = serviceLists.get(i);
            iv_icon = glService.getChildAt(i).findViewById(R.id.iv_icon);
            tv_name = glService.getChildAt(i).findViewById(R.id.tv_name);
            if (i == childCount - 1) {
                iv_icon.setImageResource(R.drawable.more);
                tv_name.setText("更多");
                continue;
            }
            ImageUtil.UploadImage(getContext(), iv_icon, REQUEST_URL + serviceList.imgUrl, 0, 0);
            tv_name.setText(serviceList.serviceName);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            String keyWord = v.getText().toString().trim();
            if (TextUtils.isEmpty(keyWord)) {
                Toast.makeText(getContext(), "内容不得为空", Toast.LENGTH_SHORT).show();
                return false;
            }
            httpUtil.NewsListWithKeyword(NEWS_LIST, TYPE, keyWord, newsHandler, News.class);
        }
        return true;
    }
}