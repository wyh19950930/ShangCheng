package com.jymj.zhglxt.ui.activity.me

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.KdQueryBean
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.CouPonContract
import com.jymj.zhglxt.login.contract.WlxqContract
import com.jymj.zhglxt.login.presenter.CouPonPresenter
import com.jymj.zhglxt.login.presenter.WlxqPresenter
import com.jymj.zhglxt.ui.activity.shopping.CommodityDetailsActivity
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_wlxq.*
import java.util.regex.Pattern

class WlxqActivity : BaseActivity<WlxqPresenter, WlxqContract.Model>(), WlxqContract.View {

    var aMap: AMap? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_wlxq
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        map_act_wlxq.onCreate(this!!.intent.extras)// 此方法必须重写
        if (aMap == null) {
            aMap = map_act_wlxq.getMap()
        }
        iv_act_wlxc_back.setOnClickListener {
            finish()
        }
        tv_act_wlxq_query.setOnClickListener {
            mPresenter.getKuaiDiQuery("JD","0092198586855")
        }
        tv_act_wlxq_copy.setOnClickListener {
            //获取系统剪贴板
            val cbm =this?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cbm.text = tv_act_wlxq_kdbh.text.toString()
            ToastUtils.showShort("已经复制")
        }

    }

    override fun initDatas() {

        var gdjxspList = ArrayList<String>()
        gdjxspList.add("自产柴鸡蛋5斤")
        gdjxspList.add("自产柴鸡蛋5斤")
        gdjxspList.add("自产柴鸡蛋5斤")
        gdjxspList.add("自产柴鸡蛋5斤")
        gdjxspList.add("自产柴鸡蛋5斤")
        rlv_act_wlxq_gdjxsp.layoutManager = object:GridLayoutManager(this,2){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rlv_act_wlxq_gdjxsp.adapter = object: BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_search_cnxh,gdjxspList){
            override fun convert(helper: BaseViewHolder?, item: String?) {
                helper!!.setText(R.id.tv_item_serach_cnxh_money,"￥${helper.adapterPosition+2}")
                        .setText(R.id.tv_item_serach_cnxh_gqmoney,"￥${helper.adapterPosition+1}")
                        .setText(R.id.tv_item_serach_cnxh_title,item)
                val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
                val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
                tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
                val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                        .transforms(RoundedCorners(30))//图片圆角为30
                Glide.with(this@WlxqActivity).load("") //图片地址
                        .apply(options)
                        .into(iv_item_serach_cnxh_img)

                helper.itemView.setOnClickListener {
                    val intent = Intent(this@WlxqActivity, CommodityDetailsActivity::class.java)
                    startActivity(intent)
                }

            }
        }

        val kdQueryList = ArrayList<KdQueryBean.DataBean>()
        kdQueryList.add(KdQueryBean.DataBean("2022-12-28 06:32:22","2022-12-28 06:32:22","[广东省 深圳市 龙华区]快件离开【深圳转运中心】已发往【深圳市罗湖笋岗网点】13934339275","广东省 深圳市 龙华区"))
        kdQueryList.add(KdQueryBean.DataBean("2022-12-28 05:21:23","2022-12-28 05:21:23","[广东省 深圳市 龙华区]快件到达【深圳转运中心】","广东省 深圳市 龙华区"))
        kdQueryList.add(KdQueryBean.DataBean("2022-12-28 05:18:19","2022-12-28 05:18:19","[广东省 深圳市 龙华区]【深圳龙华区观澜网点】的陈月媚(0755-61670079)已取件。投诉电话：0755-61670079","广东省 深圳市 龙华区"))
        kdQueryList.add(KdQueryBean.DataBean("2022-12-28 04:33:31","2022-12-28 04:33:31","[广东省 深圳市 龙华区]快件离开【观湖集散点】已发往【深圳转运中心】","广东省 深圳市 龙华区"))
        rlv_act_wlxq_ddjd.layoutManager = object:LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false){
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        rlv_act_wlxq_ddjd.adapter = object:BaseQuickAdapter<KdQueryBean.DataBean,BaseViewHolder>(R.layout.item_wlxq,kdQueryList){
            override fun convert(helper: BaseViewHolder?, item: KdQueryBean.DataBean?) {
                helper!!.setText(R.id.tv_item_wlxq_content, item!!.context)
                        .setText(R.id.tv_item_wlxq_time, item!!.time)
                val iv_item_wlxq_img = helper.getView<ImageView>(R.id.iv_item_wlxq_img)
                val tv_item_wlxq_content = helper.getView<TextView>(R.id.tv_item_wlxq_content)
                val iv_item_wlxq_yxd = helper.getView<TextView>(R.id.iv_item_wlxq_yxd)
                val view_item_wlxq_line = helper.getView<View>(R.id.view_item_wlxq_line)
                if (helper.adapterPosition==0){//#666666
                    iv_item_wlxq_img.setImageResource(R.drawable.yuan_shi_red)
                    iv_item_wlxq_yxd.setTextColor(Color.parseColor("#FF000000"))
                    tv_item_wlxq_content.setTextColor(Color.parseColor("#FF000000"))
                }else{
                    iv_item_wlxq_img.setImageResource(R.drawable.yuan_gray)
                    iv_item_wlxq_yxd.setTextColor(Color.parseColor("#666666"))
                    tv_item_wlxq_content.setTextColor(Color.parseColor("#999999"))
                }
                if (helper.adapterPosition==kdQueryList.size-1){
                    view_item_wlxq_line.visibility = View.GONE
                }

                //电话号码的正则表达式（固定电话 + 11位手机号）
                var pattern = Pattern.compile("(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)|\\d{11}");
                Linkify.addLinks(tv_item_wlxq_content, pattern, "tel:");
                //修改超文本链接的字体颜色
                tv_item_wlxq_content.setLinkTextColor(getResources().getColor(R.color.blue));

            }
        }

        val latList = ArrayList<LatLng>()
        latList.add(LatLng(39.899672,116.4273404))
        latList.add(LatLng(39.901779,116.460299))
        latList.add(LatLng(39.89545,116.474032))
        latList.add(LatLng(39.884921,116.46304))
        latList.add(LatLng(39.83853,116.386141))
        latList.add(LatLng(39.77946,116.386141))
        latList.add(LatLng(39.709771,116.38339))
        AMapUtils.calculateLineDistance(latList.get(0), latList.get(latList.size-1))
//        aMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(latList.get(0), 16f, 0f, 0f)))

        var mPolylineOptions = PolylineOptions()
        mPolylineOptions!!.addAll(latList)
        mPolylineOptions!!.color(Color.argb(255, 0, 0, 255))
        aMap!!.addPolyline(mPolylineOptions.zIndex(0f))

        val mInflater = LayoutInflater.from(this)
        val view: View = mInflater.inflate(R.layout.item_image_zhizhen, null)
        val ivItemMarker = view.findViewById<ImageView>(R.id.iv_item_image_zhizhen)
        ivItemMarker.setImageResource(R.drawable.ic_yfh)//ic__zhizhen
        var bitmap = convertViewToBitmap(view)
        aMap?.addMarker(MarkerOptions().position(latList.get(0)))!!.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))


        val mInflater1 = LayoutInflater.from(this)
        val view1: View = mInflater1.inflate(R.layout.item_image_zhizhen, null)
        val ivItemMarker1 = view1.findViewById<ImageView>(R.id.iv_item_image_zhizhen)
        ivItemMarker1.setImageResource(R.drawable.ic_ysh)//ic__zhizhen
        var bitmap1 = convertViewToBitmap(view1)
        aMap?.addMarker(MarkerOptions().position(latList.get(latList.size-1)))!!.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap1))


        val mInflater2 = LayoutInflater.from(this)
        val view2: View = mInflater2.inflate(R.layout.item_image_zhizhen, null)
        val ivItemMarker2 = view2.findViewById<ImageView>(R.id.iv_item_image_zhizhen)
        ivItemMarker2.setImageResource(R.drawable.ic_ysh)//ic__zhizhen
        var bitmap2 = convertViewToBitmap(view2)
        aMap?.addMarker(MarkerOptions().position(latList.get(4)))!!.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap2))

    }

    override fun returnUser(user: User) {
        
    }

    override fun changeUser() {
        
    }

    override fun changeUser(msg: String) {
        
    }

    override fun returnWclCount(msg: Int) {
        
    }

    fun convertViewToBitmap(view: View): Bitmap {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }

//    map_act_wlxq
    override fun onResume() {
        super.onResume()
        map_act_wlxq.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_act_wlxq.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_act_wlxq.onDestroy()
    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
