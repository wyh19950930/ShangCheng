package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;

/**
 * 分组
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-31
 */
public class GroupInfo implements Serializable {

    //("分组id")
    private Long groupId;

    //("编码")
    private String number;

    //("名称")
    private String name;

    //("显示")
    private Boolean show;

    //("备注")
    private String remarks;

    //("创建时间")
    private String createTime;

    //("商品数量")
    private Integer mdseCount;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getMdseCount() {
        return mdseCount;
    }

    public void setMdseCount(Integer mdseCount) {
        this.mdseCount = mdseCount;
    }
}
