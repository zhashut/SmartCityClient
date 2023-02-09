package com.zhashut.smartcityclient.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhashut.smartcityclient.R;


/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/18
 * Time: 16:54
 * Description: No Description
 */
public class AnimationUtil extends AppCompatActivity {

    // 加载动画
    public static void LoadingAnim(Context context, ImageView iv_rotate, TextView tv_wait) {
        android.view.animation.Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate);
        iv_rotate.setVisibility(View.VISIBLE);
        tv_wait.setVisibility(View.VISIBLE);
        iv_rotate.startAnimation(animation);
    }

    // 停止动画
    public static void StopAnim(Context context, ImageView iv_rotate, TextView tv_wait) {
        iv_rotate.clearAnimation();
        iv_rotate.setVisibility(View.GONE);
        tv_wait.setVisibility(View.GONE);
    }

}
