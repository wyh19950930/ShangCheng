package com.jymj.zhglxt.ui.bean.homepage;

import java.util.Date;

/**
 * 会员信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-11-07
 */
public class MemberInfo {

//    @ApiModelProperty("id")
    private Long memberId;

//    @ApiModelProperty("用户id")
    private Long userId;

//    @ApiModelProperty("姓名")
    private String name;

//    @ApiModelProperty("手机号")
    private String mobile;

//    @ApiModelProperty("详细地址")
    private String address;

//    @ApiModelProperty("身份证号")
    private String idNumber;

//    @ApiModelProperty(value = "创建时间")
    private Date createTime;

//    @ApiModelProperty(value = "会员等级")
    private Integer memberLevel;

//    @ApiModelProperty(value = "会员等级")
    private Integer level;

//    @ApiModelProperty("邮箱")
    private String email;

//    @ApiModelProperty("生日")
    private String birth;

//    @ApiModelProperty(value = "状态")
    private Integer state;

//    @ApiModelProperty(value = "年龄")
    private Integer age;

//    @ApiModelProperty(value = "性别")
    private Integer gender;

//    @ApiModelProperty(value = "城市代码")
    private String cityCode;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
