package com.jymj.zhglxt.ui.bean.me;

import com.jymj.zhglxt.ui.bean.homepage.OrderStatusEnum;

import java.util.Date;
import java.util.List;

/**
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */
public class OrderPageQuery extends BasePageQueryDTO {

//    @ApiModelProperty("用户id")
    private Long userId;

//    @ApiModelProperty("订单状态")
    private OrderStatusEnum orderStatus;

//    @ApiModelProperty(value = "店铺id")
    private List<Long> shopIdList;

//    @ApiModelProperty("订单编号")
    private String orderNo;

//    @ApiModelProperty("商品id")
    private Long mdseId;

//    @ApiModelProperty("商城id")
    private Long mallId;

//    @ApiModelProperty("商品名称")
    private String mdseName;

//    @ApiModelProperty("收货人")
    private String addressee;

//    @ApiModelProperty("手机号")
    private String mobile;

//    @ApiModelProperty("商品类型 1-商品  2-卡")
    private Integer type;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty("订单下单开始时间")
    private String startDate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty("订单下单结束时间")
    private String endDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Long> getShopIdList() {
        return shopIdList;
    }

    public void setShopIdList(List<Long> shopIdList) {
        this.shopIdList = shopIdList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getMdseId() {
        return mdseId;
    }

    public void setMdseId(Long mdseId) {
        this.mdseId = mdseId;
    }

    public Long getMallId() {
        return mallId;
    }

    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }

    public String getMdseName() {
        return mdseName;
    }

    public void setMdseName(String mdseName) {
        this.mdseName = mdseName;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
