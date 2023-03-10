package com.jymj.zhglxt.ui.fragment.shopping


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Paint
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson

import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.activity.shopping.CommodityDetailsActivity
import com.jymj.zhglxt.ui.activity.shopping.DdqrActivity
import com.jymj.zhglxt.ui.adapter.gwc.GoodsAdapter1
import com.jymj.zhglxt.ui.adapter.gwc.StoreAdapter
import com.jymj.zhglxt.ui.bean.gwc.CarResponse
import com.jymj.zhglxt.ui.bean.gwc.Constant
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.contract.shopping.ShoppingContract
import com.jymj.zhglxt.ui.model.shopping.ShoppingModel
import com.jymj.zhglxt.ui.presenter.shopping.ShoppingPresenter
import com.jymj.zhglxt.util.BigDecimalManager
import com.jymj.zhglxt.util.gwc.GoodsCallback
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.commonutils.*
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.include_gwc.*
import kotlinx.android.synthetic.main.include_gwc_null.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class ShoppingFragment : BaseFragment<ShoppingPresenter, ShoppingModel>(), ShoppingContract.View ,GoodsCallback{



    var gdjxsp = ArrayList<String>()

    private var mList = ArrayList<CarResponse.OrderDataBean>()
    private var gwcList : ArrayList<GwcList.DataBean.ContentBean>?=null
    private var storeAdapter:StoreAdapter?=null
    private var gwcAdapter:GoodsAdapter1?=null
    private var isAllChecked = false //????????????
	private var shopIdList = ArrayList<Int>()//????????????
	private var shopCartIdList = ArrayList<String>()//????????????
    private var totalPrice = 0.00//????????????
    private var totalCount = 0//???????????????
    private var dialog: AlertDialog? = null//??????
    private var isEdit = false//????????????
    private var isHaveGoods = false//????????????????????????


    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_shopping
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {//SelectZhActivity
//        activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        EventBus.getDefault().register(this)
        mPresenter.getCartPages(1,999)
        //?????????????????????????????????
        refresh!!.setEnableRefresh(false)
        refresh!!.setEnableLoadMore(false)
        //??????????????????
        refresh!!.setOnRefreshListener { refreshLayout ->
//            gwcInitView()
        }

//        gwcInitView()
        tv_gwc_null_qgg.setOnClickListener {
            val intent = Intent(activity, FirstActivity::class.java)
            intent.putExtra("jump", 0)
            startActivity(intent)
        }

    }


    /**
     * ????????????
     */
    private fun deleteGoods() {
        //????????????
        var storeList =  ArrayList<GwcList.DataBean.ContentBean>()

        for (i in 0 until gwcList!!.size) {
            val store = gwcList!![i]
            if (store.isChecked) {
                //??????????????????????????????????????????
                storeList.add(store)
            }

        }
        //????????????
        gwcList!!.removeAll(storeList)
        tv_total.text = "??0.00"
        tv_settlement.text = "??????"
        isAllChecked = false

//        shopIdList.clear()//????????????????????????????????????????????????
        shopCartIdList.clear()//????????????????????????????????????????????????
        controlAllChecked()//???????????????
        //????????????UI
         tv_edit.text ="??????"
            lay_edit.visibility = View.GONE
        isEdit = false
        //????????????
        gwcAdapter!!.notifyDataSetChanged()
        if (gwcList!!.size<=0){
            //?????????
            isHaveGoods = false
            //??????????????????????????????????????????????????????
            refresh!!.setEnableRefresh(true)
            //????????????
//            lay_empty!!.visibility = View.VISIBLE
            include_gwc_null!!.visibility = View.VISIBLE
        }
    }


    //????????????????????????
    private fun controlAllChecked() {
//        if (shopIdList.size == mList.size) {
        if (shopCartIdList.size == gwcList!!.size&&gwcList!!.size!=0) {
            //??????
            iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_checked))
            isAllChecked = true
        } else {
            //?????????
            iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_check))
            isAllChecked = false
        }
        //????????????
        calculationPrice()
    }

    override fun initDatas() {

        mPresenter.getMdseInfo(100)

    }
    /**
     * ????????????????????????????????????????????????
     * @param shopId ??????id
     */
    override fun checkedStore(shopId: Int, state: Boolean) {
        for (bean in mList) {
            //??????
            if (shopId == bean.shopId) {
                bean.isChecked = state
                storeAdapter!!.notifyDataSetChanged()
                //?????????????????????shopid,???????????????????????????
                if (!shopIdList.contains(bean.shopId) && state) {
                    //?????????????????????????????????Id??????????????????????????????
                    shopIdList.add(bean.shopId)
                } else {
                    if (shopIdList.contains(bean.shopId)) {
                        //??????list.indexOf???????????????????????????????????????????????????Integer?????????
                        shopIdList.remove(bean.shopId)
                    }
                }
            }
        }
        controlAllChecked()
    }


    /**
     * ????????????
     */
    @SuppressLint("SetTextI18n")
    override fun calculationPrice() {
        //???????????????????????????
        totalPrice = 0.00
        totalCount = 0
        //?????????????????????????????????
        /*for (i in 0 until mList.size) {
            val orderDataBean = mList[i]
            //??????????????????????????????
            for (j in 0 until orderDataBean.cartlist.size) {
                val cartlistBean = orderDataBean.cartlist[j]
                //??????????????????????????????????????????
                if (cartlistBean.isChecked) {
                    totalCount++
                    totalPrice += cartlistBean.price * cartlistBean.count
                }
            }
        }*/
        for (i in 0 until gwcList!!.size) {
            val orderDataBean = gwcList!![i]
            //??????????????????????????????
                //??????????????????????????????????????????
                if (orderDataBean.isChecked) {
                    totalCount++
                    totalPrice += orderDataBean.price * orderDataBean.quantity
                }
        }
        tv_total.text = "???${String.format("%.2f",totalPrice)}"
        tv_settlement.text = if (totalCount == 0) "??????" else "??????($totalCount)"
    }




    /**
     * ????????????
     *
     * @param state ??????
     */
    private fun isSelectAllStore(state: Boolean) {
        if (gwcList==null||gwcList!!.size==0){
            ToastUtils.showShort("????????????")
            return
        }
        //????????????
        iv_checked_all.setImageDrawable(activity!!.getDrawable(if (state) R.drawable.ic_checked else R.drawable.ic_check))

        for (orderDataBean in gwcList!!) {
            //??????????????????
            gwcAdapter!!.controlGoods(orderDataBean.shoppingCartId, state)
            //??????????????????
            orderDataBean.isChecked = state
            if (state) {
                //?????????????????????????????????Id??????????????????????????????
                if (!shopCartIdList.contains(orderDataBean.shoppingCartId)){
                    shopCartIdList.add(orderDataBean.shoppingCartId)
                }
            } else {
                if (shopCartIdList.contains(orderDataBean.shoppingCartId)){
                    shopCartIdList.remove(orderDataBean.shoppingCartId)
                }
            }
        }
        controlAllChecked()
        gwcAdapter!!.notifyDataSetChanged()
        isAllChecked = state
    }

    private fun updateGoodsNum(bean:GwcList.DataBean.ContentBean,adapter:GoodsAdapter1,state:Boolean){
        var inventory = 10
        var count = bean.quantity
        if (state){
            if (count>=inventory){
                ToastUtils.showShort("??????????????????????????????")
                return
            }
            count++
        }else{
            if (count<=1){
                ToastUtils.showShort("????????????????????????")
                return
            }
            count--
        }
        bean.quantity = count
        adapter.notifyDataSetChanged()
        calculationPrice()
    }

    override fun returnCartPages(msg: List<GwcList.DataBean.ContentBean>) {
        val groupBy = msg.groupBy {
            it.shopInfo
        }
        if (msg!=null&&msg.size>0){
            gwcList = ArrayList<GwcList.DataBean.ContentBean>()
            gwcList!!.addAll(msg)

            gwcAdapter = GoodsAdapter1(R.layout.item_good,gwcList)
            rv_store.layoutManager = LinearLayoutManager(activity)
            rv_store.adapter = gwcAdapter

            gwcAdapter!!.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                val item = gwcList!![position]
                when(view.id){
                    R.id.iv_checked_goods->{
                        item.isChecked = !item.isChecked
                        gwcAdapter!!.notifyDataSetChanged()
                        calculationPrice()
                        //?????????????????????id
                        if (item.isChecked) {
                            //??????????????????
                            if (!shopCartIdList.contains(item.shoppingCartId)) {
                                //????????????
                                gwcAdapter!!.controlGoods(item.shoppingCartId, true)
                                //?????????????????????????????????Id??????????????????????????????
                                shopCartIdList.add(item.shoppingCartId)
                            }

                        } else {
                            //??????????????????
                            if (shopCartIdList.contains(item.shoppingCartId)) {
                                //??????????????????
                                gwcAdapter!!.controlGoods(item.shoppingCartId, false)
                                shopCartIdList.remove(item.shoppingCartId)
                            }
                        }
                        controlAllChecked()

                    }
                    R.id.tv_increase_goods_num->{//+
                        updateGoodsNum(item,gwcAdapter!!,true)

//                        ToastUtils.showShort(item.quantity.toString())
                        mPresenter.getUpdateCart(item.mdseId.toLong(),item.quantity,item.shopInfo.shopId.toLong(),
                                item.stockInfo.stockId.toLong(), item.shoppingCartId.toLong())
                    }
                    R.id.tv_reduce_goods_num->{//-
                        updateGoodsNum(item,gwcAdapter!!,false)
//                        ToastUtils.showShort(item.quantity.toString())
                        mPresenter.getUpdateCart(item.mdseId.toLong(),item.quantity,item.shopInfo.shopId.toLong(),
                                item.stockInfo.stockId.toLong(), item.shoppingCartId.toLong())
                    }

                }
            }

            //?????????
            isHaveGoods = true
            //??????????????????????????????????????????????????????
            refresh!!.finishRefresh()
            //????????????
//        lay_empty!!.visibility = View.GONE
            include_gwc_null!!.visibility = View.GONE
            rv_store!!.visibility = View.VISIBLE

        }else{
            include_gwc_null!!.visibility = View.VISIBLE
            rv_store!!.visibility = View.GONE
        }
        //??????????????????
        ll_checked_all.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("???????????????????????????~")
            }
            if (isAllChecked) {
                //????????????
                isSelectAllStore(false)
            } else {
                //??????
                isSelectAllStore(true)
            }
        }

        //??????
        tv_edit.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("???????????????????????????~")
            }
            if (isEdit) {
                tv_edit.text = "??????"
                lay_edit.visibility = View.GONE
                isEdit = false
            } else {
                tv_edit.text = "??????"
                lay_edit.visibility = View.VISIBLE
                isEdit = true
            }
        }

        //????????????
        tv_delete_goods.setOnClickListener {
            if (totalCount == 0) {
                ToastUtils.showShort("???????????????????????????")
                return@setOnClickListener
            }
            //??????
            dialog = AlertDialog.Builder(activity)
                    .setMessage("???????????????????????????????")
                    .setPositiveButton("??????") { dialog, which ->
                        //                        deleteGoods()
                        deleteIds()
                    }
                    .setNegativeButton("??????") { dialog, which -> dialog.dismiss() }
                    .create()
            dialog!!.show()

        }

        //????????????
        tv_settlement.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("???????????????????????????~")
            }
            if (totalCount == 0) {
                ToastUtils.showShort("???????????????????????????")
                return@setOnClickListener
            }
            //??????
            dialog = AlertDialog.Builder(activity)
                    .setMessage("??????:" + totalCount + "????????????" + String.format("%.2f",totalPrice) + "???")
                    .setPositiveButton("??????") { dialog, which ->
                        settlement()

                    }
                    .setNegativeButton("??????") { dialog, which -> dialog.dismiss() }
                    .create()
            dialog!!.show()
        }


    }

    //??????
    fun settlement(){
        var settlementList = ArrayList<GwcList.DataBean.ContentBean>()
        for (i in 0 until gwcList!!.size) {
            val store = gwcList!![i]
            if (store.isChecked) {
                //??????????????????????????????????????????
                settlementList.add(gwcList!![i])
            }
        }
        var intent = Intent(activity,DdqrActivity::class.java)
        intent.putExtra("jumpDdqrActivity",2)
        intent.putExtra("settlement",settlementList)
        startActivity(intent)
    }


    fun deleteIds(){
        var idsList =  ArrayList<String>()
        var idsStr = ""

        for (i in 0 until gwcList!!.size) {
            val store = gwcList!![i]
            if (store.isChecked) {
                //??????????????????????????????????????????
                idsList.add(store.shoppingCartId)
                idsStr += store.shoppingCartId+","
            }

        }
        mPresenter.getDeleteCart(idsStr)
    }

    override fun returnUpdateCart(msg: String) {

    }
    override fun returnDeleteCart(msg: String) {
        deleteGoods()
    }
    // ????????????
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun getAddressPoition(messageEvent: String) {
//        iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_check))
        deleteGoods()
        if (messageEvent.equals("???????????????"))
        mPresenter.getCartPages(1,999)
    }
    var cnxhList = ArrayList<MdseInfo>()
    override fun returnMdseInfo(user: List<MdseInfo>) {
        if (user.isNotEmpty()) {

            EventBus.getDefault().postSticky(user.size.toString())//??????????????????????????????????????????????????????

            for (i in 0 until user.size) {
                if (user.get(i).typeInfo != null && user.get(i).typeInfo.typeId == 5L) {//????????????
                    cnxhList.add(user.get(i))
                }

            }


            val cnxhAll = ArrayList<MdseInfo>()
            if (cnxhList.size > 3) {
                for (i in 0..2) {
                    cnxhAll.add(cnxhList.get(i))
                }
            } else {
                cnxhAll.addAll(cnxhList)
            }
            rlv_frag_shop_gdjxsp.layoutManager = GridLayoutManager(activity,2)
            rlv_frag_shop_gdjxsp.adapter = object : BaseQuickAdapter<MdseInfo, BaseViewHolder>(R.layout.item_search_cnxh, cnxhAll) {
                override fun convert(helper: BaseViewHolder?, item: MdseInfo?) {
                    helper!!.setText(R.id.tv_item_serach_cnxh_money, "???${item!!.price}")
                            .setText(R.id.tv_item_serach_cnxh_gqmoney, "???${helper.adapterPosition + 1}")
                            .setText(R.id.tv_item_serach_cnxh_title, item.name)
                    val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
                    val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
                    tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //????????????
                    val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                            .transforms(RoundedCorners(30))//???????????????30
                    Glide.with(this@ShoppingFragment).load(if (item!!.pictureList != null && item.pictureList.size > 0) item.pictureList.get(0).url else "") //????????????
                            .apply(options)
                            .into(iv_item_serach_cnxh_img)

                    helper.itemView.setOnClickListener {
                        val intent = Intent(activity, CommodityDetailsActivity::class.java)
                        intent.putExtra("details", item)
                        startActivity(intent)
                    }

                }
            }


        }
    }

    override fun onResume() {
        super.onResume()
//        AppManager.getAppManager().finishActivity()
    }
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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
