package com.jymj.zhglxt.widget.zdybj.bean;

public class TimeLineBean {

    private int id;
    private int select;//选中状态 0 未选中  1 选中
    private int selected;//当前完成进度  0 默认 1 到达
    private String name;//状态名称

    public TimeLineBean(String name) {
        this.name = name;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
