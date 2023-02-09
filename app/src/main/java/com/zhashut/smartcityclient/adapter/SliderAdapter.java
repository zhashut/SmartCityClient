package com.zhashut.smartcityclient.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhashut.smartcityclient.bean.Rotation;
import com.zhashut.smartcityclient.fragment.SliderFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SliderAdapter extends FragmentPagerAdapter {

    private List<Rotation.RotationList> rotationLists;

    public SliderAdapter(@NonNull @NotNull FragmentManager fm, List<Rotation.RotationList> rotationLists) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.rotationLists = rotationLists;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return SliderFragment.newInstance(position, rotationLists.get(position).advImg, rotationLists.size());
    }

    @Override
    public int getCount() {
        return rotationLists.size();
    }
}
