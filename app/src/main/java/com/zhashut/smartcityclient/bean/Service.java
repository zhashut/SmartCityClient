package com.zhashut.smartcityclient.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable {
    public String msg;

    public String code;

    @SerializedName("rows")
    public List<ServiceList> serviceLists;

    public class ServiceList implements Serializable {
        public int id;

        public String serviceName;

        public String link;

        public String imgUrl;

    }
}
