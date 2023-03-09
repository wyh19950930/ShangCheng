package com.jymj.zhglxt.tools;

import android.graphics.Color;

import com.amap.api.maps2d.model.TileOverlayOptions;
import com.jymj.basemessagesystem.ui.views.LegendEntity;
import com.jymj.zhglxt.api.AppMenus;
import com.jymj.zhglxt.bean.LayerEntity;
import com.jymj.zhglxt.util.HeritageScopeTileProvider;
import com.jymj.zhglxt.util.HeritageScopeTileProvider1;
import com.setsuna.common.baseapp.AppCache;

import java.util.ArrayList;
import java.util.List;

/**
 * @package com.jymj.landrenovation.tools
 * 你@fileName LayerInit
 * @date 2019/2/2213:41
 * @name qzw
 */
public class LayerInit {
    public static void initMain(String mXzqCode, List<LayerEntity> layers, List<LayerEntity> layers1, ArrayList<LegendEntity> legendsYL) {
        layers.clear();
        layers1.clear();
        String NAME_XZGL = "bcjcxt";//postgres_towns
        String MAP_URLEND = "&srs=EPSG:3857&format=image%2Fpng&bbox=";
//        mXzqCode = "110115015";
        String NAME = "dx";
        String MAP = "http://139.224.52.12/geoserver/dx/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE";
//        String MAP_xzgl = "http://139.224.52.12/geoserver/postgres_towns/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE";
        String MAP_xzgl = "http://59.110.171.147:8081/geoserver/bcjcxt/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE";
        String MAP_URL = MAP;

        ///////////////////////////////
        String MAP_CG = "&layers=" + NAME + ":dx_cg&styles=&viewparams=code:";//城规//code:
        String MAP_YZT = "&layers=dx:hc_yzt&styles=&viewparams=code:";//code:
//        String MAP_NYD1 = "&layers=dx:" + NAME +  "_nyd&styles=&viewparams=code:";//code:
//        String MAP_HCZ_RJHJ = "&layers=postgres_towns:point_record&styles=&viewparams=code:";//hczzhglxt:point_record//point_record
//        String MAP_HCZ_FJ = "&layers=postgres_towns:xzgl_fj&styles=&viewparams=code:";//hczzhglxt:hc_fj
//        String MAP_HCZ_BASIS = "&layers=" + NAME_XZGL + ":yzt_qy&styles=&viewparams=code:";//enterprise_basis

        ///////////////乡镇管理///////////////
        String MAP_xzglcj = "&layers=" + NAME_XZGL + ":bcjc_cj&styles=&viewparams=code:";//村界
        String MAP_xzglcj_shi = "&layers=" + NAME_XZGL + ":bcjc_cj_shi&styles=&viewparams=code:";//村界
        String MAP_bcjc_cj_qu = "&layers=" + NAME_XZGL + ":bcjc_cj_qu&styles=&viewparams=code:";//村界
        String MAP_bcjc_cj_zhen = "&layers=" + NAME_XZGL + ":bcjc_cj_zhen&styles=&viewparams=code:";//村界
        String MAP_xzglzj = "&layers=" + NAME_XZGL + ":bcjc_zj&styles=&viewparams=code:";//镇界
        String MAP_xzglfw = "&layers=" + NAME_XZGL + ":bcjc_fw&styles=&viewparams=code:";//房屋  xzgl_fw
        String MAP_wy_sql = "&layers=" + NAME_XZGL + ":bcjc_wy&styles=&viewparams=code:";//房屋  xzgl_fw
        String MAP_xzglyl = "&layers=" + NAME_XZGL + ":bcjc_yl_ps&styles=&viewparams=code:";//院落  bcjc_yl_ps
        String MAP_xzglyzt = "&layers=" + NAME_XZGL + ":xzgl_yzt&styles=&viewparams=code:";//一张图新
        String MAP_xzgltg = "&layers=" + NAME_XZGL + ":xzgl_tg&styles=&viewparams=code:";//土规
        String MAP_xzgltdly= "&layers=" + NAME_XZGL + ":xzgl_tdly&styles=&viewparams=code:";//土地利用
        String MAP_xzglqs= "&layers=" + NAME_XZGL + ":bcjc_qs&styles=&viewparams=code:";//权属
        String MAP_xzglqsjt= "&layers=" + NAME_XZGL + ":bcjc_qs_jt&styles=&viewparams=code:";//权属集体
        String MAP_xzglgh= "&layers=" + NAME_XZGL + ":bcjc_gh&styles=&viewparams=code:";//规划
        String MAP_xzglldrk= "&layers=" + NAME_XZGL + ":xzgl_ldrk&styles=&viewparams=code:";//流动人口
//        String MAP_xzglyztcctt= "&layers=" + NAME_XZGL + ":yzt_cctt&styles=&viewparams=code:";//一张图拆除腾退
//        String MAP_xzglyztlh= "&layers=" + NAME_XZGL + ":yzt_lh&styles=&viewparams=code:";//一张图绿化
        String MAP_xzglyztsy= "&layers=" + NAME_XZGL + ":yzt_sy&styles=&viewparams=code:";//一张图水域
        String MAP_bcnyyq= "&layers=" + NAME_XZGL + ":bcnyyq&styles=&viewparams=";//一张图水域  code:
        String MAP_bcnyyq_sql = "&layers=" + NAME_XZGL + ":bcjc_bcnyyq_sql&styles=&viewparams=code:";//一张图水域
        String MAP_xzglyztdl= "&layers=" + NAME_XZGL + ":yzt_dl&styles=&viewparams=code:";//一张图道路
        String MAP_xzglyztdp= "&layers=" + NAME_XZGL + ":yzt_dp&styles=&viewparams=code:";//一张图大棚
        String MAP_xzglyztfz= "&layers=" + NAME_XZGL + ":yzt_fz&styles=&viewparams=code:";//一张图非宅
        String MAP_xzglyztskt= "&layers=" + NAME_XZGL + ":yzt_skt&styles=&viewparams=code:";//一张图三块田
        String MAP_xzglnyyq= "&layers=" + NAME_XZGL + ":xzgl_nyyq&styles=&viewparams=code:";//农业园区点
        String MAP_yztnyyq= "&layers=" + NAME_XZGL + ":yzt_nyyq&styles=&viewparams=code:";//农业园区面
        String MAP_czsq_fl = "&layers=" + NAME_XZGL + ":bcjc_czsq&styles=&viewparams=code:";//农业园区面
        String MAP_cyyq_fl= "&layers=" + NAME_XZGL + ":bcjc_cyyq_fl&styles=&viewparams=code:";//农业园区面
        String MAP_cyyq_fl_xin= "&layers=" + NAME_XZGL + ":bcjc_cyyq&styles=&viewparams=code:";//
        String MAP_nysq_fl_xin= "&layers=" + NAME_XZGL + ":bcjc_ncsq&styles=&viewparams=code:";//
        String MAP_nyyq_fl_xin= "&layers=" + NAME_XZGL + ":bcjc_nyyq&styles=&viewparams=code:";//
        String MAP_xzgl_yl_new= "&layers=" + NAME_XZGL + ":bcjc_yl&styles=&viewparams=code:";//农业园区面
        String MAP_xzgldkf= "&layers=" + NAME_XZGL + ":xzgl_dkf&styles=&viewparams=code:";//待开发
        String MAP_xzgldaikaifa= "&layers=" + NAME_XZGL + ":xzgl_daikaifa&styles=&viewparams=code:";//待开发
        String MAP_xzglxm= "&layers=" + NAME_XZGL + ":20221111手绘图加范围2&styles=&viewparams=code:";//项目  20221111手绘图加范围2
        String MAP_hsd2= "&layers=" + NAME_XZGL + ":hsd2&styles=&viewparams=code:";//项目
        String MAP_qj= "&layers=" + NAME_XZGL + ":qj&styles=&viewparams=code:";//项目
        String MAP_xzgltdly2009= "&layers=" + NAME_XZGL + ":xzgl_tdly2009&styles=&viewparams=code:";//土地利用2009
        String MAP_xzgltdly2018= "&layers=" + NAME_XZGL + ":tdly2018&styles=&viewparams=code:";//土地利用2018
        String MAP_xzgltdly2020= "&layers=" + NAME_XZGL + ":xzgl_tdly2020&styles=&viewparams=code:";//土地利用2020
        String MAP_xzgl_wwjxc= "&layers=" + NAME_XZGL + ":xzgl_wwjxc&styles=&viewparams=code:";//土地利用2020
        String MAP_xzgl_lnxz2017= "&layers=" + NAME_XZGL + ":xzgl_lnxz2017&styles=&viewparams=code:";//  pre3:2017年台账;
        String MAP_xzgl_lnxz2018= "&layers=" + NAME_XZGL + ":xzgl_lnxz2018&styles=&viewparams=code:";//  pre3:2018年台账;
        String MAP_xzgl_lnxz2019= "&layers=" + NAME_XZGL + ":xzgl_lnxz2019&styles=&viewparams=code:";//  pre3:2019年台账;
        String MAP_xzgl_lnxz2020= "&layers=" + NAME_XZGL + ":xzgl_lnxz2020&styles=&viewparams=code:";//  pre3:2020年台账;
        String MAP_xzglfwxc= "&layers=" + NAME_XZGL + ":xzgl_fwxc&styles=&viewparams=code:";//土地利用2020
//        String MAP_gyq = "&layers=" + NAME_XZGL + ":gyq&styles=&viewparams=code:";//工业区
        String MAP_zzjzdj = "&layers=" + NAME_XZGL + ":zzjzdj&styles=&viewparams=code:";//工业区
        String MAP_bc_project = "&layers=" + NAME_XZGL + ":project&styles=&viewparams=code:";//

//        String url_cg = MAP_URL + MAP_CG + mXzqCode + MAP_URLEND;
        //////////////////////////
//        String url_yzt = MAP_URL + MAP_YZT + mXzqCode + MAP_URLEND;
//        String url_nyd1 = MAP_URL + MAP_NYD1 + mXzqCode + MAP_URLEND;
//        String url_hcz_rjhj = MAP_xzgl + MAP_HCZ_RJHJ + mXzqCode + MAP_URLEND;//MAP_hczzhglxt
//        String url_hcz_fj = MAP_xzgl + MAP_HCZ_FJ + mXzqCode + MAP_URLEND;
//        String url_hcz_basis = MAP_xzgl + MAP_HCZ_BASIS + mXzqCode + MAP_URLEND;

        String url_xzglcj = MAP_xzgl + MAP_xzglcj + mXzqCode + MAP_URLEND;
        String url_xzglcj_shi = MAP_xzgl + MAP_xzglcj_shi + mXzqCode + MAP_URLEND;
        String url_bcjc_cj_qu = MAP_xzgl + MAP_bcjc_cj_qu + mXzqCode + MAP_URLEND;
        String url_bcjc_cj_zhen = MAP_xzgl + MAP_bcjc_cj_zhen + mXzqCode + MAP_URLEND;
        String url_xzglzj = MAP_xzgl + MAP_xzglzj + mXzqCode + MAP_URLEND;
        String url_xzglfw = MAP_xzgl + MAP_xzglfw + mXzqCode + MAP_URLEND;
        String url_wy_sql = MAP_xzgl + MAP_wy_sql + mXzqCode + MAP_URLEND;
        String url_xzglyl = MAP_xzgl + MAP_xzglyl + mXzqCode + MAP_URLEND;
        String url_xzglyzt = MAP_xzgl + MAP_xzglyzt + mXzqCode + MAP_URLEND;
        String url_xzgltg = MAP_xzgl + MAP_xzgltg + mXzqCode + MAP_URLEND;
        String url_xzgltdly = MAP_xzgl + MAP_xzgltdly + mXzqCode + MAP_URLEND;
        String url_xzglqs = MAP_xzgl + MAP_xzglqs + mXzqCode + MAP_URLEND;
        String url_xzglqsjt = MAP_xzgl + MAP_xzglqsjt + mXzqCode + MAP_URLEND;
        String url_xzglgh = MAP_xzgl + MAP_xzglgh + mXzqCode + MAP_URLEND;
//        String url_xzglldrk = MAP_xzgl + MAP_xzglldrk + mXzqCode + MAP_URLEND;
//        String url_xzglyztcctt = MAP_xzgl + MAP_xzglyztcctt + mXzqCode + MAP_URLEND;
//        String url_xzglyztlh = MAP_xzgl + MAP_xzglyztlh + mXzqCode + MAP_URLEND;
//        String url_xzglyztsy = MAP_xzgl + MAP_xzglyztsy + mXzqCode + MAP_URLEND;
        String url_bcnyyq = MAP_xzgl + MAP_bcnyyq  + MAP_URLEND;//+ mXzqCode
        String url_bcnyyq_sql = MAP_xzgl + MAP_bcnyyq_sql + mXzqCode + MAP_URLEND;
//        String url_xzglyztdl = MAP_xzgl + MAP_xzglyztdl + mXzqCode + MAP_URLEND;
        String url_xzglyztdp = MAP_xzgl + MAP_xzglyztdp + mXzqCode + MAP_URLEND;
//        String url_xzglyztfz = MAP_xzgl + MAP_xzglyztfz + mXzqCode + MAP_URLEND;
        String url_xzglyztskt = MAP_xzgl + MAP_xzglyztskt + mXzqCode + MAP_URLEND;
//        String url_xzglnyyq = MAP_xzgl + MAP_xzglnyyq + mXzqCode + MAP_URLEND;
//        String url_yztnyyq = MAP_xzgl + MAP_yztnyyq + mXzqCode + MAP_URLEND;
        String url_czsq_fl = MAP_xzgl + MAP_czsq_fl + mXzqCode + MAP_URLEND;
        String url_cyyq_fl = MAP_xzgl + MAP_cyyq_fl + mXzqCode + MAP_URLEND;
        String url_cyyq_fl_xin = MAP_xzgl + MAP_cyyq_fl_xin+ mXzqCode+ MAP_URLEND;// + mXzqCode
        String url_nysq_fl_xin = MAP_xzgl + MAP_nysq_fl_xin+ mXzqCode + MAP_URLEND;
        String url_nyyq_fl_xin = MAP_xzgl + MAP_nyyq_fl_xin+ mXzqCode+ MAP_URLEND;
        String url_xzgl_yl_new = MAP_xzgl + MAP_xzgl_yl_new + mXzqCode + MAP_URLEND;
        String url_xzgldkf = MAP_xzgl + MAP_xzgldkf + mXzqCode + MAP_URLEND;
//        String url_xzgldaikaifa = MAP_xzgl + MAP_xzgldaikaifa + mXzqCode + MAP_URLEND;
        String url_xzglxm = MAP_xzgl + MAP_xzglxm + mXzqCode + MAP_URLEND;
//        String url_hsd2 = MAP_xzgl + MAP_hsd2 + mXzqCode + MAP_URLEND;
        String url_qj = MAP_xzgl + MAP_qj + mXzqCode + MAP_URLEND;
        String url_xzgltdly2009 = MAP_xzgl + MAP_xzgltdly2009 + mXzqCode + MAP_URLEND;
        String url_xzgltdly2018 = MAP_xzgl + MAP_xzgltdly2018 + mXzqCode + MAP_URLEND;
        String url_xzgltdly2020 = MAP_xzgl + MAP_xzgltdly2020 + mXzqCode + MAP_URLEND;
        String url_xzgl_wwjxc = MAP_xzgl + MAP_xzgl_wwjxc + mXzqCode + MAP_URLEND;
//        String url_xzgl_lnxz2017 = MAP_xzgl + MAP_xzgl_lnxz2017 + mXzqCode + MAP_URLEND;
//        String url_xzgl_lnxz2018 = MAP_xzgl + MAP_xzgl_lnxz2018 + mXzqCode + MAP_URLEND;
//        String url_xzgl_lnxz2019 = MAP_xzgl + MAP_xzgl_lnxz2019 + mXzqCode + MAP_URLEND;
//        String url_xzgl_lnxz2020 = MAP_xzgl + MAP_xzgl_lnxz2020 + mXzqCode + MAP_URLEND;
//        String url_xzglfwxc = MAP_xzgl + MAP_xzglfwxc + mXzqCode + MAP_URLEND;
//        String url_gyq = MAP_xzgl + MAP_gyq + mXzqCode + MAP_URLEND;
        String url_zzjzdj = MAP_xzgl + MAP_zzjzdj + mXzqCode + MAP_URLEND;
        String url_bc_project = MAP_xzgl + MAP_bc_project + mXzqCode + MAP_URLEND;
        String sdCardPath = AppMenus.getInstance().getCardPath();
//        String path_cg = sdCardPath + "BMS/map/zhglxt/" + NAME + "hc" + mXzqCode+ "/cg/";
//        String path_yzt = sdCardPath + "BMS/map/zhglxt/" + NAME + "hc"+ mXzqCode + "/yzt/";
//        String path_nyd1 = sdCardPath + "BMS/map/zhglxt/" + NAME + "hc"+ mXzqCode + "/nyd1/";
//        String path_hcz_rjhj = sdCardPath + "BMS/map/zhglxt/" + NAME + "hc"+ mXzqCode + "/cz_rjhj/";
//        String path_hcz_fj = sdCardPath + "BMS/map/zhglxt/" + NAME + "hc"+ mXzqCode + "/cz_fj/";


        String path_xzglcj = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_cj_shi/";
        String path_xzglzj = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_zj/";
        String path_xzglfw = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_fw/";
        String path_wy_sql = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_wy/";
        String path_xzglyl = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_yl_ps/";
        String path_xzglyzt = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyzt/";
        String path_xzgltg = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgltg/";
        String path_xzgltdly = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgltdly/";
        String path_xzglqs = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_qs_2018/";
        String path_xzglqsjt = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_qsjt_2018/";
        String path_xzglgh = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglgh/";
//        String path_xzglldrk = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglldrk/";
//        String path_xzglyztcctt = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztcctt/";
//        String path_xzglyztlh = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztlh/";
//        String path_xzglyztsy = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztsy/";
        String path_bcnyyq = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcnyyq/";
        String path_bcnyyq_sql = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_bcnyyq_sql/";
//        String path_xzglyztdl = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztdl/";
        String path_xzglyztdp = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztdp/";
//        String path_xzglyztfz = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglyztfz/";
        String path_xzglyztskt = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_skt/";
//        String path_xzglnyyq = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglnyyq/";
//        String path_yztnyyq = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/yztnyyq/";
        String path_czsq_fl = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_czsq/";
        String path_cyyq_fl = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_cyyq_fl/";
        String path_cyyq_fl_xin = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_cyyq/";
        String path_nysq_fl_xin = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_ncsq/";
        String path_nyyq_fl_xin = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_nyyq/";
        String path_xzgl_yl_new = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_yl/";
        String path_xzgldkf = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgldkf/";
//        String path_xzgldaikaifa = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgldaikaifa/";
        String path_xzglxm = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglxm/";
//        String path_hsd2 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/hsd2/";
        String path_qj = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_qj/";
        String path_xzgltdly2009 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgltdly2009/";
        String path_xzgltdly2018 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/bcjc_tdly_2018/";
        String path_xzgltdly2020 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgltdly2020/";
        String path_xzgl_wwjxc = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgl_wwjxc/";
//        String path_xzgl_lnxz2017 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgl_lnxz2017/";
//        String path_xzgl_lnxz2018 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgl_lnxz2018/";
//        String path_xzgl_lnxz2019 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgl_lnxz2019/";
//        String path_xzgl_lnxz2020 = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzgl_lnxz2020/";
//        String path_xzglfwxc = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL+ mXzqCode+ "/xzglfwxc/";
//        String path_yzt_gyq = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL + mXzqCode + "/gyq/";
        String path_zzjzdj = sdCardPath + "BMS/map/zhglxt/" + NAME_XZGL + mXzqCode + "/zzjzdj/";

        /////////////////////////
//        HeritageScopeTileProvider layer_cg = new HeritageScopeTileProvider(url_cg, path_cg);
//        HeritageScopeTileProvider layer_yzt = new HeritageScopeTileProvider(url_yzt, path_yzt);
//        HeritageScopeTileProvider layer_nyd1 = new HeritageScopeTileProvider(url_nyd1, path_nyd1);
//        HeritageScopeTileProvider1 layer_hcz_rjhj = new HeritageScopeTileProvider1(url_hcz_rjhj);//, path_hcz_ldrk   不做缓存 便于图层刷新
//        HeritageScopeTileProvider1 layer_hcz_fj = new HeritageScopeTileProvider1(url_hcz_fj);//, path_hcz_fj  不做缓存 便于图层刷新
//        HeritageScopeTileProvider1 layer_hcz_basis = new HeritageScopeTileProvider1(url_hcz_basis);//, path_hcz_fj  不做缓存 便于图层刷新

//        HeritageScopeTileProvider1 layer_xzglbc = new HeritageScopeTileProvider1(url_xzglcj);
        HeritageScopeTileProvider1 layer_bc_project = new HeritageScopeTileProvider1(url_bc_project);
        HeritageScopeTileProvider1 layer_xzglcj = new HeritageScopeTileProvider1(url_xzglcj);
        HeritageScopeTileProvider1 layer_xzglcj_shi = new HeritageScopeTileProvider1(url_xzglcj_shi);
        HeritageScopeTileProvider1 layer_bcjc_cj_qu = new HeritageScopeTileProvider1(url_bcjc_cj_qu);
        HeritageScopeTileProvider1 layer_bcjc_cj_zhen = new HeritageScopeTileProvider1(url_bcjc_cj_zhen);
        HeritageScopeTileProvider layer_xzglzj = new HeritageScopeTileProvider(url_xzglzj, path_xzglzj);
        HeritageScopeTileProvider layer_xzglfw = new HeritageScopeTileProvider(url_xzglfw, path_xzglfw);
        HeritageScopeTileProvider layer_wy_sql = new HeritageScopeTileProvider(url_wy_sql, path_wy_sql);
        HeritageScopeTileProvider layer_xzglyl = new HeritageScopeTileProvider(url_xzglyl, path_xzglyl);
//        HeritageScopeTileProvider layer_xzglyzt = new HeritageScopeTileProvider(url_xzglyzt, path_xzglyzt);
//        HeritageScopeTileProvider layer_xzgltg = new HeritageScopeTileProvider(url_xzgltg, path_xzgltg);
//        HeritageScopeTileProvider layer_xzgltdly = new HeritageScopeTileProvider(url_xzgltdly, path_xzgltdly);
        HeritageScopeTileProvider layer_xzglqs = new HeritageScopeTileProvider(url_xzglqs, path_xzglqs);
        HeritageScopeTileProvider layer_xzglqsjt = new HeritageScopeTileProvider(url_xzglqsjt, path_xzglqsjt);
        HeritageScopeTileProvider layer_xzglgh = new HeritageScopeTileProvider(url_xzglgh, path_xzglgh);
//        HeritageScopeTileProvider1 layer_xzglldrk = new HeritageScopeTileProvider1(url_xzglldrk);
//        HeritageScopeTileProvider layer_xzglyztcctt = new HeritageScopeTileProvider(url_xzglyztcctt, path_xzglyztcctt);
//        HeritageScopeTileProvider layer_xzglyztlh = new HeritageScopeTileProvider(url_xzglyztlh, path_xzglyztlh);
//        HeritageScopeTileProvider layer_xzglyztsy = new HeritageScopeTileProvider(url_xzglyztsy, path_xzglyztsy);
        HeritageScopeTileProvider layer_bcnyyq = new HeritageScopeTileProvider(url_bcnyyq, path_bcnyyq);
        HeritageScopeTileProvider layer_bcnyyq_sql = new HeritageScopeTileProvider(url_bcnyyq_sql, path_bcnyyq_sql);
//        HeritageScopeTileProvider layer_xzglyztdl = new HeritageScopeTileProvider(url_xzglyztdl, path_xzglyztdl);
        HeritageScopeTileProvider layer_xzglyztdp = new HeritageScopeTileProvider(url_xzglyztdp, path_xzglyztdp);
//        HeritageScopeTileProvider layer_xzglyztfz = new HeritageScopeTileProvider(url_xzglyztfz, path_xzglyztfz);
        HeritageScopeTileProvider layer_xzglyztskt = new HeritageScopeTileProvider(url_xzglyztskt, path_xzglyztskt);
//        HeritageScopeTileProvider layer_xzglnyyq = new HeritageScopeTileProvider(url_xzglnyyq, path_xzglnyyq);
//        HeritageScopeTileProvider layer_yztnyyq = new HeritageScopeTileProvider(url_yztnyyq, path_yztnyyq);
        HeritageScopeTileProvider layer_czsq_fl = new HeritageScopeTileProvider(url_czsq_fl, path_czsq_fl);
        HeritageScopeTileProvider layer_cyyq_fl = new HeritageScopeTileProvider(url_cyyq_fl, path_cyyq_fl);
        HeritageScopeTileProvider layer_cyyq_fl_xin = new HeritageScopeTileProvider(url_cyyq_fl_xin, path_cyyq_fl_xin);
        HeritageScopeTileProvider layer_nysq_fl_xin = new HeritageScopeTileProvider(url_nysq_fl_xin, path_nysq_fl_xin);
        HeritageScopeTileProvider layer_nyyq_fl_xin = new HeritageScopeTileProvider(url_nyyq_fl_xin, path_nyyq_fl_xin);
        HeritageScopeTileProvider layer_xzgl_yl_new = new HeritageScopeTileProvider(url_xzgl_yl_new, path_xzgl_yl_new);
        HeritageScopeTileProvider layer_xzgldkf = new HeritageScopeTileProvider(url_xzgldkf, path_xzgldkf);
//        HeritageScopeTileProvider layer_xzgldaikaifa = new HeritageScopeTileProvider(url_xzgldaikaifa, path_xzgldaikaifa);
        HeritageScopeTileProvider layer_xzglxm = new HeritageScopeTileProvider(url_xzglxm, path_xzglxm);
//        HeritageScopeTileProvider layer_hsd2 = new HeritageScopeTileProvider(url_hsd2, path_hsd2);
        HeritageScopeTileProvider layer_qj = new HeritageScopeTileProvider(url_qj, path_qj);
        HeritageScopeTileProvider layer_xzgltdly2009 = new HeritageScopeTileProvider(url_xzgltdly2009, path_xzgltdly2009);
        HeritageScopeTileProvider layer_xzgltdly2018 = new HeritageScopeTileProvider(url_xzgltdly2018, path_xzgltdly2018);
        HeritageScopeTileProvider layer_xzgltdly2020 = new HeritageScopeTileProvider(url_xzgltdly2020, path_xzgltdly2020);
        HeritageScopeTileProvider layer_xzgl_wwjxc = new HeritageScopeTileProvider(url_xzgl_wwjxc, path_xzgl_wwjxc);
//        HeritageScopeTileProvider layer_xzgl_lnxz2017 = new HeritageScopeTileProvider(url_xzgl_lnxz2017, path_xzgl_lnxz2017);
//        HeritageScopeTileProvider layer_xzgl_lnxz2018 = new HeritageScopeTileProvider(url_xzgl_lnxz2018, path_xzgl_lnxz2018);
//        HeritageScopeTileProvider layer_xzgl_lnxz2019 = new HeritageScopeTileProvider(url_xzgl_lnxz2019, path_xzgl_lnxz2019);
//        HeritageScopeTileProvider layer_xzgl_lnxz2020 = new HeritageScopeTileProvider(url_xzgl_lnxz2020, path_xzgl_lnxz2020);
//        HeritageScopeTileProvider layer_xzglfwxc = new HeritageScopeTileProvider(url_xzglfwxc, path_xzglfwxc);
//        HeritageScopeTileProvider layer_gyq = new HeritageScopeTileProvider(url_gyq, path_yzt_gyq);
        HeritageScopeTileProvider layer_zzjzdj = new HeritageScopeTileProvider(url_zzjzdj, path_zzjzdj);


//        TileOverlayOptions opt_cg = new TileOverlayOptions().tileProvider(layer_cg).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_cg).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_yzt = new TileOverlayOptions().tileProvider(layer_yzt).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_yzt).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_nyd1 = new TileOverlayOptions().tileProvider(layer_nyd1).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_nyd1).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_hcz_rjhj = new TileOverlayOptions().tileProvider(layer_hcz_rjhj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_hcz_rjhj).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_hcz_fj = new TileOverlayOptions().tileProvider(layer_hcz_fj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_hcz_fj).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_hcz_basis = new TileOverlayOptions().tileProvider(layer_hcz_basis).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_hcz_fj).diskCacheSize(10 * 1024);


        TileOverlayOptions opt_bc_project = new TileOverlayOptions().tileProvider(layer_bc_project).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglcj = new TileOverlayOptions().tileProvider(layer_xzglcj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglcj_shi = new TileOverlayOptions().tileProvider(layer_xzglcj_shi).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024);
        TileOverlayOptions opt_bcjc_cj_qu = new TileOverlayOptions().tileProvider(layer_bcjc_cj_qu).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024);
        TileOverlayOptions opt_bcjc_cj_zhen = new TileOverlayOptions().tileProvider(layer_bcjc_cj_zhen).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglcj).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglzj = new TileOverlayOptions().tileProvider(layer_xzglzj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglzj).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglfw = new TileOverlayOptions().tileProvider(layer_xzglfw).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_wy_sql).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_wy_sql = new TileOverlayOptions().tileProvider(layer_wy_sql).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglfw).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglyl = new TileOverlayOptions().tileProvider(layer_xzglyl).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyl).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyzt = new TileOverlayOptions().tileProvider(layer_xzglyzt).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyzt).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgltg = new TileOverlayOptions().tileProvider(layer_xzgltg).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgltg).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgltdly = new TileOverlayOptions().tileProvider(layer_xzgltdly).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgltdly).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglqs = new TileOverlayOptions().tileProvider(layer_xzglqs).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglqs).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglqsjt = new TileOverlayOptions().tileProvider(layer_xzglqsjt).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglqsjt).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglgh = new TileOverlayOptions().tileProvider(layer_xzglgh).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglgh).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglldrk = new TileOverlayOptions().tileProvider(layer_xzglldrk).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglldrk).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyztcctt = new TileOverlayOptions().tileProvider(layer_xzglyztcctt).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztcctt).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyztlh = new TileOverlayOptions().tileProvider(layer_xzglyztlh).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztlh).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyztsy = new TileOverlayOptions().tileProvider(layer_xzglyztsy).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztsy).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_bcnyyq = new TileOverlayOptions().tileProvider(layer_bcnyyq).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_bcnyyq).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_bcnyyq_sql = new TileOverlayOptions().tileProvider(layer_bcnyyq_sql).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_bcnyyq_sql).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyztdl = new TileOverlayOptions().tileProvider(layer_xzglyztdl).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztdl).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglyztdp = new TileOverlayOptions().tileProvider(layer_xzglyztdp).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztdp).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglyztfz = new TileOverlayOptions().tileProvider(layer_xzglyztfz).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztfz).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglyztskt = new TileOverlayOptions().tileProvider(layer_xzglyztskt).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglyztskt).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglnyyq = new TileOverlayOptions().tileProvider(layer_xzglnyyq).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglnyyq).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_yztnyyq = new TileOverlayOptions().tileProvider(layer_yztnyyq).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_yztnyyq).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_czsq_fl = new TileOverlayOptions().tileProvider(layer_czsq_fl).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_czsq_fl).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_cyyq_fl = new TileOverlayOptions().tileProvider(layer_cyyq_fl).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_cyyq_fl).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_cyyq_fl_xin = new TileOverlayOptions().tileProvider(layer_cyyq_fl_xin).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_cyyq_fl_xin).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_nysq_fl_xin = new TileOverlayOptions().tileProvider(layer_nysq_fl_xin).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_nysq_fl_xin).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_nyyq_fl_xin = new TileOverlayOptions().tileProvider(layer_nyyq_fl_xin).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_nyyq_fl_xin).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgl_yl_new = new TileOverlayOptions().tileProvider(layer_xzgl_yl_new).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_yl_new).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgldkf = new TileOverlayOptions().tileProvider(layer_xzgldkf).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgldkf).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgldaikaifa = new TileOverlayOptions().tileProvider(layer_xzgldaikaifa).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgldaikaifa).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzglxm = new TileOverlayOptions().tileProvider(layer_xzglxm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglxm).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_hsd2 = new TileOverlayOptions().tileProvider(layer_hsd2).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_hsd2).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_qj = new TileOverlayOptions().tileProvider(layer_qj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_qj).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgltdly2009 = new TileOverlayOptions().tileProvider(layer_xzgltdly2009).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgltdly2009).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgltdly2018 = new TileOverlayOptions().tileProvider(layer_xzgltdly2018).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgltdly2018).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgltdly2020 = new TileOverlayOptions().tileProvider(layer_xzgltdly2020).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgltdly2020).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_xzgl_wwjxc = new TileOverlayOptions().tileProvider(layer_xzgl_wwjxc).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_wwjxc).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgl_lnxz2017 = new TileOverlayOptions().tileProvider(layer_xzgl_lnxz2017).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_lnxz2017).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgl_lnxz2018 = new TileOverlayOptions().tileProvider(layer_xzgl_lnxz2018).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_lnxz2018).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgl_lnxz2019 = new TileOverlayOptions().tileProvider(layer_xzgl_lnxz2019).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_lnxz2019).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzgl_lnxz2020 = new TileOverlayOptions().tileProvider(layer_xzgl_lnxz2020).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzgl_lnxz2020).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_xzglfwxc = new TileOverlayOptions().tileProvider(layer_xzglfwxc).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_xzglfwxc).diskCacheSize(10 * 1024);
//        TileOverlayOptions opt_gyq = new TileOverlayOptions().tileProvider(layer_gyq).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_yzt_gyq).diskCacheSize(10 * 1024);
        TileOverlayOptions opt_zzjzdj = new TileOverlayOptions().tileProvider(layer_zzjzdj).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(path_zzjzdj).diskCacheSize(10 * 1024);
        // LayerEntity: 第一个参数：图层名字 第二个参数：图层 第三个参数：是否添加图层

        layers.add(new LayerEntity("底图", null, false,2));
        layers.add(new LayerEntity("天地图", null, false,2));
        layers.add(new LayerEntity("影像图", null, false,2));
//        layers.add(new LayerEntity("城规", opt_cg, false,2));
//        layers.add(new LayerEntity("农用地", opt_nyd1, false,2));
//        layers.add(new LayerEntity("一张图", opt_yzt, false,2));
//        layers.add(new LayerEntity("人居环境", opt_hcz_rjhj,false,1));//新加
//        layers.add(new LayerEntity("翻建", opt_hcz_fj,false,1));//新加
//        layers.add(new LayerEntity("工业区", opt_gyq,false,1));
        layers.add(new LayerEntity("基准地价", opt_zzjzdj,false,2));
//        layers.add(new LayerEntity("企业", opt_hcz_basis,false,1));//新加



        ///////////////////0414新加/////////////////////
        //乡镇管理
//        layers.add(new LayerEntity("一张图新", opt_xzglyzt, false,1));
       /* if (AppCache.getInstance().getCode().equals("100110")){
            layers.add(new LayerEntity("房屋", opt_xzglfw,true,1));
        }else {*/
        layers.add(new LayerEntity("院落", opt_xzglyl,false,1));
        layers.add(new LayerEntity("物业", opt_wy_sql,false,1));
//        }
//        layers.add(new LayerEntity("土规", opt_xzgltg,false,2));
//        layers.add(new LayerEntity("土现", opt_xzgltdly,false));
        layers.add(new LayerEntity("权属", opt_xzglqs,false,1));
        layers.add(new LayerEntity("权属集体", opt_xzglqsjt,false,1));
        layers.add(new LayerEntity("规划", opt_xzglgh,false,2));
//        layers.add(new LayerEntity("拆除腾退", opt_xzglyztcctt,false,1));
//        layers.add(new LayerEntity("绿化", opt_xzglyztlh,false,1));
//        layers.add(new LayerEntity("水域", opt_xzglyztsy,false,1));
        layers.add(new LayerEntity("农业园区新", opt_bcnyyq,false,1));
        layers.add(new LayerEntity("农业园区新村", opt_bcnyyq_sql,false,1));
//        layers1.add(new LayerEntity("道路", opt_xzglyztdl,false,1));
//        layers.add(new LayerEntity("大棚", opt_xzglyztdp,true,1));
//        layers1.add(new LayerEntity("非宅", opt_xzglyztfz,false,1));
        layers1.add(new LayerEntity("三块田", opt_xzglyztskt,false,1));
//        layers1.add(new LayerEntity("流动人口", opt_xzglldrk,false,1));
//        layers1.add(new LayerEntity("农业园区点", opt_xzglnyyq,false,1));
//        layers1.add(new LayerEntity("农业园区面", opt_yztnyyq,false,1));
        layers1.add(new LayerEntity("城镇社区", opt_czsq_fl,false,1));
        layers1.add(new LayerEntity("农业园区超新", opt_nyyq_fl_xin,false,1));
        layers1.add(new LayerEntity("产业园区", opt_cyyq_fl,false,1));
        layers1.add(new LayerEntity("产业园区新", opt_cyyq_fl_xin,false,1));
        layers1.add(new LayerEntity("农村社区新", opt_nysq_fl_xin,false,1));
        layers1.add(new LayerEntity("新院落", opt_xzgl_yl_new,false,1));
        layers.add(new LayerEntity("房屋", opt_xzglfw,false,1));
//        layers.add(new LayerEntity("待开发", opt_xzgldkf,false,1));
//        layers1.add(new LayerEntity("待开发", opt_xzgldaikaifa,false,1));
        layers1.add(new LayerEntity("土地利用2009", opt_xzgltdly2009,false,1));
        layers1.add(new LayerEntity("土地利用2018", opt_xzgltdly2018,false,1));
        layers1.add(new LayerEntity("土地利用2020", opt_xzgltdly2020,false,1));
        layers1.add(new LayerEntity("无违建巡查", opt_xzgl_wwjxc,false,1));
//        layers1.add(new LayerEntity("拆违腾地2017年台账", opt_xzgl_lnxz2017,false,2));
//        layers1.add(new LayerEntity("拆违腾地2018年台账", opt_xzgl_lnxz2018,false,2));
//        layers1.add(new LayerEntity("拆违腾地2019年台账", opt_xzgl_lnxz2019,false,2));
//        layers1.add(new LayerEntity("拆违腾地2020年台账", opt_xzgl_lnxz2020,false,2));
//        layers1.add(new LayerEntity("房屋巡查", opt_xzglfwxc,false,1));
        if (AppCache.getInstance().getType()==1){
            layers1.add(new LayerEntity("镇界", opt_xzglzj,true,2));
            layers1.add(new LayerEntity("区界", opt_qj,true,2));
        }else if (AppCache.getInstance().getType()==2){
            layers1.add(new LayerEntity("镇界", opt_xzglzj,true,2));
            layers1.add(new LayerEntity("区界", opt_qj,true,2));
        }else if (AppCache.getInstance().getType()==3){
            layers1.add(new LayerEntity("镇界", opt_xzglzj,true,2));
        }else if (AppCache.getInstance().getType()==4){
        }
        if (mXzqCode.length()==6){
            layers1.add(new LayerEntity("村界", opt_xzglcj_shi,true,2));
        }else if (mXzqCode.length()==9){
            layers1.add(new LayerEntity("村界", opt_bcjc_cj_qu,true,2));
        }else if (mXzqCode.length()==12){
            layers1.add(new LayerEntity("村界", opt_bcjc_cj_zhen,true,2));
        }else if (mXzqCode.length()==15){
            layers1.add(new LayerEntity("村界", opt_xzglcj,true,2));
        }


        layers1.add(new LayerEntity("项目", opt_xzglxm,false,1));
        layers1.add(new LayerEntity("新项目", opt_bc_project,false,2));
//        layers1.add(new LayerEntity("黄山店图", opt_hsd2,false,1));
        /*if (AppCache.getInstance().getCunCode().length()==15) {
            layers1.add(new LayerEntity("镇界", opt_xzglzj,false,2));
            layers1.add(new LayerEntity("区界", opt_qj,false,2));
        }else {
            layers1.add(new LayerEntity("镇界", opt_xzglzj,true,2));
            layers1.add(new LayerEntity("区界", opt_qj,true,2));
        }*/

//        legendsYL.add(new LegendEntity("4分以下", Color.argb(153, 56, 168, 0)));
//        legendsYL.add(new LegendEntity("4分-6分", Color.argb(153, 139, 209, 0)));
//        legendsYL.add(new LegendEntity("6分-8分", Color.argb(153, 255, 255, 0)));
//        legendsYL.add(new LegendEntity("8分-1亩", Color.argb(153, 255, 128, 0)));
//        legendsYL.add(new LegendEntity("1亩以上", Color.argb(153, 255, 0, 0)));
    }
}
