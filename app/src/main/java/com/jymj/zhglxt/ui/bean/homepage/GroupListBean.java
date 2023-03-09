package com.jymj.zhglxt.ui.bean.homepage;

import java.util.List;

public class GroupListBean {
    /**
     * createTime : string
     * groupId : 0
     * mdseCount : 0
     * name : string
     * number : string
     * remarks : string
     * show : false
     */

    private String createTime;
    private int groupId;   //分组id
    private int mdseCount; //商品数量
    private String name;   //名称
    private String number; //编码
    private String remarks;//备注
    private boolean show;  //是否显示
    private boolean check;  //是否选中

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getMdseCount() {
        return mdseCount;
    }

    public void setMdseCount(int mdseCount) {
        this.mdseCount = mdseCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
