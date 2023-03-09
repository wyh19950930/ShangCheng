package com.jymj.zhglxt.util

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.RequiresApi
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.util.version.VersionInfo
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.commonutils.AppUtils
import com.setsuna.common.commonutils.SDCardUtils
import com.setsuna.common.commonutils.ToastUtils
import com.zhy.http.okhttp.BuildConfig
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

object  CheckUpdateUtil {
    //检查更新
    fun checkUpdate(activity: Activity) {//lastest version
        val appName = AppUtils.getAppName()
        val httpParams = HttpParams()//  APPID_HSD
        httpParams.put("appid", ApiConstants.APPID_SHANG)
                                        httpParams.put("v", AppUtils.getAppVersionCode().toString())
                                        OkGo.post<VersionInfo>(ApiConstants.CHECK_UPDATE).params(httpParams).execute(object : BaseNet<VersionInfo>() {
                                            override fun onSuccess(response: Response<VersionInfo>?) {
                                                if (response?.body()?.code == 0) {
                                                    val url = response.body()?.data
                                                    if (url != null) {
                                                        if (!activity.isFinishing){
                                                            val content = TextView(activity)
                                                            content.setText(response.body().data.msg?.replace("\\n", "\n"))
                                                            content.setTextColor(Color.GRAY)
                                                            content.gravity= Gravity.HORIZONTAL_GRAVITY_MASK
                                                            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                                                    .setTitleText(activity.getString(R.string.update))
                                                                    .setCustomView(content)
                                                                    .setConfirmButton(activity.getString(R.string.update)) { sweetAlertDialog ->
                                                                        checkCanUpdate(response.body().data, sweetAlertDialog, content,activity)
                                                                    }
                                                                    .setCancelButton(activity.getString(R.string.cancel), { sweetAlertDialog ->
                                        sweetAlertDialog?.dismiss()
                                    })
                                    .show()
                        }

                        /*  val builder = AlertDialog.Builder(this@LoginActivity)
                          builder.setTitle("检测到新版本，是否更新？")
                                  .setIcon(R.mipmap.ic_launcher)
                                  .setPositiveButton("确定") { dialog, which ->
  //                                    VersionUpdateUtil.checkAppVersion(mContext as Activity?,response?.body())
                                      val content = TextView(mContext)
                                      content.setText(response.body().msg.replace("\\n", "\n"))
                                      content.setTextColor(Color.GRAY)
                                      checkCanUpdate(url, dialog, content)
                                  }
                                  .setNegativeButton("取消") { dialog, which ->

                                  }
                                  .create()
                                  .show()*/
                    }
                }else{
                    ToastUtils.showShort("已经是最新版本")
                }
            }

            override fun onError(response: Response<VersionInfo>?) {
                // ToastUtils.showShort(response?.message())
            }

        })
    }
    //检查更新
    fun checkUpdate(activity: Activity,onCheckLinear:OnCheckLinear) {
        val appName = AppUtils.getAppName()
        val httpParams = HttpParams()//  APPID_HSD
        httpParams.put("appid", ApiConstants.APPID_SHANG)
                                        httpParams.put("v", AppUtils.getAppVersionCode().toString())
                                        OkGo.post<VersionInfo>(ApiConstants.CHECK_UPDATE).params(httpParams).execute(object : BaseNet<VersionInfo>() {
                                            override fun onSuccess(response: Response<VersionInfo>?) {
                                                if (response?.body()?.code == 0) {
                                                    val url = response.body()?.data
                                                    if (url != null) {
                                                        onCheckLinear.onIsUpdate(true)
                                                        if (!activity.isFinishing){
                                                            val content = TextView(activity)
                                                            content.setText(response.body().data.msg?.replace("\\n", "\n"))
                                                            content.setTextColor(Color.GRAY)
                                                            content.gravity= Gravity.HORIZONTAL_GRAVITY_MASK
                                                            SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                                                                    .setTitleText(activity.getString(R.string.update))
                                                                    .setCustomView(content)
                                                                    .setConfirmButton(activity.getString(R.string.update)) { sweetAlertDialog ->
                                                                        checkCanUpdate(response.body().data, sweetAlertDialog, content,activity)
                                                                    }
                                                                    .setCancelButton(activity.getString(R.string.cancel), { sweetAlertDialog ->
                                        sweetAlertDialog?.dismiss()
                                    })
                                    .show()
                        }

                        /*  val builder = AlertDialog.Builder(this@LoginActivity)
                          builder.setTitle("检测到新版本，是否更新？")
                                  .setIcon(R.mipmap.ic_launcher)
                                  .setPositiveButton("确定") { dialog, which ->
  //                                    VersionUpdateUtil.checkAppVersion(mContext as Activity?,response?.body())
                                      val content = TextView(mContext)
                                      content.setText(response.body().msg.replace("\\n", "\n"))
                                      content.setTextColor(Color.GRAY)
                                      checkCanUpdate(url, dialog, content)
                                  }
                                  .setNegativeButton("取消") { dialog, which ->

                                  }
                                  .create()
                                  .show()*/
                    }
                }else{
                                                    onCheckLinear.onIsUpdate(false)
                    ToastUtils.showShort("已经是最新版本")
                }
            }

            override fun onError(response: Response<VersionInfo>?) {
                onCheckLinear.onIsUpdate(true)
                // ToastUtils.showShort(response?.message())
            }

        })
    }
    /**
     * 检测是否有安装权限
     */
    private fun checkCanUpdate(updateEntity: VersionInfo.DataBean, sweetAlertDialog: SweetAlertDialog, content: TextView,activity: Activity) {
        if (Build.VERSION.SDK_INT >= 26) {
            val b = activity.packageManager.canRequestPackageInstalls()
            if (b) {

                sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
                sweetAlertDialog.setCancelable(false)
                sweetAlertDialog.showCancelButton(false)
                content.gravity = Gravity.CENTER
                content.text = activity.getString(R.string.update_download_content)
                sweetAlertDialog.titleText = activity.getString(R.string.update_download)
                sweetAlertDialog.progressHelper.setInstantProgress(0f)
                sweetAlertDialog.progressHelper.rimWidth = 10
                sweetAlertDialog.progressHelper.barWidth = 10
                sweetAlertDialog.progressHelper.rimColor = activity.resources.getColor(R.color.gray)
                Thread(DownloadApk(updateEntity.path,sweetAlertDialog,content,activity)).start();
//                DownloadHelper.getInstance(activity,activity,"update.apk",updateEntity.path,sweetAlertDialog,content).startDownload("apk")

            } else {
                content.setText("更新")
                sweetAlertDialog
                        .setConfirmButton("去设置") { sweetAlertDialog ->
                            startInstallPermissionSettingActivity(activity)
                            content.setText(updateEntity.msg.replace("\\n", "\n"))
                            sweetAlertDialog
                                    .setConfirmButton("更新") { sweetAlertDialog ->
                                        checkCanUpdate(updateEntity, sweetAlertDialog, content,activity)
                                    }
                        }
            }
        } else {

            sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
            sweetAlertDialog.setCancelable(false)
            sweetAlertDialog.showCancelButton(false)
            content.gravity = Gravity.CENTER
            content.text = activity.getString(R.string.update_download_content)
            sweetAlertDialog.titleText = activity.getString(R.string.update_download)
            sweetAlertDialog.progressHelper.setInstantProgress(0f)
            sweetAlertDialog.progressHelper.rimWidth = 10
            sweetAlertDialog.progressHelper.barWidth = 10
            sweetAlertDialog.progressHelper.rimColor = activity.resources.getColor(R.color.gray)
            Thread(DownloadApk(updateEntity.path,sweetAlertDialog,content,activity)).start();
//            DownloadHelper.getInstance(activity,activity,"update.apk",updateEntity.path,sweetAlertDialog,content).startDownload("apk")

        }
    }

    /**
     * 请求更新
     */
//    SDCardUtils.getSDCardPath
    private fun getUpdate(updateEntity: VersionInfo.DataBean, sweetAlertDialog: SweetAlertDialog, content: TextView,activity: Activity) {//GetFileUtil.getSDCardPath

        OkGo.get<File>(updateEntity.path).execute(
                object : FileCallback(AppMenus.getInstance().cardPath + "JYMJManager/uploader/app",
                        "update.apk") {
            override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
                sweetAlertDialog.setCancelable(false)
                sweetAlertDialog.showCancelButton(false)
                content.gravity = Gravity.CENTER
                content.setText(activity.getString(R.string.update_download_content))
                sweetAlertDialog.setTitleText(activity.getString(R.string.update_download))
                sweetAlertDialog.progressHelper.setInstantProgress(0f)
                sweetAlertDialog.progressHelper.rimWidth = 10
                sweetAlertDialog.progressHelper.barWidth = 10
                sweetAlertDialog.progressHelper.rimColor = activity.resources.getColor(R.color.gray)
            }

            override fun onSuccess(response: Response<File>?) {
                sweetAlertDialog.dismissWithAnimation()
                val file = response?.body()
                if (file != null) {
                    val appPackageName = AppUtils.getAppPackageName()
                    AppUtils.installApp(activity,file, appPackageName)//+"_file"
                }
            }

            override fun downloadProgress(progress: Progress?) {
                if (progress != null) {
                    sweetAlertDialog.progressHelper.setInstantProgress(progress.fraction)
                }
            }

            override fun onError(response: Response<File>?) {
                super.onError(response)
                if (response!=null){

                }
            }
        })

    }

    private fun downIng(sweetAlertDialog: SweetAlertDialog, content: TextView,activity: Activity){
        sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.showCancelButton(false)
        content.gravity = Gravity.CENTER
        content.setText(activity.getString(R.string.update_download_content))
        sweetAlertDialog.setTitleText(activity.getString(R.string.update_download))
        sweetAlertDialog.progressHelper.setInstantProgress(0f)
        sweetAlertDialog.progressHelper.rimWidth = 10
        sweetAlertDialog.progressHelper.barWidth = 10
        sweetAlertDialog.progressHelper.rimColor = activity.resources.getColor(R.color.gray)
    }

    /**
     * 这个是8.0安装
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity(activity: Activity) {
        //注意这个是8.0新API
        /*val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
        activity.startActivity(intent)*/
        //没有权限让调到设置页面进行开启权限；
        val appPackageName = AppUtils.getAppPackageName()
                var packageURI = Uri.parse("package:" + appPackageName);
                var intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        activity.startActivityForResult(intent, 10086);
    }

    public interface OnCheckLinear{
        fun onIsUpdate(boolean: Boolean)
    }
}