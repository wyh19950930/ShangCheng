package com.jymj.zhglxt.widget.gxt.bean;

import java.io.Serializable;

public class RelationParent implements Serializable {

    private int group;//父类的id
    private int pictureCount;//与父类的证据
    private String relation;//测试两下 哈哈哈   ="父子"
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

    public String getRelation() {
        return relation==null?"":relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
