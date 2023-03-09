package com.jymj.zhglxt.ui.activity.me.order

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import com.journeyapps.barcodescanner.CaptureManager
import com.jymj.zhglxt.R
import kotlinx.android.synthetic.main.activity_write_off_qr_code.*

/**
 * 商品核销-》二维码页面
 */
class WriteOffQrCodeActivity : AppCompatActivity() {
    private var captureManager: CaptureManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_off_qr_code)
        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            switch_light.visibility = View.GONE
        }
        iv_act_write_orr_qr_code_back.setOnClickListener {
            finish()
        }
        captureManager = CaptureManager(this, dbv_custom);
        captureManager!!.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager!!.decode();
        switch_light.setOnClickListener {

            Log.d("CJT" , "switch_light  --  onClick");
            switch_light.switchState(true);
            if(switch_light.isIconEnabled){
                dbv_custom.setTorchOn() // 打开手电筒
            }else{
                dbv_custom.setTorchOff() // 关闭手电筒
            }
        }
        dbv_custom.setOnClickListener {
            Log.d("CJT" , "dbv_custom  --  onClick");
        }
    }
    override fun onPause() {
        super.onPause()
        captureManager?.onPause()
    }

    override fun onResume() {
        super.onResume()
        captureManager?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        captureManager?.onSaveInstanceState(outState)
    }
    // 判断是否有闪光灯功能
    private fun hasFlash():Boolean {
        return applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
    }
}
