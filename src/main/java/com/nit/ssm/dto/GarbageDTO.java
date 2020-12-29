package com.nit.ssm.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class GarbageDTO {
    private Integer key;
    private Integer garbageId;
    private String garbageName;
    private String imageUrl;
    private Integer sortId;
    private String sortName;
    private String sortInfo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public GarbageDTO() {
    }

    public GarbageDTO(Integer key, Integer garbageId, String garbageName, String imageUrl, Integer sortId, String sortName, String sortInfo, Date gmtCreate) {
        this.key = key;
        this.garbageId = garbageId;
        this.garbageName = garbageName;
        this.imageUrl = imageUrl;
        this.sortId = sortId;
        this.sortName = sortName;
        this.sortInfo = sortInfo;
        this.gmtCreate = gmtCreate;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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
