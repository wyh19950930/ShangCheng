package com.jymj.zhglxt.widget.zdybj.bean;

public class TuliColorBean {

    public String name;
    public int tuLiColor;

    public TuliColorBean(String name, int tuLiColor) {
        this.name = name;
        this.tuLiColor = tuLiColor;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getTuLiColor() {
        return tuLiColor;
    }

    public void setTuLiColor(int tuLiColor) {
        this.tuLiColor = tuLiColor;
    }
}
