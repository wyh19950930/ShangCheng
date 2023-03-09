package com.jymj.zhglxt.ui.bean.me;


import com.jymj.zhglxt.ui.bean.homepage.EffectiveRulesInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单商品详情
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-17
 */
public class MallOrderMdseDetailsInfo implements Serializable {

//    @ApiModelProperty("商品id")
    private Long mdseId;

//    @ApiModelProperty("商品编号")
    private String number;

//    @ApiModelProperty("库存id")
    private Long stockId;

//    @ApiModelProperty("店铺id")
    private Long shopId;

//    @ApiModelProperty("购买数量")
    private Integer quantity;

//    @ApiModelProperty("商品类型 1-商品 2-卡")
    private Integer type;

//    @ApiModelProperty("店铺名称")
    private String shopName;

//    @ApiModelProperty("商品名称")
    private String mdseName;

//    @ApiModelProperty("商品规格参数")
    private String mdseStockSpec;

//    @ApiModelProperty("商品图片")
    private String mdsePicture;

//    @ApiModelProperty("商品价格")
    private BigDecimal mdsePrice;

//    @ApiModelProperty("使用状态 1-已使用 2-未使用")
    private Integer usageStatus;

//    @ApiModelProperty("使用数量")
    private Integer usageQuantity;

//    @ApiModelProperty("使用时间")
    private String usageDate;

//    @ApiModelProperty("订单卡商品")
    private List<MallOrderMdseDetailsInfo> cardMdseInfoList;

//    @ApiModelProperty("卡生效规则")
    private EffectiveRulesInfo effectiveRules;

    public Long getMdseId() {
        return mdseId;
    }

    public void setMdseId(Long mdseId) {
        this.mdseId = mdseId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getShopId() {
        return shopId == null?0L:shopId;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMdseName() {
        return mdseName;
    }

    public void setMdseName(String mdseName) {
        this.mdseName = mdseName;
    }

    public String getMdseStockSpec() {
        return mdseStockSpec;
    }

    public void setMdseStockSpec(String mdseStockSpec) {
        this.mdseStockSpec = mdseStockSpec;
    }

    public String getMdsePicture() {
        return mdsePicture;
    }

    public void setMdsePicture(String mdsePicture) {
        this.mdsePicture = mdsePicture;
    }

    public BigDecimal getMdsePrice() {
        return mdsePrice;
    }

    public void setMdsePrice(BigDecimal mdsePrice) {
        this.mdsePrice = mdsePrice;
    }

    public Integer getUsageStatus() {
        return usageStatus;
    }

    public void setUsageStatus(Integer usageStatus) {
        this.usageStatus = usageStatus;
    }

    public Integer getUsageQuantity() {
        return usageQuantity;
    }

    public void setUsageQuantity(Integer usageQuantity) {
        this.usageQuantity = usageQuantity;
    }

    public String getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(String usageDate) {
        this.usageDate = usageDate;
    }

    public List<MallOrderMdseDetailsInfo> getCardMdseInfoList() {
        return cardMdseInfoList;
    }

    public void setCardMdseInfoList(List<MallOrderMdseDetailsInfo> cardMdseInfoList) {
        this.cardMdseInfoList = cardMdseInfoList;
    }

    public EffectiveRulesInfo getEffectiveRules() {
        return effectiveRules;
    }

    public void setEffectiveRules(EffectiveRulesInfo effectiveRules) {
        this.effectiveRules = effectiveRules;
    }
}
