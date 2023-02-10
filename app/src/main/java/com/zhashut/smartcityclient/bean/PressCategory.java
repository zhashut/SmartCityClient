package com.zhashut.smartcityclient.bean;

// 新闻分类

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PressCategory implements Serializable {

    public String code;

    public String msg;

    @SerializedName("data")
    public List<PressList> pressLists;

    public class PressList implements Serializable {
        public int id;

        public String name;
    }

}
