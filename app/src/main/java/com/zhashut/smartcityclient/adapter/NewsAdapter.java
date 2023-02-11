package com.zhashut.smartcityclient.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhashut.smartcityclient.bean.News;
import com.zhashut.smartcityclient.bean.PressCategory;
import com.zhashut.smartcityclient.fragment.NewsChildFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsAdapter extends FragmentPagerAdapter {

    private List<PressCategory.PressList> pressLists;

    public NewsAdapter(@NonNull @NotNull FragmentManager fm, List<PressCategory.PressList> pressLists) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pressLists = pressLists;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        int id = pressLists.get(position).id;
        String idStr = String.valueOf(id);
        return NewsChildFragment.newInstance(idStr);
    }

    @Override
    public int getCount() {
        return pressLists.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pressLists.get(position).name;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

    }
}
