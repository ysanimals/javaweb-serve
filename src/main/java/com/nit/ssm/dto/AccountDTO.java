package com.nit.ssm.dto;


import java.util.List;

/**
 * @date: 2020/12/27
 */
public class AccountDTO {
    private Integer accountId;
    private String userName;
    private Integer roleId;
    private String token;
    private List<MenuDTO> roles;

    public AccountDTO() {
    }

    public AccountDTO(Integer accountId, String userName, Integer roleId, String token, List<MenuDTO> roles) {
        this.accountId = accountId;
        this.userName = userName;
        this.roleId = roleId;
        this.token = token;
        this.roles = roles;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<MenuDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<MenuDTO> roles) {
        this.roles = roles;
    }
}
