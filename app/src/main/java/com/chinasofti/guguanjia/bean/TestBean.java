package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

/**
 * Created by hshuai on 2017/3/1.
 * {"resultcode":"105","reason":"应用未审核超时，请提交认证","result":null,"error_code":10005}
 */

public class TestBean implements Serializable {
    private String resultcode;
    private String reason;
    private String result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                ", error_code=" + error_code +
                '}';
    }
}
