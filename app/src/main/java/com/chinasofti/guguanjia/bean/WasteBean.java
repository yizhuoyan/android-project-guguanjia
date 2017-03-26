package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

public class WasteBean implements  Serializable{
	private long id;
	private String name;
	private long updateDate;
	private String code;
	private String hazardousCharacteristics;
	private String delFlag;
	private long parentId;
	private long createDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHazardousCharacteristics() {
		return hazardousCharacteristics;
	}

	public void setHazardousCharacteristics(String hazardousCharacteristics) {
		this.hazardousCharacteristics = hazardousCharacteristics;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

}
