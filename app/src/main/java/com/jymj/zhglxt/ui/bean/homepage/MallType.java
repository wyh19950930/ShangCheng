package com.jymj.zhglxt.ui.bean.homepage;


import java.util.ArrayList;
import java.util.List;

/**
 * 商场类型
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-17
 */
public enum MallType {

    /**
     * 商场类型
     */
    ZI_YING(0,"自营"),

    SHOU_QUAN(1,"授权");


    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    private final Integer value;

    private final String label;


    MallType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<MallTypeInfo> toList(){
        List<MallTypeInfo> mallTypeInfoList = new ArrayList<>();
        for (MallType value : MallType.values()) {
            MallTypeInfo info = new MallTypeInfo(value,value.getLabel());

            mallTypeInfoList.add(info);
        }
        return mallTypeInfoList;
    }
}
