package com.jymj.zhglxt.ui.activity.me.address

import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.CoordinateConverter
import com.amap.api.maps2d.model.*
import com.amap.api.services.core.AMapException
import com.amap.api.services.help.Inputtips
import com.amap.api.services.help.InputtipsQuery
import com.amap.api.services.help.Tip
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.basemessagesystem.ui.views.LegendEntity
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.bean.LayerEntity
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.me.SearchPoiBean
import com.jymj.zhglxt.ui.contract.me.DzglContract
import com.jymj.zhglxt.ui.model.me.DzglModel
import com.jymj.zhglxt.ui.presenter.me.DzglPresenter
import com.jymj.zhglxt.util.maputils.AMapUtil
import com.jymj.zhglxt.widget.TDTOSMTileProvider
import com.jymj.zhglxt.widget.TDTTileProvider
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.DisplayUtil
import com.setsuna.common.commonutils.ToastUtils
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_dzgl_select.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DzglSelectActivity : BaseActivity<DzglPresenter, DzglModel>(), DzglContract.View ,AMap.OnMapClickListener, AMapLocationListener , Inputtips.InputtipsListener{
    override fun returnDeleteAddress(message: String) {

    }

    override fun returnUpdateAddress(message: String) {

    }

    override fun returnAddressInfo(message: AddAddressBean) {

    }

    private val VEC_URL = "http://t1.tianditu.com/DataServer?T=vec_w&l=%d&x=%d&y=%d&tk=2877dbc929a5fbba4917e4cbb6dbc06a"
    var tdtNormalN = TDTTileProvider(TDTTileProvider.NORMAL_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/note/")//tdt
    var opt_tdtnN = TileOverlayOptions().tileProvider(tdtNormalN)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024)
            .diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/note/").diskCacheSize(10 * 1024)
    val tdtSta = TDTTileProvider(TDTTileProvider.STA, AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/")
    val opt_tdtSta = TileOverlayOptions().tileProvider(tdtSta).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/sta/").diskCacheSize(10 * 1024)
    val tdtStaN = TDTTileProvider(TDTTileProvider.STA_NOTE, AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/")
    val opt_tdtStaN = TileOverlayOptions().tileProvider(tdtStaN).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/staNote/").diskCacheSize(10 * 1024)

    val tdtNormal = TDTTileProvider(TDTTileProvider.NORMAL, AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/")
    val opt_tdtn = TileOverlayOptions().tileProvider(tdtNormal)
            .diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/normal/").diskCacheSize(10 * 1024)
    //?????????
    val tdtOsm = TDTOSMTileProvider("", AppMenus.getInstance().cardPath + "BMS/map/tdt/osmKong1/")//??????
    val opt_tdtOsm = TileOverlayOptions().tileProvider(tdtOsm).diskCacheEnabled(false).memoryCacheEnabled(true).memCacheSize(10 * 1024).diskCacheDir(AppMenus.getInstance().cardPath + "BMS/map/tdt/osmKong1/").diskCacheSize(10 * 1024)
    private var aMap: AMap? = null
    private var mPolygonOptions: PolygonOptions? = null
    private var mMyLocationStyle: MyLocationStyle? = null
    private var mPolygon: Polygon? = null
    var aMapLocation: AMapLocation? = null//????????????
    private val mLatLngs = ArrayList<LatLng>()
    private var mLocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var addMarker: Marker? = null
    private var pointStr = ""
    public val mLayers = ArrayList<LayerEntity>()
    public val mLayers1 = ArrayList<LayerEntity>()
    public var legendsYL: ArrayList<LegendEntity>? = null
    private var pointMarker: Marker? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_dzgl_select
    }

    override fun initPresenter() {
    }

    override fun initViews() {
//        MapsInitializer.replaceURL(VEC_URL, "tdt-vecosm43")
        map_act_dzgl_select.onCreate(intent.extras)

        initAMap()
        setAMap()
        
        supl_act_dzgl_select_search.panelHeight = DisplayUtil.dip2px(50f)
        supl_act_dzgl_select_search.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

//        aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(point, 12.5f, 0f, 0f)))
        mactv_act_dzgl_select_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val newText = s.toString().trim { it <= ' ' }
                if (!AMapUtil.IsEmptyOrNullString(newText)) {
                    val inputquery = InputtipsQuery(newText, mactv_act_dzgl_select_search.getText().toString())
                    val inputTips = Inputtips(this@DzglSelectActivity, inputquery)
                    inputTips.setInputtipsListener(this@DzglSelectActivity)
                    inputTips.requestInputtipsAsyn()
                }else{
                    adapterInt = 0
                    val inputquery = InputtipsQuery(addressType, "")
                    val inputTips = Inputtips(this@DzglSelectActivity, inputquery)
                    inputTips.setInputtipsListener(this@DzglSelectActivity)
                    inputTips.requestInputtipsAsyn()
                }
//                et_act_xm_issue_count.setText("${et_act_xm_issue_content.text.toString().length}/500")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        bt_act_dzgl_location.setOnClickListener {

            //??????
            if (aMapLocation != null) {
//                val model = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)//????????????  ???????????? ??? 2000
                val point = LatLng(aMapLocation!!.latitude, aMapLocation!!.longitude)
//            val point = LatLng(myLocation.latitude, myLocation.longitude)
                if (point.latitude > 0 && point.longitude > 0) {//point.longitude
                    aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point.latitude,
                            point.longitude)))
//                    pointMarker = aMap?.addMarker(MarkerOptions().position(point))

                    var pointString = "POINT(" + point.longitude + " " + point.latitude + ")"

                } else {
                    Thread(object : Runnable {
                        override fun run() {
                            try {
                                Thread.sleep(2000); // ??????3???
                            } catch (e: Exception) {
                                e.printStackTrace();
                            }
//                            val model1 = PositionUtil.gcj_To_Gps84(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            val point1 = LatLng(aMapLocation!!.latitude, aMapLocation!!.longitude)
                            //            val point = LatLng(myLocation.latitude, myLocation.longitude)
                            if (point1.latitude > 0 && point1.longitude > 0) {
                                aMap?.animateCamera(CameraUpdateFactory.changeLatLng(LatLng(point1.latitude,
                                        point1.longitude)))
//                                pointMarker = aMap?.addMarker(MarkerOptions().position(point1))

                                var pointString1 = "POINT(" + point1.longitude + " " + point1.latitude + ")"

                            } else {
                                Looper.prepare()
                                ToastUtils.showShort("")
                                Looper.loop()
                            }
                        }
                    }).start();
                }
            }
        }

        location()

        iv_act_dzgl_select_back.setOnClickListener {
            finish()
        }
        iv_act_dzgl_select_confirm.setOnClickListener {
//            ToastUtils.showShort(adapterStr)
            EventBus.getDefault().post(adapterStr)
            finish()
        }

    }

    override fun initDatas() {
    }

    override fun onMapClick(p0: LatLng?) {
        if (addMarker!=null){
            addMarker!!.remove()
        }
        val markerOptions = MarkerOptions()
        addMarker = aMap!!.addMarker(markerOptions.position(p0))
        pointStr = "POINT(" + (p0?.longitude) + " " + p0?.latitude + ")"
    }

    var addressType = ""
    var isFirstLoc = true
    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (aMapLocation != null) {
            this.aMapLocation = aMapLocation

            if (aMapLocation.getErrorCode() == 0) {
                //?????????????????????????????????????????????
                aMapLocation.getLocationType();//????????????????????????????????????????????????????????????????????????????????????
                aMapLocation.getLatitude();//????????????
                aMapLocation.getLongitude();//????????????
                aMapLocation.getAccuracy();//??????????????????
                var df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                var date = Date(aMapLocation.getTime());
                df.format(date);//????????????
                aMapLocation.getAddress();//???????????????option?????????isNeedAddress???false??????????????????????????????????????????????????????????????????GPS??????????????????????????????
                aMapLocation.getCountry();//????????????
                aMapLocation.getProvince();//?????????
                aMapLocation.getCity();//????????????
                aMapLocation.getDistrict();//????????????
                aMapLocation.getStreet();//????????????
                aMapLocation.getStreetNum();//?????????????????????
                aMapLocation.getCityCode();//????????????
                aMapLocation.getAdCode();//????????????

                // ???????????????????????????????????????????????????????????????????????????????????????????????????
                // isFirstLoc = false;
                if (isFirstLoc) {
                    isFirstLoc = false;
                    addressType = aMapLocation.aoiName
                    val inputquery = InputtipsQuery(aMapLocation.aoiName, "")
                    val inputTips = Inputtips(this@DzglSelectActivity, inputquery)
                    inputTips.setInputtipsListener(this@DzglSelectActivity)
                    inputTips.requestInputtipsAsyn()

                    //??????????????????
//                    aMap!!.moveCamera(CameraUpdateFactory.zoomTo(17f));

                    //???????????????????????????
                    /*val model = PositionUtil.gcj_To_Gps84(aMapLocation.latitude, aMapLocation.longitude)
                    val point = LatLng(model.wgLat, model.wgLon)
                    aMap!!.moveCamera(CameraUpdateFactory.changeLatLng(point));//LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())
                    aMap?.addMarker(MarkerOptions().position(point))*/
                    //?????????????????? ??????????????????????????????????????????
//                    mListener!!.onLocationChanged(aMapLocation);
                    //????????????
                    // aMap.addMarker(getMarkerOptions(amapLocation));
                    //??????????????????
                    var buffer = StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum())
//                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();

                }


            } else {
                //??????????????????ErrCode???????????????errInfo???????????????????????????????????????
// Toast.makeText(fragivity!!.getApplicationContext(), "????????????", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    /**
     * ?????????AMap??????
     */
    private fun initAMap() {
        if (aMap == null) {
            aMap = map_act_dzgl_select.getMap()
        }
        setUpMap()
    }
    /**
     * ????????????amap?????????
     */
    private fun setUpMap() { // ???????????????????????????
        mMyLocationStyle = MyLocationStyle()
        //??????????????????????????????????????????
        mMyLocationStyle!!.strokeWidth(3f)
        mMyLocationStyle!!.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap!!.uiSettings.isMyLocationButtonEnabled = true // ????????????????????????????????????
        aMap!!.uiSettings.isZoomGesturesEnabled = true
        aMap!!.uiSettings.isScrollGesturesEnabled = true
//        aMap!!.uiSettings.setZoomInByScreenCenter(false)
//                aMap!!.setMyLocationEnabled(true);// ?????????true??????????????????????????????????????????false??????????????????????????????????????????????????????false
// ???????????????????????????????????? ??????????????????????????????????????????????????????????????????
        aMap!!.setMyLocationStyle(mMyLocationStyle)
        aMap!!.isMyLocationEnabled = true // ?????????true?????????????????????????????????false??????????????????????????????????????????????????????false???
        mMyLocationStyle!!.showMyLocation(true)
        aMap!!.uiSettings.logoPosition = -1

    }
    /**
     * ????????????
     */
    private fun setAMap() { //????????????????????????????????????
        aMap!!.uiSettings.isMyLocationButtonEnabled = false
        aMap!!.uiSettings.isScaleControlsEnabled = false // ???????????????
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.setMapLanguage(AMap.CHINESE)
        aMap!!.setOnMapClickListener(this)
        mPolygonOptions = PolygonOptions()
//        aMap!!.addTileOverlay(opt_tdtnN)

//        aMap!!.// = 18f

    }

    var adapterInt = 0
    var adapterStr = ""
    var searchPoiBeans = ArrayList<SearchPoiBean>()
    var dzAdapter: BaseQuickAdapter<SearchPoiBean, BaseViewHolder>? = null
    override fun onGetInputtips(p0: MutableList<Tip>?, p1: Int) {
        if (p1 == AMapException.CODE_AMAP_SUCCESS&&p0!=null) {// ????????????
            val listString = java.util.ArrayList<String>()
            searchPoiBeans.clear()
            for (i in p0!!.indices) {
                listString.add(p0.get(i).getName())
                searchPoiBeans.add(SearchPoiBean(searchPoiBeans.size,p0.get(i).name,p0.get(i).district,p0.get(i).address,
                        "POINT(" + p0.get(i).point.longitude + " " + p0.get(i).point.latitude + ")",false,false))
            }
            rlv_act_dzgl_select_search.layoutManager = LinearLayoutManager(this)
            dzAdapter = object : BaseQuickAdapter<SearchPoiBean, BaseViewHolder>(R.layout.item_search_dz, searchPoiBeans) {
                override fun convert(helper: BaseViewHolder?, item: SearchPoiBean?) {
                    helper!!.setText(R.id.tv_itme_serach_dz_name, item!!.name)
                            .setText(R.id.tv_itme_serach_dz_detail, item!!.detailDz)
                    val ivItmeSerachDzSure = helper.getView<ImageView>(R.id.iv_itme_serach_dz_sure)
                    val ll_itme_search_dz = helper.getView<LinearLayout>(R.id.ll_itme_search_dz)

                    if (helper.adapterPosition == adapterInt){
                        item.isCheck = true
                        adapterInt = -1
                        adapterStr = item.district+item.name
                    }

                    if (item.isCheck) {
                        ivItmeSerachDzSure.visibility = View.VISIBLE
                    } else {
                        ivItmeSerachDzSure.visibility = View.GONE
                    }
                    /*if (!item.isFwn){//?????????????????????????????????
                        ll_itme_search_dz.setBackgroundColor(Color.parseColor("#33FF3F33"))
                    }*/


                    helper.itemView.setOnClickListener {
                        //                        if (item.isFwn){
                        for (i in searchPoiBeans){
                            i.isCheck = false
                            if (i.id == item.id){
                                item.isCheck = true
                            }
                        }
                        if (item.isCheck){
                            adapterStr = item.district+item.name
                            if (addMarker!=null){
                                addMarker!!.remove()
                            }
                            val markerOptions = MarkerOptions()
                            val center = getCenter(item.pointStr)
                            addMarker = aMap!!.addMarker(markerOptions.position(center))
                            addMarker!!.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.select_location_icon))
                            aMap?.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(center, 16f, 0f, 0f)))
                            notifyDataSetChanged()
                        }
                        /*}else{
                            ToastUtils.showShort("??????????????????????????????")
                        }*/
                    }


                }

            }
            rlv_act_dzgl_select_search.adapter = dzAdapter
            /*val aAdapter = ArrayAdapter(
                    applicationContext,
                    R.layout.route_inputs, listString)
            searchText.setAdapter(aAdapter)
            aAdapter.notifyDataSetChanged()*/
        } else {
//            ToastUtil.showerror(this@PoiKeywordSearchActivity, rCode)
            ToastUtils.showShort(""+p1)
        }

    }
    fun getCenter(center: String): LatLng {
        if (center != null) {
            if (!center.equals("")) {
                val points = center.substring(6, center.length - 1).split(" ").toTypedArray()
                return if (points != null && points.size > 1) {
                    val converter = CoordinateConverter()
                    // CoordType.GPS ?????????????????????
                    converter.from(CoordinateConverter.CoordType.GPS)
                    val sl = LatLng(points[1].toDouble(), points[0].toDouble())
                    // sourceLatLng?????????????????? LatLng??????
                    converter.coord(sl)
                    val latLng = converter.convert()
                    sl
                } else {
                    LatLng(0.0, 0.0)
                }
            } else {
                LatLng(0.0, 0.0)
            }
        }
        return LatLng(0.0, 0.0)
    }
    //?????????????????????
    private fun location() {
        //???????????????
        mLocationClient = AMapLocationClient(getApplicationContext())
        //????????????????????????
        mLocationClient!!.setLocationListener(this)
        //?????????????????????
        mLocationOption = AMapLocationClientOption()
        //????????????????????? Hight_Accuracy ??????????????????Battery_Saving ?????????????????????Device_Sensors??????????????????
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving)
        //????????????????????????????????????????????????????????????
        // mLocationOption!!.setNeedAddress(true);
        //???????????????????????????,?????????false
        mLocationOption!!.setOnceLocation(false)
        //????????????3s???????????????????????????????????????
        //??????setOnceLocationLatest(boolean b)?????????true??????????????????SDK???????????????3s???????????????????????????????????????
        // ??????????????????true???setOnceLocation(boolean b)????????????????????????true???????????????????????????false???
//        mLocationOption!!.setOnceLocationLatest(true)
        //????????????????????????WIFI????????????????????????
         mLocationOption!!.isWifiScan = true;
        //??????????????????????????????,?????????false????????????????????????
        mLocationOption!!.setMockEnable(false)
//        mLocationOption!!.setLocationCacheEnable(false)
        //??????????????????,????????????,?????????2000ms
         mLocationOption!!.setInterval(2000);
        //??????????????????????????????????????????
        mLocationClient!!.setLocationOption(mLocationOption)
        //????????????
        mLocationClient!!.startLocation()
//        mLocationClient!!.stopLocation()



    }

    override fun returnAddAddress(message: String) {

    }
    override fun returnAddressList(message: List<AddressInfo>) {

    }


    override fun onResume() {
        super.onResume()
        map_act_dzgl_select?.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_act_dzgl_select?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_act_dzgl_select?.onDestroy()

    }
    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }
    
}
