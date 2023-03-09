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
    private var isAllChecked = false //是否全选
	private var shopIdList = ArrayList<Int>()//店铺列表
	private var shopCartIdList = ArrayList<String>()//商品列表
    private var totalPrice = 0.00//商品总价
    private var totalCount = 0//商品总数量
    private var dialog: AlertDialog? = null//弹窗
    private var isEdit = false//是否编辑
    private var isHaveGoods = false//购物车是否有商品


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
        //禁用下拉刷新喝加载更多
        refresh!!.setEnableRefresh(false)
        refresh!!.setEnableLoadMore(false)
        //下拉刷新监听
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
     * 删除商品
     */
    private fun deleteGoods() {
        //店铺列表
        var storeList =  ArrayList<GwcList.DataBean.ContentBean>()

        for (i in 0 until gwcList!!.size) {
            val store = gwcList!![i]
            if (store.isChecked) {
                //店铺如果选择则添加到此列表中
                storeList.add(store)
            }

        }
        //删除店铺
        gwcList!!.removeAll(storeList)
        tv_total.text = "¥0.00"
        tv_settlement.text = "结算"
        isAllChecked = false

//        shopIdList.clear()//删除了选中商品，清空已选择的标识
        shopCartIdList.clear()//删除了选中商品，清空已选择的标识
        controlAllChecked()//控制去全选
        //改变界面UI
         tv_edit.text ="编辑"
            lay_edit.visibility = View.GONE
        isEdit = false
        //刷新数据
        gwcAdapter!!.notifyDataSetChanged()
        if (gwcList!!.size<=0){
            //无商品
            isHaveGoods = false
            //下拉加载数据完成后，关闭下拉刷新动画
            refresh!!.setEnableRefresh(true)
            //隐藏布局
//            lay_empty!!.visibility = View.VISIBLE
            include_gwc_null!!.visibility = View.VISIBLE
        }
    }


    //底部全选按钮状态
    private fun controlAllChecked() {
//        if (shopIdList.size == mList.size) {
        if (shopCartIdList.size == gwcList!!.size&&gwcList!!.size!=0) {
            //全选
            iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_checked))
            isAllChecked = true
        } else {
            //不全选
            iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_check))
            isAllChecked = false
        }
        //计算价格
        calculationPrice()
    }

    override fun initDatas() {

        mPresenter.getMdseInfo(100)

    }
    /**
     * 选中店铺（商品全选后反选中店铺）
     * @param shopId 店铺id
     */
    override fun checkedStore(shopId: Int, state: Boolean) {
        for (bean in mList) {
            //遍历
            if (shopId == bean.shopId) {
                bean.isChecked = state
                storeAdapter!!.notifyDataSetChanged()
                //记录选中店铺的shopid,添加到一个列表中。
                if (!shopIdList.contains(bean.shopId) && state) {
                    //如果列表中没有这个店铺Id且当前店铺为选中状态
                    shopIdList.add(bean.shopId)
                } else {
                    if (shopIdList.contains(bean.shopId)) {
                        //通过list.indexOf获取属性的在列表中的下标，不过强转Integer更简洁
                        shopIdList.remove(bean.shopId)
                    }
                }
            }
        }
        controlAllChecked()
    }


    /**
     * 商品价格
     */
    @SuppressLint("SetTextI18n")
    override fun calculationPrice() {
        //每次计算之前先置零
        totalPrice = 0.00
        totalCount = 0
        //循环购物车中的店铺列表
        /*for (i in 0 until mList.size) {
            val orderDataBean = mList[i]
            //循环店铺中的商品列表
            for (j in 0 until orderDataBean.cartlist.size) {
                val cartlistBean = orderDataBean.cartlist[j]
                //当有选中商品时计算数量和价格
                if (cartlistBean.isChecked) {
                    totalCount++
                    totalPrice += cartlistBean.price * cartlistBean.count
                }
            }
        }*/
        for (i in 0 until gwcList!!.size) {
            val orderDataBean = gwcList!![i]
            //循环店铺中的商品列表
                //当有选中商品时计算数量和价格
                if (orderDataBean.isChecked) {
                    totalCount++
                    totalPrice += orderDataBean.price * orderDataBean.quantity
                }
        }
        tv_total.text = "￥${String.format("%.2f",totalPrice)}"
        tv_settlement.text = if (totalCount == 0) "结算" else "结算($totalCount)"
    }




    /**
     * 是否全选
     *
     * @param state 状态
     */
    private fun isSelectAllStore(state: Boolean) {
        if (gwcList==null||gwcList!!.size==0){
            ToastUtils.showShort("暂无商品")
            return
        }
        //修改背景
        iv_checked_all.setImageDrawable(activity!!.getDrawable(if (state) R.drawable.ic_checked else R.drawable.ic_check))

        for (orderDataBean in gwcList!!) {
            //商品是否选中
            gwcAdapter!!.controlGoods(orderDataBean.shoppingCartId, state)
            //店铺是否选中
            orderDataBean.isChecked = state
            if (state) {
                //如果列表中没有这个店铺Id且当前店铺为选中状态
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
                ToastUtils.showShort("商品数量不可超过库存")
                return
            }
            count++
        }else{
            if (count<=1){
                ToastUtils.showShort("已是最低商品数量")
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
                        //传递选中店铺的id
                        if (item.isChecked) {
                            //添加到列表中
                            if (!shopCartIdList.contains(item.shoppingCartId)) {
                                //全选商品
                                gwcAdapter!!.controlGoods(item.shoppingCartId, true)
                                //如果列表中没有这个店铺Id且当前店铺为选中状态
                                shopCartIdList.add(item.shoppingCartId)
                            }

                        } else {
                            //从列表中清除
                            if (shopCartIdList.contains(item.shoppingCartId)) {
                                //清除全选商品
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

            //有商品
            isHaveGoods = true
            //下拉加载数据完成后，关闭下拉刷新动画
            refresh!!.finishRefresh()
            //隐藏布局
//        lay_empty!!.visibility = View.GONE
            include_gwc_null!!.visibility = View.GONE
            rv_store!!.visibility = View.VISIBLE

        }else{
            include_gwc_null!!.visibility = View.VISIBLE
            rv_store!!.visibility = View.GONE
        }
        //底部全选按钮
        ll_checked_all.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("当前购物车空空如也~")
            }
            if (isAllChecked) {
                //取消全选
                isSelectAllStore(false)
            } else {
                //全选
                isSelectAllStore(true)
            }
        }

        //编辑
        tv_edit.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("当前购物车空空如也~")
            }
            if (isEdit) {
                tv_edit.text = "编辑"
                lay_edit.visibility = View.GONE
                isEdit = false
            } else {
                tv_edit.text = "完成"
                lay_edit.visibility = View.VISIBLE
                isEdit = true
            }
        }

        //删除商品
        tv_delete_goods.setOnClickListener {
            if (totalCount == 0) {
                ToastUtils.showShort("请选择要删除的商品")
                return@setOnClickListener
            }
            //弹窗
            dialog = AlertDialog.Builder(activity)
                    .setMessage("确定要删除所选商品吗?")
                    .setPositiveButton("确定") { dialog, which ->
                        //                        deleteGoods()
                        deleteIds()
                    }
                    .setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
                    .create()
            dialog!!.show()

        }

        //结算商品
        tv_settlement.setOnClickListener {
            if (!isHaveGoods) {
                ToastUtils.showShort("当前购物车空空如也~")
            }
            if (totalCount == 0) {
                ToastUtils.showShort("请选择要结算的商品")
                return@setOnClickListener
            }
            //弹窗
            dialog = AlertDialog.Builder(activity)
                    .setMessage("总计:" + totalCount + "种商品，" + String.format("%.2f",totalPrice) + "元")
                    .setPositiveButton("确定") { dialog, which ->
                        settlement()

                    }
                    .setNegativeButton("取消") { dialog, which -> dialog.dismiss() }
                    .create()
            dialog!!.show()
        }


    }

    //结算
    fun settlement(){
        var settlementList = ArrayList<GwcList.DataBean.ContentBean>()
        for (i in 0 until gwcList!!.size) {
            val store = gwcList!![i]
            if (store.isChecked) {
                //店铺如果选择则添加到此列表中
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
                //店铺如果选择则添加到此列表中
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
    // 接收地址
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun getAddressPoition(messageEvent: String) {
//        iv_checked_all.setImageDrawable(activity!!.getDrawable(R.drawable.ic_check))
        deleteGoods()
        if (messageEvent.equals("刷新购物车"))
        mPresenter.getCartPages(1,999)
    }
    var cnxhList = ArrayList<MdseInfo>()
    override fun returnMdseInfo(user: List<MdseInfo>) {
        if (user.isNotEmpty()) {

            EventBus.getDefault().postSticky(user.size.toString())//发送购物车数量用于导航菜单小红点显示

            for (i in 0 until user.size) {
                if (user.get(i).typeInfo != null && user.get(i).typeInfo.typeId == 5L) {//热门推荐
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
                    helper!!.setText(R.id.tv_item_serach_cnxh_money, "￥${item!!.price}")
                            .setText(R.id.tv_item_serach_cnxh_gqmoney, "￥${helper.adapterPosition + 1}")
                            .setText(R.id.tv_item_serach_cnxh_title, item.name)
                    val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
                    val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
                    tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                    val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                            .transforms(RoundedCorners(30))//图片圆角为30
                    Glide.with(this@ShoppingFragment).load(if (item!!.pictureList != null && item.pictureList.size > 0) item.pictureList.get(0).url else "") //图片地址
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
