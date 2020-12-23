package com.nit.ssm.dto;

import java.util.List;
//持久化，与数据库对应；
//
//实体bean，一般是用于ORM对象关系映射，一个实体映射成一张表，一般无业务逻辑代码。
public class TableRspDTO {
    private Integer total;
    private List listTable;

    public TableRspDTO() {
    }

    public TableRspDTO(Integer total, List listTable) {
        this.total = total;
        this.listTable = listTable;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getListTable() {
        return listTable;
    }

    public void setListTable(List listTable) {
        this.listTable = listTable;
    }
}
