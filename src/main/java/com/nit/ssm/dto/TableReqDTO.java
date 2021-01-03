package com.nit.ssm.dto;

import com.alibaba.fastjson.JSONObject;

public class TableReqDTO {
    private Integer pageNo;
    private Integer pageSize;
    private String sortField;
    private String sortOrder;
    private String queryParam;

    public TableReqDTO() {
    }

    public TableReqDTO(Integer pageNo, Integer pageSize, String sortField, String sortOrder, String queryParam) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
        this.queryParam = queryParam;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField == null ? null : CamelCaseToUnderline(sortField);
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder == null ? "DESC" : sortOrder;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }

    // 根据pageNo和pageSize计算起始条数用户表格查询
    public Integer getStart() {
        return (this.pageNo - 1) * this.pageSize;
    }

    // 解析查询字符串 获取想查询字符串的键值
    public String parseQueryParam(String paramName) {
        String queryValue = null;
        if (queryParam != null && !queryParam.equals("{}")) {
            JSONObject jsonObject = JSONObject.parseObject(queryParam);
            queryValue = jsonObject.getString(paramName);
        }
        return queryValue;
    }

    // 驼峰命名转下划线命名
    public String CamelCaseToUnderline(String paramName) {
        StringBuilder sb = new StringBuilder(paramName);
        int temp = 0;
        if (!paramName.contains("_")) {
            for (int i = 0; i < paramName.length(); i++) {
                if (Character.isUpperCase(paramName.charAt(i))) {
                    sb.insert(i + temp, "_");
                    temp += 1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
