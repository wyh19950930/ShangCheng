package com.jymj.zhglxt.ui.bean.homepage;


/**
 * 枚举类型信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-09
 */
public class EnumTypeInfo {

    private  String value;

    private  String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public EnumTypeInfo(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
