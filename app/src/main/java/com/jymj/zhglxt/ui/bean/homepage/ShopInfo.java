package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;

/**
 * 店铺（网点）
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-30
 */
public class ShopInfo implements Serializable {

    //("店铺（网点）id")
    private Long shopId;

    //("店铺名称")
    private String name;

    //("详细地址")
    private String address;

    //("负责人")
    private String director;

    //("联系电话")
    private String mobile;

    //("状态")
    private Integer status;

    //("营业状态 1-营业中 2-休息")
    private Integer inBusiness;


    //("营业开始时间")
    private String businessStartTime;


    //("营业结束时间")
    private String businessEndTime;

    //("经度")
    private Double longitude;

    //("纬度")
    private Double latitude;

    //("店铺位置")
    private GeoPoint position;

    private boolean isChecked;

    public Long getShopId() {
        return shopId == null?0L:shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInBusiness() {
        return inBusiness;
    }

    public void setInBusiness(Integer inBusiness) {
        this.inBusiness = inBusiness;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public GeoPoint getPosition() {
        return position;
    }

    public void setPosition(GeoPoint position) {
        this.position = position;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
