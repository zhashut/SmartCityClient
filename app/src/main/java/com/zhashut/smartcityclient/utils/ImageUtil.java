package com.zhashut.smartcityclient.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.PropertyResourceBundle;

public class ImageUtil {

    public static void UploadImage(Context context, ImageView imgView, String imgpath, int width, int height) {
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(
                imgpath,
                imgView::setImageBitmap,
                width, height,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                error -> {
                }
        );
        queue.add(imageRequest);
    }

}
