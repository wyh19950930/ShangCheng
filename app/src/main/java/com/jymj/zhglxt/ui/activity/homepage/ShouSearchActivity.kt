package com.jymj.zhglxt.ui.activity.homepage

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.basemessagesystem.ui.adapter.CnxhAdapter
import com.jymj.basemessagesystem.ui.adapter.SplbAdapter
import com.jymj.zhglxt.R
import com.jymj.zhglxt.bean.JsjbBean
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.ShouSearchContract
import com.jymj.zhglxt.login.model.ShowSearchModel
import com.jymj.zhglxt.login.presenter.ShouSearchPresenter
import com.jymj.zhglxt.ui.adapter.TagBaseAdapter
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.widget.zdybj.TagCloudLayout
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.commonutils.SPUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import kotlinx.android.synthetic.main.activity_shou_search.*

class ShouSearchActivity : BaseActivity<ShouSearchPresenter, ShowSearchModel>(), ShouSearchContract.View {


    var lsjlList = ArrayList<JsjbBean>()
    var tjssList = ArrayList<JsjbBean>()
    var cplbList = ArrayList<MdseInfo>()//
    var cnxhList = ArrayList<MdseInfo>()//猜你喜欢
    var sjqjapterxl: TagBaseAdapter? = null
    var tjssapterxl: TagBaseAdapter? = null
    var tabPosition = 0
    var isJgSheng = true//true
    var size = 20
    var page = 1
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayLsjlList = ArrayList<String>()
    var name =""
    var properties =""
    var lat = 0.0
    var lon = 0.0
    var direction = 0



    override fun getLayoutId(): Int {
        return R.layout.activity_shou_search
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        val sharedLsjlList = SPUtils.getSharedStringData(this, "lsjlList")
        if (!sharedLsjlList.equals("")){
            val gson = Gson()
            val list:List<String> = gson.fromJson(sharedLsjlList, object : TypeToken<List<String>>() {}.type)
            arrayLsjlList.addAll(list)
        }

        iv_shou_search_back.setOnClickListener {
            finish()
        }
        for (i in 0..arrayLsjlList.size-1){
            lsjlList.add(JsjbBean(i+1,arrayLsjlList.get(i),false))
        }
        /*lsjlList.add(JsjbBean(1,"亲子研学",false))
        lsjlList.add(JsjbBean(2,"卡产品",false))
        lsjlList.add(JsjbBean(3,"运动健康",false))
        lsjlList.add(JsjbBean(4,"乡食",false))
        lsjlList.add(JsjbBean(5,"商务拓展",false))*/
        sjqjapterxl = TagBaseAdapter(mContext, lsjlList) //createData(mListxl, xl)

        tcl_act_shou_search_lsjl.setAdapter(sjqjapterxl)
        tcl_act_shou_search_lsjl.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
            override fun itemClick(position1: Int) {
                name = lsjlList.get(position1).text
                mactv_act_shou_search_spss.setText(name)
                tlb_act_shou_serach.getTabAt(0)?.select()
            }
        })
        iv_act_shou_search_delete.setOnClickListener {
            lsjlList.clear()
            arrayLsjlList.clear()
            sjqjapterxl!!.setmList(lsjlList,tcl_act_shou_search_lsjl)
            SPUtils.setSharedStringData(this,"lsjlList",Gson().toJson(arrayLsjlList))

        }
        tv_act_shou_search_ss.setOnClickListener {
            name = mactv_act_shou_search_spss.text.toString()
            if (!name.equals("")){
                if (!arrayLsjlList.contains(name)){
                    if (lsjlList.size==10){
                        lsjlList.removeAt(lsjlList.size-1)
                        arrayLsjlList.removeAt(arrayLsjlList.size-1)
                    }
                    lsjlList.add(0,JsjbBean(1,name,false))
                    sjqjapterxl!!.setmList(lsjlList,tcl_act_shou_search_lsjl)
                    arrayLsjlList.add(0,name)
                    SPUtils.setSharedStringData(this,"lsjlList",Gson().toJson(arrayLsjlList))
                }
                properties = ""
                lat = 0.0
                lon = 0.0
                page = 1
                direction = 0
                tlb_act_shou_serach.getTabAt(0)?.select()
            }else{
                ToastUtils.showShort("请输入搜索内容")
            }
//            mPresenter.getMdseInfo(name, properties, lat, lon, page, size, direction)
        }
        tv_act_shou_search_delete.setOnClickListener {
            mactv_act_shou_search_spss.setText("")
        }

        mactv_act_shou_search_spss.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val toString = s.toString()
                /*if (!toString.equals("")){//&&!arrayList.contains(toString)
//                    ToastUtils.showShort("搜索")
                    tv_act_shou_search_delete.visibility = View.VISIBLE
                }*/
                if (!toString.equals("")){
                    tv_act_shou_search_delete.visibility = View.VISIBLE
                }else if (toString.equals("")){
                    ll_act_shou_search_lsjl.visibility = View.VISIBLE
                    ll_act_shou_search_cplb.visibility = View.GONE
                    ll_act_shou_search_zwcxjg.visibility = View.GONE
                    ll_act_shou_search_wlcxwt.visibility = View.GONE
                    tv_act_shou_search_delete.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        /*mactv_act_shou_search_spss.setAdapter(adapter)
        mactv_act_shou_search_spss.setOnItemClickListener { parent, view, position, id ->
            mactv_act_shou_search_spss.setText(arrayList.get(position))

        }
        //设置分隔符
        mactv_act_shou_search_spss.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer());
        // 显示下拉框
        mactv_act_shou_search_spss.showDropDown();*/
        //隐藏价格右侧的图标    http://t.zoukankan.com/lanzhi-p-6467148.html
        tlb_act_shou_serach.getTabAt(2)!!.customView!!.findViewById<ImageView>(android.R.id.icon).visibility = View.GONE
        tlb_act_shou_serach.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Log.e("tlb_act_shou_serach:","onTabReselected"+ tab!!.text.toString())
                val text = tab!!.text.toString()
                if (tab!!.customView!=null){
                    if (text.equals("价格")){
                        val icon = tab!!.customView!!.findViewById<ImageView>(android.R.id.icon)
                        if (direction == 1){
                            direction = 2
//                            ToastUtils.showShort("降")
                            icon.setImageDrawable(getDrawable(R.drawable.icon_back_down))
                        }else{
                            direction = 1
//                            ToastUtils.showShort("升")
                            icon.setImageDrawable(getDrawable(R.drawable.icon_back_up))
                        }
                        lat = 0.0
                        lon = 0.0
                        page = 1
                        properties = "price"
                    }else{
                        ToastUtils.showShort(text)
                    }
                }
                mPresenter.getMdseInfo(name, properties, lat, lon, page, size, direction)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Log.e("tlb_act_shou_serach","onTabUnselected:"+ tab!!.text.toString())
                if (tab!!.customView!=null){
                    val icon = tab!!.customView!!.findViewById<ImageView>(android.R.id.icon)
                    val text1 = tab!!.customView!!.findViewById<TextView>(android.R.id.text1)
                    icon.visibility = View.GONE
                    text1.setTextColor(Color.parseColor("#424242"))
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Log.e("tlb_act_shou_serach","onTabSelected:"+ tab!!.text.toString())
                if (tab!!.customView!=null){
                    val text1 = tab!!.customView!!.findViewById<TextView>(android.R.id.text1)
                    val icon = tab!!.customView!!.findViewById<ImageView>(android.R.id.icon)
                    val toString = text1.text.toString()
                    if (toString.equals("价格")){
                        direction = 1
//                        ToastUtils.showShort("升")
                        icon.visibility = View.VISIBLE
                        icon.setImageDrawable(getDrawable(R.drawable.icon_back_up))
                        text1.setTextColor(Color.parseColor("#CE4A41"))
                    }else{
                        ToastUtils.showShort(toString)
                    }

                }
                lat = 0.0
                lon = 0.0
                page = 1
                if (tab.text.toString().equals("综合")){
                    properties = ""
                }else if (tab.text.toString().equals("销量")){
                    properties = "salesVolume"
                }else if (tab.text.toString().equals("价格")){
                    properties = "price"
                }else if (tab.text.toString().equals("距离")){
                    properties = "location"
                    lat = 0.0
                    lon = 0.0
                }else if (tab.text.toString().equals("评分")){
                    properties = ""//
                }
                mPresenter.getMdseInfo(name, properties, lat, lon, page, size, direction)

            }
        })


    }

    override fun initDatas() {
//        mPresenter.getMdseInfo(name,properties,lat,lon,page,size,direction)
        mPresenter.getMdseInfo1(100)
    }


    override fun returnMdseInfo(user: List<MdseInfo>) {

        if (page == 1){
            cplbList.clear()
        }
        cplbList.addAll(user)
        var firstVisibleItem = 0
        if (linearLayoutManager != null && page != 1) {
            firstVisibleItem = linearLayoutManager!!.findFirstVisibleItemPosition()
        }
        linearLayoutManager = LinearLayoutManager(this)
        rlv_act_shou_search_cplb.layoutManager = linearLayoutManager

        val cplbAdapter = SplbAdapter(R.layout.item_shou_search_cplb, cplbList,this,true)
        rlv_act_shou_search_cplb.adapter = cplbAdapter
        rlv_act_shou_search_cplb.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                val lastCompletelyVisibleItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1&&cplbList.size==page*size) {
                    if (cplbList.size !=0 && cplbList.size%size == 0){
                        page++
                        mPresenter.getMdseInfo(name, properties, lat, lon, page, size, direction)
                    }else{
                        ToastUtils.showShort("滑动到底部了")
                    }
                }
            }
        })
        if (page!=1){
            rlv_act_shou_search_cplb.scrollToPosition(firstVisibleItem+1)//(page-1)*limit+
        }

        if (cplbList.size>0){
            ll_act_shou_search_lsjl.visibility = View.GONE
            ll_act_shou_search_cplb.visibility = View.VISIBLE
            ll_act_shou_search_zwcxjg.visibility = View.GONE
            ll_act_shou_search_wlcxwt.visibility = View.GONE
        }else{
            ll_act_shou_search_lsjl.visibility = View.GONE
            ll_act_shou_search_cplb.visibility = View.GONE
            ll_act_shou_search_zwcxjg.visibility = View.VISIBLE
            ll_act_shou_search_wlcxwt.visibility = View.GONE
        }

    }

    override fun returnMdseInfo1(user: List<MdseInfo>) {

        if (user.isNotEmpty()){

            for (i in 0 until user.size){
                val get = user.get(i)
                if (user.get(i).typeInfo!=null&&user.get(i).typeInfo.typeId==5L){//热门推荐
                    tjssList.add(JsjbBean(get.mallId.toInt(),get.name,false))
                }
                if (user.get(i).typeInfo!=null&&user.get(i).typeInfo.typeId==6L){//热门推荐（猜你喜欢）
                    cnxhList.add(user.get(i))
                }
            }

            tjssapterxl = TagBaseAdapter(mContext, tjssList) //createData(mListxl, xl)

            tcl_act_shou_search_tjss.setAdapter(tjssapterxl)
            tcl_act_shou_search_tjss.setItemClickListener(object : TagCloudLayout.TagItemClickListener {
                override fun itemClick(position1: Int) {
                    name = tjssList.get(position1).text
                    mactv_act_shou_search_spss.setText(name)
                    properties = ""
                    lat = 0.0
                    lon = 0.0
                    page = 1
                    direction = 0

//                    mPresenter.getMdseInfo(name,properties,lat,lon,page,size,direction)
                    tlb_act_shou_serach.getTabAt(0)?.select()
                    if (!arrayLsjlList.contains(tjssList.get(position1).text)){
                        if (lsjlList.size==10){
                            lsjlList.removeAt(lsjlList.size-1)
                            arrayLsjlList.removeAt(arrayLsjlList.size-1)
                        }
                        lsjlList.add(0,JsjbBean(1,tjssList.get(position1).text,false))
                        sjqjapterxl!!.setmList(lsjlList,tcl_act_shou_search_lsjl)
                        arrayLsjlList.add(0,tjssList.get(position1).text)
                        SPUtils.setSharedStringData(this@ShouSearchActivity,"lsjlList",Gson().toJson(arrayLsjlList))
                    }

                }
            })
            /**
             *  猜你喜欢
             */
            var cnxhAll = ArrayList<MdseInfo>()
            if (cnxhList.size>3){
                for (i in 0..2 ){
                    cnxhAll.add(cnxhList.get(i))
                }
            }else{
                cnxhAll.addAll(cnxhList)
            }
            rlv_act_shou_serach_cnxh.layoutManager = GridLayoutManager(this,3)
            val splbAdapter = CnxhAdapter(R.layout.item_search_cnxh, cnxhAll,this)
            rlv_act_shou_serach_cnxh.adapter = splbAdapter

        }

    }

    /*
    * val arrayList = ArrayList<String>()
        if (arrayList.size==0){
            ll_act_shou_search_lsjl.visibility = View.GONE
            ll_act_shou_search_cplb.visibility = View.GONE
            ll_act_shou_search_zwcxjg.visibility = View.VISIBLE
            ll_act_shou_search_wlcxwt.visibility = View.GONE
        }else{
            ll_act_shou_search_lsjl.visibility = View.GONE
            ll_act_shou_search_cplb.visibility = View.VISIBLE
            ll_act_shou_search_zwcxjg.visibility = View.GONE
            ll_act_shou_search_wlcxwt.visibility = View.GONE
        }*/

    override fun showLoading(title: String?) {
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {
        if (msg!!.contains("网络连接失败,请连接网络")){
            ll_act_shou_search_lsjl.visibility = View.GONE
            ll_act_shou_search_cplb.visibility = View.GONE
            ll_act_shou_search_zwcxjg.visibility = View.GONE
            ll_act_shou_search_wlcxwt.visibility = View.VISIBLE
        }
        ToastUtils.showShort(msg)
    }


}
