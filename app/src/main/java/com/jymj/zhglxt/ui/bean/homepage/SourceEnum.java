package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 来源
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-16
 */
public enum SourceEnum implements BaseEnum<Integer>,Serializable {
    /**
     * 用户来源
     */
    WEB(0, "网页端"),

    WECHAT(1, "微信小程序"),

    APP(2, "APP");


//    @Getter
    private final Integer value;

//    @Getter
    private final String label;


    SourceEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<EnumTypeInfo> toList() {
        List<EnumTypeInfo> typeInfoList = new ArrayList<>();
        for (SourceEnum value : SourceEnum.values()) {

            EnumTypeInfo info = new EnumTypeInfo(value.name(), value.getLabel());

            typeInfoList.add(info);
        }
        return typeInfoList;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
