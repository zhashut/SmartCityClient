package com.zhashut.smartcityclient.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 轮播图
 */
public class Rotation implements Serializable {
    public String msg;

    public String code;

    @SerializedName("rows")
    public List<RotationList> rotationLists;

    public class RotationList implements Serializable {
        public int id;

        public String type;

        public String advImg;
    }
}
