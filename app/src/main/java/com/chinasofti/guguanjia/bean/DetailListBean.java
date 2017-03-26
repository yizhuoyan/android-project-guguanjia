package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

/**
 * Created by hshuai on 2017/2/22.
 */

public class DetailListBean implements Serializable{
    private String code;
    private String baozhuang;
    private String weight;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBaozhuang() {
        return baozhuang;
    }

    public void setBaozhuang(String baozhuang) {
        this.baozhuang = baozhuang;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
