package com.chinasofti.guguanjia.bean;

import java.util.ArrayList;

/**
 * 工单对象
 * Created by hshuai on 2017/2/20.
 */

public class ElecLedgerListBean extends BaseBean{
    private ArrayList<WorkOrdersBean> data;

    public ArrayList<WorkOrdersBean> getData() {
        return data;
    }

    public void setData(ArrayList<WorkOrdersBean> data) {
        this.data = data;
    }


}
