package com.jymj.zhglxt.ui.activity.me.order

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jymj.zhglxt.R
import com.jymj.zhglxt.ui.bean.me.MallOrderMdseDetailsInfo
import com.jymj.zhglxt.util.QRcode
import kotlinx.android.synthetic.main.activity_write_off.*

class WriteOffActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_off)
        iv_act_write_off_back.setOnClickListener {
            finish()
        }

        val orderNo = intent.getStringExtra("orderNo")
        val orderKaName = intent.getStringExtra("orderKaName")
        val orderInfo = intent.getSerializableExtra("orderInfo") as MallOrderMdseDetailsInfo
        if (orderInfo!=null){
            val qRcode = QRcode()
            val bitmap = qRcode.qrcode(orderNo + "_" + orderInfo.mdseId.toString())
            iv_act_write_off_ewm.setImageBitmap(bitmap)
            tv_act_write_off_hxm.text = "核销码："+orderNo + "_" + orderInfo.mdseId.toString()
            Glide.with(this).load(orderInfo.mdsePicture).into(iv_act_write_off_img)
            tv_act_write_off_name.text = orderInfo.mdseName
            tv_act_write_off_num.text = "× "+orderInfo.quantity.toString()
            tv_act_write_off_ka_name.text = orderKaName
        }

    }
}
