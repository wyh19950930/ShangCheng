package com.jymj.zhglxt.ui.activity.homepage

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jymj.basemessagesystem.ui.adapter.SpflLeftAdapter
import com.jymj.basemessagesystem.ui.adapter.SplbAdapter
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.FlBean
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.SpflContract
import com.jymj.zhglxt.login.presenter.SpflPresenter
import com.jymj.zhglxt.ui.bean.homepage.GroupListBean
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.model.homepage.SpflModel
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_spfl.*

class SpflActivity : BaseActivity<SpflPresenter, SpflModel>(), SpflContract.View {

    var classify =""
//    val arrayList = ArrayList<FlBean>()
    var spflLeftAdapter: SpflLeftAdapter? = null
    var groupId = 0
    var page = 1
    var size = 20
    var mdseInfoList = ArrayList<MdseInfo>()
    var linearLayoutManager: LinearLayoutManager? = null
    var cplbAdapter: SplbAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_spfl
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_spfl_qbsp.setOnClickListener {
            finish()
        }
        classify = intent.getStringExtra("classify")

    }

    override fun initDatas() {

//        mPresenter.getMdseInfo(groupId,page,size)
        mPresenter.getGroupList(27)


    }
//    商品列表
    override fun returnMdseInfo(user: List<MdseInfo>) {

       var firstVisibleItem = 0
       if (linearLayoutManager != null && page != 1) {
           firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
       }
       if (page == 1) {
           mdseInfoList.clear()
       }
       mdseInfoList.addAll(user)
       linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
       rlv_act_spfl_right.layoutManager = linearLayoutManager
       cplbAdapter = SplbAdapter(R.layout.item_shou_search_cplb, mdseInfoList,this,true)
       rlv_act_spfl_right.adapter = cplbAdapter
       rlv_act_spfl_right.setItemViewCacheSize(-1)
       rlv_act_spfl_right.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
               val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
               val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
               if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1 && mdseInfoList.size == page * size) {
                   if (mdseInfoList.size != 0 && mdseInfoList.size % size == 0) {
                       page++
                       mPresenter.getMdseInfo(groupId, page, size)
                   } else {
                       ToastUtils.showShort("滑动到底部了")
                   }
               }
           }
       })
       if (page != 1) {
           rlv_act_spfl_right.scrollToPosition(firstVisibleItem + 1)//(page-1)*limit+
       }
    if (mdseInfoList.isNotEmpty()) {
        ll_act_spfl_right.visibility = View.GONE
        rlv_act_spfl_right.visibility = View.VISIBLE
    }else{
        ll_act_spfl_right.visibility = View.VISIBLE
        rlv_act_spfl_right.visibility = View.GONE
    }

    }
    //分组列表
    override fun returnGroupList(message: ArrayList<GroupListBean>) {

       /* arrayList.add(FlBean("乡食"))
        arrayList.add(FlBean("乡居"))
        arrayList.add(FlBean("乡游"))
        arrayList.add(FlBean("乡货"))
        arrayList.add(FlBean("农业体验"))
        arrayList.add(FlBean("亲子研学"))
        arrayList.add(FlBean("运动健康"))
        arrayList.add(FlBean("商务拓展"))
        arrayList.add(FlBean("卡产品"))*/
        tv_act_spfl_right_name.setText(classify)
        spflLeftAdapter = SpflLeftAdapter(R.layout.item_spfl_left, message)
        rlv_act_spfl_left.layoutManager = LinearLayoutManager(this)
        rlv_act_spfl_left.adapter = spflLeftAdapter
        spflLeftAdapter!!.setOnItemClickListener(object:BaseQuickAdapter.OnItemClickListener{
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                selectClassIfy(message.get(position).name,message)
            }
        })
        selectClassIfy(classify,message)
    }

    private fun selectClassIfy(name: String,groupList: List<GroupListBean>) {
        var isContain = true
        for (get in groupList) {
            get.isCheck = false
            if (get.name.equals(name)) {
                isContain = false
                get.isCheck = true
                groupId = get.groupId
                page = 1
                mPresenter.getMdseInfo(groupId,page,size)
            }
        }
        tv_act_spfl_right_name.setText(name)
        spflLeftAdapter!!.notifyDataSetChanged()
        if (isContain){
            mdseInfoList.clear()
            cplbAdapter!!.notifyDataSetChanged()
            ll_act_spfl_right.visibility = View.GONE
            rlv_act_spfl_right.visibility = View.VISIBLE
        }
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
