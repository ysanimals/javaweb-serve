package com.nit.ssm.dto;


import java.util.List;

public class PagingDTO {
    private Integer pageNo;
    private Integer pageSize;
    private Long totalCount;
    private Integer totalPage;
    private List<?> data;

    public PagingDTO() {
    }

    public PagingDTO(Integer pageNo, Integer pageSize, Long totalCount, List<?> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = new Double(Math.ceil(totalCount * 1.0 / pageSize)).intValue();
        this.data = data;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
