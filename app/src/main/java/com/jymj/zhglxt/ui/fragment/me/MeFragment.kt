package com.jymj.zhglxt.ui.fragment.me


import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.itingchunyu.badgeview.BadgeTextView
import com.jymj.zhglxt.BuildConfig

import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.activity.LoginActivity
import com.jymj.zhglxt.ui.activity.me.CommodityWriteOffActivity
import com.jymj.zhglxt.ui.activity.me.address.DzglActivity
import com.jymj.zhglxt.ui.activity.me.CouponActivity
import com.jymj.zhglxt.ui.activity.me.order.OrderCenterActivity
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.MeContract
import com.jymj.zhglxt.ui.model.MeModel
import com.jymj.zhglxt.ui.presenter.MePresenter
import com.jymj.zhglxt.util.*
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.commonutils.*
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_me.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class MeFragment : BaseFragment<MePresenter, MeModel>(), MeContract.View {

    private var clearUpPopu: CommenPop? = null
    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_me
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {//SelectZhActivity
        var versionName = BuildConfig.VERSION_NAME
//        tv_act_me_bbh.setText("v ${versionName}版本")//设置版本号


        val uname = SPUtils.getSharedStringData(activity, "uname")
        /*if (uname.equals("ly001")){
            ll_act_me_qhzh.visibility = View.VISIBLE
            ll_act_me_mmaq.visibility = View.GONE
        }else{
            ll_act_me_qhzh.visibility = View.GONE
            ll_act_me_mmaq.visibility = View.VISIBLE
        }*/
//        }
        //CouponActivity
        act_me_coupon.setOnClickListener {
            val intent = Intent(activity, CouponActivity::class.java)
            startActivity(intent)
        }
        tv_act_me_ckqb.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "全部")
            startActivity(intent)
        }
        tv_act_me_dfk.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "待付款")
            startActivity(intent)
        }
        tv_act_me_dfh.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "待发货")
            startActivity(intent)
        }
        tv_act_me_dsh.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "待收货")
            startActivity(intent)
        }
        tv_act_me_ywc.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "已完成")
            startActivity(intent)
        }
        tv_act_me_tksh.setOnClickListener {
            val intent = Intent(activity, OrderCenterActivity::class.java)
            intent.putExtra("tabName", "全部")
            startActivity(intent)
        }
        rl_act_me_personal_data.setOnClickListener {
            //跳转个人资料
            if (SingleOnClickUtil1.isFastClick()) {
                /*val intent = Intent(activity, PersonalDataActivity::class.java)
                startActivity(intent)*/
            }
        }

        but_act_me_out.setOnClickListener {
            //登出系统
            if (SingleOnClickUtil1.isFastClick()) {
                SPUtils.setSharedBooleanData(activity, "islogin", false)
                SPUtils.setSharedStringData(activity, "unpwd", "")
                SPUtils.setSharedStringData(activity, "uname", "")
                SPUtils.setSharedStringData(activity, "deviceId", "")
//            AppManager.getAppManager().finishAllActivity()
                val intentmain = Intent(activity, LoginActivity::class.java)//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intentmain)
            }
        }
//        tv_act_me_size.setText(FileUtils.getDirSize(AppMenus.getInstance().cardPath + "BMS/map/"))
        //商品核销
        ll_frag_me_commodity_write_off.setOnClickListener {

            val intent = Intent(activity,CommodityWriteOffActivity::class.java)
            startActivity(intent)

        }

        //地址管理
        ll_frag_me_dzgl.setOnClickListener {
            startActivity(Intent(activity, DzglActivity::class.java))
        }

        /*val badgeTextView = BadgeTextView(activity)
        badgeTextView.setTargetView(tv_act_me_dfk)
        badgeTextView.setBadgeCount(9)*/


    }

    override fun initDatas() {
    }

    override fun returnOrderPages(message: List<MallOrderInfo>, type: String) {
        when (type) {
            "UNPAID" -> {
                val badgeTextView = BadgeTextView(activity)
                if (message != null && message.size > 0) {
                    badgeTextView.setTargetView(tv_act_me_dfk)
                    badgeTextView.setBadgeCount(message.size)
                            .setmDefaultRightPadding(10)
                }else{
                    badgeTextView.visibility = View.GONE
                }
            }
            "UNSHIPPED" -> {
                val badgeTextView = BadgeTextView(activity)
                if (message != null && message.size > 0) {
                    badgeTextView.setTargetView(tv_act_me_dfh)
                    badgeTextView.setBadgeCount(message.size)
                            .setmDefaultRightPadding(10)
                }else{
                    badgeTextView.visibility = View.GONE
                }
            }
            "UNRECEIVED" -> {
                val badgeTextView = BadgeTextView(activity)
                if (message != null && message.size > 0) {
                    badgeTextView.setTargetView(tv_act_me_dsh)
                    badgeTextView.setBadgeCount(message.size)
                            .setmDefaultRightPadding(10)
                }else{
                    badgeTextView.visibility = View.GONE
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()

        mPresenter.getOrderPages("UNPAID", 1, 99)
        mPresenter.getOrderPages("UNSHIPPED", 1, 99)
        mPresenter.getOrderPages("UNRECEIVED", 1, 99)

    }

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(activity)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
