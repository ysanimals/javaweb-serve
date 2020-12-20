package com.nit.ssm.dto;

public class OpResultDTO {

    private Integer intResult;
    private Object objResult;

    public OpResultDTO() {
    }

    public OpResultDTO(Integer intResult, Object objResult) {
        this.intResult = intResult;
        this.objResult = objResult;
    }

    public Integer getIntResult() {
        return intResult;
    }

    public void setIntResult(Integer intResult) {
        this.intResult = intResult;
    }

    public Object getObjResult() {
        return objResult;
    }

    public void setObjResult(Object objResult) {
        this.objResult = objResult;
    }
}
