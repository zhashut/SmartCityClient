package com.zhashut.smartcityclient.common;

import com.google.gson.annotations.SerializedName;

public class ResultEntity {
    public String code;

    @SerializedName("msg")
    public String message;
}
