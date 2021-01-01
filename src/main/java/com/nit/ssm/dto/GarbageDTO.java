package com.nit.ssm.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class GarbageDTO {
    private Integer key;
    private Integer garbageId;
    private String garbageName;
    private String imageUrl;
    private Integer sortId;
    private String garbageFlag;
    private String sortName;
    private String sortInfo;
    private Integer total;
    private Integer right;
    private Integer wrong;
    private Integer noAnswer;
    private Double accuracy;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public GarbageDTO() {
    }

    public GarbageDTO(Integer key, Integer garbageId, String garbageName, String imageUrl, Integer sortId, String garbageFlag, String sortName, String sortInfo, Integer total, Integer right, Integer wrong, Integer noAnswer, Double accuracy, Date gmtCreate) {
        this.key = key;
        this.garbageId = garbageId;
        this.garbageName = garbageName;
        this.imageUrl = imageUrl;
        this.sortId = sortId;
        this.garbageFlag = garbageFlag;
        this.sortName = sortName;
        this.sortInfo = sortInfo;
        this.total = total;
        this.right = right;
        this.wrong = wrong;
        this.noAnswer = noAnswer;
        this.accuracy = accuracy;
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

    public String getGarbageFlag() {
        return garbageFlag;
    }

    public void setGarbageFlag(String garbageFlag) {
        this.garbageFlag = garbageFlag;
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

    public Integer getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Integer noAnswer) {
        this.noAnswer = noAnswer;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
