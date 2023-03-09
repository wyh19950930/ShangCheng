package com.jymj.zhglxt.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.activity.me.order.OrderDetailsActivity
import com.jymj.zhglxt.ui.activity.shopping.DdqrActivity
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import java.text.DecimalFormat

/**
 * Administrator on 2018/1/11 create this class
 */

class OrderCenterAdapter(layoutResId: Int, data: MutableList<MallOrderInfo>?, activity: Activity, type: String) : BaseQuickAdapter<MallOrderInfo, BaseViewHolder>(layoutResId, data) {
    private val mDf = DecimalFormat("#######.########")
    private var activity = activity
    private var type = type


    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: MallOrderInfo) {
        val ll_item_order_center_dan = helper!!.getView<LinearLayout>(R.id.ll_item_order_center_dan)//单
        val ll_item_order_center_shuang = helper!!.getView<LinearLayout>(R.id.ll_item_order_center_shuang)//双

        val rlv_item_order_center_sgwp = helper!!.getView<RecyclerView>(R.id.rlv_item_order_center_sgwp)//双  图片
        val tv_item_order_center_price1 = helper!!.getView<TextView>(R.id.tv_item_order_center_price1)//双  总数
        val tv_item_order_center_num1 = helper!!.getView<TextView>(R.id.tv_item_order_center_num1)//双  总数
        val tv_item_order_center_nyf = helper!!.getView<TextView>(R.id.tv_item_order_center_nyf)//双  运费
        val tv_item_order_center_dfk = helper!!.getView<TextView>(R.id.tv_item_order_center_dfk)//待付款

        val tv_item_order_center_delete = helper!!.getView<TextView>(R.id.tv_item_order_center_delete)//删除订单
        val tv_item_order_center_cancel = helper!!.getView<TextView>(R.id.tv_item_order_center_cancel)//取消订单
        val tv_item_order_center_ticket = helper!!.getView<TextView>(R.id.tv_item_order_center_ticket)//申请开票
        val tv_item_order_center_refund = helper!!.getView<TextView>(R.id.tv_item_order_center_refund)//申请退款
        val tv_item_order_center_again = helper!!.getView<TextView>(R.id.tv_item_order_center_again)//再次购买
        val tv_item_order_center_logistics = helper!!.getView<TextView>(R.id.tv_item_order_center_logistics)//查看物流
        val tv_item_order_center_update = helper!!.getView<TextView>(R.id.tv_item_order_center_update)//修改地址
        val tv_item_order_center_continue = helper!!.getView<TextView>(R.id.tv_item_order_center_continue)//继续付款
        val tv_item_order_center_affirm = helper!!.getView<TextView>(R.id.tv_item_order_center_affirm)//确认收货
        val tv_item_order_center_appraise = helper!!.getView<TextView>(R.id.tv_item_order_center_appraise)//评价

        helper.addOnClickListener(R.id.tv_item_order_center_delete)
                .addOnClickListener(R.id.tv_item_order_center_cancel)
                .addOnClickListener(R.id.tv_item_order_center_ticket)
                .addOnClickListener(R.id.tv_item_order_center_refund)
                .addOnClickListener(R.id.tv_item_order_center_again)
                .addOnClickListener(R.id.tv_item_order_center_logistics)
                .addOnClickListener(R.id.tv_item_order_center_update)
                .addOnClickListener(R.id.tv_item_order_center_continue)
                .addOnClickListener(R.id.tv_item_order_center_affirm)
                .addOnClickListener(R.id.tv_item_order_center_appraise)
                .addOnClickListener(R.id.ll_item_order_center_dan)
                .addOnClickListener(R.id.ll_item_order_center_shuang)


        if (item.orderMdseDetailsInfoList.size > 1) {
            ll_item_order_center_shuang.visibility = View.VISIBLE
            ll_item_order_center_dan.visibility = View.GONE

            rlv_item_order_center_sgwp.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rlv_item_order_center_sgwp.adapter = object : BaseQuickAdapter<MallOrderMdseDetailsInfo, BaseViewHolder>(R.layout.item_ddqr_img, item.orderMdseDetailsInfoList) {
                override fun convert(helper: BaseViewHolder?, items: MallOrderMdseDetailsInfo?) {
                    val tvItemDdqrImgImg = helper!!.getView<ImageView>(R.id.tv_item_ddqr_img_img)
                    helper.setText(R.id.tv_item_ddqr_img_name, items!!.mdseName)
                    val options = RequestOptions().transforms(RoundedCorners(30))
                            .error(R.drawable.icon_cnxh_ys)//图片圆角为30
                    Glide.with(activity).load(items.mdsePicture) //图片地址
                            .apply(options)
                            .into(tvItemDdqrImgImg)

                    helper.itemView.setOnClickListener {
                        var intent = Intent(mContext, OrderDetailsActivity::class.java)
                        intent.putExtra("orderId", item.orderId)
                        mContext.startActivity(intent)
                    }


                }
            }

            tv_item_order_center_num1.text = "共"+item.orderMdseDetailsInfoList.size+"件"

            var totalPrice = 0.0
            for (i in 0 until item.orderMdseDetailsInfoList.size){
                totalPrice += item.orderMdseDetailsInfoList[i].quantity * item.orderMdseDetailsInfoList[i].mdsePrice.toDouble()
            }
            tv_item_order_center_dfk.text = "￥${String.format("%.2f", totalPrice)}"
        } else {//单
            ll_item_order_center_dan.visibility = View.VISIBLE
            ll_item_order_center_shuang.visibility = View.GONE
            val image = helper.getView<ImageView>(R.id.iv_item_order_center_img)//单  图片
            Glide.with(mContext).load(item.orderMdseDetailsInfoList[0].mdsePicture).into(image)
            helper.setText(R.id.tv_item_order_center_name, item.orderMdseDetailsInfoList[0].mdseName)
                    .setText(R.id.tv_item_order_center_price, "￥" + item.orderMdseDetailsInfoList[0].mdsePrice.toString())
                    .setText(R.id.tv_item_order_center_fen, item.orderMdseDetailsInfoList[0].mdseStockSpec)
                    .setText(R.id.tv_item_order_center_num, "× " + item.orderMdseDetailsInfoList[0].quantity.toString())

            tv_item_order_center_dfk.text = "￥${String.format("%.2f", item.orderMdseDetailsInfoList[0]
                    .mdsePrice.multiply(item.orderMdseDetailsInfoList[0].quantity.toBigDecimal()))}"
        }

        //再次购买
        tv_item_order_center_again.setOnClickListener {

            var dataList = ArrayList<GwcList.DataBean.ContentBean>()

            for (i in 0 until item.orderMdseDetailsInfoList.size){
                var data = GwcList.DataBean.ContentBean()
                data.mdseId = item.orderMdseDetailsInfoList[i].mdseId.toString()
                data.pictureInfo.url = item.orderMdseDetailsInfoList[i].mdsePicture
                data.name = item.orderMdseDetailsInfoList[i].mdseName
                data.number = item.orderMdseDetailsInfoList[i].number
                data.price = item.orderMdseDetailsInfoList[i].mdsePrice.toDouble()
                data.status = item.orderMdseDetailsInfoList[i].type
                if (item.orderMdseDetailsInfoList[i].type == 1){
                    data.shopInfo.shopId = item.orderMdseDetailsInfoList[i].shopId.toString()
                }
                data.quantity = item.orderMdseDetailsInfoList[i].quantity
                data.stockInfo.stockId = item.orderMdseDetailsInfoList[i].stockId.toString()
                dataList.add(data)
            }

            var intent = Intent(mContext, DdqrActivity::class.java)
            intent.putExtra("jumpDdqrActivity",3)
            intent.putExtra("settlement",dataList)
            mContext.startActivity(intent)
        }

        helper.setText(R.id.tv_item_order_center_time, item.createTime)

        val stutsa = helper.getView<TextView>(R.id.tv_item_order_center_stutas)
        val price_stutas = helper.getView<TextView>(R.id.tv_item_order_center_price_stutas)
        when (item.orderStatus.value) {
            0 -> {
                stutsa.text = "等待买家付款"
                price_stutas.text = "待付款"
            }
            1 -> {
                stutsa.text = "买家已付款"
                price_stutas.text = "已付款"
            }
            2 -> {
                stutsa.text = "买家已付款"
                price_stutas.text = "已付款"
            }
            3 -> {
                stutsa.text = "已完成"
                price_stutas.text = "已付款"
            }
            4 -> {
                stutsa.text = "买家已付款"
                price_stutas.text = "已付款"
            }
            5 -> {
                stutsa.text = "订单已取消"
                price_stutas.text = "未付款"
            }
            6 -> {
                stutsa.text = "订单已关闭"
                price_stutas.text = "未付款"
            }
        }

        when (type) {
            "" -> {
//                ToastUtils.showShort("全部")

                when (item.orderStatus.value) {
                    0 -> {
                        stutsa.text = "等待买家付款"
                        tv_item_order_center_delete.visibility = View.GONE  //删除订单
                        tv_item_order_center_cancel.visibility = View.VISIBLE  //取消订单
                        tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                        tv_item_order_center_refund.visibility = View.GONE  //申请退款
                        tv_item_order_center_again.visibility = View.GONE  //再次购买
                        tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                        tv_item_order_center_update.visibility = View.GONE  //修改地址
                        tv_item_order_center_continue.visibility = View.VISIBLE  //继续付款
                        tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                        tv_item_order_center_appraise.visibility = View.GONE  //评价
                    }
                    2 -> {
                        stutsa.text = "买家已付款"
                        tv_item_order_center_delete.visibility = View.GONE  //删除订单
                        tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                        tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                        tv_item_order_center_refund.visibility = View.GONE  //申请退款
                        tv_item_order_center_again.visibility = View.GONE  //再次购买
                        tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                        tv_item_order_center_update.visibility = View.VISIBLE  //修改地址
                        tv_item_order_center_continue.visibility = View.GONE  //继续付款
                        tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                        tv_item_order_center_appraise.visibility = View.GONE  //评价
                    }
                    3 -> {
                        stutsa.text = "已完成"
                        tv_item_order_center_delete.visibility = View.GONE  //删除订单
                        tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                        tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                        tv_item_order_center_refund.visibility = View.VISIBLE  //申请退款
                        tv_item_order_center_again.visibility = View.GONE  //再次购买
                        tv_item_order_center_logistics.visibility = View.VISIBLE  //查看物流
                        tv_item_order_center_update.visibility = View.GONE  //修改地址
                        tv_item_order_center_continue.visibility = View.GONE  //继续付款
                        tv_item_order_center_affirm.visibility = View.VISIBLE  //确认收货
                        tv_item_order_center_appraise.visibility = View.GONE  //评价
                    }
                    6 -> {
                        stutsa.text = "订单已关闭"
                        tv_item_order_center_delete.visibility = View.VISIBLE  //删除订单
                        tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                        tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                        tv_item_order_center_refund.visibility = View.GONE  //申请退款
                        tv_item_order_center_again.visibility = View.VISIBLE  //再次购买
                        tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                        tv_item_order_center_update.visibility = View.GONE  //修改地址
                        tv_item_order_center_continue.visibility = View.GONE  //继续付款
                        tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                        tv_item_order_center_appraise.visibility = View.GONE  //评价
                    }
                }

            }
            "UNPAID" -> {
//                ToastUtils.showShort("待付款")
                tv_item_order_center_delete.visibility = View.GONE  //删除订单
                tv_item_order_center_cancel.visibility = View.VISIBLE  //取消订单
                tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                tv_item_order_center_refund.visibility = View.GONE  //申请退款
                tv_item_order_center_again.visibility = View.GONE  //再次购买
                tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                tv_item_order_center_update.visibility = View.GONE  //修改地址
                tv_item_order_center_continue.visibility = View.VISIBLE  //继续付款
                tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                tv_item_order_center_appraise.visibility = View.GONE  //评价
            }
            "UNSHIPPED" -> {
//                ToastUtils.showShort("待发货")
                tv_item_order_center_delete.visibility = View.GONE  //删除订单
                tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                tv_item_order_center_refund.visibility = View.GONE  //申请退款
                tv_item_order_center_again.visibility = View.GONE  //再次购买
                tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                tv_item_order_center_update.visibility = View.VISIBLE  //修改地址
                tv_item_order_center_continue.visibility = View.GONE  //继续付款
                tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                tv_item_order_center_appraise.visibility = View.GONE  //评价
            }
            "UNRECEIVED" -> {
//                ToastUtils.showShort("待收货")
                tv_item_order_center_delete.visibility = View.GONE  //删除订单
                tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                tv_item_order_center_refund.visibility = View.VISIBLE  //申请退款
                tv_item_order_center_again.visibility = View.GONE  //再次购买
                tv_item_order_center_logistics.visibility = View.VISIBLE  //查看物流
                tv_item_order_center_update.visibility = View.GONE  //修改地址
                tv_item_order_center_continue.visibility = View.GONE  //继续付款
                tv_item_order_center_affirm.visibility = View.VISIBLE  //确认收货
                tv_item_order_center_appraise.visibility = View.GONE  //评价
            }
            "COMPLETED" -> {
//                ToastUtils.showShort("已完成")
                tv_item_order_center_delete.visibility = View.VISIBLE  //删除订单
                tv_item_order_center_cancel.visibility = View.GONE  //取消订单
                tv_item_order_center_ticket.visibility = View.GONE  //申请开票
                tv_item_order_center_refund.visibility = View.VISIBLE  //申请退款
                tv_item_order_center_again.visibility = View.VISIBLE  //再次购买
                tv_item_order_center_logistics.visibility = View.GONE  //查看物流
                tv_item_order_center_update.visibility = View.GONE  //修改地址
                tv_item_order_center_continue.visibility = View.GONE  //继续付款
                tv_item_order_center_affirm.visibility = View.GONE  //确认收货
                tv_item_order_center_appraise.visibility = View.VISIBLE  //评价
            }
        }

    }

}
