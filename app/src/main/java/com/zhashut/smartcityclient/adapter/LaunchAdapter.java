package com.zhashut.smartcityclient.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhashut.smartcityclient.fragment.LaunchFragment;


public class LaunchAdapter extends FragmentPagerAdapter {
    private int[] images;

    public LaunchAdapter(FragmentManager fm, int[] images) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return LaunchFragment.newInstance(position, images[position], images.length);
    }

    @Override
    public int getCount() {
        return images.length;
    }
}
