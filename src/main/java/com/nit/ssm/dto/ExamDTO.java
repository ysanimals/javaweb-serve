package com.nit.ssm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExamDTO {
    private Integer key;
    private Integer examId;
    private String examSn;
    private Integer garbageId;
    private String garbageName;
    private String imageUrl;
    private Integer userId;
    private String userName;
    private Integer sortId;
    private Integer answerId;
    // 0未答1正确2错误
    private Integer answerState;
    private Boolean res;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public ExamDTO() {
    }

    public ExamDTO(Integer key, Integer examId, String examSn, Integer garbageId, String garbageName, String imageUrl, Integer userId, String userName, Integer sortId, Integer answerId, Integer answerState, Boolean res, Date gmtCreate) {
        this.key = key;
        this.examId = examId;
        this.examSn = examSn;
        this.garbageId = garbageId;
        this.garbageName = garbageName;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.userName = userName;
        this.sortId = sortId;
        this.answerId = answerId;
        this.answerState = answerState;
        this.res = res;
        this.gmtCreate = gmtCreate;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamSn() {
        return examSn;
    }

    public void setExamSn(String examSn) {
        this.examSn = examSn;
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

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getAnswerState() {
        return answerState;
    }

    public void setAnswerState(Integer answerState) {
        this.answerState = answerState;
    }

    public Boolean getRes() {
        return res;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
