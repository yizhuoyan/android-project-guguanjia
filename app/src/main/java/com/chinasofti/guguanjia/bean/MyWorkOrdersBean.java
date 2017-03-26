package com.chinasofti.guguanjia.bean;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/3/6.
 */

public class MyWorkOrdersBean extends  BaseBean{
    private WorkOrdersList data;

      public class WorkOrdersList{

          private ArrayList<WorkOrdersBean> handlerMe;

          private ArrayList<WorkOrdersBean> startMe;

          public ArrayList<WorkOrdersBean> getHandlerMe() {
              return handlerMe;
          }

          public void setHandlerMe(ArrayList<WorkOrdersBean> handlerMe) {
              this.handlerMe = handlerMe;
          }

          public ArrayList<WorkOrdersBean> getStartMe() {
              return startMe;
          }

          public void setStartMe(ArrayList<WorkOrdersBean> startMe) {
              this.startMe = startMe;
          }
      }


    public WorkOrdersList getData() {
        return data;
    }

    public void setData(WorkOrdersList data) {
        this.data = data;
    }
}
