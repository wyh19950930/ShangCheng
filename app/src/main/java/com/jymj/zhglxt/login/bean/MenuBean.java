package com.jymj.zhglxt.login.bean;

public class MenuBean {

    /**
     * menuId : 111
     * parentId : 5
     * parentName : 人口流动app
     * name : 流动人口管理app
     * url : 999
     * perms : null
     * type : 1
     * icon : 999
     * orderNum : 1
     * ident : 0
     * open : null
     * list : null
     * summary : null
     * mold : 1
     * createUserId : null
     */

    private int menuId;
    private int parentId;
    private String parentName;
    private String name;
    private String url;
    private Object perms;
    private int type;
    private String icon;
    private int orderNum;
    private int ident;
    private Object open;
    private Object list;
    private Object summary;
    private int mold;
    private Object createUserId;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName==null?"":parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getPerms() {
        return perms;
    }

    public void setPerms(Object perms) {
        this.perms = perms;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Object getOpen() {
        return open;
    }

    public void setOpen(Object open) {
        this.open = open;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public int getMold() {
        return mold;
    }

    public void setMold(int mold) {
        this.mold = mold;
    }

    public Object getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Object createUserId) {
        this.createUserId = createUserId;
    }
}
