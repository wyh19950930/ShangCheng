package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单配送方式
 *
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */
public enum OrderDeliveryMethodEnum implements BaseEnum<Integer>,Serializable {


    /**
     * 订单配送方式
     */
    EXPRESS(0, "快递"),
    PICK_UP(1, "自提");

//    @Getter
    private final Integer value;

//    @Getter
    private final String label;

    OrderDeliveryMethodEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<EnumTypeInfo> toList() {
        List<EnumTypeInfo> typeInfoList = new ArrayList<>();
        for (OrderDeliveryMethodEnum value : OrderDeliveryMethodEnum.values()) {

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
