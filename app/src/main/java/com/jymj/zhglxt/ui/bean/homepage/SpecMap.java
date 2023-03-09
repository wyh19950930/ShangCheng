package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.util.List;

/**
 * 规格集合
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-20
 */
public class SpecMap implements Serializable {

    private String key;

    private List<String> values;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
