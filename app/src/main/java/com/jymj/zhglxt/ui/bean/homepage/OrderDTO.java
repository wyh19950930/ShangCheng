package com.jymj.zhglxt.ui.bean.homepage;

import java.util.List;

/**
 * 订单
 *
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */

public class OrderDTO {

//    @ApiModelProperty("订单id")
    private Long orderId;

//    @ApiModelProperty("订单编号")
    private String orderNo;

//    @ApiModelProperty("订单状态")
    private OrderStatusEnum statusEnum;

//    @ApiModelProperty("地址id")
    private String addressId;

//    @ApiModelProperty("优惠券id")
    private List<Long> couponIdList;

//    @ApiModelProperty("促销活动id")
    private List<Long> promotionIdList;

//    @NotNull(message = "商品不能为空")
//    @ApiModelProperty("订单商品集合")
    private List<OrderMdseDTO> orderMdseList;

//    @ApiModelProperty("配送方式")
    private OrderDeliveryMethodEnum orderDeliveryMethod;

//    @ApiModelProperty("备注")
    private String remarks;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public OrderStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(OrderStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<Long> getCouponIdList() {
        return couponIdList;
    }

    public void setCouponIdList(List<Long> couponIdList) {
        this.couponIdList = couponIdList;
    }

    public List<Long> getPromotionIdList() {
        return promotionIdList;
    }

    public void setPromotionIdList(List<Long> promotionIdList) {
        this.promotionIdList = promotionIdList;
    }

    public List<OrderMdseDTO> getOrderMdseList() {
        return orderMdseList;
    }

    public void setOrderMdseList(List<OrderMdseDTO> orderMdseList) {
        this.orderMdseList = orderMdseList;
    }

    public OrderDeliveryMethodEnum getOrderDeliveryMethod() {
        return orderDeliveryMethod;
    }

    public void setOrderDeliveryMethod(OrderDeliveryMethodEnum orderDeliveryMethod) {
        this.orderDeliveryMethod = orderDeliveryMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
