package com.jymj.zhglxt.ui.bean.me;

import com.jymj.zhglxt.ui.bean.homepage.MemberEnum;
import com.jymj.zhglxt.ui.bean.homepage.SourceEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 用户
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-13
 */
public class UserInfo implements Serializable {

//    @ApiModelProperty(value = "id" )
    private Long userId;

//    @ApiModelProperty(value = "用户名")
    private String username;

//    @ApiModelProperty(value = "昵称")
    private String nickName;

//    @ApiModelProperty(value = "手机号")
    private String mobile;

//    @ApiModelProperty(value = "性别")
    private Integer gender;

    private String birthday;

//    @ApiModelProperty(value = "头像")
    private String avatarUrl;

//    @ApiModelProperty(value = "城市")
    private String city;

//    @ApiModelProperty(value = "国家")
    private String country;

//    @ApiModelProperty(value = "语言")
    private String language;

//    @ApiModelProperty(value = "省份")
    private String province;

//    @ApiModelProperty(value = "状态")
    private Integer status;

//    @ApiModelProperty(value = "用户身份")
    private MemberEnum memberType;

//    @ApiModelProperty(value = "来源")
    private SourceEnum sourceType;

//    @ApiModelProperty(value = "购买次数")
    private Integer purchaseCount;

//    @ApiModelProperty(value = "创建时间")
    private String createTime;

//    @ApiModelProperty(value = "登录时间")
    private String loginTime;

//    @ApiModelProperty(value = "核销人员 1-是 2-不是")
    private Integer verifyPerson;

//    @ApiModelProperty(value = "会员等级")
    private Integer memberLevel;

//    @ApiModelProperty("姓名")
    private String memberName;

//    @ApiModelProperty("手机号")
    private String memberMobile;

//    @ApiModelProperty("会员信息")
//    private MemberInfo memberInfo;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MemberEnum getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberEnum memberType) {
        this.memberType = memberType;
    }

    public SourceEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceEnum sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(Integer verifyPerson) {
        this.verifyPerson = verifyPerson;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
