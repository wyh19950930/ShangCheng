package com.jymj.zhglxt.api;
/**
 * Created by setsuna on 2018/3/7.
 */
public interface ApiConstants {//<土地利用框查统计>
    final String APPID = "804802";//8018  100村：804802  黄村：  北臧村：  黄山店：  804803
    final String APPID_HSD = "804803";
    final String APPID_SHANG = "804805";
    final boolean IS_ENCRYPT = false;//是否加密
    final String RELEASE_URL = "https://shop.t625.com/";//http://59.110.171.147:8061/
    //final String RELEASE_URL = "http://192.168.1.4:8084/gdzl/";//8015 8088
    //final String RELEASE_URL = "http://39.105.122.55:8080/";//8015
    final String DEBUG_URL1 = "http://192.168.3.32:8084/";//gdzl/
        final String DEBUG_URL = "http://192.168.3.138:9010/";
//    final String DEBUG_URL = "http://192.168.3.124:9320/";//嘉宾
    final String LOGIN_NAME = "HGZ";
    final String WXAppID = "wx10e3d1292c7f5247";
    final String WXAppSecret = "wx10e3d1292c7f5247";


    //更新app
//    final String CHECK_UPDATE = "http://139.224.52.12:8001/api/ver/get";
    final String CHECK_UPDATE = "http://39.105.122.55:8080/jymj-huitouzi/ver/get";
    final String BASE_URL = RELEASE_URL;//  api/
//    final String BASE_URL = DEBUG_URL;
//    final String BASEAPI = "";//jymj-gdzl/
//    final String BASEAPI = "";//jymj-gdzl/

    //登录
    final String LOGIN_URL = BASE_URL+"mall-oauth/oauth/token";//sys/applogin
    final String SC_USERS_URL = BASE_URL+"mall-user/api/v1/users/";//{userId}/info 商城用户信息
    final String SC_MDSE_PAGES_URL = BASE_URL+"mall-search/api/v1/mdse/pages";//商城商品分页 默认首页
    final String SC_MDSE_LISTS_URL = BASE_URL+"mall-mdse/api/v1/group/lists";//分组
    final String SC_ADD_CART_URL = BASE_URL+"mall-order/api/v1/cart";//添加商品到购物车
    final String SC_CART_PAGES_URL = BASE_URL+"mall-order/api/v1/cart/pages";//购物车列表
    final String SC_ADD_ADDRESS_URL = BASE_URL+"mall-user/api/v1/address";//添加地址
    final String SC_ADDRESS_LIST_URL = BASE_URL+"mall-user/api/v1/address/list";//地址列表
    final String SC_order_URL = BASE_URL+"mall-order/api/v1/user/order";//创建订单
    final String SC_ORDER_PAGES_URL = BASE_URL+"mall-order/api/v1/user/order/pages";//订单分页
    final String SC_ORDER_INFO_URL = BASE_URL+"mall-order/api/v1/user/order/id";//订单信息
    final String SC_MEMBERS_USER_URL = BASE_URL+"mall-user/api/v1/members/user";//会员信息
    final String SC_MEMBERS_URL = BASE_URL+"mall-user/api/v1/members";//添加会员 修改会员 删除会员
    final String SC_ORDER_NO_URL = BASE_URL+"mall-order/api/v1/user/order/no";//核销订单信息
    final String SC_VERIFICATION_ORDER_MDSE_URL = BASE_URL+"mall-shop/api/v1/verification/order/mdse";//核销商品或卡


    final String MODIFYPWD_URL = BASE_URL + "sys/user/restpassword";//修改密码
    final String SYS_USER_UPDATE = BASE_URL + "sys/user/update";//修改
    final String SYS_XZQ_LIST = BASE_URL + "sys/layer/list";//获取图层列表
    final String SYS_XZQ_PERMS = BASE_URL + "sys/xzq/perms";//获取行政区树形
    final String SYS_USER_GETUSERLST = BASE_URL + "sys/user/getUserList";// 用户列表
    final String SYS_USER_DELETE = BASE_URL + "sys/user/delete";//  用户删除
    final String SYS_USER_INFO = BASE_URL + "sys/user/info";//   用户详情
    final String SYS_USER_SAVEUSER = BASE_URL + "sys/user/saveUser";//     添加/修改 用户信息
    final String KUAIDI_QUERY = "http://www.kuaidi100.com/query";//     快递信息查询



    final String FLOW_GET_INDUSTRY = BASE_URL+"flow/getIndustry";///  查询首页行业类型统计 code
    final String FLOW_GET_HOME_COUNT = BASE_URL+"flow/getHomeCount";///  / 查询首页基本情况统计 code
    final String FLOW_GET_BY_POINT = BASE_URL+"flow/getByPoint";//  点查院落信息  point
    final String FLOW_XZQ_LIST = BASE_URL+"flow/getXzqList";// 查询村庄情况列表  lgyname:流管员名称   xzqmc:村名
    final String FLOW_QUERY_LIST = BASE_URL+"flow/queryList";//  流动人员列表   code:登陆账号所关联的code  name:人员名称   xzqmc:村名
    final String FLOW_GET_RRCODE = BASE_URL+"flow/getRRCode";//  扫描二维码查找院落  qrcode
    final String FLOW_GET_INFO = BASE_URL+"flow/getInfo";//  搜索或者点击查看流动人员详情  flowId:点击查看详情是使用   idCard:添加时搜索已添加信息
    final String FLOW_SAVE_FLOW = BASE_URL+"flow/saveFlow";//   添加流动人员
    final String FLOW_SAVE_ZHAIRQ = BASE_URL+"flow/saveZhaiRQ";//    扫描二维码和宅基地进行绑定  objectid:院落id  qrcode:二维码code
    final String MESSAGE_QUERY_LIST = BASE_URL+"message/queryList"; //消息列表  limit  page
    final String MESSAGE_DELETE_FILE_BYID = BASE_URL+"message/deleteFileById"; // 删除图片  ids
    final String MESSAGE_SAVE_ORUPDATE = BASE_URL+"message/saveOrUpdate"; // 发布消息/处理or修改消息
    final String MESSAGE_UPLOAD_FILE = BASE_URL+"message/uploadFile"; //   上传图片
    final String FLOW_GET_HEAT_MAP = BASE_URL+"flow/getHeatMap"; //    热力图  code  type  xmax  xmin  ymax  ymin
    final String FLOW_GET_BCJH = BASE_URL+"point/getBcjh"; //
    final String FLOW_OUT_FLOW = BASE_URL+"flow/outFlow"; //  流出人员,参数 ids(流动人员id)
    final String MESSAGE_GET_PERSONNEL = BASE_URL+"message/getPersonnel"; //    获取流管员列表
    final String MESSAGE_GET_COUNT = BASE_URL+"message/getCount"; //  未处理消息统计数量
    final String FLOW_GET_YLLIST = BASE_URL+"flow/getYLList"; //  院落列表

    final String LOGIN_NUMBER_URL = "authBymobileAnddeviceId";

    //人居环境
    final String SYS_XZQ_QUERY_XZQ_LIST = BASE_URL + "sys/xzq/queryXzqList";//queryXzqList
    final String TOWN_GET_BYPOINT= BASE_URL + "EnviorSupvs/getByPoint";//
    final String TOWN_QUERY_IMPROVERATE= BASE_URL + "EnviorSupvs/queryImproveRate";//近一年 近一季 近一月
    final String TOWN_GET_CUN_COUNT= BASE_URL + "EnviorSupvs/getCunCount";////这个好像是获取个村整改率的
    final String TOWN_GETTOWNTYPE= BASE_URL + "EnviorSupvs/getTownType";
    final String ENVIORSUPVSLIST = BASE_URL + "EnviorSupvs/queryList";//环保监察查看列表
    final String ENVIORSUPVS_ILLEGAL= BASE_URL + "EnviorSupvs/queryByPoints";//人居环境点查//illegal
    final String ENVIOR_SUPVS_QUERY_BY_POINT = BASE_URL + "EnviorSupvs/queryByPoints";//
    final String ENVIORFILEDELETE = BASE_URL + "EnviorSupvs/deleteFile";//删除文件
    final String ENVIORFILEUPLOAD = BASE_URL + "EnviorSupvs/upload";//上传文件
    final String PJROLEENVIORSAVE = BASE_URL + "EnviorSupvs/save";//
    final String ENVIORSUPVSDELETE = BASE_URL + "EnviorSupvs/delete";//删除
    final String ENVIORSUPVSAVE = BASE_URL + "EnviorSupvs/save";//新增
    final String ENVIORSUPV_QUERY_POINT_LIST = BASE_URL + "EnviorSupvs/queryPointList";//固定点列表
    final String ENVIORSUPV_SAVE_POINT = BASE_URL + "EnviorSupvs/savePoint";//固定点添加
    final String ENVIORSUPV_UPDATE_POINT = BASE_URL + "EnviorSupvs/updatePoint";//固定点修改
    final String ENVIORSUPV_DELETE_POINT = BASE_URL + "EnviorSupvs/deletePoint";//固定点删除
    final String ENVIORSUPV_QUERY_RY = BASE_URL + "EnviorSupvs/queryRy";//获取村人名单
    final String ENVIORSUPV_QUERY_HOME = BASE_URL + "EnviorSupvs/queryHome";//  参数 stDate   enDate
    final String ENVIORSUPV_QUERY_CUN = BASE_URL + "EnviorSupvs/queryCun";//  查看村名
    final String ENVIORSUPVS_COUNT_BY_CODE = BASE_URL + "EnviorSupvs/countByCode";
    final String queryByCunEnviorSupvs = BASE_URL + "EnviorSupvs/queryByCun";//环保监察总览列表
    final String ENVIORFILE_DELETE_FILE = BASE_URL + "EnviorSupvs/deletePointFile";//固定点位公厕删除图片 @RequestBody Long... ids数组json
    final String ENVIORSUPVSDELETES = BASE_URL + "EnviorSupvs/deleteBatch";//批量删除
    final String ENVIORFILE_UPLOAD_FILE = BASE_URL + "EnviorSupvs/uploadPointFile";//公厕固定点位图片上传
    final String ENVIORSUPVS_GETENVIORS_BYXY = BASE_URL + "EnviorSupvs/getEnviorsByxy";//点位聚合
    final String FALL_BACK_QUTYPE = BASE_URL + "EnviorSupvs/fallBackQutype";//详情
    final String ENVIORSUPVSUPDATE = BASE_URL + "EnviorSupvs/update";//修改
    final String UPDATE_CASH = BASE_URL + "invest/updateInvest";
    final String ENVIORSUPVS_INFO = BASE_URL + "EnviorSupvs/info";//详情
    final String POINT_QUERY_DKDL = BASE_URL + "EnviorSupvs/queryDkDl";
    final String ENVIOR_FILE_INFO = BASE_URL + "EnviorFile/info";//聚合图片详情  参数limit: 20 page: 1 type: 2 xmin: 116.38702405872394 xmax: 117.03109754505206 ymin: 39.36281234320512 ymax: 39.954014125431684



    //翻建的接口
    final String YL_GET_RENOVATED = BASE_URL + "renovated/getRenovated";//院落点查  String point
    final String YL_UPLOAD_FILE = BASE_URL + "renovated/uploadFile";//院落上传文件
    final String YL_RENOVATED_SAVE_OR_UPDATE = BASE_URL + "renovated/RenovatedSaveOrUpdate";//添加修改  renovatedDto
    final String YL_DELETE_FILE = BASE_URL + "renovated/deleteFile";//删除图片 @RequestBody Long... ids数组json
    //翻建统计 入参: date  yyyy-MM   非必穿 返回: StatisticalEntity集合
    final String RENOVATED_GET_STATISTICAL= BASE_URL + "renovated/getStatistical";
    //翻建列表 入参: jltime  yyyy-MM-dd   非必穿Qutype   非必穿 Xzqmc 非必穿Code 非必穿Bh 非必穿 Limit 非必穿 Page 非必穿 返回  PageInfo的Renovated集合
    final String RENOVATED_QUERY_LIST= BASE_URL + "renovated/queryList";
    final String RENOVATED_GET_RENOVATED_BYXY = BASE_URL + "renovated/getRenovatedByxy";//翻建点位聚合
    //获取翻建各步骤的数量
    final String RENOVATED_GET_STEPS= BASE_URL + "renovated/getSteps";
    //翻建详情 入参:id 翻建的id   必穿  返回 RenovatedDto实体
    final String RENOVATED_QUERY_INFO= BASE_URL + "renovated/queryInfo";
    final String YL_DELETE_RENOVATED = BASE_URL + "renovated/deleteRenovated";//对象



    ///////////////人居环境用地申请///////////
    final String APPLY_LAND_LIST= BASE_URL + "applyLand/list";//  列表 参数ApplyDto
    final String APPLY_LAND_INFO= BASE_URL + "applyLand/info";//  详情  参数 id
    final String APPLY_LAND_DELETE= BASE_URL + "applyLand/delete";//  删除   参数 ids
    final String APPLY_LAND_GET_BY_POINT= BASE_URL + "applyLand/getByPoint";//  点查  参数point
    final String POINT_GETYL= BASE_URL + "point/getYl";//  点查院落  参数point
    final String APPLY_LAND_SAVE_LAND= BASE_URL + "applyLand/saveLand";//  添加  参数 ApplyLandEntity集合
    final String APPLY_LAND_UPDATE_LAND= BASE_URL + "applyLand/updateLand";//  添加  参数 ApplyLandEntity集合
    final String APPLY_LAND_UPLOAD_FILE= BASE_URL + "applyLand/uploadFile";//  图片上传
    final String APPLY_LAND_DELETE_FILE= BASE_URL + "applyLand/deleteFile";//  图片删除 ids
    final String APPLY_LAND_GET_SUMMARY= BASE_URL + "applyLand/getSummary";//  图片删除 ids
    final String APPLY_LAND_GET_HUJI= BASE_URL + "applyLand/getHuji";//   查询没有分户的人员 参数 ylId
    final String APPLY_LAND_QUERY_COUNT= BASE_URL + "applyLand/queryCount";//    聚合展示
    final String COMPLAINTS_UPLOAD_FILE= BASE_URL + "complaints/uploadFile";//   接诉即办文件上传
    final String COMPLAINTS_DELETE_FILE= BASE_URL + "complaints/deleteFile";//   参数ids  接诉即办删除图片 添加 修改穿ids (图片id)
    final String APPLY_QUERY_COUNT= BASE_URL + "apply/queryCount";//  聚合  参数 PointAndTypeEntity 实体  返回ApplyCountEntity集合
    final String APPLYLAND_QUERY_STATISTICAL= BASE_URL + "applyLand/queryStatistical";//  参数 实体ApplyDto  用来获取用地每步骤的数量
    final String APPLY_QUERY_STATISTICAL= BASE_URL + "apply/queryStatistical";//  参数 实体ApplyDto  用来获取翻建每步骤的数量

    ///////////////////////////////人居环境翻建管理/////////////////////////////////
    final String APPLY_BY_POINT1= BASE_URL + "apply/applyByPoint";//点查  参数json   point
    final String APPLY_DELETE_HUJI= BASE_URL + "apply/deleteHuji";// 删除户籍  参数 ids
    final String APPLY_DELETE_FJFW= BASE_URL + "apply/deleteFjfw";// 上传文件   参数 file
    final String APPLY_LIST1= BASE_URL + "apply/list";//   列表  参数json   sptype   codelist   code xzqmc
    final String APPLY_GETSUMMARY= BASE_URL + "apply/getSummary";//统计
    final String APPLY_INFO1= BASE_URL + "apply/info";//   /apply / info   详情  参数json  applyId

    //一张图
    final String FLOAT_POPULAT_LIST = BASE_URL + "floatPopulat/list";//流动人口 点查
    final String FLOAT_POPULAT_DELETE = BASE_URL + "floatPopulat/delete";//流动人口 删除
    final String POINT_QUERY_BY_YZT = BASE_URL + "point/queryByYzt";
    //申请 列表 zjdSq/queryList 参数 page limit sptype(进度) xzqmc 返回 PageUtils
    final String SQGL_queryList_URL = BASE_URL + "zjdSq/queryList";
    //申请 点击查看详情 ZjdLz/info  参数 id 返回值 zjdLzentity
    final String SQGL_Detail_URL = BASE_URL + "zjdSq/info";
    final String EXIT_INFO = BASE_URL + "exit/info";//退出详情
    final String APPLY_CHECK = BASE_URL + "apply/spApply";
    final String APPLY_LOG = BASE_URL + "apply/saveLog";
    final String APPLY_APPLYBYPOINT = BASE_URL + "apply/applyByPoint";
    final String EXIT_QUERY_LIST = BASE_URL + "exit/queryList";
    //退出 上传图片
    final String TCGL_upload_img_URL = BASE_URL + "exit/upload";
    //退出 下一步
    final String TCGL_next_exit_URL = BASE_URL + "exit/approval";
    final String TCGL_DELETE_URL = BASE_URL + "exit/delete";
    //宅基地翻接口
    final String JBQK_APPLY_LIST_URL = BASE_URL + "/apply/list";
    //框查院落明细
    final String GETYlQueryFrame = BASE_URL + "point/getYlQueryFrame";
    final String GETTdlyQueryFrame = BASE_URL + "huhoinquery/queryBypoint";
    //框查土规明细
    final String GETTgQueryFrame = BASE_URL + "hoOwner/getTgQueryFrame";
    //框查非宅明细
    final String GETTgqueryYlFei = BASE_URL + "point/queryYlFei";
    //框查流动人口明细
    final String GETLdrkqueryList = BASE_URL + "floatPopulat/queryList";
    //框查人口明细
    final String GETZhaiQueryFrame = BASE_URL + "point/getZhaiQueryFrame";
    final String URL_FRAMENEW_JCXX = BASE_URL + "huhoinquery/queryJcxx";//新框查基础信息
    final String URL_FRAME_YEWU = BASE_URL + "huhoinquery/queryZjdCount";//框查业务
    final String HUHOINQUERY_QUERY_RECORD = BASE_URL + "/huhoinquery/queryRecord";//框选列表  参数 limit page type (1.报告文档  2.智慧测算框选 3 按村范围测算)  projectName 项目名
    final String MOVE_COST_CH_YE_FZH = BASE_URL + "moveCost/chYeFzh";
    final String MOVE_COST_CS = BASE_URL + "moveCost/cs";


    final String APPLY_SAVE1= BASE_URL + "apply/save";// 添加  参数json  ApplyEntity 实体(包含  YlEntity院落实体  hujiZhaiEntities户籍实体  applyFwEntities翻建房屋实体   applyFileList 翻建图片实体)
    final String APPLY_UPDATE1= BASE_URL + "apply/update";// 修改  参数json  ApplyEntity 实体(包含  YlEntity院落实体  hujiZhaiEntities户籍实体  applyFwEntities翻建房屋实体   applyFileList 翻建图片实体)
    final String APPLY_DELETE= BASE_URL + "apply/delete";//  删除文件  参数ids
    final String APPLY_FILE_DELETE_FILE= BASE_URL + "applyFile/deleteFile";//  删除文件  参数ids
    final String APPLY_FILE_UPLOAD_FILE= BASE_URL + "applyFile/uploadFile";//
    final String URL_PJPROJSAVE = BASE_URL + "pjProj/save";//业务拆腾片区添加  2
    final String URL_PJPROJFILE_UPLOAD = BASE_URL + "pjProjFile/upload";//图片删除  参数id  项目id
    final String URL_QUERYCTGK = BASE_URL + "demolition/queryCtgk";//业务拆腾片区里项目的流程数量
    final String URL_QUERYPROLIST = BASE_URL + "pjProj/queryProList";//业务拆腾片区里项目列表  1
    final String URL_PJPROJUPDATE = BASE_URL + "pjProj/update";//业务拆腾片区修改  1
    final String URL_PJPROJ_PJINFO = BASE_URL + "pjProj/pjInfo";//详情  参数id  项目id  1
    final String URL_PJPROJFILE_DELETE = BASE_URL + "pjProjFile/delete";//图片删除  参数id  项目id
    final String URL_PJPROJDELETE = BASE_URL + "pjProj/delete";//业务拆腾片区删除


    ////////////////////////////产业//////////////////////

     final String NYD_LIST = BASE_URL + "nyd/list";//农业园区 农用地统计

    ////////////////////////土地相关/////////////////////////
    final String POINT_QUERY_TDLY = BASE_URL + "point/queryTdly";//  土地利用
    final String POINT_QUERY_TDGH = BASE_URL + "point/queryTdgh";//   土地规划 参数 point
    final String POINT_QUERY_TDQS = BASE_URL + "point/queryTdqs";//   土地权属 参数 point
    final String POINT_QUERY_KJGH = BASE_URL + "point/queryKjgh";//   空间规划参数 point

    final String POINT_GET_TX_KXHZ = BASE_URL + "point/getTxKxHz";//   土地利用框查统计
    final String POINT_GET_TG_KXHZ = BASE_URL + "point/getTgKxHz";//    土地规划框查统计
    final String POINT_GET_QS_KXHZ = BASE_URL + "point/getQsKxHz";//    土地权属框查统计
    final String POINT_GET_GH_KXHZ = BASE_URL + "point/getGhKxHz";//    空间规划框查统计

    final String POINT_GET_TDLY_QUERY_FRAME = BASE_URL + "point/getTdlyQueryFrame";//      土地利用框查详情列表
    final String POINT_GET_TG_QUERY_FRAME = BASE_URL + "point/getTgQueryFrame";//     土地规划框查详情列表
    final String POINT_GET_QS_QUERY_FRAME = BASE_URL + "point/getQsQueryFrame";//       土地权属框查详情列表
    final String POINT_GET_GH_QUERY_FRAME = BASE_URL + "point/getGhQueryFrame";//        空间规划框查详情列表

    //////////////////////////////园区管理/////////////////////////////////
    final String ENTERPRISE_GETXZQCOUNT = BASE_URL + "enterprise/getXzqCount";//产业园区柱状图
    final String ENTERPRISE_QUERYBYPOINT = BASE_URL + "enterprise/queryByPoint";//产业园区点查
    final String ENTRPRIE_QUERY_LIST = BASE_URL + "enterprise/queryList";//列表 参数:EnterpriseDto 实体返回:PageInfo<EnterpriseInfoEntity>
    final String POINT_QUERYNYYQBYPOINT = BASE_URL + "point/queryNyyqByPoint";//农业园区点查
    final String POINT_QUERYDKFBYPOINT = BASE_URL + "point/queryDkfByPoint";//待开发点查
    final String NYD_GETCUNLIST = BASE_URL + "nyd/getCunList";//农用地村列表
    final String NYD_GETNydLIST = BASE_URL + "nyd/getNydList";//农用地村列表
    final String NYD_GETNydINFO = BASE_URL + "nyd/getNydInfo";//农用地详情
    final String NYD_UPLOADFILE = BASE_URL + "nyd/uploadFile";//农用地详情图片文件上传
    final String NYD_DELETEFILE = BASE_URL + "nyd/deleteFile";//农用地详情图片文件删除
    final String NYD_SAVEORUPDATE = BASE_URL + "nyd/saveOrUpdate";//农用地详情合同信息添加或修改
    final String NYD_SAVEZj = BASE_URL + "nyd/saveZj";//农用地详情合同下租金信息添加或修改
    final String NYD_DELETEZj = BASE_URL + "nyd/deleteZj";//农用地详情租金删除



    /////////////////////////////////////项目模块////////////////////////////////////
    final String QUERYDKFBYPOINT = BASE_URL + "point/queryXmByPoint";//项目模块点查（待开发）
    final String PROJECTSAVE = BASE_URL + "project/save";//添加项目
    final String PROJECTGETLIST = BASE_URL + "project/getList";//项目列表
    final String PROJECTGETINFO = BASE_URL + "project/getInfo";//项目详情
    final String PROJECTUPDATE = BASE_URL + "project/update";//项目修改
    final String PROJECTSTATISTICAL = BASE_URL + "project/statistical";//首页统计



    //////////////////////////////////////企业模块//////////////////////////////////////
    final String ENTERPRISE_UPLOAD_FILE = BASE_URL + "enterprise/uploadFile";//图片上传 gid  file
    final String ENTERPRISE_SAVEORUPDATE = BASE_URL + "enterprise/saveOrUpdate";//企业添加or修改 EnterpriseBasisEntity
    final String ENTERPRISE_SAVE_ENTERPRISE= BASE_URL + "enterprise/saveEnterprise";//添加or修改入驻企业信息 EnterpriseEntity
    final String ENTERPRISE_SAVE_BUSINESS= BASE_URL + "enterprise/saveBusiness";// 添加企业营收信息 EnterpriseBusiness
    final String ENTERPRISE_GET_BUSINESS= BASE_URL + "enterprise/getBusiness";//  查找企业营收信息 id month(年/月)
    final String ENTERPRISE_DELETE_FILE= BASE_URL + "enterprise/deleteFile";//  删除图片 ids [int]
    final String ENTERPRISE_DELETE= BASE_URL + "enterprise/delete";// 企业删除  ids [int]
    final String ENTERPRISE_DELETE_ENTERPRISE= BASE_URL + "enterprise/deleteEnterprise";// 企业删除  ids [int]
    final String ENTERPRISE_QUERY_INFO= BASE_URL + "enterprise/queryInfo";//企业详情 id

    /////////////////////////////////无违建//////////////////////////////////////////
    final String WUWEIJIAN_CLICKQUERY= BASE_URL + "wuweijian/clickQuery";//无违建点查
    //////////////////////////////////物业//////////////////////////////////////////////
    final String PROPERTY_QUERYBYPOINT= BASE_URL + "property/queryByPoint";//物业点查
    final String PROPERTY_UPLOADFILE= BASE_URL + "property/uploadFile";//返租合同上传
    final String PROPERTY_UPLOADROOMFILE= BASE_URL + "property/uploadRoomFile";//返租房间图片上传
    final String PROPERTY_SAVEORUPDATE= BASE_URL + "property/saveOrUpdate";//返租 房源信息添加/修改
    final String PROPERTY_QUERYBYCUN= BASE_URL + "property/queryByCun";//物业管理村统计
    final String PROPERTY_GETLEASEBACK= BASE_URL + "property/getLeaseback";//物业管理村返租列表
    final String PROPERTY_GETRENTAILLIST= BASE_URL + "property/getRentalList";//物业管理村出租列表
    final String FLOW_GET_INFOBYYLID = BASE_URL+"flow/getInfoByYlId";//  搜索或者点击查看流动人员详情  flowId:点击查看详情是使用   idCard:添加时搜索已添加信息
    final String PROPERTY_ADDLEASEINFO = BASE_URL+"property/addLeaseInfo";//添加入住信息
    final String PROPERTY_HISTORYLEASEINFO = BASE_URL+"property/historyLeaseInfo";//历史租客
    final String PROPERTY_RENEWALLEASEINFO = BASE_URL+"property/renewalLeaseInfo";//续租入住信息
    final String PROPERTY_GETLEASEINFO = BASE_URL+"property/getLeaseInfo";//查询入住信息
    final String PROPERTY_WITHDRAWALLEASEINFO = BASE_URL+"property/withdrawalLeaseInfo";//物业退租
    final String PROPERTY_DELETEFILEBYID = BASE_URL+"property/deleteFileById";//物业返租合同和院落删除
    final String PROPERTY_DELETEROOMFILE = BASE_URL+"property/deleteRoomFile";//物业返租房间图片删除
    final String PROPERTY_DELETELEASEFILEBYID = BASE_URL+"property/deleteLeaseFileById";//物业出租和续租文件删除

    final String OPINION_DELETE_FILE_BYID = BASE_URL+"opinion/deleteFileById";// 删除图片 参数 ids (map类型)
    final String OPINION_GET_DEPART = BASE_URL+"opinion/getDepart";//  获取部门  实体 SysDepartEntity
    final String OPINION_GET_INFO = BASE_URL+"opinion/getInfo";//  查询工单详情 参数 id  返回 实体 OpinionEntity
    final String OPINION_GET_LIST = BASE_URL+"opinion/getList";//  12345舆情列表 参数OpinionDto实体   返回OpinionEntity 实体
    final String OPINION_SAVE = BASE_URL+"opinion/save";//   保存工单 参数 OpinionEntity实体
    final String OPINION_UPDATE = BASE_URL+"opinion/update";// 修改/进行下一步工单 参数OpinionEntity实体
    final String OPINION_UPLOAD_FILE = BASE_URL+"opinion/uploadFile";//  上传图片 参数带OpinionFile实体
    final String OPINION_GET_COUNT = BASE_URL+"opinion/getCount";//  步骤统计
    final String ENUM_GET_OPINION_ENUM = BASE_URL+"enum/getOpinionEnum";//
    final String LIGHT_GET_LIST = BASE_URL+"light/getList";//
    final String POINT_GET_BYMAP = BASE_URL+"point/getByMap"; //根据图层行政区查询数据
    final String OPINION_GET_STATISTICAL = BASE_URL+"opinion/getStatistical"; // 首页统计









    //////////////////////////////////百村模块/////////////////////////////
    final String BCJC_GETYEARS = BASE_URL+"bcjc/getYears"; //获取年份
    final String BCJC_RKBDQKSAVE = BASE_URL+"bcjc/rkbdqkSave"; //人口变动情况添加
    final String BCJC_GETRKBDQK = BASE_URL+"bcjc/getRkbdqk"; //获取人口变动情况
    final String BCJC_RKBDQKUPDATE = BASE_URL+"bcjc/rkbdqkUpdate"; //人口变动情况修改、下一步、驳回、通过
    final String BCJC_LDLBDQKSAVE = BASE_URL+"bcjc/ldlbdqkSave"; //劳动力变动情况添加
    final String BCJC_GETLDLBDQK = BASE_URL+"bcjc/getLdlbdqk"; //获取劳动力变动情况
    final String BCJC_LDLBDQKUPDATE = BASE_URL+"bcjc/ldlbdqkUpdate"; //劳动力变动情况修改、下一步、驳回、通过
    final String BCJC_WCWGBDQKSAVE = BASE_URL+"bcjc/wcjybdqkSave"; //外出务工变动情况添加
    final String BCJC_GETWCWGBDQK = BASE_URL+"bcjc/getWcjybdqk"; //获取外出务工变动情况
    final String BCJC_WCJYBDQKUPDATE = BASE_URL+"bcjc/wcjybdqkUpdate"; //外出务工变动情况修改、下一步、驳回、通过
    final String BCJC_GETINWCRY = BASE_URL+"bcjc/getlnwcry"; // 获取历年长期外出务工人员变动表
    final String BCJC_CGBQKSAVE = BASE_URL+"bcjc/cgbqkSave"; // 村干部基本情况添加
    final String BCJC_GETCGBQK = BASE_URL+"bcjc/getCgbqk"; // 获取村干部情况

    final String BCJC_CJJZSRQKSAVE = BASE_URL+"bcjc/cjjzsrqkSave"; //村经济总收入情况添加
    final String BCJC_CJJZSRQKUPDATE = BASE_URL+"bcjc/cjjzsrqkUpdate"; //村经济总收入情况修改
    final String BCJC_GETCJJZSRQK = BASE_URL+"bcjc/getCjjzsrqk"; //获取村经济总收入情况
    final String BCJC_CJTJJZYSRQKSAVE = BASE_URL+"bcjc/cjtjjsrqkSave"; //村集体经济主要收入情况添加
    final String BCJC_CJTJJZYSRQKUPDATE = BASE_URL+"bcjc/cjtjjsrqkUpdate"; //村集体经济主要收入情况修改
    final String BCJC_GETCJTJJZYSRQK = BASE_URL+"bcjc/getCjtjjsrqk"; //获取村集体经济主要收入情况
    final String BCJC_GETCJJJSRZC = BASE_URL+"bcjc/getCjjjsrzc"; // 获取历年村集体经济收入支出情况
    final String BCJC_CJTJJZYZCQKSAVE = BASE_URL+"bcjc/cjtjjzcqkSave"; //村集体经济主要支出情况添加
    final String BCJC_CJTJJZYZCQKUPDATE = BASE_URL+"bcjc/cjtjjzcqkUpdate"; //村集体经济主要支出情况修改
    final String BCJC_GETCJTJJZYZCQK = BASE_URL+"bcjc/getCjtjjzcqk"; //获取村集体经济主要支出情况
    final String BCJC_TDLYSAVE = BASE_URL+"bcjc/tdlySave"; ///  土地利用添加
    final String BCJC_TDLY_UPDATE = BASE_URL+"bcjc/tdlyUpdate"; ///   土地利用修改
    final String BCJC_GETTDLY = BASE_URL+"bcjc/getTdly"; /// 获取土地利用
    final String BCJC_JTZCSAVE = BASE_URL+"bcjc/jtzcSave"; //集体资产添加
    final String BCJC_JTZCUPDATE = BASE_URL+"bcjc/jtzcUpdate"; //集体资产修改
    final String BCJC_GETJTZC = BASE_URL+"bcjc/getJtzc"; //获取集体资产
    final String BCJC_CYJGSAVE = BASE_URL+"bcjc/cyjgSave"; //产业结构添加
    final String BCJC_CYJGUPDATE = BASE_URL+"bcjc/cyjgUpdate"; //产业结构修改
    final String BCJC_GETSAVE = BASE_URL+"bcjc/getCyjg"; //获取产业结构
    final String BCJC_ZDQKSAVE = BASE_URL+"bcjc/zdqkSave"; //征地情况添加
    final String BCJC_ZDQKUPDATE = BASE_URL+"bcjc/zdqkUpdate"; //征地情况修改
    final String BCJC_GETZDQK = BASE_URL+"bcjc/getZdqk"; //获取征地情况
    final String BCJC_GETLNZD = BASE_URL+"bcjc/getLnzd"; // 获取历年征地情况
    final String BCJC_GETLNZHAND = BASE_URL+"bcjc/getLnZhand"; //  获取历年占地情况
    final String BCJC_ZHANQKSAVE = BASE_URL+"bcjc/zhandqkSave"; //占地情况添加
    final String BCJC_ZHANQKUPDATE = BASE_URL+"bcjc/zhandqkUpdate"; //占地情况修改
    final String BCJC_GETZHANDQK = BASE_URL+"bcjc/getZhandqk"; //获取占地情况
    final String BCJC_SKTSAVE = BASE_URL+"bcjc/sktSave"; //三块田添加
    final String BCJC_GETSKT = BASE_URL+"bcjc/getSkt"; //获取三块田
    final String MOVE_COST_GETFIVE_CS = BASE_URL+"moveCost/getFiveCs"; // 五个测算(土改)
    final String MOVE_COST_TWO_CS = BASE_URL+"moveCost/getTwoCs"; //  两个测算(土地征收、自住上楼)

    final String BCJC_GET_OPTIONS = BASE_URL+"bcjc/getOptions"; //   获取发展意愿选项|发展选项
    final String BCJC_FZYY_SAVE = BASE_URL+"bcjc/fzyySave"; //    发展意愿添加
    final String BCJC_GET_FZYY = BASE_URL+"bcjc/getFzyy"; //     获取发展意愿

    final String BCJC_GET_REPORT = BASE_URL+"bcjc/getReport"; //     百村报表


    final String BCJCJT_GET_LIST = BASE_URL + "bcjcjt/getList"; //     获取村里的家庭列表    参数：code years   返回实体：List<JtcyjcEntity>
    final String BCJCJT_IMPORT_DATA = BASE_URL + "bcjcjt/importData"; //       导入数据   参数：code 有数据的：years  被导入的：years1
    final String BCJCJT_GET_INFO = BASE_URL + "bcjcjt/getInfo"; //      根据id获取家庭详情   参数：id     返回实体：JtcyjcEntity
    final String BCJCJT_SAVE_OR_UPDATE = BASE_URL + "bcjcjt/saveOrUpdate"; //        添加或者修改  参数：JtcyjcEntity


    final String PROJECT_GETXMLIST = BASE_URL + "project/getXmList"; // project/getXmList  百村项目列表(旧)


    ////////////////////////////////////////项目///////////////////////////////////////////////////
    final String PROJECT_DELETE_FILEBYID = BASE_URL + "project/deleteFileById"; // 删除图片  【0】
    final String PROJECT_DELETE_PROJECT = BASE_URL + "project/deleteProject"; //  删除项目
    final String PROJECT_PROJECT = BASE_URL + "project/getProject"; //    百村项目列表(分页)
    final String PROJECT_SAVE_ORUPDATE = BASE_URL + "project/saveOrUpdate"; //     添加或修改
    final String PROJECT_UPLOAD_FILE = BASE_URL + "project/uploadFile"; //      上传图片
    final String PROJECT_GETBUPOINT = BASE_URL + "project/getByPoint"; //       查询点位是否在村界内
    final String PROJECT_SAVE_COLLECTION = BASE_URL + "project/saveCollection"; //        收藏
    final String PROJECT_DELETE_COLLECTION = BASE_URL + "project/deleteCollection"; //         取消收藏
    final String PROJECT_GET_CALLECTION = BASE_URL + "project/getCollection"; //          收藏列表
    final String PROJECT_GET_PROJECT_BYPOINT = BASE_URL + "project/getProjectByPoint"; //           点查
    final String PROJECT_GET_BY_XY = BASE_URL + "project/getByXY"; //      获取数量
    final String PROJECT_GET_LABEL= BASE_URL + "project/getLabel"; //        获取标签
    final String PROJECT_SAVE_LABEL= BASE_URL + "project/saveLabel"; //      添加标签
    final String PROJECT_DELETE_LABEL= BASE_URL + "project/deleteLabel"; //   删除标签








    //镇政府
    final int LDRK_ZZF= 1;//镇政府
    //流动人口
    final int LDRK_LGB = 1;//流管办
    final int LDRK_LGY = 3;//流管员
    //人居环境
    final int RJHJ_NY = 1;//内业人员（镇级审核人员）
    final int RJHJ_WYLR = 4;//外业录入人员
    final int RJHJ_WY = 5;//物业人员（处理问题页面）
    final int RJHJ_JSJB_LR = 6;//接诉即办录入人员



}