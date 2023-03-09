package com.jymj.zhglxt.ui.bean.homepage;

/**
 * 地址信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-10-18
 */
public class AddressInfo {

//    @ApiModelProperty("地址id")
    private Long addressId;

//    @ApiModelProperty("姓名")
    private String name;

//    @ApiModelProperty("手机号")
    private String mobile;

//    @ApiModelProperty("行政区")
    private String region;

//    @ApiModelProperty("详细地址")
    private String detailedAddress;

//    @ApiModelProperty("标签")
    private String label;

//    @ApiModelProperty("状态 1-默认")
    private Integer status;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
