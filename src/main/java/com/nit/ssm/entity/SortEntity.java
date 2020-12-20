package com.nit.ssm.entity;

public class SortEntity {
    private Integer sortId;
    private String sortName;
    private String sortInfo;

    public SortEntity() {
    }

    public SortEntity(Integer sortId, String sortName, String sortInfo) {
        this.sortId = sortId;
        this.sortName = sortName;
        this.sortInfo = sortInfo;
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
}

