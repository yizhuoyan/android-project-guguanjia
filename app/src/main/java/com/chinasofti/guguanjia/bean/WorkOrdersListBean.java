package com.chinasofti.guguanjia.bean;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/3/6.
 */

public class WorkOrdersListBean extends  BaseBean{
    private ArrayList<WorkOrdersBean> data;

    public ArrayList<WorkOrdersBean> getData() {
        return data;
    }

    public void setData(ArrayList<WorkOrdersBean> data) {
        this.data = data;
    }
}
