package com.nit.ssm.entity;
//用途： 实体层，用于存放我们的实体类，与数据库中的属性值基本保持一致，实现set和get的方法。
//数据传输层

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SortEntity {
    private Integer sortId;
    private String sortName;
    private String sortInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    public SortEntity() {
    }

    public SortEntity(Integer sortId, String sortName, String sortInfo, Date gmtCreate) {
        this.sortId = sortId;
        this.sortName = sortName;
        this.sortInfo = sortInfo;
        this.gmtCreate = gmtCreate;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortInfo() {
        return sortInfo;
    }

    public void setSortInfo(String sortInfo) {
        this.sortInfo = sortInfo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}

