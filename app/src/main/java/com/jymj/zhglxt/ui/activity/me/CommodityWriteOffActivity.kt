package com.jymj.zhglxt.ui.activity.me

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.google.zxing.integration.android.IntentIntegrator
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.CommodityWriteOffContract
import com.jymj.zhglxt.login.presenter.CommodityWriteOffPresenter
import com.jymj.zhglxt.ui.activity.me.order.WriteOffQrCodeActivity
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import com.jymj.zhglxt.ui.model.me.CommodityWriteOffModel
import com.jymj.zhglxt.util.SingleOnClickUtil1
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_commodity_write_off.*

/**
 * 商品核销页面
 */
class CommodityWriteOffActivity : BaseActivity<CommodityWriteOffPresenter,CommodityWriteOffModel>(),CommodityWriteOffContract.View {

    var ssIsCheck = false
    var smIsCheck = false
    override fun getLayoutId(): Int {
        return R.layout.activity_commodity_write_off
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_commodity_write_off_back.setOnClickListener {
            finish()
        }

        rbt_act_com_w_o_ss.setOnClickListener {
            ToastUtils.showShort("搜索")
        }
        rbt_act_com_w_o_sm.setOnClickListener {
            if (SingleOnClickUtil1.isFastClick()) {
                // 创建IntentIntegrator对象
                var intentIntegrator = IntentIntegrator(this)//Scanfragivity//IntentIntegrator(activity)
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                intentIntegrator.setBarcodeImageEnabled(false)//设置是否保存图片
                intentIntegrator.setBeepEnabled(true)//设置扫码成功后的提示音是否显示
                intentIntegrator.setOrientationLocked(false)//该方法用于设置方向锁
                intentIntegrator.setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                intentIntegrator.captureActivity = WriteOffQrCodeActivity::class.java // 设置自定义的fragivity
                intentIntegrator.initiateScan() // 开始扫描
            }
        }

        tab_act_com_w_o.addTab(tab_act_com_w_o.newTab().setText("核销详情"))
        tab_act_com_w_o.addTab(tab_act_com_w_o.newTab().setText("核销记录"))

        tab_act_com_w_o.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text.toString()){
                    "核销详情"->{
                        ll_act_com_w_o_hxxq.visibility = View.VISIBLE
                        rlt_act_com_w_o_hxjl.visibility = View.GONE
                    }
                    "核销记录"->{
                        ll_act_com_w_o_hxxq.visibility = View.GONE
                        rlt_act_com_w_o_hxjl.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun initDatas() {
    }

    var mdseIdFlag = 0L
    var contentsFlag = ""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 获取解析结果  二维码扫描结果
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)//IntentResult
        if (result != null) {
            if (result.contents == null) {
                ToastUtils.showShort("取消扫描")
            } else {
                if (result.contents != null) {
                    val split = result.contents.split("_")
                    if (split.size > 1) {
                        mdseIdFlag = split[1].toLong()
                        contentsFlag = split[0]
                        mPresenter.getOrderNo(split[0])
                    } else {
                        contentsFlag = result.contents
                        mPresenter.getOrderNo(result.contents)
                    }
                } else {
                    ToastUtils.showShort("扫描二维码错误")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    override fun returnOrderNo(message: MallOrderInfo) {
        if (message!=null){
            if (message.orderMdseDetailsInfoList!=null&&message.orderMdseDetailsInfoList.size>0){
                ll__act_com_w_o.visibility = View.VISIBLE
                if (message.orderMdseDetailsInfoList[0].type == 1){//商品
                    rlv_act_com_w_o_hxxq.layoutManager = LinearLayoutManager(this)
                    rlv_act_com_w_o_hxxq.adapter = object: BaseQuickAdapter<MallOrderMdseDetailsInfo, BaseViewHolder>
                    (R.layout.item_commodity_write_off,message.orderMdseDetailsInfoList){
                        override fun convert(helper: BaseViewHolder?, item: MallOrderMdseDetailsInfo?) {
                            helper!!.setText(R.id.tv_item_com_w_o_title,item!!.mdseName)
                                    .setText(R.id.tv_item_com_w_o_price,item.mdsePrice.toString()+" x "+item.quantity.toString())
                            val bt_hx = helper.getView<Button>(R.id.tv_item_com_w_o_hx)
                            if (item.usageStatus==1){
                                bt_hx.text = "已核销"
                                bt_hx.setBackgroundResource(R.drawable.shi_background_green)
                            }else{
                                bt_hx.text = "未核销"
                                bt_hx.setBackgroundResource(R.drawable.shi_background_red)
                            }

                            val imageView = helper.getView<ImageView>(R.id.tv_item_com_w_o_ddxx)
                            Glide.with(this@CommodityWriteOffActivity).load(item.mdsePicture).into(imageView)

                            bt_hx.setOnClickListener {
                                    mPresenter.getOrderMdse(item.mdseId,message.orderId,item.shopId,item.stockId,item.quantity)
                            }
                        }
                    }
                }else{//卡产品
                    rlv_act_com_w_o_hxxq.layoutManager = LinearLayoutManager(this)
                    var list = ArrayList<MallOrderMdseDetailsInfo>()
                    for (i in 0 until message.orderMdseDetailsInfoList[0].cardMdseInfoList.size){
                        if (message.orderMdseDetailsInfoList[0].cardMdseInfoList[i].mdseId == mdseIdFlag){
                            list.add(message.orderMdseDetailsInfoList[0].cardMdseInfoList[i])
                        }
                    }
                    rlv_act_com_w_o_hxxq.adapter = object: BaseQuickAdapter<MallOrderMdseDetailsInfo, BaseViewHolder>
                    (R.layout.item_commodity_write_off,list){
                        override fun convert(helper: BaseViewHolder?, item: MallOrderMdseDetailsInfo?) {
                            helper!!.setText(R.id.tv_item_com_w_o_title,item!!.mdseName)
                                    .setText(R.id.tv_item_com_w_o_price,item.mdsePrice.toString()+" x "+item.quantity.toString())

                            val bt_hx = helper.getView<Button>(R.id.tv_item_com_w_o_hx)
                            if (item.usageStatus==1){
                                bt_hx.text = "已核销"
                                bt_hx.setBackgroundResource(R.drawable.shi_background_green)
                            }else{
                                bt_hx.text = "未核销"
                                bt_hx.setBackgroundResource(R.drawable.shi_background_red)
                            }

                            val imageView = helper.getView<ImageView>(R.id.tv_item_com_w_o_ddxx)
                            Glide.with(this@CommodityWriteOffActivity).load(item.mdsePicture).into(imageView)
                            bt_hx.setOnClickListener {
                                mPresenter.getOrderMdse(item.mdseId,message.orderId,item.shopId,item.stockId,item.quantity)
                            }
                        }
                    }
                }
            }
        }else{
            ToastUtils.showShort("暂无商品信息")
        }

    }

    override fun returnOrderMdse(message: String) {
        mPresenter.getOrderNo(contentsFlag)
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
