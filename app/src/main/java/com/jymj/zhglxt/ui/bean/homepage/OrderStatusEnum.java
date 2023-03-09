package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单状态
 *
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */
public enum OrderStatusEnum implements BaseEnum<Integer>,Serializable {

    /**
     * 订单状态
     */
    UNPAID(0, "待付款"),//未付款
    UNSHIPPED(1, "待发货"),//未发货
    UNRECEIVED(2, "待收货"),//未收货
    COMPLETED(3, "已完成"),//已完成
    AFTER_SALES(4, "售后"),//售后
    CANCELED(5, "已取消"),//取消
    CLOSED(6, "已关闭");//关闭

//    @Getter
    private final Integer value;

//    @Getter
    private final String label;

    OrderStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<EnumTypeInfo> toList() {
        List<EnumTypeInfo> typeInfoList = new ArrayList<>();
        for (OrderStatusEnum value : OrderStatusEnum.values()) {

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
