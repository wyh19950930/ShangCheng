package com.jymj.zhglxt.login.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.view.animation.AccelerateInterpolator
import com.jymj.zhglxt.R
import com.jymj.zhglxt.util.GpsUtil
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.baseapp.AppManager
import com.setsuna.common.commonutils.AppUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<BasePresenter<*,*>, BaseModel>() {//BaseActivity<Ipresenter<*>, BaseModel>()

    companion object {
        var isForeground = false
        val MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION"
        val KEY_MESSAGE = "message"
        val KEY_EXTRAS = "extras"
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews()
    }*/
    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initPresenter() {

    }

    override fun initViews() {
        if (!isTaskRoot){
            finish()
            return
        }
        iv_logo.setImageDrawable(AppUtils.getAppIcon())//设置图标
        tv_name.text = AppUtils.getAppName()//  "黄村镇综合管理系统"
        val rxPermissions = RxPermissions(this)
        SetTranslanteBar()
        val alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f)
        val objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tv_name, alpha, scaleX, scaleY)
        val objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(iv_logo, alpha, scaleX, scaleY)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(objectAnimator1, objectAnimator2)
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.duration = 2000
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
//                XXPermissions.setScopedStorage(true);
                /* XXPermissions.with(this@SplashActivity)
                         // 申请安装包权限
                         //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                         // 申请悬浮窗权限
                         //.permission(Permission.SYSTEM_ALERT_WINDOW)
                         // 申请通知栏权限
                         //.permission(Permission.NOTIFICATION_SERVICE)
                         // 申请系统设置权限
                         //.permission(Permission.WRITE_SETTINGS)
                         // 申请单个权限
                         .permission(Permission.READ_PHONE_STATE)
                         .permission(Permission.WRITE_EXTERNAL_STORAGE)
                         .permission(Permission.READ_EXTERNAL_STORAGE)
 //                        .permission(Permission.ACCESS_WIFI_STATE)
                         .permission(Permission.ACCESS_COARSE_LOCATION)
                         .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                         .permission(Permission.CAMERA)
                         .permission(Permission.ACCESS_FINE_LOCATION)
                         .permission(Permission.RECORD_AUDIO)
                         // 申请多个权限
                         .permission(Permission.Group.CALENDAR)
                         .request(object : OnPermissionCallback {
                             override fun onGranted(permissions: MutableList<String>?, all: Boolean) {
                                 if (all) {
                                     if (GpsUtil.isOPen(this@SplashActivity)){

                                         LoginActivity.startAction(this@SplashActivity)
                                         AppManager.getAppManager().finishActivity(this@SplashActivity)
                                     }else{
                                         GpsUtil.openGPS(this@SplashActivity)
                                     }
 //                                    ToastUtils.showShort("获取录音和日历权限成功");
                                 } else {
                                     ToastUtils.showShort("获取部分权限成功，但部分权限未正常授予");
                                 }
                             }

                             override fun onDenied(permissions: MutableList<String>?, never: Boolean) {
                                 super.onDenied(permissions, never)
                                 if (never) {
                                     ToastUtils.showShort("被永久拒绝授权，请手动授予录音和日历权限");
                                     // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                     XXPermissions.startPermissionActivity(this@SplashActivity, permissions);
                                 } else {
                                     ToastUtils.showShort("获取录音和日历权限失败");
                                 }
                             }
                         });*/
                /**
                 * android.Manifest.permission.READ_PHONE_STATE,
                ,android.Manifest.permission.CAMERA
                 */
                rxPermissions.request(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ,android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ,android.Manifest.permission.ACCESS_WIFI_STATE
                        ,android.Manifest.permission.ACCESS_COARSE_LOCATION
                        ,android.Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe({ granted ->
                            if (granted) {
                                if (GpsUtil.isOPen(this@SplashActivity)){

                                    LoginActivity.startAction(this@SplashActivity)
                                    AppManager.getAppManager().finishActivity(this@SplashActivity)

                                }else{
                                    GpsUtil.openGPS(this@SplashActivity)
                                }
                            } else {
                                AppManager.getAppManager().finishActivity(this@SplashActivity)
                            }
                        })
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
        animatorSet.start()
    }

    override fun initDatas() {
//        JPushInterface.init(getApplicationContext());

    }

    override fun onResume() {
        isForeground = true
        super.onResume()
    }


    override fun onPause() {
        isForeground = false
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            887 ->  //开启GPS，重新添加地理监听
                if (GpsUtil.isOPen(this@SplashActivity)){
                    LoginActivity.startAction(this@SplashActivity)
                    AppManager.getAppManager().finishActivity(this@SplashActivity)
                }else{
                    GpsUtil.openGPS(this@SplashActivity)
                }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}