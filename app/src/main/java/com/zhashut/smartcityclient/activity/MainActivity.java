package com.zhashut.smartcityclient.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.fragment.AllServiceFragment;
import com.zhashut.smartcityclient.fragment.DataFragment;
import com.zhashut.smartcityclient.fragment.HomeFragment;
import com.zhashut.smartcityclient.fragment.MyCenterFragment;
import com.zhashut.smartcityclient.fragment.NewsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layoutBottom;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private int childCount;
    private LinearLayout[] linearLayouts;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutBottom = findViewById(R.id.layout_bottom);
        manager = getSupportFragmentManager();
        initFragment();
        initBottomLayout();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new AllServiceFragment());
        fragments.add(new DataFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MyCenterFragment());
        manager.beginTransaction()
                .add(R.id.layout_container, fragments.get(0))
                .add(R.id.layout_container, fragments.get(1))
                .add(R.id.layout_container, fragments.get(2))
                .add(R.id.layout_container, fragments.get(3))
                .add(R.id.layout_container, fragments.get(4))
                .commit();
    }

    private void initBottomLayout() {
        childCount = layoutBottom.getChildCount();
        linearLayouts = new LinearLayout[childCount];
        for (int i = 0; i < childCount; i++) {
            linearLayouts[i] = (LinearLayout) layoutBottom.getChildAt(i);
            linearLayouts[i].setTag(i);
            linearLayouts[i].setOnClickListener(v -> {
                int position = (int) v.getTag();
                setSelectedState(position);
            });
        }
        linearLayouts[0].setSelected(true);
    }

    private void setSelectedState(int position) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        for (int i = 0; i < childCount; i++) {
            linearLayouts[i].setSelected(i == position);
            fragmentTransaction.hide(fragments.get(i));
        }
        fragmentTransaction.show(fragments.get(position)).commit();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}