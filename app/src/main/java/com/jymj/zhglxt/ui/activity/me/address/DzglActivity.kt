package com.jymj.zhglxt.ui.activity.me.address

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.contract.me.DzglContract
import com.jymj.zhglxt.ui.model.me.DzglModel
import com.jymj.zhglxt.ui.presenter.me.DzglPresenter
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_dzgl.*
import org.greenrobot.eventbus.EventBus

/**
 * 地址管理
 */
class DzglActivity : BaseActivity<DzglPresenter,DzglModel>(),DzglContract.View {

    private var dialog: AlertDialog? = null//弹窗
    private var jumpType = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_dzgl
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        val intExtra = intent.getIntExtra("ddqr", 0)
        jumpType = intExtra

        iv_act_dzgl_back.setOnClickListener {
            finish()
        }

        bt_act_dzgl_add.setOnClickListener {
            val intent = Intent(this, DzglAddActivity::class.java)
            intent.putExtra("dzglType",0)
            startActivity(intent)
        }
    }

    override fun initDatas() {
        mPresenter.addressList()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.addressList()
    }
    override fun returnAddressList(message: List<AddressInfo>) {
        rlv_act_dzgl.layoutManager = LinearLayoutManager(this)
        if (message!=null&&message.size>0){
            rlv_act_dzgl.visibility = View.VISIBLE
            rlv_act_dzgl.adapter = object :BaseQuickAdapter<AddressInfo,BaseViewHolder>(R.layout.item_dzgl_list,message){
                override fun convert(helper: BaseViewHolder?, item: AddressInfo?) {
                    helper!!.setText(R.id.tv_item_dzgl_list_name,item!!.name)
                            .setText(R.id.tv_item_dzgl_list_mobile,item.mobile)
                            .setText(R.id.tv_item_dzgl_list_address,item.region+" "+item.detailedAddress)
                    val mrTv = helper.getView<TextView>(R.id.tv_item_dzgl_list_mr)
                    if (item.status == 1){
                        mrTv.visibility = View.VISIBLE
                    }else{
                        mrTv.visibility = View.GONE
                    }
                    helper.getView<TextView>(R.id.tv_item_dzgl_list_bj).setOnClickListener {
                        var intent = Intent(this@DzglActivity, DzglAddActivity::class.java)
                        intent.putExtra("dzglType",1)
                        intent.putExtra("dzglId",item.addressId)
                        startActivity(intent)
                    }
                    helper.getView<TextView>(R.id.tv_item_dzgl_list_det).setOnClickListener {
                        //弹窗
                        dialog = AlertDialog.Builder(this@DzglActivity)
                                .setMessage("确定要删除改地址吗?")
                                .setPositiveButton("确定") { dialog, which ->
                                    mPresenter.deleteAddress(item.addressId.toString())
                                }
                                .setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
                                .create()
                        dialog!!.show()
                    }
                    helper.itemView.setOnClickListener {
                        EventBus.getDefault().postSticky(helper.adapterPosition.toString())
                        if (jumpType==1){
                            finish()
                        }
                    }
                }
            }
        }else{
            rlv_act_dzgl.visibility = View.GONE
        }

    }

    override fun returnDeleteAddress(message: String) {
        mPresenter.addressList()
    }

    override fun returnUpdateAddress(message: String) {

    }

    override fun returnAddressInfo(message: AddAddressBean) {

    }
    override fun returnAddAddress(message: String) {

    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
        ToastUtils.showShort(msg)
    }

}
