package com.nit.ssm.dto.system;


public class TokenDTO {

    private Integer accountId;
    private Integer accountType;

    public TokenDTO() {
    }

    public TokenDTO(Integer accountId, Integer accountType) {
        this.accountId = accountId;
        this.accountType = accountType;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "{" +
                "accountId:" + accountId +
                ", accountType:" + accountType +
                '}';
    }
}
