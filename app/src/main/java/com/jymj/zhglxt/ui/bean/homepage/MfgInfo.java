package com.jymj.zhglxt.ui.bean.homepage;


import java.io.Serializable;

/**
 * 厂家
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-31
 */
public class MfgInfo implements Serializable {

    //("厂家id")
    private Long mfgId;

    //("名称")
    private String name;

    //("logo")
    private String logo;

    //("地址")
    private String address;

    //("备注")
    private String remarks;

    public Long getMfgId() {
        return mfgId;
    }

    public void setMfgId(Long mfgId) {
        this.mfgId = mfgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
