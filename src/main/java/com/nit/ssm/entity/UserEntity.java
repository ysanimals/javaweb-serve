package com.nit.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserEntity {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userPhone;
    private String userCard;
    private Integer userStatus;
    private Integer userType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public UserEntity() {
    }
    public UserEntity(Integer userId, String userName, String userPhone,String userCard,Integer userStatus,Integer userType,Date gmtCreate) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
        this.userType = userType;
        this.gmtCreate = gmtCreate;
    }
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserCard() {
        return userCard;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public Integer getUserType() {
        return userType;
    }


}
