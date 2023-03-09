package com.jymj.zhglxt.ui.fragment.homepage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Outline
import android.graphics.Paint
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.basemessagesystem.ui.adapter.SplbAdapter

import com.jymj.zhglxt.R
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.contract.HomePageContract
import com.jymj.zhglxt.login.model.HomePageModel
import com.jymj.zhglxt.login.presenter.HomePagePresenter
import com.jymj.zhglxt.ui.activity.shopping.CommodityDetailsActivity
import com.jymj.zhglxt.ui.activity.homepage.ShouSearchActivity
import com.jymj.zhglxt.ui.activity.homepage.SpflActivity
import com.jymj.zhglxt.ui.activity.me.WlxqActivity
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.setsuna.common.base.BaseFragment
import com.setsuna.common.commonutils.*
import com.setsuna.common.commonwidget.LoadingDialog
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.include_homepage_default.*
import me.iwf.photopicker.PhotoPreview

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class HomePageFragment : BaseFragment<HomePagePresenter, HomePageModel>(), HomePageContract.View {

    override fun lazyLoad() {
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home_page
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {//SelectZhActivity
        ll_homepage_default_search.setOnClickListener {
            val intent = Intent(activity, ShouSearchActivity::class.java)
            startActivity(intent)
        }
        tv_homepage_default_qb.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
//            val intent = Intent(activity, WlxqActivity::class.java)
            intent.putExtra("classify","乡居")
            startActivity(intent)
        }
        tv_homepage_default_xs.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","乡食")
            startActivity(intent)
        }
        tv_homepage_default_xj.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","乡居")
            startActivity(intent)
        }
        tv_homepage_default_xy.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","乡游")
            startActivity(intent)
        }
        tv_homepage_default_xh.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","乡货")
            startActivity(intent)
        }
        tv_homepage_default_nyty.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","农业体验")
            startActivity(intent)
        }
        tv_homepage_default_qzyx.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","亲子研学")
            startActivity(intent)
        }
        tv_homepage_default_ydjk.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","运动健康")
            startActivity(intent)
        }
        tv_homepage_default_swtz.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","商务拓展")
            startActivity(intent)
        }
        tv_homepage_default_kcp.setOnClickListener {
            val intent = Intent(activity, SpflActivity::class.java)
            intent.putExtra("classify","卡产品")
            startActivity(intent)
        }

        //轮播图
        val imgList = ArrayList<String>()
        imgList.add("https://jymj-mall.oss-cn-beijing.aliyuncs.com/swiper1.png")
//        imgList.add("https://img1.baidu.com/it/u=38231409,2215725747&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
        val imgTitleList = ArrayList<String>()
        imgTitleList.add("")
//        imgTitleList.add("森林")
        /*for (i in bcProjectEntity.projectFiles){
            imgList.add(i.url)
            imgTitleList.add(i.sorting.toString())
        }*/
        banner_homepage_default.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setDelayTime(6000)
                .setImageLoader(NetViewHolder())
                .setBannerTitles(imgTitleList)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImages(imgList).setOnBannerListener(object: OnBannerListener {
                    override fun OnBannerClick(position: Int) {
                        PhotoPreview.builder()
                                .setPhotos(imgList)
                                .setCurrentItem(position)
                                .setShowDeleteButton(false)
                                .start(activity!!)
//                            ToastUtils.showShort("点击第"+position+"张")
                    }
                })
                .outlineProvider = object : ViewOutlineProvider(){
            override fun getOutline(view: View?, outline: Outline?) {
                outline!!.setRoundRect(0,0,view!!.width,view.height,10f)
            }
        }
        banner_homepage_default.clipToOutline = true
        banner_homepage_default.start()




    }

    override fun initDatas() {
    }
    class NetViewHolder : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            if (!(context as Activity).isDestroyed&&path!=null&&imageView!=null){
                Glide.with(context).load(path).into(imageView)
            }
        }
    }

    //首页商品信息
    var rmtjList = ArrayList<MdseInfo>()  //热门推荐
    val pslbList = ArrayList<MdseInfo>() //为你优选
    val kcpList = ArrayList<MdseInfo>() //卡产品
    override fun returnMdseInfo(user: List<MdseInfo>) {
        rmtjList.clear()
        pslbList.clear()
        kcpList.clear()
            /*rmtjList.add("童话森林门票")
            rmtjList.add("自产柴鸡蛋5斤")
            rmtjList.add("黄山店猕猴桃")*/

            for (i in 0 until user.size){
                if (user.get(i).classify == 1){//1 商品 2 卡产品
                    if (user.get(i).typeInfo!=null&&user.get(i).typeInfo.typeId==5L){//热门推荐
                        rmtjList.add(user.get(i))
                    }
                    if (user.get(i).typeInfo!=null&&(user.get(i).typeInfo.typeId==6L||user.get(i).typeInfo.typeId==3L)){//为您优选
                        pslbList.add(user.get(i))
                    }
                }else if (user.get(i).classify == 2){//1 商品 2 卡产品
                    kcpList.add(user.get(i))
                }
            }

            val rmtjAll = ArrayList<MdseInfo>()
            if (rmtjList.size>3){
                for (i in 0..2 ){
                    rmtjAll.add(rmtjList.get(i))
                }
            }else{
                rmtjAll.addAll(rmtjList)
            }

            rlv_homepage_default_rmtj.layoutManager = GridLayoutManager(activity!!,3)
            rlv_homepage_default_rmtj.adapter = object: BaseQuickAdapter<MdseInfo, BaseViewHolder>(R.layout.item_search_cnxh,rmtjAll){
                override fun convert(helper: BaseViewHolder?, item: MdseInfo?) {
                    helper!!.setText(R.id.tv_item_serach_cnxh_money,item!!.price.toString())
                            .setText(R.id.tv_item_serach_cnxh_gqmoney,"￥${helper.adapterPosition+1}")
                            .setText(R.id.tv_item_serach_cnxh_title,item.name)
                    val iv_item_serach_cnxh_img = helper.getView<ImageView>(R.id.iv_item_serach_cnxh_img)
                    val tv_item_serach_cnxh_gqmoney = helper.getView<TextView>(R.id.tv_item_serach_cnxh_gqmoney)
                    tv_item_serach_cnxh_gqmoney.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
                    val options = RequestOptions().error(R.drawable.icon_cnxh_ys)
                            .transforms(RoundedCorners(30))//图片圆角为30
                    Glide.with(activity!!).load(if (item.pictureList!=null && item.pictureList.size>0) item.pictureList.get(0).url else "") //图片地址
                            .apply(options)
                            .into(iv_item_serach_cnxh_img)

                    helper.itemView.setOnClickListener {
                        val intent = Intent(activity!!, CommodityDetailsActivity::class.java)
                        intent.putExtra("details",item)
                        startActivity(intent)
                    }

                }
            }


            /*pslbList.add("坡峰岭红叶节")
            pslbList.add("童话森林水乐园")
            pslbList.add("坡峰岭红叶节")
            pslbList.add("姥姥家农家院")
            pslbList.add("坡峰岭红叶节")*/
            rlv_homepage_default_wnyx.layoutManager = LinearLayoutManager(activity!!)
            val cplbAdapter = SplbAdapter(R.layout.item_shou_search_cplb, pslbList,activity!!,false)
            rlv_homepage_default_wnyx.adapter = cplbAdapter

            //卡产品
            rlv_homepage_default_kcp.layoutManager = LinearLayoutManager(activity!!)
            val kcpAdapter = SplbAdapter(R.layout.item_shou_search_cplb, kcpList,activity!!,false)
            rlv_homepage_default_kcp.adapter = kcpAdapter


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

    override fun onResume() {
        super.onResume()
        mPresenter.getMdseInfo(100)
    }

}
