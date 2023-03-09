package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;

/**
 * 规格信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-02
 */
public class SpecInfo implements Serializable {

    //("规格Id")
    private Long specId;

    //("规格名称")
    private String key;

    //("规格值")
    private String value;

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
