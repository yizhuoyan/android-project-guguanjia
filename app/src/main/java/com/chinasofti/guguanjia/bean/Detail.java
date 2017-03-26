package com.chinasofti.guguanjia.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hshuai on 2017/3/8.
 */

public class Detail implements Serializable{
    private long id;
    /** 工单ID. */
    private Long workOrderId;

    /** 废物类型ID,大类ID. */
    private Long wasteTypeId;

    /** 废物ID,小类ID */
    private Long wasteId;

    /** 主要危害成份. */
    private String component;

    /** 重量,单位吨 */
    private Float weight;

    /** 形态,0=固态;1=粉尘;2=液态 */
    private String morphological;

    /** 包装方式,0=袋装;1=桶装;2散装 */
    private String packaging;

    /** 车牌号. */
    private String plateNumber;

    /** 创建时间,新增时设置. */
    private Date createDate;

    /** 修改时间,数据新增和修改时设置. */
    private Date updateDate;

    /** 删除标记(0：正常；1：删除；2：审核；) */
    private String delFlag;

    /** 创建用户,数据由谁创建. */
    private String createBy;

    private String workOrderCode;

    private String wasteTypeName;

    private String wasteCode;

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getWasteTypeId() {
        return wasteTypeId;
    }

    public void setWasteTypeId(Long wasteTypeId) {
        this.wasteTypeId = wasteTypeId;
    }

    public Long getWasteId() {
        return wasteId;
    }

    public void setWasteId(Long wasteId) {
        this.wasteId = wasteId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getMorphological() {
        return morphological;
    }

    public void setMorphological(String morphological) {
        this.morphological = morphological;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public String getWasteTypeName() {
        return wasteTypeName;
    }

    public void setWasteTypeName(String wasteTypeName) {
        this.wasteTypeName = wasteTypeName;
    }

    public String getWasteCode() {
        return wasteCode;
    }

    public void setWasteCode(String wasteCode) {
        this.wasteCode = wasteCode;
    }
}
