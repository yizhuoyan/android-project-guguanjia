package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

/**
 * 所有bean的基类
 * Created by hshuai on 2017/2/20.
 */

public class BaseBean implements Serializable{
    /**
     * 请求状态码
     */
    private int status;
    /**
     * 请求是否成功
     */
    private boolean success;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
