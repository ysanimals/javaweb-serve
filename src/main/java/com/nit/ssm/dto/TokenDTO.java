package com.nit.ssm.dto;

public class TokenDTO {
    private Integer userId;
    private String userName;

    public TokenDTO() {
    }

    public TokenDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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

    @Override
    public String toString() {
        return "{" +
                "\"userId\":" + userId +
                ",\"userName\":\"" + userName + '\"' +
                '}';
    }

    public static void main(String[] args) {
        TokenDTO tokenDTO = new TokenDTO(1, "张三");
        System.out.println(tokenDTO.toString());
    }

}
