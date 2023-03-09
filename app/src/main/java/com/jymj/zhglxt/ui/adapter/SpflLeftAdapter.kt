package com.jymj.basemessagesystem.ui.adapter

import android.graphics.Color
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.FlBean
import com.jymj.zhglxt.ui.bean.homepage.GroupListBean
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class SpflLeftAdapter(layoutResId: Int, data: MutableList<GroupListBean>?) : BaseQuickAdapter<GroupListBean, BaseViewHolder>(layoutResId, data){
    private val mDf = DecimalFormat("#######.########")

    override fun convert(helper: BaseViewHolder?, item: GroupListBean) {
        val viewItemSpflLeft = helper!!.getView<View>(R.id.view_item_spfl_left)
        helper!!.setText(R.id.tv_item_spfl_left_name,item.name)
        if (item.isCheck){
            viewItemSpflLeft.visibility = View.VISIBLE
            helper.setTextColor(R.id.tv_item_spfl_left_name, Color.parseColor("#CE4A41"))
            helper.setBackgroundColor(R.id.ll_item_spfl_left, Color.parseColor("#ffffff"))
        }else{
            viewItemSpflLeft.visibility = View.GONE
            helper.setTextColor(R.id.tv_item_spfl_left_name, Color.parseColor("#333333"))
            helper.setBackgroundColor(R.id.ll_item_spfl_left, Color.parseColor("#F3F5FA"))
        }
        /*helper.itemView.setOnClickListener {
            setCheck(helper.adapterPosition)
        }*/
    }
    /*fun setCheck(position:Int){
        for (i in data.indices){
            val get = data.get(i)
            get.isCheck = false
            if (i == position){
                get.isCheck = true
            }
        }
        notifyDataSetChanged()
    }*/

}
