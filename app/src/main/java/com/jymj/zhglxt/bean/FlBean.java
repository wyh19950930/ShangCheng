package com.jymj.zhglxt.bean;

public class FlBean {
    private String name;
    private boolean isCheck = false;

    public FlBean(String name) {
        this.name = name;
    }

    public FlBean(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
