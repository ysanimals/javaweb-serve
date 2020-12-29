package com.nit.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GarbageEntity {
    private Integer garbageId;
    private String garbageFlag;
    private String garbageName;
    private String imageUrl;
    private String originalName;
    private Integer sortId;
    private Integer total;
    private Integer right;
    private Integer wrong;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public GarbageEntity() {
    }

    public GarbageEntity(Integer garbageId, String garbageFlag, String garbageName, String imageUrl, String originalName, Integer sortId, Integer total, Integer right, Integer wrong, Date gmtCreate) {
        this.garbageId = garbageId;
        this.garbageFlag = garbageFlag;
        this.garbageName = garbageName;
        this.imageUrl = imageUrl;
        this.originalName = originalName;
        this.sortId = sortId;
        this.total = total;
        this.right = right;
        this.wrong = wrong;
        this.gmtCreate = gmtCreate;
    }

    public Integer getGarbageId() {
        return garbageId;
    }

    public void setGarbageId(Integer garbageId) {
        this.garbageId = garbageId;
    }

    public String getGarbageFlag() {
        return garbageFlag;
    }

    public void setGarbageFlag(String garbageFlag) {
        this.garbageFlag = garbageFlag;
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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
