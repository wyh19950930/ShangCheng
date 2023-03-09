package com.jymj.zhglxt.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.jymj.zhglxt.R;
import com.jymj.zhglxt.api.AppMenus;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.AppUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import cn.pedant.SweetAlert.SweetAlertDialog;
import freemarker.template.utility.StringUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadApk implements Runnable {
    private String path;
    private SweetAlertDialog sweetAlertDialog;
    private TextView content;
    private Activity activity;
    InputStream is;
    FileOutputStream fos;

    public DownloadApk(String path, SweetAlertDialog sweetAlertDialog, TextView content , Activity activity ) {//ProgressDialog dialog
        this.path = path;
        this.sweetAlertDialog = sweetAlertDialog;
        this.content = content;
        this.activity = activity;

    }

    @Override
    public void run() {

        OkHttpClient client = new OkHttpClient();
        //下载地址
//        String url = StringUtil.APPDOWNLOAD_URL;
        Request request = new Request.Builder().get().url(path).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                //获取内容总长度
                long contentLength = response.body().contentLength();
                //设置最大值
//                dialog.setMax((int) contentLength);
                String sdCardPath = AppMenus.getInstance().getCardPath();
                //保存到sd卡
//                File apkFile = new File("mnt/sdcard", "HomeTheaters.apk");
                File apkFile = new File(sdCardPath, "HomeTheaters.apk");
                fos = new FileOutputStream(apkFile);
                //获得输入流
                is = response.body().byteStream();
                //定义缓冲区大小
                byte[] bys = new byte[1024];
                int progress = 0;
                int len = -1;
                while ((len = is.read(bys)) != -1) {
                    try {
                        Thread.sleep(1);
                        fos.write(bys, 0, len);
                        fos.flush();
                        progress += len;
                        //设置进度

                        int finalProgress = progress;
                        activity.runOnUiThread(new Runnable() {
                            public void run() {
//                                BigDecimal bigDecimal = new BigDecimal(finalProgress);
//                                BigDecimal bigDecimal1 = new BigDecimal(contentLength);
//                                float v = Float.intBitsToFloat(finalProgress);
//                                float v = Float.intBitsToFloat(contentLength);
//                                BigDecimal multiply = bigDecimal.divide(bigDecimal1).multiply(new BigDecimal(100));
                                float v = Float.parseFloat("" + finalProgress);
                                float f = Float.parseFloat("" + contentLength);
                                float size = v/f*1;
                                DecimalFormat decimalFormat = new DecimalFormat("###");//.##
                                String format = decimalFormat.format(size * 100);
                                sweetAlertDialog.setTitleText(activity.getString(R.string.update_download)+" "+format+"%");
//                                Log.e("finalProgress",finalProgress+" - "+contentLength+" - "+size);
//                                sweetAlertDialog.getProgressHelper().set
                                sweetAlertDialog.getProgressHelper().setInstantProgress(size);
                            }
                        });
                        /*if (progress != null) {
                        }*/
//                        dialog.setProgress(progress);
                    } catch (InterruptedException e) {

                    }
                }
                //下载完成,提示用户安装
//                installApk(apkFile);
//                sweetAlertDialog.dismissWithAnimation();
//                val file = response?.body()
                if (apkFile != null) {
                    String appPackageName = AppUtils.getAppPackageName();
                    AppUtils.installApp(activity,apkFile, appPackageName);//+"_file"
                }
            }
        } catch (IOException e) {

        } finally {
            //关闭io流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                is = null;
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fos = null;
            }
        }

        activity.runOnUiThread(new Runnable() {
            public void run() {

                sweetAlertDialog.dismissWithAnimation();
            }
        });
//        dialog.dismiss();
    }


}
