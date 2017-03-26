package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

/**
 * 工单
 * Created by hshuai on 2017/3/6.
 */

public class WorkOrdersBean implements Serializable {
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
     * 公司id
     */
    private long companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司LOGO地址
     */
    private String companyLogo;
    /**
     * 删除标记
     */
    private String delFlag;

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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCreateBy() {
        return createBy;
    }


    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "WorkOrdersBean{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", createUserId=" + createUserId +
                ", transportUserId=" + transportUserId +
                ", recipientUserId=" + recipientUserId +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
