package com.jymj.zhglxt.widget.zdybj.bean;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/8/3 11:05
 */
public class TimeLineXmItem {
    private String name;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TimeLineXmItem(String name, String time) {
        this.name = name;
        this.time = time;
    }
}
