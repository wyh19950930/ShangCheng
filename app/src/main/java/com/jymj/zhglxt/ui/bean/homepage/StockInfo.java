package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 库存
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-01
 */
public class StockInfo implements Serializable {

    //("规格ID")
    private Long stockId;

    //("规格A")
    private SpecInfo specA;

    //("规格B")
    private SpecInfo specB;

    //("规格C")
    private SpecInfo specC;

    //("价格")
    private BigDecimal price;

    //("总库存")
    private Integer totalInventory;

    //("剩余库存")
    private Integer remainingStock;

    //("库存编号")
    private String number;

    //("库存图片")
    private List<PictureInfo> pictureList;

    //原价
    private BigDecimal originalPrice;

    //活动名称 例：团购享折扣
    private String discount;

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscount() {
        return discount == null?"":discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public SpecInfo getSpecA() {
        return specA;
    }

    public void setSpecA(SpecInfo specA) {
        this.specA = specA;
    }

    public SpecInfo getSpecB() {
        return specB;
    }

    public void setSpecB(SpecInfo specB) {
        this.specB = specB;
    }

    public SpecInfo getSpecC() {
        return specC;
    }

    public void setSpecC(SpecInfo specC) {
        this.specC = specC;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(Integer remainingStock) {
        this.remainingStock = remainingStock;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<PictureInfo> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureInfo> pictureList) {
        this.pictureList = pictureList;
    }
}
