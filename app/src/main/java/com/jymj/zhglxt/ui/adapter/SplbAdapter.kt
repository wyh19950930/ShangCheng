package com.jymj.basemessagesystem.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.ContentLoadingProgressBar
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

class SplbAdapter(layoutResId: Int, data: MutableList<MdseInfo>?, activity: Activity, isShowCore: Boolean) : BaseQuickAdapter<MdseInfo, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#######.########")
    var activity = activity
    var isShowCore = isShowCore //是否显示评分

    override fun convert(helper: BaseViewHolder?, item: MdseInfo) {
        val ivCplbImg = helper!!.getView<ImageView>(R.id.iv_item_shou_search_cplb_img)
        val llCplbScore= helper.getView<LinearLayout>(R.id.ll_item_shou_search_cplb_score)
        val clpbCplbImg = helper!!.getView<ContentLoadingProgressBar>(R.id.clpb_item_shou_search_cplb)
        val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                .transforms(RoundedCorners(30))//图片圆角为30
        Glide.with(activity).load(if (item.pictureList!=null && item.pictureList.size>0) item.pictureList.get(0).url else "") //图片地址
                .apply(options)
                .into(ivCplbImg)
        var position = (helper.layoutPosition + 1) % 5 * 10
        clpbCplbImg.progress = position
        helper.setText(R.id.tv_item_shou_search_cplb_title, item.name)
                .setText(R.id.tv_item_shou_search_cplb_jl, "${helper.layoutPosition + 2}km")
                .setText(R.id.tv_item_shou_search_cplb_fs, "${helper.layoutPosition + 1}分")
                .setText(R.id.tv_item_shou_search_cplb_ys, "已售9份")
                .setText(R.id.tv_item_shou_search_cplb_price, item.price.toString())
        if (isShowCore){
            llCplbScore.visibility = View.VISIBLE
        }else{
            llCplbScore.visibility = View.INVISIBLE
        }
        /*if (helper.layoutPosition%2==0){
            helper.setVisible(R.id.tv_item_shou_search_cplb_yh,false)
        }else{
            helper.setVisible(R.id.tv_item_shou_search_cplb_yh,true)
        }*/
        helper.itemView.setOnClickListener {
            val intent = Intent(activity, CommodityDetailsActivity::class.java)
            intent.putExtra("details",item)
            activity.startActivity(intent)
        }

    }

}
