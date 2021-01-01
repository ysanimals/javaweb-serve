package com.nit.ssm.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @date: 2020/12/27
 */
public class MenuDTO {
    private Integer menuId;
    private String menuCode;
    private String menuName;
    private Integer fatherId;
    private Integer levelType;
    private Integer menuStatus;
    private String menuIcon;
    private Integer menuSort;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtCreate;
    private List<MenuDTO> children;

    public MenuDTO() {
    }

    public MenuDTO(Integer menuId, String menuCode, String menuName, Integer fatherId, Integer levelType, Integer menuStatus, String menuIcon, Integer menuSort, Date gmtCreate, List<MenuDTO> children) {
        this.menuId = menuId;
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.fatherId = fatherId;
        this.levelType = levelType;
        this.menuStatus = menuStatus;
        this.menuIcon = menuIcon;
        this.menuSort = menuSort;
        this.gmtCreate = gmtCreate;
        this.children = children;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }
}
