package com.nit.ssm.dto;

public class TokenDTO {
    private Integer userId;
    private String userName;
    private Integer roleId;

    public TokenDTO(Integer userId, String userName, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.roleId = roleId;
    }

    public TokenDTO() {
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
}
