package com.jymj.zhglxt.ui.bean.me;

import com.jymj.zhglxt.ui.bean.homepage.OrderDeliveryMethodEnum;
import com.jymj.zhglxt.ui.bean.homepage.OrderPayMethodEnum;
import com.jymj.zhglxt.ui.bean.homepage.OrderStatusEnum;
import com.jymj.zhglxt.ui.bean.user.ScUserEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */
public class MallOrderInfo implements Serializable {

//    @ApiModelProperty("订单id")
    private Long orderId;

//    @ApiModelProperty("订单编号")
    private String orderNo;

//    @ApiModelProperty("订单状态")
    private OrderStatusEnum orderStatus;

//    @ApiModelProperty("订单总额")
    private BigDecimal totalAmount;

//    @ApiModelProperty("应付金额")
    private BigDecimal amountPayable;

//    @ApiModelProperty("实付金额")
    private BigDecimal amountActuallyPaid;

//    @ApiModelProperty("支付方式")
    private OrderPayMethodEnum orderPayMethod;

//    @ApiModelProperty("创建时间")
    private String createTime;

//    @ApiModelProperty("支付时间")
    private String payTime;

//    @ApiModelProperty("发货时间")
    private String deliveryTime;

//    @ApiModelProperty("收货时间")
    private String receivingTime;

//    @ApiModelProperty("订单商品信息")
    private List<MallOrderMdseDetailsInfo> orderMdseDetailsInfoList;

//    @ApiModelProperty("订单配送信息")
    private MallOrderDeliveryDetailsInfo orderDeliveryDetailsInfo;

//    @ApiModelProperty("备注")
    private String remarks;

//    @ApiModelProperty("配送方式")
    private OrderDeliveryMethodEnum orderDeliveryMethod;

//    @ApiModelProperty("用户信息")
    private UserInfo userInfo;

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

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(BigDecimal amountPayable) {
        this.amountPayable = amountPayable;
    }

    public BigDecimal getAmountActuallyPaid() {
        return amountActuallyPaid;
    }

    public void setAmountActuallyPaid(BigDecimal amountActuallyPaid) {
        this.amountActuallyPaid = amountActuallyPaid;
    }

    public OrderPayMethodEnum getOrderPayMethod() {
        return orderPayMethod;
    }

    public void setOrderPayMethod(OrderPayMethodEnum orderPayMethod) {
        this.orderPayMethod = orderPayMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }

    public List<MallOrderMdseDetailsInfo> getOrderMdseDetailsInfoList() {
        if (orderMdseDetailsInfoList==null){
            orderMdseDetailsInfoList = new ArrayList<MallOrderMdseDetailsInfo>();
        }
        return orderMdseDetailsInfoList;
    }

    public void setOrderMdseDetailsInfoList(List<MallOrderMdseDetailsInfo> orderMdseDetailsInfoList) {
        this.orderMdseDetailsInfoList = orderMdseDetailsInfoList;
    }

    public MallOrderDeliveryDetailsInfo getOrderDeliveryDetailsInfo() {
        return orderDeliveryDetailsInfo;
    }

    public void setOrderDeliveryDetailsInfo(MallOrderDeliveryDetailsInfo orderDeliveryDetailsInfo) {
        this.orderDeliveryDetailsInfo = orderDeliveryDetailsInfo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public OrderDeliveryMethodEnum getOrderDeliveryMethod() {
        return orderDeliveryMethod;
    }

    public void setOrderDeliveryMethod(OrderDeliveryMethodEnum orderDeliveryMethod) {
        this.orderDeliveryMethod = orderDeliveryMethod;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
