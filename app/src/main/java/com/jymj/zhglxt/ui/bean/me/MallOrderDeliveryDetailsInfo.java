package com.jymj.zhglxt.ui.bean.me;


import com.jymj.zhglxt.ui.bean.homepage.OrderDeliveryMethodEnum;

import java.io.Serializable;

/**
 * 订单配送详情
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-17
 */
public class MallOrderDeliveryDetailsInfo implements Serializable {

//    @ApiModelProperty("配送方式")
    private OrderDeliveryMethodEnum orderDeliveryMethod;

//    @ApiModelProperty("地址id")
    private Long addressId;

//    @ApiModelProperty("收件人")
    private String addressee;

//    @ApiModelProperty("电话")
    private String mobile;

//    @ApiModelProperty("详细地址")
    private String detailedAddress;

    public OrderDeliveryMethodEnum getOrderDeliveryMethod() {
        return orderDeliveryMethod;
    }

    public void setOrderDeliveryMethod(OrderDeliveryMethodEnum orderDeliveryMethod) {
        this.orderDeliveryMethod = orderDeliveryMethod;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressee() {
        return addressee == null?"":addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMobile() {
        return mobile == null?"":mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDetailedAddress() {
        return detailedAddress == null?"":detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
}
