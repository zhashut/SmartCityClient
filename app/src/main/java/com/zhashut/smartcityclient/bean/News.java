package com.zhashut.smartcityclient.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class News implements Serializable {

    public String msg;

    public String code;

    @SerializedName("rows")
    public List<NewsList> newsLists;

    public class NewsList implements Serializable {
        public int id;

        public String createTime;

        public String cover;

        public String title;

        public String content;

        public String hot;

        public String type;

        public int commentNum;

        public int likeNum;

        public int readNum;

    }
}
