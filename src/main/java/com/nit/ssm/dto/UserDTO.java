package com.nit.ssm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserDTO {
    private Integer key;
    private Integer userId;
    private String userName;
    private Integer roleId;
    private String userPhone;
    private String userPwd;
    private String userCard;
    private Integer userType;
    private Integer total;
    private Integer right;
    private Integer wrong;
    private Integer noAnswer;
    private Double accuracy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    public UserDTO() {
    }

    public UserDTO(Integer key, Integer userId, String userName, Integer roleId, String userPhone, String userPwd, String userCard, Integer userType, Integer total, Integer right, Integer wrong, Integer noAnswer, Double accuracy, Date gmtCreate) {
        this.key = key;
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleId;
        this.userPhone = userPhone;
        this.userPwd = userPwd;
        this.userCard = userCard;
        this.userType = userType;
        this.total = total;
        this.right = right;
        this.wrong = wrong;
        this.noAnswer = noAnswer;
        this.accuracy = accuracy;
        this.gmtCreate = gmtCreate;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
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

    public Integer getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Integer noAnswer) {
        this.noAnswer = noAnswer;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
