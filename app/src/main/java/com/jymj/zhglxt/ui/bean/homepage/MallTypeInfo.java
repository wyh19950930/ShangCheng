package com.jymj.zhglxt.ui.bean.homepage;


/**
 * 商场类型信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-26
 */
public class MallTypeInfo {
    private  MallType value;

    private  String label;


    public MallType getValue() {
        return value;
    }

    public void setValue(MallType value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MallTypeInfo(MallType value, String label) {
        this.value = value;
        this.label = label;
    }
}
