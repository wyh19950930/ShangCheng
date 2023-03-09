package com.jymj.zhglxt.ui.activity.me.order

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.tabs.TabLayout
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.OrderCenterContract
import com.jymj.zhglxt.login.presenter.OrderCenterPresenter
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.activity.me.tuikuan.AfterSalesActivity
import com.jymj.zhglxt.ui.activity.me.tuikuan.RetreatActivity
import com.jymj.zhglxt.ui.adapter.OrderCenterAdapter
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.model.me.OrderCenterModel
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_order_center.*
import java.io.Serializable

class OrderCenterActivity : BaseActivity<OrderCenterPresenter, OrderCenterModel>(), OrderCenterContract.View {

    var orderList = ArrayList<MallOrderInfo>()
    private var mdseName = ""
    private var page = 1
    private var limit = 10
    var linearLayoutManager: LinearLayoutManager? = null
    var positionType = 0
    var ddlbAdapter : OrderCenterAdapter?= null
    var tabName = "全部"
    var orderStutasStr = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_order_center
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        tabName = intent.getStringExtra("tabName")
        tab_order_center.addTab(tab_order_center.newTab().setText("全部"))
        tab_order_center.addTab(tab_order_center.newTab().setText("待付款"))
        tab_order_center.addTab(tab_order_center.newTab().setText("待发货"))
        tab_order_center.addTab(tab_order_center.newTab().setText("待收货"))
        tab_order_center.addTab(tab_order_center.newTab().setText("已完成"))
        iv_act_order_center_back.setOnClickListener {
            finish()
        }
        tv_act_order_center_qgg.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            intent.putExtra("jump", 0)
            startActivity(intent)
        }
        mactv_act_order_center_spss.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (mactv_act_order_center_spss.text.toString().equals("")){
                    tv_act_order_center_delete.visibility = View.GONE
                    mdseName = mactv_act_order_center_spss.text.toString()
                    page = 1
                    mPresenter.getOrderPages(orderStutasStr,mdseName, page, limit)
                }else{
                    tv_act_order_center_delete.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        tv_act_order_center_delete.setOnClickListener {//清空搜索框
            mactv_act_order_center_spss.setText("")
        }
        tv_act_order_center_ss.setOnClickListener {
            mdseName = mactv_act_order_center_spss.text.toString()
            page = 1
            mPresenter.getOrderPages(orderStutasStr,mdseName, page, limit)
        }


    }

    override fun initDatas() {



    }
    override fun returnOrderPages(message: List<MallOrderInfo>,type:String) {
        var firstVisibleItem = 0
        if (linearLayoutManager != null && page != 1) {
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        if (page == 1) {
            orderList.clear()
        }
        orderList.addAll(message)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rlv_act_order_center_dllb.layoutManager = linearLayoutManager
        ddlbAdapter = OrderCenterAdapter(R.layout.item_order_center, orderList, this,type)
        rlv_act_order_center_dllb.adapter = ddlbAdapter
        rlv_act_order_center_dllb.setItemViewCacheSize(-1)
        rlv_act_order_center_dllb.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1 && orderList.size == page * limit) {
                    if (orderList.size != 0 && orderList.size % limit == 0) {
                        page++
                        mPresenter.getOrderPages(orderStutasStr,mdseName, page, limit)
                    } else {
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page != 1) {
            rlv_act_order_center_dllb.scrollToPosition(firstVisibleItem + 1)//(page-1)*limit+
        }
        if (orderList.isNotEmpty()) {
            ll_order_center_zwdd.visibility = View.GONE
            rlv_act_order_center_dllb.visibility = View.VISIBLE
        }else{
            ll_order_center_zwdd.visibility = View.VISIBLE
            rlv_act_order_center_dllb.visibility = View.GONE
        }

        //操作
        ddlbAdapter!!.onItemChildClickListener = object:BaseQuickAdapter.OnItemChildClickListener {
            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                when (view!!.id) {
                    R.id.tv_item_order_center_delete -> {//删除订单
                        var dialog = SweetAlertDialog(this@OrderCenterActivity, SweetAlertDialog.WARNING_TYPE)
                        dialog.titleText = "是否删除？"
                        dialog.confirmText = "确认"
                        dialog.cancelText = "取消"
                        dialog.showCancelButton(true)
                        dialog.showContentText(false)
                        dialog.show()
                        dialog.setCancelClickListener { dialog.dismiss() }
                        dialog.setConfirmClickListener {
                            positionType = position
                            mPresenter.deleteOrderPages(orderList[position].orderId.toString())
                            dialog.cancel()
                        }
                    }
                    R.id.tv_item_order_center_affirm->{//确认收货
                        var dialog = SweetAlertDialog(this@OrderCenterActivity, SweetAlertDialog.WARNING_TYPE)
                        dialog.titleText = "是否确认收货？"
                        dialog.confirmText = "确认"
                        dialog.cancelText = "取消"
                        dialog.showCancelButton(true)
                        dialog.showContentText(false)
                        dialog.show()
                        dialog.setCancelClickListener { dialog.dismiss() }
                        dialog.setConfirmClickListener {
                            positionType = position
                            mPresenter.putOrderPages(orderList[position].orderId.toString(),"COMPLETED")
                            dialog.cancel()
                        }
                    }
                    R.id.tv_item_order_center_refund->{//申请售后 退款
                        var intent = Intent(this@OrderCenterActivity, AfterSalesActivity::class.java)
                        intent.putExtra("afterData",orderList[position] as Serializable)
                        startActivity(intent)
                    }
                    R.id.ll_item_order_center_dan ->{//单商品
                        var intent = Intent(this@OrderCenterActivity, OrderDetailsActivity::class.java)
                        intent.putExtra("orderId",orderList[position].orderId)
                        startActivity(intent)


                    }
                    R.id.ll_item_order_center_shuang ->{//多商品
                        var intent = Intent(this@OrderCenterActivity, OrderDetailsActivity::class.java)
                        intent.putExtra("orderId",orderList[position].orderId)
                        startActivity(intent)
                    }
                }
            }
        }
    }
    override fun returnDeleteOrderPages(message: String) {
        if (ddlbAdapter!=null){
            orderList.removeAt(positionType)
            ddlbAdapter!!.setNewData(orderList)
            ddlbAdapter!!.notifyDataSetChanged()
        }

    }
    override fun returnPutOrderPages(message: String) {
        if (ddlbAdapter!=null){
            orderList.removeAt(positionType)
            ddlbAdapter!!.setNewData(orderList)
            ddlbAdapter!!.notifyDataSetChanged()
        }
    }


    var resumeFlag = 0
    override fun onResume() {
        super.onResume()
        tab_order_center.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectTab(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectTab(tab)

            }
        })
        if (resumeFlag == 0){
            if (tabName.equals("全部")) {
                tab_order_center.getTabAt(0)!!.select()
            }else if (tabName.equals("待付款")) {
                tab_order_center.getTabAt(1)!!.select()
            } else if (tabName.equals("待发货")) {
                tab_order_center.getTabAt(2)!!.select()
            } else if (tabName.equals("待收货")) {
                tab_order_center.getTabAt(3)!!.select()
            } else if (tabName.equals("已完成")) {
                tab_order_center.getTabAt(4)!!.select()
            }
            resumeFlag = 1
        }

    }
    //tab选择方法
    private fun selectTab(tab: TabLayout.Tab?) {
        tabName = tab!!.text.toString()
        page = 1
        when (tabName) {
            "全部" -> {
                orderStutasStr = ""
            }
            "待付款" -> {
                orderStutasStr = "UNPAID"
            }
            "待发货" -> {
                orderStutasStr = "UNSHIPPED"
            }
            "待收货" -> {
                orderStutasStr = "UNRECEIVED"
            }
            "已完成" -> {
                orderStutasStr = "COMPLETED"
            }
        }
        mPresenter.getOrderPages(orderStutasStr, mdseName, page, limit)
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
