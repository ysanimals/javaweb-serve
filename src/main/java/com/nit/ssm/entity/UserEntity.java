package com.nit.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserEntity {
    private Integer userId;
    private String userName;
    private Integer roleId;
    private String userPwd;
    private String userPhone;
    private String userCard;
    private Integer userStatus;
    private Integer userType;
    private Integer total;
    private Integer right;
    private Integer wrong;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public UserEntity() {
    }

    public UserEntity(Integer userId, String userName, Integer roleId, String userPwd, String userPhone, String userCard, Integer userStatus, Integer userType, Integer total, Integer right, Integer wrong, Date gmtCreate) {
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleId;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userCard = userCard;
        this.userStatus = userStatus;
        this.userType = userType;
        this.total = total;
        this.right = right;
        this.wrong = wrong;
        this.gmtCreate = gmtCreate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
