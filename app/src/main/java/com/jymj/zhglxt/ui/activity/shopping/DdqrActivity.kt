package com.jymj.zhglxt.ui.activity.shopping

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.activity.me.address.DzglActivity
import com.jymj.zhglxt.ui.bean.homepage.*
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.shopping.DdqrContract
import com.jymj.zhglxt.ui.model.shopping.DdqrModel
import com.jymj.zhglxt.ui.presenter.shopping.DdqrPresenter
import com.jymj.zhglxt.util.CommenPop
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_ddqr.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 提交订单  确认
 */
class DdqrActivity : BaseActivity<DdqrPresenter, DdqrModel>(), DdqrContract.View {
    var settlementList: ArrayList<GwcList.DataBean.ContentBean>? = null
    var mdseInfo: MdseInfo? = null

    var addressIdType = 0L
    var addressPoition = 0
    var jumpType = 0
    var quantityFlag = 1
    var tabFlag = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_ddqr
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {
        EventBus.getDefault().register(this)
        mPresenter.addressList()

        tab_act_ddqr.addTab(tab_act_ddqr.newTab().setText("快递"))
        tab_act_ddqr.addTab(tab_act_ddqr.newTab().setText("自提"))
        tab_act_ddqr.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabFlag = tab!!.position
                when(tabFlag){
                    0->{
                        ll_act_ddqr_address_child.visibility = View.VISIBLE
                    }
                    1->{
                        ll_act_ddqr_address_child.visibility = View.GONE
                    }
                }
            }
        })

        var totalprice = 0.0

        jumpType = intent.getIntExtra("jumpDdqrActivity", 0)
        if (jumpType == 2) {
            settlementList = intent.getSerializableExtra("settlement")
                    as ArrayList<GwcList.DataBean.ContentBean>
        } else if (jumpType == 3) {
            settlementList = intent.getSerializableExtra("settlement")
                    as ArrayList<GwcList.DataBean.ContentBean>
        } else {
            mdseInfo = intent.getSerializableExtra("mdseInfo") as MdseInfo
        }

        var type = ""
        if (settlementList != null && settlementList!!.size > 0) {
            if (settlementList!![0].status == 2) {//商品数量最少为1 又因为商品和卡不能一起购买，所以去第一条就可以
                ll_act_ddqr_address.visibility = View.GONE
                //如果是卡产品订单，可以在此页面修改数量
                tv_act_ddqr_sgwp_num.visibility = View.GONE
                ll_act_ddqr_det.visibility = View.VISIBLE
            } else {
                ll_act_ddqr_address.visibility = View.VISIBLE
            }
            if (settlementList!!.size == 1) {
                ll_act_ddqr_sgwp_dan.visibility = View.VISIBLE
                ll_act_ddqr_sgwp_shuang.visibility = View.GONE

                Glide.with(this).load(settlementList!![0].pictureInfo.url).into(iv_act_ddqr_sgwp_img)
                tv_act_ddqr_sgwp_name.text = settlementList!![0].name
                if (settlementList!![0].stockInfo.specA != null) {
                    type = settlementList!![0].stockInfo.specA.value
                }
                if (settlementList!![0].stockInfo.specB != null) {
                    type = type + " " + settlementList!![0].stockInfo.specB.value
                }
                if (settlementList!![0].stockInfo.specC != null) {
                    type = type + " " + settlementList!![0].stockInfo.specC.value
                }
                tv_act_ddqr_sgwp_fen.text = type
                tv_act_ddqr_sgwp_price.text = "￥" + settlementList!![0].price.toString()
                ll_act_ddqr_spje.text = "￥" + settlementList!![0].price.toString()
                tv_act_ddqr_sgwp_num.text = "× " + settlementList!![0].quantity.toString()
                totalprice = settlementList!![0].price * settlementList!![0].quantity

                //加减卡产品数量
                quantityFlag = settlementList!![0].quantity
                tv_act_ddqr_det_num.text = quantityFlag.toString()

                tv_act_ddqr_det_del.setOnClickListener {
                    quantityFlag -= 1
                    tv_act_ddqr_det_del.isEnabled = quantityFlag != 0
                    tv_act_ddqr_det_num.text = quantityFlag.toString()
                    settlementList!![0].quantity = quantityFlag
                }
                tv_act_ddqr_det_add.setOnClickListener {
                    tv_act_ddqr_det_del.isEnabled = true
                    quantityFlag += 1
                    tv_act_ddqr_det_num.text = quantityFlag.toString()
                    settlementList!![0].quantity = quantityFlag
                }

            } else {
                var price = 0.0
                var num = 0
                ll_act_ddqr_sgwp_dan.visibility = View.GONE
                ll_act_ddqr_sgwp_shuang.visibility = View.VISIBLE
                rlv_act_ddqr_sgwp.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rlv_act_ddqr_sgwp.adapter = object : BaseQuickAdapter<GwcList.DataBean.ContentBean, BaseViewHolder>(R.layout.item_ddqr_sgwq,
                        settlementList) {
                    override fun convert(helper: BaseViewHolder?, item: GwcList.DataBean.ContentBean?) {
                        val imageView = helper!!.getView<ImageView>(R.id.iv_item_ddqr_sgwq_img)
                        Glide.with(this@DdqrActivity).load(item!!.pictureInfo.url).into(imageView)
                        helper.setText(R.id.tv_item_ddqr_sgwq_title, item.name)

                    }
                }
                for (i in 0 until settlementList!!.size) {
                    price += settlementList!![i].price * settlementList!![i].quantity
                    num += settlementList!![i].quantity
                }
                tv_act_ddqr_sgwp_price1.text = "￥${String.format("%.2f", price)}"
                ll_act_ddqr_spje.text = "￥${String.format("%.2f", price)}"
                tv_act_ddqr_sgwp_num1.text = "共" + num.toString() + "件"
                totalprice = price

            }
            ll_act_ddqr_total.text = "￥${String.format("%.2f", totalprice)}"
        } else {
            if (mdseInfo!!.classify == 2) {
                ll_act_ddqr_address.visibility = View.GONE
                //如果是卡产品订单，可以在此页面修改数量
                tv_act_ddqr_sgwp_num.visibility = View.GONE
                ll_act_ddqr_det.visibility = View.VISIBLE

            } else {
                ll_act_ddqr_address.visibility = View.VISIBLE
            }

            ll_act_ddqr_sgwp_dan.visibility = View.VISIBLE
            ll_act_ddqr_sgwp_shuang.visibility = View.GONE

            ll_act_ddqr_sgwp_dan.visibility = View.VISIBLE
            ll_act_ddqr_sgwp_shuang.visibility = View.GONE

            Glide.with(this).load(mdseInfo!!.pictureList[0].url).into(iv_act_ddqr_sgwp_img)
            tv_act_ddqr_sgwp_name.text = mdseInfo!!.name
            if (mdseInfo!!.stockList[0].specA != null) {
                type = mdseInfo!!.stockList[0].specA.value
            }
            if (mdseInfo!!.stockList[0].specB != null) {
                type = type + " " + mdseInfo!!.stockList[0].specB.value
            }
            if (mdseInfo!!.stockList[0].specC != null) {
                type = type + " " + mdseInfo!!.stockList[0].specC.value
            }
            tv_act_ddqr_sgwp_fen.text = type
            tv_act_ddqr_sgwp_price.text = "￥" + mdseInfo!!.price.toString()
            ll_act_ddqr_spje.text = "￥" + mdseInfo!!.price.toString()
            tv_act_ddqr_sgwp_num.text = "× " + mdseInfo!!.quantity.toString()
            totalprice = mdseInfo!!.price.toDouble() * mdseInfo!!.quantity
            ll_act_ddqr_total.text = "￥${String.format("%.2f", totalprice)}"

            //加减卡产品数量
            quantityFlag = mdseInfo!!.quantity
            tv_act_ddqr_det_num.text = quantityFlag.toString()

            tv_act_ddqr_det_del.setOnClickListener {
                quantityFlag -= 1
                tv_act_ddqr_det_del.isEnabled = quantityFlag != 0
                tv_act_ddqr_det_num.text = quantityFlag.toString()
                mdseInfo!!.quantity = quantityFlag
            }
            tv_act_ddqr_det_add.setOnClickListener {
                tv_act_ddqr_det_del.isEnabled = true
                quantityFlag += 1
                tv_act_ddqr_det_num.text = quantityFlag.toString()
                mdseInfo!!.quantity = quantityFlag
            }
        }


        iv_act_ddqr_back.setOnClickListener {
            finish()
        }
        tv_act_ddqr_adddz.setOnClickListener {
            val intent = Intent(this, DzglActivity::class.java)
            intent.putExtra("ddqr", 1)
            startActivity(intent)
        }
        tv_act_ddqr_update.setOnClickListener {
            val intent = Intent(this, DzglActivity::class.java)
            intent.putExtra("ddqr", 1)
            startActivity(intent)
        }
        //优惠券
        ll_act_ddqr_yhpj.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()) {
                initPopuPoint()
            }
        }
        //提交订单
        ll_act_ddqr_submit.setOnClickListener {

            if (settlementList != null && settlementList!!.size > 0) {
                var entity = OrderDTO()
                var orderMdseDTO = ArrayList<OrderMdseDTO>()
                for (i in 0 until settlementList!!.size) {
                    orderMdseDTO.add(OrderMdseDTO(settlementList!![i].mdseId.toLong(), settlementList!![i].stockInfo.stockId.toLong(),
                            settlementList!![i].shopInfo.shopId.toLong(), settlementList!![i].quantity, settlementList!![i].status))
                }
                entity.orderMdseList = orderMdseDTO
                entity.remarks = ll_act_ddqr_remark.text.toString()
                if (settlementList!![0].status == 1) {
                    entity.addressId = addressIdType.toString()
                    if (tabFlag == 0){
                        entity.orderDeliveryMethod = OrderDeliveryMethodEnum.EXPRESS
                    }else{
                        entity.orderDeliveryMethod = OrderDeliveryMethodEnum.PICK_UP
                    }
                } else {
                    entity.orderDeliveryMethod = OrderDeliveryMethodEnum.PICK_UP
                }
                mPresenter.getCreateOrder(entity)

            } else {
                var entity = OrderDTO()
                var orderMdseDTO = ArrayList<OrderMdseDTO>()

                orderMdseDTO.add(OrderMdseDTO(mdseInfo!!.mdseId, mdseInfo!!.stockList[0].stockId,
                        mdseInfo!!.shopInfo.shopId.toLong(), mdseInfo!!.quantity, mdseInfo!!.classify))
                entity.orderMdseList = orderMdseDTO
                entity.remarks = ll_act_ddqr_remark.text.toString()
                if (mdseInfo!!.classify == 1) {
                    entity.addressId = addressIdType.toString()
                    if (tabFlag == 0){
                        entity.orderDeliveryMethod = OrderDeliveryMethodEnum.EXPRESS
                    }else{
                        entity.orderDeliveryMethod = OrderDeliveryMethodEnum.PICK_UP
                    }
                } else {
                    entity.orderDeliveryMethod = OrderDeliveryMethodEnum.PICK_UP
                }
                mPresenter.getCreateOrder(entity)
            }


        }


    }

    override fun initDatas() {


    }

    fun initPopuPoint() {
        val isSure = booleanArrayOf(false)
        var mPointPopu = CommenPop.getNormalPopu(this, R.layout.pop_ddqr_yhq, ll_act_ddqr)//pop_point
        val contentView = mPointPopu.getContentView()

        mPointPopu.setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
        val iv_pop_ddqr_yhq_close = contentView.findViewById<ImageView>(R.id.iv_pop_ddqr_yhq_close)
        val rlv_pop_ddqr_yhq = contentView.findViewById<RecyclerView>(R.id.rlv_pop_ddqr_yhq)
        val iv_pop_ddqr_yhq_sure = contentView.findViewById<TextView>(R.id.iv_pop_ddqr_yhq_sure)

        val arrayList = ArrayList<String>()
        arrayList.add("")
        arrayList.add("")
        arrayList.add("")
        rlv_pop_ddqr_yhq.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_pop_ddqr_yhq.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_ddqr_yhq, arrayList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                val iv_item_ddqr_yhq_yuan = helper!!.getView<ImageView>(R.id.iv_item_ddqr_yhq_yuan)

                /*if (helper.adapterPosition%2==0){
                    iv_item_ddqr_yhq_yuan.setImageResource(R.drawable.yuan_kong_gray)
                }else{
                    iv_item_ddqr_yhq_yuan.setImageResource(R.drawable.yuan_shi_red)
                }*/
                helper.itemView.setOnClickListener {
                    iv_item_ddqr_yhq_yuan.setImageResource(R.drawable.yuan_shi_red)
                }

            }
        }

        iv_pop_ddqr_yhq_sure.setOnClickListener {
            ToastUtils.showShort("确定")
        }
        iv_pop_ddqr_yhq_close.setOnClickListener {
            mPointPopu.dismiss()
        }

        CommenPop.backgroundAlpha(0.5f, this)
        mPointPopu.showAtLocation(ll_act_ddqr, Gravity.BOTTOM, 0, 0)
        mPointPopu.setOnDismissListener(PopupWindow.OnDismissListener {
            CommenPop.backgroundAlpha(1f, this)

        })

    }

    override fun returnAddressList(message: List<AddressInfo>) {
        if (message != null && message.size > addressPoition) {
            ll_act_ddqr_dz.visibility = View.VISIBLE
            tv_act_ddqr_adddz.visibility = View.GONE
            val data = message.get(addressPoition)
            tv_act_ddqr_name.text = data.name
            tv_act_ddqr_phone.text = data.mobile
            if (data.status == 1) tv_act_ddqr_mr.visibility = View.VISIBLE else tv_act_ddqr_mr.visibility = View.GONE
            tv_act_ddqr_dz.text = data.region + data.detailedAddress
            addressIdType = data.addressId

        } else {
            ll_act_ddqr_dz.visibility = View.GONE
            tv_act_ddqr_adddz.visibility = View.VISIBLE

        }

    }

    override fun returnCreateOrder(message: MallOrderInfo) {
        if (message != null) {
            if (message.orderMdseDetailsInfoList != null && message.orderMdseDetailsInfoList.size > 0) {
                var idsStr = ""
                val infoList = message.orderMdseDetailsInfoList
                if (settlementList != null && settlementList!!.size > 0 && jumpType == 2) {
                    for (i in 0 until infoList.size) {
                        if (infoList[i].mdseId == settlementList!![i].mdseId.toLong()) {
                            idsStr += settlementList!![i].shoppingCartId + ","
                        }
                    }
                    mPresenter.getDeleteCart(idsStr)
                    EventBus.getDefault().postSticky("刷新购物车")
                }
            }
        }

    }

    override fun returnDeleteCart(msg: String) {

    }

    // 接收地址
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun getAddressPoition(messageEvent: String) {
        addressPoition = messageEvent.toInt()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.addressList()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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
