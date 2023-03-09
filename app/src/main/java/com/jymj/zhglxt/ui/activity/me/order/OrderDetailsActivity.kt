package com.jymj.zhglxt.ui.activity.me.order

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.activity.shopping.DdqrActivity
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import com.jymj.zhglxt.ui.contract.me.OrderCenterDetailsContract
import com.jymj.zhglxt.ui.model.me.OrderCenterDetailsModel
import com.jymj.zhglxt.ui.presenter.me.OrderCenterDetailsPresenter
import com.jymj.zhglxt.util.QRcode
import com.setsuna.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order_details.*

class OrderDetailsActivity : BaseActivity<OrderCenterDetailsPresenter, OrderCenterDetailsModel>(), OrderCenterDetailsContract.View {


    override fun getLayoutId(): Int {
        return R.layout.activity_order_details
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_orderdetails_back.setOnClickListener {
            finish()
        }
//        ToastUtils.showShort("111")
        val orderId = intent.getLongExtra("orderId", 0L)
        mPresenter.getOrderDetails(orderId.toInt())

    }

    override fun initDatas() {
    }

    @SuppressLint("SetTextI18n")
    override fun returnOrderDetails(message: MallOrderInfo) {
        if (message!=null){
            tv_act_order_details_orderStatus.text = message.orderStatus.label
            if (message.orderMdseDetailsInfoList!=null&&message.orderMdseDetailsInfoList.size>0){
                if (message.orderMdseDetailsInfoList[0].type == 2){//卡产品
                    ll_act_order_details_address_all.visibility = View.GONE
                    ll_act_order_details_ka.visibility = View.VISIBLE

                    Glide.with(this).load(message.orderMdseDetailsInfoList[0].mdsePicture).into(iv_act_order_details_ka_img)
                    tv_act_order_details_ka_name.text = message.orderMdseDetailsInfoList[0].mdseName
                    tv_act_order_details_ka_price.text = "￥"+message.orderMdseDetailsInfoList[0].mdsePrice.toString()
                    tv_act_order_details_ka_fen.text = message.orderMdseDetailsInfoList[0].mdseStockSpec
                    tv_act_order_details_ka_num.text = "× "+message.orderMdseDetailsInfoList[0].quantity.toString()

                    rlv_act_order_details_ddxx.layoutManager = LinearLayoutManager(this)
                    rlv_act_order_details_ddxx.adapter = object:BaseQuickAdapter<MallOrderMdseDetailsInfo,BaseViewHolder>
                    (R.layout.item_orderdetails_ddxx,message.orderMdseDetailsInfoList[0].cardMdseInfoList){
                        override fun convert(helper: BaseViewHolder?, item: MallOrderMdseDetailsInfo?) {
                            helper!!.setText(R.id.tv_item_orderdetails_ddxx_title,item!!.mdseName)
                                    .setText(R.id.tv_item_orderdetails_ddxx_price,item.mdsePrice.toString()+" x "+item.quantity.toString())
                                    .setText(R.id.tv_item_orderdetails_ddxx_hx,if (item.usageStatus == 1)"已核销" else "未核销")
                                    .setText(R.id.tv_item_orderdetails_ddxx_lx,"去核销")
                            val imageView = helper.getView<ImageView>(R.id.iv_item_orderdetails_ddxx)
                            Glide.with(this@OrderDetailsActivity).load(item.mdsePicture).into(imageView)
                            //去核销
                            helper.itemView.setOnClickListener {
                                var intent = Intent(this@OrderDetailsActivity, WriteOffActivity::class.java)
                                intent.putExtra("orderNo",message.orderNo)
                                intent.putExtra("orderKaName",message.orderMdseDetailsInfoList[0].mdseName)
                                intent.putExtra("orderInfo",item)
                                startActivity(intent)
                            }
                        }
                    }

                }else if (message.orderMdseDetailsInfoList[0].type == 1){//普通商品
                    iv_act_order_details_ewm.visibility = View.VISIBLE//商品二维码
                    val qRcode = QRcode()
                    val bitmap = qRcode.qrcode(message.orderNo)
                    iv_act_order_details_ewm.setImageBitmap(bitmap)
                    rlv_act_order_details_ddxx.layoutManager = LinearLayoutManager(this)
                    rlv_act_order_details_ddxx.adapter = object:BaseQuickAdapter<MallOrderMdseDetailsInfo,BaseViewHolder>
                    (R.layout.item_orderdetails_ddxx,message.orderMdseDetailsInfoList){
                        override fun convert(helper: BaseViewHolder?, item: MallOrderMdseDetailsInfo?) {
                            helper!!.setText(R.id.tv_item_orderdetails_ddxx_title,item!!.mdseName)
                                    .setText(R.id.tv_item_orderdetails_ddxx_price,item.mdsePrice.toString()+" x "+item.quantity.toString())
                                    .setText(R.id.tv_item_orderdetails_ddxx_hx,if (item.usageStatus == 1)"已核销" else "未核销")
                            val imageView = helper.getView<ImageView>(R.id.iv_item_orderdetails_ddxx)
                            Glide.with(this@OrderDetailsActivity).load(item.mdsePicture).into(imageView)
                        }
                    }
                }

            }
            when(message.orderStatus.value){
                0 -> {
                    tv_act_order_details_status.text = "等待买家付款"
                    tv_act_order_details_fk.text = "待付款"
                    tv_act_order_details_qxdd.visibility = View.VISIBLE
                    tv_act_order_details_zcgm.text = "继续付款"
                }
                1 -> {
                    tv_act_order_details_status.text = "买家已付款"
                    tv_act_order_details_fk.text = "已付款"
                    tv_act_order_details_qxdd.visibility = View.VISIBLE
                    tv_act_order_details_xgdd.visibility = View.VISIBLE
                }
                2 -> {
                    tv_act_order_details_status.text = "买家已付款"
                    tv_act_order_details_fk.text = "已付款"
                    tv_act_order_details_qxdd.visibility = View.VISIBLE
                    tv_act_order_details_xgdd.visibility = View.VISIBLE
                }
                3 -> {
                    tv_act_order_details_status.text = "买家已付款"
                    tv_act_order_details_fk.text = "已付款"
                    tv_act_order_details_zcgm.text = "再次购买"
                }
                4 -> {
                    tv_act_order_details_status.text = "买家已付款"
                    tv_act_order_details_fk.text = "已付款"
                    tv_act_order_details_zcgm.text = "再次购买"
                }
                5 -> {
                    tv_act_order_details_status.text = "订单已取消"
                    tv_act_order_details_fk.text = "未付款"
                    tv_act_order_details_zcgm.text = "再次购买"

                }
                6 -> {
                    tv_act_order_details_status.text = "订单已关闭"
                    tv_act_order_details_fk.text = "未付款"
                    tv_act_order_details_zcgm.text = "再次购买"

                }
            }
            tv_act_order_details_amountPayable.text = "需付款：￥"+message.amountPayable.toString()
            if (message.orderDeliveryDetailsInfo!=null){
                tv_act_order_details_name.text = message.orderDeliveryDetailsInfo.addressee
                tv_act_order_details_phone.text = message.orderDeliveryDetailsInfo.mobile
                tv_act_order_details_dz.text = message.orderDeliveryDetailsInfo.detailedAddress
            }


            tv_act_order_details_ordeno.text = message.orderNo
            tv_act_order_details_createtime.text = message.createTime
            tv_act_order_details_sqje.text = "￥"+message.amountPayable.toString()

            //再次购买
            tv_act_order_details_zcgm.setOnClickListener {
                if (tv_act_order_details_zcgm.text.toString().equals("再次购买")){
                    var dataList = ArrayList<GwcList.DataBean.ContentBean>()

                    for (i in 0 until message.orderMdseDetailsInfoList.size){
                        var data = GwcList.DataBean.ContentBean()
                        data.mdseId = message.orderMdseDetailsInfoList[i].mdseId.toString()
                        data.pictureInfo.url = message.orderMdseDetailsInfoList[i].mdsePicture
                        data.name = message.orderMdseDetailsInfoList[i].mdseName
                        data.number = message.orderMdseDetailsInfoList[i].number
                        data.price = message.orderMdseDetailsInfoList[i].mdsePrice.toDouble()
                        data.status = message.orderMdseDetailsInfoList[i].type
                        if (message.orderMdseDetailsInfoList[i].type == 1){
                            data.shopInfo.shopId = message.orderMdseDetailsInfoList[i].shopId.toString()
                        }
                        data.quantity = message.orderMdseDetailsInfoList[i].quantity
                        data.stockInfo.stockId = message.orderMdseDetailsInfoList[i].stockId.toString()
                        dataList.add(data)
                    }

                    var intent = Intent(this, DdqrActivity::class.java)
                    intent.putExtra("jumpDdqrActivity",3)
                    intent.putExtra("settlement",dataList)
                    startActivity(intent)
                    finish()
                }
            }


        }

    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
    }*/
}
