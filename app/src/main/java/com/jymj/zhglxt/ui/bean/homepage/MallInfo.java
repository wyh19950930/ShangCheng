package com.jymj.zhglxt.ui.bean.homepage;

import java.util.Date;
import java.util.List;

/**
 * 商场信息
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-08-26
 */
public class MallInfo {

    //("商场id")
    private Long mallId;

    //("部门id")
    private Long deptId;

    //("商场编号")
    private String mallNo;

    //("商场名称")
    private String name;

    //("商场LOGO")
    private String logo;

    //("简介")
    private String introduce;

    //("商场类型 1-自营 2-授权等")
    private String type;

    //("行政区")
    private DistrictInfo districtInfo;

    //("标签列表")
    private List<TagInfo> tagList;

    //("管理人姓名")
    private String managerName;

    //("管理人电话")
    private String managerMobile;

    //("管理人身份证号")
    private String managerIdNumber;

    //("创建时间")
    private String createTime;

    //("认证信息")
    private MallAuthInfo authInfo;
}
