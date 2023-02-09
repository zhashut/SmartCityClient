package com.zhashut.smartcityclient.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.utils.ImageUtil;

import static com.zhashut.smartcityclient.common.RequestUrl.REQUEST_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SliderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SliderFragment extends Fragment {

    private int position;
    private String imgPath;
    private int count;

    public static SliderFragment newInstance(int position, String imgPath, int count) {
        SliderFragment fragment = new SliderFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("count", count);
        args.putString("img_path", imgPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            count = getArguments().getInt("count");
            imgPath = getArguments().getString("img_path");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ImageView ivSlider = view.findViewById(R.id.iv_slider);
        ImageUtil.UploadImage(getContext(), ivSlider, REQUEST_URL + imgPath, 0, 0);
        RadioGroup rgSlider = view.findViewById(R.id.rg_slider);
        for (int i = 0; i < count; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            rb.setPadding(10, 10, 10, 10);
            rgSlider.addView(rb);
        }
        ((RadioButton) rgSlider.getChildAt(position)).setChecked(true);
        return view;
    }
}