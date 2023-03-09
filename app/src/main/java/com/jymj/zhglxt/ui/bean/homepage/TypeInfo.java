package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;

/**
 * 商品类型
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-02
 */
public class TypeInfo implements Serializable {

    //("类型id")
    private Long typeId;

    //("类型名称")
    private String name;

    //("备注")
    private String remarks;

    //("商场id")
    private Long mallId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getMallId() {
        return mallId;
    }

    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }
}
