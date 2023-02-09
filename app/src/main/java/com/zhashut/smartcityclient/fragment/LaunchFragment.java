package com.zhashut.smartcityclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.zhashut.smartcityclient.R;
import com.zhashut.smartcityclient.activity.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LaunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LaunchFragment extends Fragment {

    private int position;
    private int imageId;
    private int count;

    public static LaunchFragment newInstance(int position, int imageId, int count) {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("image_id", imageId);
        args.putInt("count", count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
            imageId = getArguments().getInt("image_id");
            count = getArguments().getInt("count");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_launch, container, false);
        ImageView iv_launch = view.findViewById(R.id.iv_launch);
        RadioGroup rg_launch = view.findViewById(R.id.rg_launch);
        Button btn_launch = view.findViewById(R.id.btn_launch);
        iv_launch.setImageResource(imageId);
        for (int i = 0; i < count; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            rb.setPadding(10, 10, 10, 10);
            rg_launch.addView(rb);
        }
        ((RadioButton) rg_launch.getChildAt(position)).setChecked(true);
        if (position == count - 1) {
            btn_launch.setVisibility(View.VISIBLE);
            btn_launch.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                getActivity().startActivity(intent);
            });
        }

        return view;
    }
}