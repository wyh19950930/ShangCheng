package com.jymj.zhglxt.ui.activity.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.FillMemberInfoContract
import com.jymj.zhglxt.login.model.FillMemberInfoModel
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.presenter.shopping.FillMemberInfoPresenter
import com.jymj.zhglxt.util.EditHitUtils
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.RegexUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonutils.constant.RegexConstants
import kotlinx.android.synthetic.main.activity_fill_member_info.*

class FillMemberInfoActivity : BaseActivity<FillMemberInfoPresenter, FillMemberInfoModel>(), FillMemberInfoContract.View {
    override fun getLayoutId(): Int {
        return R.layout.activity_fill_member_info
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    override fun initViews() {
        iv_act_fillmemberinfo_back.setOnClickListener {
            finish()
        }

        tv_act_fillmemberinfo_tj.setOnClickListener {

            if (EditHitUtils.setEditToast(tv_act_fillmemberinfo_name, tv_act_fillmemberinfo_mobile, tv_act_fillmemberinfo_idnumber)) {
            } else {
                if (!cb_act_fillmemberinfo_sygz.isChecked) {
                    ToastUtils.showShort("请阅读并勾选会员卡信息使用规则")
                    return@setOnClickListener
                } else {
                    if (!RegexUtils.isMobileExact(tv_act_fillmemberinfo_mobile.text.toString())){
                        ToastUtils.showShort("请输入正确的手机号")
                        return@setOnClickListener
                    }
                    if (!RegexUtils.isIDCard18(tv_act_fillmemberinfo_idnumber.text.toString())){
                        ToastUtils.showShort("请输入正确的身份证号")
                        return@setOnClickListener

                    }
                    mPresenter.addMembersUserInfo(tv_act_fillmemberinfo_name.text.toString(), tv_act_fillmemberinfo_mobile.text.toString(),
                            tv_act_fillmemberinfo_idnumber.text.toString(), tv_act_fillmemberinfo_address.text.toString()
                            , tv_act_fillmemberinfo_email.text.toString())
                }
            }
        }
    }

    override fun initDatas() {
    }

    override fun returnMembersUserInfo(msg: MemberInfo) {
        finish()
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
