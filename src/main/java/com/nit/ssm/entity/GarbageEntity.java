package com.nit.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GarbageEntity {
    private Integer garbageId;
    private String garbageName;
    private String imageUrl;
    private Integer sortId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public GarbageEntity() {
    }

    public GarbageEntity(Integer garbageId, String garbageName, String imageUrl, Integer sortId, Date gmtCreate) {
        this.garbageId = garbageId;
        this.garbageName = garbageName;
        this.imageUrl = imageUrl;
        this.sortId = sortId;
        this.gmtCreate = gmtCreate;
    }

    public Integer getGarbageId() {
        return garbageId;
    }

    public void setGarbageId(Integer garbageId) {
        this.garbageId = garbageId;
    }

    public String getGarbageName() {
        return garbageName;
    }

    public void setGarbageName(String garbageName) {
        this.garbageName = garbageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
