package com.jymj.zhglxt.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.util.version.VersionInfo
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.commonutils.ActivityUtils
import com.setsuna.common.commonutils.AppUtils
import com.setsuna.common.commonutils.SDCardUtils
import java.io.File

/**
 * @Author:         Mr.haozi
 * @CreateDate:     2020/9/16 10:21
 */
 class UpdateUtils {

    fun checkUpdate(contents: Context){
        val appName = AppUtils.getAppName()
        val httpParams = HttpParams()//  APPID_HSD
        httpParams.put("appid", ApiConstants.APPID_SHANG)
        httpParams.put("v", AppUtils.getAppVersionCode().toString())
        OkGo.post<VersionInfo>("http://139.224.52.12:8001/api/ver/get").params(httpParams).execute(object : BaseNet<VersionInfo>(){//BaseCallBack
            override fun onSuccess(response: Response<VersionInfo>?) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().data!=null){
                            val updateEntity = response.body().data
                            val content = TextView(contents)
                            content.setText(updateEntity.msg)
                            content.setTextColor(Color.GRAY)
                            SweetAlertDialog(contents, SweetAlertDialog.NORMAL_TYPE)
                                    .setTitleText("检查更新")
                                    .setCustomView(content)
                                    .setConfirmButton("更新") { sweetAlertDialog ->
                                        checkCanUpdate(updateEntity, sweetAlertDialog, content,contents)
                                    }
                                    .setCancelButton("取消", { sweetAlertDialog ->
                                        sweetAlertDialog?.dismiss()
                                    })
                                    .show()
                        }else{
                            Toast.makeText(contents, "已是最新版本！", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(contents, "检查更新失败，请联系管理员获取更新！", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(contents, "检查更新失败，请联系管理员获取更新！", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onError(response: Response<VersionInfo>?) {
                super.onError(response)
                Toast.makeText(contents, response?.exception?.message, Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun checkCanUpdate(updateEntity: VersionInfo.DataBean, sweetAlertDialog: SweetAlertDialog, content: TextView, contents: Context) {
        if (Build.VERSION.SDK_INT >= 26) {

            val b = contents.packageManager.canRequestPackageInstalls()
            if (b) {
                getUpdate(updateEntity, sweetAlertDialog, content,contents)
            } else {
                content.setText("请在设置中允许我们的应用安装更新")
                sweetAlertDialog
                        .setConfirmButton("去设置") { sweetAlertDialog ->
                            startInstallPermissionSettingActivity(contents)
                            content.setText(updateEntity.msg)
                            sweetAlertDialog
                                    .setConfirmButton("更新") { sweetAlertDialog ->
                                        checkCanUpdate(updateEntity, sweetAlertDialog, content,contents)
                                    }
                        }
            }
        } else {
            getUpdate(updateEntity, sweetAlertDialog, content,contents)
        }
    }

    private fun getUpdate(updateEntity: VersionInfo.DataBean, sweetAlertDialog: SweetAlertDialog, content: TextView, contents: Context) {//update.apk SDCardUtils.getSDCardPath
        OkGo.get<File>(updateEntity.path).execute(object : FileCallback(GetFileUtil.getSDCardPath() + "BMS/download/files", "update.apk") {
            override fun onStart(request: Request<File, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
                sweetAlertDialog.setCancelable(false)
                sweetAlertDialog.showCancelButton(false)
                content.gravity = Gravity.CENTER
                content.setText("即将完成更新，请耐心等待")
                sweetAlertDialog.setTitleText("下载更新")
                sweetAlertDialog.progressHelper.setInstantProgress(0f)
                sweetAlertDialog.progressHelper.rimWidth = 10
                sweetAlertDialog.progressHelper.barWidth = 10
                //sweetAlertDialog.progressHelper.rimColor = resources.getColor(R.color.gray)
            }

            override fun onSuccess(response: Response<File>?) {
                sweetAlertDialog.dismissWithAnimation()
                val file = response?.body()
                if (file != null) {
                    AppUtils.installApp(contents,file, AppUtils.getAppPackageName()+ "_file")//+"_file"
//                    AppUtils.installApp(contents,file, AppUtils.getAppPackageName())// + "_file"
                }
            }

            override fun downloadProgress(progress: Progress?) {
                if (progress != null) {
                    sweetAlertDialog.progressHelper.setInstantProgress(progress.fraction)
                }
            }
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity(contents: Context) {
        //注意这个是8.0新API
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
        contents.startActivity(intent)
    }
}