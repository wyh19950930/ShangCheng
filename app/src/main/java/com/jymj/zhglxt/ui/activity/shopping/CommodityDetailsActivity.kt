package com.jymj.zhglxt.ui.activity.shopping

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.contract.CommodityDetailsContract
import com.jymj.zhglxt.login.model.CommodityDetailsModel
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.jymj.zhglxt.ui.adapter.SpecsGroupAdapter
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.bean.homepage.SpecMap
import com.jymj.zhglxt.ui.presenter.homepage.CommodityDetailsPresenter
import com.jymj.zhglxt.util.CommenPop
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_commodity_details.*
import me.iwf.photopicker.PhotoPreview
import org.greenrobot.eventbus.EventBus

class CommodityDetailsActivity : BaseActivity<CommodityDetailsPresenter, CommodityDetailsModel>(), CommodityDetailsContract.View {


    var bottomPopup: CommenPop? = null
    var cnxhList = ArrayList<MdseInfo>()
    var buyType = 0 //0加入购物车 1立即购买

    var mdseInfoFlag : MdseInfo?=null

    override fun getLayoutId(): Int {
        return R.layout.activity_commodity_details
    }

    override fun initPresenter() {
        mPresenter.setVM(this, mModel)
    }

    @SuppressLint("SetTextI18n")
    override fun initViews() {

        iv_act_com_det_back.setOnClickListener {
            finish()
        }

        //跳转到购物车
        tv_act_commodity_details_jump_gwc.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            intent.putExtra("jump", 1)
            startActivity(intent)
//            finish()
        }

        val mdseInfo = intent.getSerializableExtra("details") as MdseInfo
        mdseInfoFlag = mdseInfo
        if (mdseInfo != null) {
            if (mdseInfo.classify == 2){
                tv_act_commodity_details_gwc.visibility = View.GONE
                tv_act_commodity_details_ljgm.setBackgroundResource(R.drawable.bt_actiive_ljgm_kuang_round)

                if (mdseInfo.mdseInfoList!=null&&mdseInfo.mdseInfoList.size>0){
                    rlt_act_com_det_kcp.visibility = View.VISIBLE
                    rlv_act_com_det_kcp.layoutManager = LinearLayoutManager(this)
                    rlv_act_com_det_kcp.adapter = object:BaseQuickAdapter<MdseInfo,BaseViewHolder>
                    (R.layout.item_orderdetails_ddxx,mdseInfo.mdseInfoList){
                        override fun convert(helper: BaseViewHolder?, item: MdseInfo?) {
                            helper!!.setText(R.id.tv_item_orderdetails_ddxx_title,item!!.name)
                                    .setText(R.id.tv_item_orderdetails_ddxx_price,item.price.toString()+" x "+item.quantity.toString())
                            helper.getView<TextView>(R.id.tv_item_orderdetails_ddxx_hx).visibility = View.GONE
                            helper.getView<TextView>(R.id.tv_item_orderdetails_ddxx_lx).visibility = View.GONE
                            helper.getView<ImageView>(R.id.iv_item_orderdetails_ddxx_lx).visibility = View.GONE
                            val imageView = helper.getView<ImageView>(R.id.iv_item_orderdetails_ddxx)
                            Glide.with(this@CommodityDetailsActivity).load(item.pictureList[0].url).into(imageView)

                        }
                    }

                }

            }


            tv_act_xm_tz_price.text = mdseInfo.price.toString()
            tv_act_xm_tz_title.text = mdseInfo.name

            val imgList = ArrayList<String>()
            val imgTitleList = ArrayList<String>()
            if (mdseInfo.pictureList != null && mdseInfo.pictureList.size > 0) {
                for (i in 0 until mdseInfo.pictureList.size) {
                    imgList.add(mdseInfo.pictureList.get(i).url)
                    imgTitleList.add(" ")
                }
            }


            banner_commodity_detail.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                    .setDelayTime(6000)
                    .setImageLoader(NetViewHolder())
                    .setBannerTitles(imgTitleList)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .setImages(imgList).setOnBannerListener(object : OnBannerListener {
                        override fun OnBannerClick(position: Int) {
                            PhotoPreview.builder()
                                    .setPhotos(imgList)
                                    .setCurrentItem(position)
                                    .setShowDeleteButton(false)
                                    .start(this@CommodityDetailsActivity)
//                            ToastUtils.showShort("点击第"+position+"张")
                        }
                    })
            banner_commodity_detail.start()


            //商品详情
            val replace = mdseInfo.details.replace("<img", "<img style=\"display:        ;max-width:100%;\"")
            web_act_com_det_spxq.loadDataWithBaseURL(null, replace, "text/html", "utf-8", null)



            //猜你喜欢


            //购物车
            tv_act_commodity_details_gwc.setOnClickListener {
                buyType = 0
                bottomPop(mdseInfo)
            }

            //立即购买
            tv_act_commodity_details_ljgm.setOnClickListener {
                buyType = 1
                if (mdseInfo.classify == 2){//卡产品
                    mPresenter.getMembersUserInfo(AppCache.getInstance().userId.toInt())
                }else if (mdseInfo.classify == 1){
                    bottomPop(mdseInfo)
                }

            }

        }


    }

    override fun initDatas() {
        mPresenter.getMdseInfo(100)
    }

    var selectSpecsGroupList = ArrayList<String>()
    var changeSpecsGroupList: ArrayList<String>? = null
    var allSpecsGroupList = ArrayList<ArrayList<String>>()

    var specABCList: ArrayList<String>? = null
    var specABCLists: ArrayList<String>? = null
    var specABCListss: ArrayList<String>? = null

    var selectList: ArrayList<ArrayList<Int>>? = null
    var adapterList: ArrayList<SpecsGroupAdapter>? = null
    var pic_url = ""
    var repertory_counts = 0

    //选择参数（用于加入购物车）
    var mdseIdFlag = 0L
    var quantityFlag = 1
    var shopIdFlag = 0L
    var stockIdFlag = 0L
    @SuppressLint("SetTextI18n")
    fun bottomPop(mdseInfo: MdseInfo) {
        mdseIdFlag = mdseInfo.mdseId
        if (mdseInfo.classify == 1){
            shopIdFlag = mdseInfo.shopInfo.shopId
        }
        selectList = ArrayList<ArrayList<Int>>()
        adapterList = ArrayList<SpecsGroupAdapter>()
        bottomPopup = CommenPop.getNormalPopu(this, R.layout.pop_commodity_details_gwc, ll_commodity_details_top)

        val fl_rlv = bottomPopup!!.contentView.findViewById<RecyclerView>(R.id.pop_com_det_fl_rlv)
        val price_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_del_price)
        val originalprice = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_del_originalprice)
        val discount = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_del_discount)
        val number_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_del_num)
        val title_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_del_title)
        val del_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_det_del)
        val num_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_det_num)
        val add_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.tv_pop_com_det_add)
        val determine_tv = bottomPopup!!.contentView.findViewById<TextView>(R.id.bt_pop_com_det_determine)
        val close_iv = bottomPopup!!.contentView.findViewById<ImageView>(R.id.iv_pop_com_del_close)
        val img_iv = bottomPopup!!.contentView.findViewById<ImageView>(R.id.iv_pop_com_del_img)

        CommenPop.backgroundAlpha(0.5f, this)
        bottomPopup!!.showAtLocation(ll_commodity_details_top, Gravity.BOTTOM, 0, 0)


        //选择规格逻辑
        //规格组合的第一个 为选择
        if (mdseInfo.stockList.size > 0 && mdseInfo.stockList != null) {
            changeSpecsGroupList = ArrayList<String>()
            val stockList = mdseInfo.stockList
            specABCList = ArrayList<String>()
            if (stockList[0].specA != null) {
                specABCList!!.add(stockList[0].specA.value)
            }
            if (stockList[0].specB != null) {
                specABCList!!.add(stockList[0].specB.value)
            }
            if (stockList[0].specC != null) {
                specABCList!!.add(stockList[0].specC.value)
            }
            selectSpecsGroupList = specABCList!!

            changeSpecsGroupList!!.addAll(selectSpecsGroupList)

            stockIdFlag = stockList[0].stockId
            price_tv.text = stockList[0].price.toString()
            if (stockList[0].originalPrice!=null) originalprice.visibility = View.VISIBLE else originalprice.visibility = View.GONE
            originalprice.text = "原价：￥"+stockList[0].originalPrice
            originalprice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            if (!stockList[0].discount.equals("")) discount.visibility = View.VISIBLE else discount.visibility = View.GONE
            discount.text = stockList[0].discount
            number_tv.text = "剩余" + stockList[0].remainingStock + "件"
            if (!stockList[0].pictureList[0].url.equals("")){
                Glide.with(this@CommodityDetailsActivity).load(stockList[0].pictureList[0].url).into(img_iv)
            }else{
                Glide.with(this@CommodityDetailsActivity).load(mdseInfo.pictureList[0].url).into(img_iv)
            }
            title_tv.text = "已选：" + specABCList.toString()
        }
        //后台所给的所有规格组合的集合
        for (specBean in mdseInfo.stockList) {
            specABCLists = ArrayList<String>()
            if (specBean.specA != null) {
                specABCLists!!.add(specBean.specA.value)
            }
            if (specBean.specB != null) {
                specABCLists!!.add(specBean.specB.value)
            }
            if (specBean.specC != null) {
                specABCLists!!.add(specBean.specC.value)
            }
            allSpecsGroupList!!.add(specABCLists!!)
        }

        for (i in 0 until mdseInfo.specMap.size) {
            var list = ArrayList<Int>()
            val specKeyString = mdseInfo.specMap[i].values
            for (j in 0 until specKeyString.size) {
                if (specKeyString[j].equals(selectSpecsGroupList[i])) {
                    list.add(j, 1)
                } else {
                    list.add(j, 0)
                }
            }
            selectList!!.add(i, list)
        }

        fl_rlv.layoutManager = LinearLayoutManager(this)
        fl_rlv.adapter = object : BaseQuickAdapter<SpecMap, BaseViewHolder>(R.layout.item_com_det_gwc_fl_item, mdseInfo.specMap) {
            override fun convert(helper: BaseViewHolder?, item: SpecMap?) {
                helper!!.setText(R.id.tv_item_com_det_gwc_fl_title, item!!.key)
                val fl_item_rlv = helper.getView<RecyclerView>(R.id.rlv_item_com_det_gwc_fl)

                val flexboxLayoutManager = FlexboxLayoutManager(this@CommodityDetailsActivity)
                flexboxLayoutManager.flexWrap = FlexWrap.WRAP
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                fl_item_rlv.layoutManager = flexboxLayoutManager

                //各规格属性联动
                getSetting(helper.adapterPosition, mdseInfo.specMap[helper.adapterPosition])
                val specsGroupAdapter = SpecsGroupAdapter(this@CommodityDetailsActivity,
                        mdseInfo.specMap[helper.adapterPosition].values, selectList!![helper.adapterPosition])
                adapterList!!.add(specsGroupAdapter)
                fl_item_rlv.adapter = specsGroupAdapter

                val finalI = helper.adapterPosition
                specsGroupAdapter.setOnItemClick(object : SpecsGroupAdapter.OnItemClick {
                    override fun onClick(position: Int) {
                        var clickFormat = finalI
                        val clickSpecKeyBean = mdseInfo.specMap[clickFormat]
                        //步骤1：先处理点击事件
                        if (selectList!![clickFormat][position] != 2) {
                            //遍历一下
                            for (i in 0 until selectList!![clickFormat].size) {
                                selectList!![clickFormat][i] = 0
                                if (i == position) {
                                    selectList!![clickFormat][i] = 1
                                }
                                /*when(selectList[clickFormat][i]){
                                    0->{
                                        if (i == position) {
                                            selectList[clickFormat][i] = 1
                                        }
                                    }
                                    1->{
                                        selectList[clickFormat][i] = 0
                                    }
                                    2->{

                                    }
                                }*/
                            }
                        }
                        //点击之后的  各规格的选择状态
                        var stringsList = ArrayList<String>()
                        stringsList = clickSpecKeyBean.values as ArrayList<String>
                        if (selectList!![clickFormat].contains(1)) {
                            for (g in 0 until selectList!![clickFormat].size) {
                                if (selectList!![clickFormat][g] == 1) {
                                    changeSpecsGroupList!![clickFormat] = stringsList[g]
                                }
                            }
                        } else {
                            changeSpecsGroupList!![clickFormat] = "未选"
                        }
                        //根据选择的组合 设置价格，库存，图片
                        if (!changeSpecsGroupList!!.contains("未选")) {
                            var sb = StringBuilder()
                            for (s in changeSpecsGroupList!!) {
                                sb.append(s).append(",")
                            }
                            var newString = sb.toString()

                            for (specBean in mdseInfo.stockList) {
                                specABCListss = ArrayList<String>()
                                var sbSpec = StringBuilder()
                                if (specBean.specA != null) {
                                    specABCListss!!.add(specBean.specA.value)
                                }
                                if (specBean.specB != null) {
                                    specABCListss!!.add(specBean.specB.value)
                                }
                                if (specBean.specC != null) {
                                    specABCListss!!.add(specBean.specC.value)
                                }
                                for (j in specABCListss!!) {
                                    sbSpec.append(j).append(",")
                                }
                                //这样就可以把集合转化为字符串了
                                val specString = sbSpec.toString()
                                if (newString.contains(specString)) {
                                    pic_url = specBean.pictureList.get(0).url
                                    if (!pic_url.equals("")){
                                        Glide.with(this@CommodityDetailsActivity).load(pic_url).into(img_iv)
                                    }else{
                                        Glide.with(this@CommodityDetailsActivity).load(mdseInfo.pictureList[0].url).into(img_iv)
                                    }

                                    price_tv.text = specBean.price.toString()
                                    if (specBean.originalPrice!=null) originalprice.visibility = View.VISIBLE else originalprice.visibility = View.GONE
                                    originalprice.text = "原价：￥"+specBean.originalPrice
                                    originalprice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                                    if (!specBean.discount.equals("")) discount.visibility = View.VISIBLE else discount.visibility = View.GONE
                                    discount.text = specBean.discount
                                    number_tv.text = "剩余" + specBean.remainingStock + "件"
                                    title_tv.text = "已选：" + specABCListss.toString()
                                    repertory_counts = specBean.remainingStock
                                    stockIdFlag = specBean.stockId
                                    specBean.price
//                                    iv_minus_counts.setImageResource(R.mipmap.icon_minus_light);
                                    /*if (repertory_counts == 1) {
                                        add_tv.isEnabled = false
                                    } else {
                                        add_tv.isEnabled = true
                                    }*/
                                }
                            }

                        }

                        for (i in 0 until mdseInfo.specMap.size) {
                            val specKeyBean = mdseInfo.specMap[i]
                            //各规格属性联动
                            getSetting(i, specKeyBean)
                            adapterList!![i].notifyDataSetChanged()
                        }

                    }
                })

            }
        }


        /**/

        close_iv.setOnClickListener {
            bottomPopup!!.dismiss()
        }


        var num = 1
        num_tv.text = num.toString()

        del_tv.setOnClickListener {
            num -= 1
            quantityFlag = num
            del_tv.isEnabled = num != 0
            num_tv.text = num.toString()
        }
        add_tv.setOnClickListener {
            del_tv.isEnabled = true
            num += 1
            quantityFlag = num
            num_tv.text = num.toString()
        }



        determine_tv.setOnClickListener {
            if (buyType == 0) {
                if (stockIdFlag==545L){
                    ToastUtils.showShort("该规格商品不可添加购物车")
                    return@setOnClickListener
                }
                mPresenter.addShoppingCart(mdseIdFlag, quantityFlag, shopIdFlag, stockIdFlag, 0)
            } else {
                if (stockIdFlag==545L){
                    if (quantityFlag<2){
                        ToastUtils.showShort("团购起售数量不可小于2")
                        return@setOnClickListener
                    }
                }
                var intent = Intent(this,DdqrActivity::class.java)
                mdseInfo.quantity = quantityFlag
                mdseInfo.stockList[0].stockId = stockIdFlag
                intent.putExtra("jumpDdqrActivity",1)
                intent.putExtra("mdseInfo",mdseInfo)
                startActivity(intent)
            }
        }
    }

    private fun getSetting(position: Int, specKeyBean: SpecMap) {
        var list = ArrayList<List<String>>()
        list.addAll(allSpecsGroupList!!)
        var remove_list = ArrayList<List<String>>()
        //遍历数据中 所有规则属性的组合
        for (goods_i in 0 until list.size) {
            val goodsList = list[goods_i]
            //遍历选中组合
            for (select_i in 0 until changeSpecsGroupList!!.size) {
                //去掉一个规格
                if (select_i == position) {

                } else {
                    if (!changeSpecsGroupList!![select_i].equals(goodsList[select_i])) {
                        if (!changeSpecsGroupList!![select_i].equals("未选")) {
                            remove_list.add(list[goods_i])
                        }
                    }
                }
            }
        }
        Log.e("young", "remove_list===" + remove_list.toString())
        //除掉不含有已选规格属性的属性组合的数组  得到在包含此规格外 其他规格已选属性的组合的数组
        list.removeAll(remove_list);
        Log.e("young", "list===" + list.toString())
        var aloneString = ArrayList<String>()
        for (j in 0 until list.size) {
            val specialList = list[j]
            aloneString.add(specialList[position])
        }
        Log.e("young", "aloneString===" + aloneString.toString())
        //通过这些字段组合 去对比此规格的所有样式  然后设置 是否有此规格组合
        for (m in 0 until specKeyBean.values.size) {
            if (!aloneString.contains(specKeyBean.values[m])) {
                selectList!![position][m] = 2
            } else {
                when (selectList!![position][m]) {
                    0 -> {
                        selectList!![position][m] = 0
                    }
                    1 -> {
                        selectList!![position][m] = 1
                    }
                    2 -> {
                        selectList!![position][m] = 2
                    }
                }

            }
        }
    }
    //会员信息
    override fun returnMembersUserInfo(msg: MemberInfo) {
        if (msg!=null){
            if (mdseInfoFlag!=null){
                var intent = Intent(this,DdqrActivity::class.java)
                mdseInfoFlag!!.quantity = quantityFlag
//                mdseInfoFlag!!.stockList[0].stockId = stockIdFlag
                intent.putExtra("jumpDdqrActivity",1)
                intent.putExtra("mdseInfo",mdseInfoFlag)
                startActivity(intent)
            }else{
                ToastUtils.showShort("商品信息为空")
            }
        }

    }

    override fun returnMdseInfo(user: List<MdseInfo>) {
        if (user.isNotEmpty()) {

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
            rlv_act_com_det_cnxh.layoutManager = GridLayoutManager(this, 3)
            rlv_act_com_det_cnxh.adapter = object : BaseQuickAdapter<MdseInfo, BaseViewHolder>(R.layout.item_search_cnxh, cnxhAll) {
                override fun convert(helper: BaseViewHolder?, item: MdseInfo?) {
                    helper!!.setText(R.id.tv_item_serach_cnxh_money, "￥${item!!.price}")
                            .setText(R.id.tv_item_serach_cnxh_gqmoney, "￥${helper.adapterPosition + 1}")
                            .setText(R.id.tv_item_serach_cnxh_title, item.name)
                    val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
                    val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
                    tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                    val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                            .transforms(RoundedCorners(30))//图片圆角为30
                    Glide.with(this@CommodityDetailsActivity).load(if (item!!.pictureList != null && item.pictureList.size > 0) item.pictureList.get(0).url else "") //图片地址
                            .apply(options)
                            .into(iv_item_serach_cnxh_img)

                    helper.itemView.setOnClickListener {
                        val intent = Intent(this@CommodityDetailsActivity, CommodityDetailsActivity::class.java)
                        intent.putExtra("details", item)
                        startActivity(intent)
                    }

                }
            }


        }

    }

    override fun returnAddShoppingCart(msg: String) {
        ToastUtils.showShort("添加成功")
        EventBus.getDefault().postSticky("刷新购物车")
        if (bottomPopup!=null){
            bottomPopup!!.dismiss()
        }
        /*val intent = Intent(this, FirstActivity::class.java)
        intent.putExtra("jump", 1)
        startActivity(intent)*/
    }


    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (msg.equals("用户不存在")){
            var intent = Intent(this,FillMemberInfoActivity::class.java)
            startActivity(intent)

        }
    }


    class NetViewHolder : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            if (!(context as Activity).isDestroyed && path != null && imageView != null) {
                Glide.with(context).load(path).into(imageView!!)
            }
        }
    }
}
