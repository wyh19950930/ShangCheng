package com.jymj.zhglxt.util;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.jymj.zhglxt.app.AppApplication;
import com.setsuna.common.commonutils.CloseUtils;
import com.setsuna.common.commonutils.ToastUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GetFileUtil {
    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    public static String getSDCardPath() {
        Context context = AppApplication.getAppContext();
        /*if (!isSDCardEnable()) return null;
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();
        BufferedReader bufferedReader = null;
        try {
            Process p = run.exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream())));
            String lineStr;
            while ((lineStr = bufferedReader.readLine()) != null) {
                if (lineStr.contains("sdcard") && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray.length >= 5) {
                        return strArray[1].replace("/.android_secure", "") + File.separator;
                    }
                }
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(bufferedReader);
        }
        if (context.getExternalFilesDir(null)!=null){
            return context.getExternalFilesDir(null).getPath()+ File.separator;
        }else {
            ToastUtils.showShort("路径有误，请联系管理员！！！");
        }*/

        String path = "";
        if (Build.VERSION.SDK_INT < 29) {
            path = Environment.getExternalStorageDirectory() + "/file/";
        } else {
            //10以后
            path = context.getExternalFilesDir("").getAbsolutePath() + "/file/";
        }
        return path;
    }

}
