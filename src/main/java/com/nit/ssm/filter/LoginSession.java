package com.nit.ssm.filter;

public class LoginSession {
    private String userPhone;
    private String userPwd;

    public LoginSession() {
    }

    public LoginSession(String userPhone, String userPwd) {
        this.userPhone = userPhone;
        this.userPwd = userPwd;
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

    @Override
    public String toString() {
        return "LoginSession{" +
                "userPhone='" + userPhone + '\'' +
                '}';
    }
}
