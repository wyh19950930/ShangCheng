package com.setsuna.common.baseapp;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * App内存缓存
 */
public class AppCache {
    private volatile static AppCache instance;
    private Long userId;
    private String token;
    private int zhqx = 1;//账号权限  镇 村
    private String loginCenter;
    private String zijiLocation;
    private String name;//名字(有可能就是手机号)
    private String nickName;//微信昵称
    private String avatarUrl;//微信头像

    private String password;//密码
    private String idCard;//身份证号
    private String zmmc;//职务名称
    private String phone;//手机号
    private String code;//
    private String xzCenter;//选中的坐标
    private String xzqName;//行政区名称
    private String xzqZhenName;//镇行政区名称
    private Long ylId;//最近一次点查的院落id
    private int RefreshType; //刷新标识
    private int fwRefresh; //刷新标识  用于判断返回出租房是否刷新
    private int type; ////用于区分用户权限
    private String rjhjCode="";
    private String rjhjCenter="";
    private int isFjList = 0;
    private int isZjdUpdate = 0;//判断宅基地状态修改  0 未修改  1修改
    private int mapFlag = 0;//进入各个地图页标识   1 基本信息 2 产业发展 3 市政公服 4 拆腾安置

    private int isList = 0;
    private int duties = 0;//是否是领导0:非  1:是 2 调查人员
    private int isUpdateWt = 0;//判断问题是否下发或者修改  0、未修改  1、修改
    private String mobile="";
    private String cunCode="";//获取当前用户所属行政区的code
    private int isQyxqUpdate = 0;//企业详情 0、不更新  1、更新
    private boolean isJsjb;
    private boolean isJsjbQuan;
    private boolean isIssueXm = false;//是否新项目
    private boolean isQxSc = false;//是否取消收藏

    ///用于物业跳转回显///
    private String wyCode;
    private String wyXzqmc;
    private boolean czxq = false;//出租详情
    private boolean fwlb = false;//房屋列表
    private boolean czlb = false;//村庄列表
    private boolean dtymlb = false;//地图页面列表
    private List<Activity> activities = new ArrayList<>();



    private AppCache() {
    }
    public static AppCache getInstance() {
        if (null == instance) {
            synchronized (AppCache.class) {
                if (instance == null) {
                    instance = new AppCache();
                }
            }
        }
        return instance;
    }

    public static void setInstance(AppCache instance) {
        AppCache.instance = instance;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getZijiLocation() {
        return zijiLocation;
    }

    public void setZijiLocation(String zijiLocation) {
        this.zijiLocation = zijiLocation;
    }

    public boolean isQxSc() {
        return isQxSc;
    }

    public void setQxSc(boolean qxSc) {
        isQxSc = qxSc;
    }

    public boolean isIssueXm() {
        return isIssueXm;
    }

    public void setIssueXm(boolean issueXm) {
        isIssueXm = issueXm;
    }

    public String getXzCenter() {
        return xzCenter==null?"":xzCenter;
    }

    public void setXzCenter(String xzCenter) {
        this.xzCenter = xzCenter;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public boolean isCzxq() {
        return czxq;
    }

    public void setCzxq(boolean czxq) {
        this.czxq = czxq;
    }

    public boolean isFwlb() {
        return fwlb;
    }

    public void setFwlb(boolean fwlb) {
        this.fwlb = fwlb;
    }

    public boolean isCzlb() {
        return czlb;
    }

    public void setCzlb(boolean czlb) {
        this.czlb = czlb;
    }

    public boolean isDtymlb() {
        return dtymlb;
    }

    public void setDtymlb(boolean dtymlb) {
        this.dtymlb = dtymlb;
    }

    public boolean isJsjb() {
        return isJsjb;
    }

    public void setJsjb(boolean jsjb) {
        isJsjb = jsjb;
    }

    public boolean isJsjbQuan() {
        return isJsjbQuan;
    }

    public void setJsjbQuan(boolean jsjbQuan) {
        isJsjbQuan = jsjbQuan;
    }

    public String getWyCode() {
        return wyCode == null?"":wyCode;
    }

    public void setWyCode(String wyCode) {
        this.wyCode = wyCode;
    }

    public String getWyXzqmc() {
        return wyXzqmc == null?"":wyXzqmc;
    }

    public void setWyXzqmc(String wyXzqmc) {
        this.wyXzqmc = wyXzqmc;
    }

    public int getIsQyxqUpdate() {
        return isQyxqUpdate;
    }

    public void setIsQyxqUpdate(int isQyxqUpdate) {
        this.isQyxqUpdate = isQyxqUpdate;
    }

    public String getCunCode() {
        return cunCode == null ?"":cunCode;
    }

    public void setCunCode(String cunCode) {
        this.cunCode = cunCode;
    }

    public int getIsUpdateWt() {
        return isUpdateWt;
    }

    public void setIsUpdateWt(int isUpdateWt) {
        this.isUpdateWt = isUpdateWt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getDuties() {
        return duties;
    }

    public void setDuties(int duties) {
        this.duties = duties;
    }

    public int getIsList() {
        return isList;
    }

    public void setIsList(int isList) {
        this.isList = isList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRefreshType() {
        return RefreshType;
    }

    public void setRefreshType(int refreshType) {
        RefreshType = refreshType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginCenter() {
        return loginCenter;
    }

    public void setLoginCenter(String loginCenter) {
        this.loginCenter = loginCenter;
    }

    public int getZhqx() {
        return zhqx;
    }

    public void setZhqx(int zhqx) {
        this.zhqx = zhqx;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password==null?"":password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code == null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXzqName() {
        return xzqName==null?"":xzqName;
    }

    public void setXzqName(String xzqName) {
        this.xzqName = xzqName;
    }

    public String getXzqZhenName() {
        return xzqZhenName==null?"":xzqZhenName;
    }

    public void setXzqZhenName(String xzqZhenName) {
        this.xzqZhenName = xzqZhenName;
    }

    public Long getYlId() {
        return ylId;
    }

    public void setYlId(Long ylId) {
        this.ylId = ylId;
    }

    public String getIdCard() {
        return idCard==null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getZmmc() {
        return zmmc==null?"":zmmc;
    }

    public void setZmmc(String zmmc) {
        this.zmmc = zmmc;
    }

    public int getIsFjList() {
        return isFjList;
    }

    public void setIsFjList(int isFjList) {
        this.isFjList = isFjList;
    }

    public int getFwRefresh() {
        return fwRefresh;
    }

    public void setFwRefresh(int fwRefresh) {
        this.fwRefresh = fwRefresh;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRjhjCode() {
        return rjhjCode==null?"":rjhjCode;
    }

    public void setRjhjCode(String rjhjCode) {
        this.rjhjCode = rjhjCode;
    }

    public String getRjhjCenter() {
        return rjhjCenter==null?"":rjhjCenter;
    }

    public void setRjhjCenter(String rjhjCenter) {
        this.rjhjCenter = rjhjCenter;
    }

    public int getIsZjdUpdate() {
        return isZjdUpdate;
    }

    public void setIsZjdUpdate(int isFjUpdateWt) {
        this.isZjdUpdate = isFjUpdateWt;
    }



    public int getMapFlag() {
        return mapFlag;
    }

    public void setMapFlag(int mapFlag) {
        this.mapFlag = mapFlag;
    }
}
