package com.nit.ssm.dto;

import java.util.List;

public class TableRspDTO {

    private String message;
    private PagingDTO result;
    private Integer status;
    private Long timestamp;

    public TableRspDTO() {
    }

    public TableRspDTO(PagingDTO result) {
        this.message = "success";
        this.result = result;
        this.status = 200;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PagingDTO getResult() {
        return result;
    }

    public void setResult(PagingDTO result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
