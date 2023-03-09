package com.jymj.basemessagesystem.ui.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.activity.shopping.CommodityDetailsActivity
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class CnxhAdapter(layoutResId: Int, data: MutableList<MdseInfo>?, activity: Activity) : BaseQuickAdapter<MdseInfo, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#######.########")
    var activity = activity

    override fun convert(helper: BaseViewHolder?, item: MdseInfo) {
        helper!!.setText(R.id.tv_item_serach_cnxh_money,"￥${helper.adapterPosition+2}")
                .setText(R.id.tv_item_serach_cnxh_gqmoney,"￥${helper.adapterPosition+1}")
                .setText(R.id.tv_item_serach_cnxh_title,item.name)
        val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
        val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
        tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
        val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                .transforms(RoundedCorners(30))//图片圆角为30
        Glide.with(activity).load(if (item.pictureList!=null && item.pictureList.size>0) item.pictureList.get(0).url else "") //图片地址
                .apply(options)
                .into(iv_item_serach_cnxh_img)

        helper.itemView.setOnClickListener {
            val intent = Intent(activity, CommodityDetailsActivity::class.java)
            intent.putExtra("details",item)
            activity.startActivity(intent)
        }

    }

}
