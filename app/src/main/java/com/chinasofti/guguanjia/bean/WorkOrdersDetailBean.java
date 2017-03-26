package com.chinasofti.guguanjia.bean;

import java.util.ArrayList;

/**
 * Created by hshuai on 2017/3/6.
 */

public class WorkOrdersDetailBean extends BaseBean {
   private WorkOrderDetail data;

    public WorkOrderDetail getData() {
        return data;
    }

    public void setData(WorkOrderDetail data) {
        this.data = data;
    }

    public class WorkOrderDetail{
       /**
        * 工单id
        */
       private long id;
       /**
        * 工单编号
        */
       private String code;
       /**
        * 创建人id
        */
       private long createUserId;
       /**
        * 运输人id
        */
       private long transportUserId;
       /**
        * 接收人id
        */
       private long recipientUserId;
       /**
        * 0：待运输 1：运输中 2：验收
        */
       private int status;
       /**
        * 创建人
        */
       private String createBy;
       /**
        * 创建时间，json返回时间戳，需将时间戳转换为String类型的时间
        */
       private long createDate;
       /**
        * 更新时间
        */
       private long updateDate;

       /**
        * 删除标记
        */
       private String delFlag;
       /**
        * 转移记录
        */
       private ArrayList<TransferRecords> transferRecords;

       public long getId() {
           return id;
       }

       public void setId(long id) {
           this.id = id;
       }

       public String getCode() {
           return code;
       }

       public void setCode(String code) {
           this.code = code;
       }

       public long getCreateUserId() {
           return createUserId;
       }

       public void setCreateUserId(long createUserId) {
           this.createUserId = createUserId;
       }

       public long getTransportUserId() {
           return transportUserId;
       }

       public void setTransportUserId(long transportUserId) {
           this.transportUserId = transportUserId;
       }

       public long getRecipientUserId() {
           return recipientUserId;
       }

       public void setRecipientUserId(long recipientUserId) {
           this.recipientUserId = recipientUserId;
       }

       public int getStatus() {
           return status;
       }

       public void setStatus(int status) {
           this.status = status;
       }

       public String getCreateBy() {
           return createBy;
       }

       public void setCreateBy(String createBy) {
           this.createBy = createBy;
       }

       public long getCreateDate() {
           return createDate;
       }

       public void setCreateDate(long createDate) {
           this.createDate = createDate;
       }

       public long getUpdateDate() {
           return updateDate;
       }

       public void setUpdateDate(long updateDate) {
           this.updateDate = updateDate;
       }

       public String getDelFlag() {
           return delFlag;
       }

       public void setDelFlag(String delFlag) {
           this.delFlag = delFlag;
       }

       public ArrayList<TransferRecords> getTransferRecords() {
           return transferRecords;
       }

       public void setTransferRecords(ArrayList<TransferRecords> transferRecords) {
           this.transferRecords = transferRecords;
       }
   }
}
