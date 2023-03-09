package com.jymj.zhglxt.ui.bean.homepage;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品vo
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-31
 * 商品信息
 */

public class MdseInfo implements Serializable {

    //("商品id")
    private Long mdseId;

    //("商品图片")
    private List<PictureInfo> pictureList;

    //("商品视频")
    private List<PictureInfo> videoList;

    //("商品名称")
    private String name;

    //("商品编号")
    private String number;

    //("商品价格")
    private BigDecimal price;

    //("运费")
    private BigDecimal postage;

    //("销售数量")
    private Integer salesVolume;

    //("起售数量")
    private Integer startingQuantity;

    //("剩余数量 t-显示 f-不显示")
    private boolean showRemainingQuantity;

    //("退款 t-支持退款 f-不支持退款")
    private boolean refund;

    //("库存减少方式")
    private InventoryReductionMethod inventoryReductionMethod;

    //("购买按钮名称")
    private String buttonName;

    //("商品详情")
    private String details;

    //("分组集合")
    private List<GroupInfo> groupList;

    //("品牌")
    private BrandInfo brand;

    //("厂家")
    private MfgInfo mfg;

    //("类型")
    private TypeInfo typeInfo;

    //("店铺")
    private ShopInfo shopInfo;

    //("库存规格集合")
    private List<StockInfo> stockList;

    //("标签")
    private List<LabelInfo> labelInfoList;

    //("商品状态 1-上架 2-下架")
    private Integer status;

    //("商品数量")
    private Integer quantity;

    //("创建时间")
    private String createTime;

    //("库存规格")
    private List<SpecMap> specMap;

    //("商城id")
    private Long mallId;

    //("生效规则")
    private EffectiveRulesInfo effectiveRules;

    //("商品列表")
    private List<MdseInfo> mdseInfoList;

    //("商品分类  1-商品 2-卡")
    private Integer classify;

    //("商品坐标")
    private GeoPoint location;

    private boolean isChecked;

    public Long getMdseId() {
        return mdseId;
    }

    public void setMdseId(Long mdseId) {
        this.mdseId = mdseId;
    }

    public List<PictureInfo> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureInfo> pictureList) {
        this.pictureList = pictureList;
    }

    public List<PictureInfo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<PictureInfo> videoList) {
        this.videoList = videoList;
    }

    public String getName() {
        return name == null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number == null?"":number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price == null?new BigDecimal("0.00"):price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getStartingQuantity() {
        return startingQuantity;
    }

    public void setStartingQuantity(Integer startingQuantity) {
        this.startingQuantity = startingQuantity;
    }

    public boolean isShowRemainingQuantity() {
        return showRemainingQuantity;
    }

    public void setShowRemainingQuantity(boolean showRemainingQuantity) {
        this.showRemainingQuantity = showRemainingQuantity;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public InventoryReductionMethod getInventoryReductionMethod() {
        return inventoryReductionMethod;
    }

    public void setInventoryReductionMethod(InventoryReductionMethod inventoryReductionMethod) {
        this.inventoryReductionMethod = inventoryReductionMethod;
    }

    public String getButtonName() {
        return buttonName == null?"":buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getDetails() {
        return details == null?"":details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<GroupInfo> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupInfo> groupList) {
        this.groupList = groupList;
    }

    public BrandInfo getBrand() {
        return brand;
    }

    public void setBrand(BrandInfo brand) {
        this.brand = brand;
    }

    public MfgInfo getMfg() {
        return mfg;
    }

    public void setMfg(MfgInfo mfg) {
        this.mfg = mfg;
    }

    public TypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public ShopInfo getShopInfo() {
        if (shopInfo == null){
            shopInfo = new ShopInfo();
        }
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public List<StockInfo> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockInfo> stockList) {
        this.stockList = stockList;
    }

    public List<LabelInfo> getLabelInfoList() {
        return labelInfoList;
    }

    public void setLabelInfoList(List<LabelInfo> labelInfoList) {
        this.labelInfoList = labelInfoList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity == null?0:quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCreateTime() {
        return createTime == null?"2000-01-01 00:00:00":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<SpecMap> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(List<SpecMap> specMap) {
        this.specMap = specMap;
    }

    public Long getMallId() {
        return mallId;
    }

    public void setMallId(Long mallId) {
        this.mallId = mallId;
    }

    public EffectiveRulesInfo getEffectiveRules() {
        return effectiveRules;
    }

    public void setEffectiveRules(EffectiveRulesInfo effectiveRules) {
        this.effectiveRules = effectiveRules;
    }

    public List<MdseInfo> getMdseInfoList() {
        return mdseInfoList;
    }

    public void setMdseInfoList(List<MdseInfo> mdseInfoList) {
        this.mdseInfoList = mdseInfoList;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
