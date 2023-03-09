package com.jymj.zhglxt.bean;

import java.util.ArrayList;
import java.util.List;

public class BcjcBbBean {

    private String xzqmc;
    private List<Object> list;

    public String getXzqmc() {
        return xzqmc;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public List<Object> getList() {
        return list==null?new ArrayList<>():list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
