package com.jymj.zhglxt.ui.activity.me

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.material.tabs.TabLayout
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.CouPonContract
import com.jymj.zhglxt.login.presenter.CouPonPresenter
import com.jymj.zhglxt.util.SingleOnClickUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_coupon.*

class CouponActivity : BaseActivity<CouPonPresenter, CouPonContract.Model>(), CouPonContract.View {
    private var type = 0
    var yhqAdapter: BaseQuickAdapter<String, BaseViewHolder>? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_coupon
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_cou_pon.setOnClickListener {
            finish()
        }
        tv_act_coupon_dhan.setOnClickListener {
            if (SingleOnClickUtil.isFastClick()){
                ToastUtils.showShort(et_act_coupon_qsrdhm.text.toString())
            }
        }


    }

    override fun initDatas() {
        
        tab_act_coupon.addTab(tab_act_coupon.newTab().setText("未使用"))
        tab_act_coupon.addTab(tab_act_coupon.newTab().setText("已使用"))
        tab_act_coupon.addTab(tab_act_coupon.newTab().setText("已过期"))
        tab_act_coupon.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                type = tab!!.position
                yhqAdapter!!.notifyDataSetChanged()
            }
        })

        val arrayList = ArrayList<String>()
        arrayList.add("新人专享")
        arrayList.add("新人专享")
        arrayList.add("新人专享")
        rlv_act_coupon_yhq.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        yhqAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_coupon_yhq, arrayList) {
            override fun convert(helper: BaseViewHolder?, item: String?) {
                val iv_item_coupon_yhq_ysy = helper!!.getView<ImageView>(R.id.iv_item_coupon_yhq_ysy)
                val iv_item_coupon_yhq_ygq = helper!!.getView<ImageView>(R.id.iv_item_coupon_yhq_ygq)
                val ll_item_coupon_yhq_ljsy = helper!!.getView<LinearLayout>(R.id.ll_item_coupon_yhq_ljsy)

                if (type == 0) {
                    ll_item_coupon_yhq_ljsy.visibility = View.VISIBLE
                    iv_item_coupon_yhq_ysy.visibility = View.GONE
                    iv_item_coupon_yhq_ygq.visibility = View.GONE
                }else if (type == 1) {
                    ll_item_coupon_yhq_ljsy.visibility = View.GONE
                    iv_item_coupon_yhq_ysy.visibility = View.VISIBLE
                    iv_item_coupon_yhq_ygq.visibility = View.GONE
                }else if (type == 2) {
                    ll_item_coupon_yhq_ljsy.visibility = View.GONE
                    iv_item_coupon_yhq_ysy.visibility = View.GONE
                    iv_item_coupon_yhq_ygq.visibility = View.VISIBLE
                }
            }
        }
        rlv_act_coupon_yhq.adapter = yhqAdapter

    }

    override fun returnUser(user: User) {
        
    }

    override fun changeUser() {
        
    }

    override fun changeUser(msg: String) {
        
    }

    override fun returnWclCount(msg: Int) {
        
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
