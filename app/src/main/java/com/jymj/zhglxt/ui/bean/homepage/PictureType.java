package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片类型
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-01
 */
public enum PictureType implements Serializable {

    /**
     * 项目图片类型
     */
    MDSE_PIC(0, "商品图片"),
    MDSE_VIDEO(1, "商品视频"),
    STOCK_SPEC(2, "规格图片"),
    CARD_PIC(3, "卡图片");


    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    private final Integer value;

    private final String label;


    PictureType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static List<MallTypeInfo> toList() {
        List<MallTypeInfo> mallTypeInfoList = new ArrayList<>();
        for (MallType value : MallType.values()) {

            MallTypeInfo info = new MallTypeInfo(value, value.getLabel());

            mallTypeInfoList.add(info);
        }
        return mallTypeInfoList;
    }
}
