package com.nit.ssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
public class ExamEntity {
    private Integer examId;
    private String examSn;
    private Integer garbageId;
    private Integer userId;
    private Integer sortId;
    private Integer answerId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;

    public ExamEntity(Integer examId ,String examSn ,Integer garbageId ,Integer userId ,Integer sortId ,Integer answerId ,Date gmtCreate){
        this.examId = examId;
        this.examSn = examSn;
        this.garbageId = garbageId;
        this.userId = userId;
        this.sortId = sortId;
        this.answerId = answerId;
        this.gmtCreate = gmtCreate;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
