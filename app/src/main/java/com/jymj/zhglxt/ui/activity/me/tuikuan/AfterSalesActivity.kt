package com.jymj.zhglxt.ui.activity.me.tuikuan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.activity.me.order.OrderDetailsActivity
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext
import kotlinx.android.synthetic.main.activity_after_sales.*

/**
 * 选中售后类型
 */
class AfterSalesActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_sales)

        iv_act_after_sales_back.setOnClickListener {
            finish()
        }

        val data = intent.getSerializableExtra("afterData") as MallOrderInfo
        if (data!=null){
            if (data.orderMdseDetailsInfoList.size > 1) {
                ll_act_after_sales_commodity_shuang.visibility = View.VISIBLE
                ll_act_after_sales_commodity_dan.visibility = View.GONE

                rlv_act_after_sales_commodity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rlv_act_after_sales_commodity.adapter = object : BaseQuickAdapter<MallOrderMdseDetailsInfo, BaseViewHolder>(R.layout.item_ddqr_img, data.orderMdseDetailsInfoList) {
                    override fun convert(helper: BaseViewHolder?, items: MallOrderMdseDetailsInfo?) {
                        val tvItemDdqrImgImg = helper!!.getView<ImageView>(R.id.tv_item_ddqr_img_img)
                        helper.setText(R.id.tv_item_ddqr_img_name, items!!.mdseName)
                        val options = RequestOptions().transforms(RoundedCorners(30))
                                .error(R.drawable.icon_cnxh_ys)//图片圆角为30
                        Glide.with(this@AfterSalesActivity).load(items.mdsePicture) //图片地址
                                .apply(options)
                                .into(tvItemDdqrImgImg)
                    }
                }
                tv_act_after_sales_title2.text = "【商品待收货】"
            } else {//单
                ll_act_after_sales_commodity_dan.visibility = View.VISIBLE
                ll_act_after_sales_commodity_shuang.visibility = View.GONE
                Glide.with(this).load(data.orderMdseDetailsInfoList[0].mdsePicture).into(iv_act_after_sales_image)
                tv_act_after_sales_title.text = "【商品待收货】"+data.orderMdseDetailsInfoList[0].mdseName
            }
        }
        ll_act_after_sales_thtk.setOnClickListener {
            var intent = Intent(this,RetreatActivity::class.java)
            intent.putExtra("retreatType","退货退款")
            intent.putExtra("retreatData",data)
            startActivity(intent)
        }
        ll_act_after_sales_tk.setOnClickListener {
            var intent = Intent(this,RetreatActivity::class.java)
            intent.putExtra("retreatType","退款")
            intent.putExtra("retreatData",data)
            startActivity(intent)
        }
        ll_act_after_sales_hh.setOnClickListener {
            var intent = Intent(this,RetreatActivity::class.java)
            intent.putExtra("retreatType","换货")
            intent.putExtra("retreatData",data)
            startActivity(intent)
        }
    }
}
