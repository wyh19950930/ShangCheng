package com.jymj.zhglxt.ui.activity.me.address

import android.content.Intent
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.contract.me.DzglContract
import com.jymj.zhglxt.ui.model.me.DzglModel
import com.jymj.zhglxt.ui.presenter.me.DzglPresenter
import com.jymj.zhglxt.util.EditHitUtils
import com.setsuna.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_dzgl_add.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 新增地址
 */

class DzglAddActivity : BaseActivity<DzglPresenter, DzglModel>(), DzglContract.View {

    var isMrdz = false
    var dzglType = 0
    var dzglIdType = 0L

    override fun getLayoutId(): Int {
        return R.layout.activity_dzgl_add
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        EventBus.getDefault().register(this)


        dzglType = intent.getIntExtra("dzglType", 0)
        if (dzglType == 1) {
            dzglIdType = intent.getLongExtra("dzglId", 0L)
            mPresenter.addressInfo(dzglIdType)
        }

        iv_act_add_dzgl_back.setOnClickListener {
            finish()
        }

        ll_act_dzgl_add_szdq.setOnClickListener {

            startActivity(Intent(this, DzglSelectActivity::class.java))

        }

        //设为默认地址
        iv_act_dzgl_add_mrdz.setOnClickListener {

            if (!isMrdz) {
                iv_act_dzgl_add_mrdz.setImageResource(R.drawable.dzgl_mrk_icon)
                isMrdz = true
            } else {
                iv_act_dzgl_add_mrdz.setImageResource(R.drawable.dzgl_mrg_icon)
                isMrdz = false
            }

        }

    }

    override fun initDatas() {
        bt_act_dzgl_save.setOnClickListener {
            if (EditHitUtils.setEditToast(et_dzgl_add_name, et_dzgl_add_mobile,
                            tv_act_dzgl_add_szdq, et_dzgl_add_detailsaddress)) {
            } else {

                if (dzglIdType!=0L){
                    mPresenter.updateAddress(et_dzgl_add_name.text.toString(), et_dzgl_add_mobile.text.toString(),
                            tv_act_dzgl_add_szdq.text.toString(), et_dzgl_add_detailsaddress.text.toString(), isMrdz,dzglIdType)
                }else{
                    mPresenter.addAddress(et_dzgl_add_name.text.toString(), et_dzgl_add_mobile.text.toString(),
                            tv_act_dzgl_add_szdq.text.toString(), et_dzgl_add_detailsaddress.text.toString(), isMrdz)
                }

            }

        }
    }

    override fun returnAddAddress(message: String) {
        finish()
    }
    override fun returnUpdateAddress(message: String) {
        finish()
    }

    override fun returnAddressInfo(message: AddAddressBean) {
        if (message!=null){
            if (message.data!=null){
                et_dzgl_add_name.setText(message.data.name)
                et_dzgl_add_mobile.setText(message.data.mobile)
                tv_act_dzgl_add_szdq.text = message.data.region
                et_dzgl_add_detailsaddress.setText(message.data.detailedAddress)
                if (message.data.status == 1){
                    isMrdz = true
                    iv_act_dzgl_add_mrdz.setImageResource(R.drawable.dzgl_mrk_icon)
                }else{
                    isMrdz = false
                    iv_act_dzgl_add_mrdz.setImageResource(R.drawable.dzgl_mrg_icon)
                }
            }
        }

    }

    override fun returnAddressList(message: List<AddressInfo>) {

    }
    override fun returnDeleteAddress(message: String) {

    }

    // 接收地址
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(messageEvent: String) {
        tv_act_dzgl_add_szdq.text = messageEvent
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

}
