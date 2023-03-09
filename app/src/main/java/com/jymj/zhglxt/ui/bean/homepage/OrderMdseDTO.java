package com.jymj.zhglxt.ui.bean.homepage;

/**
 * 订单商品集合
 *
 * @author 唐嘉彬
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-13
 */

public class OrderMdseDTO {

//    @ApiModelProperty("商品id")
    private Long mdseId;

//    @ApiModelProperty("库存id")
    private Long stockId;

//    @ApiModelProperty("店铺id")
    private Long shopId;

//    @ApiModelProperty("购买数量")
    private Integer quantity;

//    @ApiModelProperty("商品类型 1-商品 2-卡")
    private Integer type;

    public OrderMdseDTO(Long mdseId, Long stockId, Long shopId, Integer quantity, Integer type) {
        this.mdseId = mdseId;
        this.stockId = stockId;
        this.shopId = shopId;
        this.quantity = quantity;
        this.type = type;
    }

    public Long getMdseId() {
        return mdseId;
    }

    public void setMdseId(Long mdseId) {
        this.mdseId = mdseId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
