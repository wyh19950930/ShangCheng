package com.jymj.zhglxt.ui.activity.me.tuikuan

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.login.contract.RetreatContract
import com.jymj.zhglxt.login.model.RetreatModel
import com.jymj.zhglxt.ui.adapter.PhotoAdapter
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import com.jymj.zhglxt.ui.presenter.me.RetreatPresenter
import com.jymj.zhglxt.util.FileUtilFjxc1
import com.jymj.zhglxt.util.IOHelper
import com.jymj.zhglxt.widget.RecyclerItemClickListener
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.ToastUtils
import kotlinx.android.synthetic.main.activity_after_sales.*
import kotlinx.android.synthetic.main.activity_retreat.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import java.io.*

/**
 * 申请退款 换货
 */
class RetreatActivity : BaseActivity<RetreatPresenter,RetreatModel>(),RetreatContract.View {
    private var selectedPhotos = ArrayList<String>()
    private var photoAdapter:PhotoAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_retreat
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }

    override fun initViews() {
        iv_act_retreat_back.setOnClickListener {
            finish()
        }

        val stringExtra = intent.getStringExtra("retreatType")
        val data = intent.getSerializableExtra("retreatData") as MallOrderInfo
        when(stringExtra){
            "退货退款"->{
                tv_act_retreat_title.text = "我要退货退款"
            }
            "退款"->{
                tv_act_retreat_title.text = "我要退款"
            }
            "换货"->{
                tv_act_retreat_title.text = "我要换货"
            }
        }
        if (data!=null){
            if (data.orderMdseDetailsInfoList.size > 1) {
                rlv_act_retreat_commodity_shuang.visibility = View.VISIBLE
                ll_act_retreat_commodity_dan.visibility = View.GONE

                rlv_act_after_sales_commodity.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rlv_act_after_sales_commodity.adapter = object : BaseQuickAdapter<MallOrderMdseDetailsInfo, BaseViewHolder>(R.layout.item_ddqr_img, data.orderMdseDetailsInfoList) {
                    override fun convert(helper: BaseViewHolder?, items: MallOrderMdseDetailsInfo?) {
                        val tvItemDdqrImgImg = helper!!.getView<ImageView>(R.id.tv_item_ddqr_img_img)
                        helper.setText(R.id.tv_item_ddqr_img_name, items!!.mdseName)
                        val options = RequestOptions().transforms(RoundedCorners(30))
                                .error(R.drawable.icon_cnxh_ys)//图片圆角为30
                        Glide.with(this@RetreatActivity).load(items.mdsePicture) //图片地址
                                .apply(options)
                                .into(tvItemDdqrImgImg)
                    }
                }
            } else {//单
                ll_act_retreat_commodity_dan.visibility = View.VISIBLE
                rlv_act_retreat_commodity_shuang.visibility = View.GONE
                Glide.with(this).load(data.orderMdseDetailsInfoList[0].mdsePicture).into(iv_act_retreat_commodity_image)
                tv_act_retreat_commodity_title.text = data.orderMdseDetailsInfoList[0].mdseName
            }
        }

    }

    override fun initDatas() {

        bt_act_retreat_sqtj.setOnClickListener {
            if (selectedPhotos.size>0){//上传完图片后调用发布接口
                var idList = ArrayList<Long>()
                for (i in selectedPhotos){
                    val file = File(i)
                    val name = file.name
                    val bitmap = IOHelper.loadBitmap(file.path,true)
                    val file1: File = compressImages(bitmap, name)
//                    upFile1(file1,idList,flowMassageEntity);
                }
            }else{
                //直接调用发布接口
//                mPresenter.getAddMessage(flowMassageEntity)
            }
        }


        rlv_act_retreat_pic.layoutManager = GridLayoutManager(this,3)
        photoAdapter = PhotoAdapter(this, selectedPhotos)
        rlv_act_retreat_pic.adapter = photoAdapter
        rlv_act_retreat_pic.addOnItemTouchListener(RecyclerItemClickListener(this, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                if (photoAdapter!!.getItemViewType(position) === PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
//                            .start(activity!!)
                            .start(this@RetreatActivity!!,234)//, this@RJHJFragment
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
//                            .start(activity!!)
                            .start(this@RetreatActivity,20)//context!!
                }
            }
        }))

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {//&&requestCode != 66
            if (requestCode==234){
                var photos: ArrayList<String>? = null
                selectedPhotos.clear()

                if (data != null) {
                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                }
                if (photos != null) {
                    /* for (i in photos){
                         val file = File(i)
                         val name = file.name
                         val bitmap = IOHelper.loadBitmap(file.path,true)
                         val file1: File = compressImages(bitmap, name)
                         upFile1(file1);
                     }*/
                    selectedPhotos.addAll(photos)
                }
                if (photoAdapter != null) photoAdapter!!.notifyDataSetChanged()
            }else if (requestCode==20){
                var photoLists = data!!.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                if (photoLists != null ) {//&& !photoLists.isEmpty()
                    selectedPhotos = photoLists
                    /*for (i in photoLists){
                        selectedPhotos.remove(i)
                    }*/
                    if (photoAdapter!=null){
                        photoAdapter!!.setNewData(selectedPhotos)
//                            photoAdapter!!.notifyDataSetChanged()
                    }
                }
//                    mAddPicture.setPaths(mImagePaths);
            }
        }
    }
    /**
     * 压缩图片（质量压缩）
     *
     * @param bitmap
     */
    fun compressImages(bitmap: Bitmap, fileName: String): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos) //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 1024) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset() //重置baos即清空baos
            options -= 10 //每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos) //这里压缩options%，把压缩后的数据存放到baos中
            val length = baos.toByteArray().size.toLong()
        }
//        val format = SimpleDateFormat("yyyyMMddHHmmss")
//        val date = Date(System.currentTimeMillis())
//        val filename = format.format(date)
        //        File file = new File(Environment.getExternalStorageDirectory(), fileName + ".png");
        val file1 = File(AppMenus.getInstance().cardPath + "jymj/shangcheng/pic/")
        if (!file1.isDirectory) {
            file1.mkdirs()
        }
        val file = File(AppMenus.getInstance().cardPath+"jymj/shangcheng/pic/1"+fileName)
//        val file = File(Environment.getExternalStorageDirectory(), fileName)
        try {
            val fos = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        recycleBitmap(bitmap)
        return file
    }

    //释放
    fun recycleBitmap(vararg bitmaps: Bitmap?) {
        if (bitmaps == null) {
            return
        }
        for (bm in bitmaps) {
            if (null != bm && !bm.isRecycled) {
                bm.recycle()
            }
        }
    }

    override fun returnMdseInfo(user: List<MdseInfo>) {
    }

    override fun returnAddShoppingCart(msg: String) {
    }

    override fun returnMembersUserInfo(msg: MemberInfo) {
    }

    override fun showLoading(title: String?) {
    }

    override fun stopLoading() {
    }

    override fun showErrorTip(msg: String?) {
    }

}
