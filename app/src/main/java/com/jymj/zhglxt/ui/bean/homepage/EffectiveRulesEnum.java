package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 生效规则枚举
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-09
 */

public enum EffectiveRulesEnum implements BaseEnum<Integer> ,Serializable {


    /**
     * 生效规则
     */
    IMMEDIATE(0,"立即生效"),

    NEXT_DAY(1,"次日生效"),

    HOURS_LATER(2,"几小时后生效");

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    private final Integer value;

    private final String label;


    EffectiveRulesEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<EnumTypeInfo> toList(){
        List<EnumTypeInfo> mallTypeInfoList = new ArrayList<>();
        for (EffectiveRulesEnum value : EffectiveRulesEnum.values()) {

            EnumTypeInfo info = new EnumTypeInfo(value.name(),value.getLabel());

            mallTypeInfoList.add(info);
        }
        return mallTypeInfoList;
    }

}
