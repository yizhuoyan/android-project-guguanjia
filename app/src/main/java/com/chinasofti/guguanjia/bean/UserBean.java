package com.chinasofti.guguanjia.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/6.
 */

public class UserBean extends BaseBean {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public class User implements Serializable{
        //ID
        private Long id;
        //用户名
        private String username;
        //姓名
        private String name;
        //手机
        private String phone;
        //用户头像
        private String headPicture;
        //公司ID
        private Long companyId;
        //公司名称
        private String companyName;
        //用户类型
        private Integer userType;
        //创建时间
        private Long createDate;
        //修改时间
        private Long updateDate;
        //产出标识
        private Boolean isOutput;
        //运输标识
        private Boolean isTransport;
        //处理标识
        private Boolean isDispose;
        //在途工单标识
        private Boolean hasIntransit;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

        public Long getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Long companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Integer getUserType() {
            return userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public Long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Long createDate) {
            this.createDate = createDate;
        }

        public Long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Long updateDate) {
            this.updateDate = updateDate;
        }

        public Boolean getIsOutput() {
            return isOutput;
        }

        public void setIsOutput(Boolean isOutput) {
            isOutput = isOutput;
        }

        public Boolean getIsTransport() {
            return isTransport;
        }

        public void setIsTransport(Boolean IsTransport) {
            isTransport = IsTransport;
        }

        public Boolean getIsDispose() {
            return isDispose;
        }

        public void setIsDispose(Boolean IsDispose) {
            isDispose = IsDispose;
        }

        public Boolean getHasIntransit() {
            return hasIntransit;
        }

        public void setHasIntransit(Boolean hasIntransit) {
            this.hasIntransit = hasIntransit;
        }

        @Override
        public String toString() {
            return "User{" +
                    "Id=" + id +
                    ", username='" + username + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", headPicture='" + headPicture + '\'' +
                    ", companyId=" + companyId +
                    ", companyName='" + companyName + '\'' +
                    ", userType=" + userType +
                    ", createDate=" + createDate +
                    ", updateDate=" + updateDate +
                    ", isOutput=" + isOutput +
                    ", isTransport=" + isTransport +
                    ", isDispose=" + isDispose +
                    ", hasIntransit=" + hasIntransit +
                    '}';
        }
    }
}
