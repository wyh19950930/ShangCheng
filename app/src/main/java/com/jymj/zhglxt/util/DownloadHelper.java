package com.jymj.zhglxt.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.google.android.material.internal.ContextUtils;
import com.jymj.zhglxt.R;
import com.setsuna.common.commonutils.AppUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import androidx.core.content.FileProvider;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/12/2 11:49
 */
public class DownloadHelper {
    private static final String TAG = "DownloadHelper";

    private boolean debug = false;

    private Context context;
    /**
     * 系统下载管理器
     */
    private DownloadManager mDownloadManager;
    /**
     * 下载ID
     */
    private long mDownloadId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件下载地址
     */
    private String downloadUrl;
    private String filePath;

    private boolean downloading;

    private SweetAlertDialog sweetAlertDialog;
    private TextView content;
    private Activity mActivity;

    /**
     * 通知栏点击事件，点击进入下载详情
     */
    private BroadcastReceiver mDownloadDetailsReceiver;

    private final Runnable mQueryProgressRunnable = new Runnable() {

        @Override
        public void run() {
            queryProgress();
            if (downloading) {
                mHandler.postDelayed(mQueryProgressRunnable, 1000);
            }
        }
    };

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private DownloadListener downloadListener;
    private TimerTask mTask;

    public interface DownloadListener {

        void onStart();

        void onProgress(long soFarSize, long totalSize);

        void onFinish(String fileFullPath, long totalSize);

        void onFailed();
    }

    public void setDownloadListener(DownloadListener listener) {
        this.downloadListener = listener;
    }

    private DownloadHelper(Context context,Activity activity, String fileName, String downloadUrl, SweetAlertDialog sweetAlertDialog, TextView contents) {
        this.context = context;
        this.mActivity = activity;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
        this.sweetAlertDialog = sweetAlertDialog;
        this.content = contents;
    }

    public static DownloadHelper getInstance(Context context, Activity activity, String fileName, String downloadUrl, SweetAlertDialog sweetAlertDialog, TextView contents) {
        DownloadHelper helper = new DownloadHelper(context, activity,fileName, downloadUrl,sweetAlertDialog,contents);
        helper.registerReceiver();
        return helper;
    }

    /**
     * 注册广播
     */
    private void registerReceiver() {

        mDownloadDetailsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)) {
                    showDownloadList();
                }
            }
        };
        context.registerReceiver(mDownloadDetailsReceiver, new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }

    /**
     * 显示下载列表
     */
    public void showDownloadList() {
        Intent downloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        if (downloadIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(downloadIntent);
        }
    }

    /**
     * 开始下载
     */
    public void startDownload(String mimeType) {
        if (downloadListener != null) downloadListener.onStart();

        mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
                .setTitle("Downloading " + fileName)    // 通知栏标题
                .setMimeType(mimeType)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName)
                .allowScanningByMediaScanner();

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName);
        if (file.exists()) {
            file.delete();
        }
        request.setDestinationUri(Uri.fromFile(file));
        filePath = file.getAbsolutePath();

        try {
            mDownloadId = mDownloadManager.enqueue(request); // 加入下载队列
            if (mDownloadId != 0) {
                startQueryProgress();
            }
        } catch (IllegalArgumentException e) {
            if (downloadListener != null) downloadListener.onFailed();
            // "更新失败", "请在设置中开启下载管理"
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + "com.android.providers.downloads"));
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }



    }

    /**
     * 查询下载进度
     */
    private void queryProgress() {
        // 通过ID向下载管理查询下载情况，返回一个cursor
        Cursor c = mDownloadManager.query(new DownloadManager.Query().setFilterById(mDownloadId));
        if (c != null && c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            logDebug("下载状态：" + status);
            switch (status) {
                case DownloadManager.STATUS_PAUSED: //下载暂停， 由系统触发
                case DownloadManager.STATUS_PENDING: //下载延迟， 由系统触发
                    break;
                case DownloadManager.STATUS_RUNNING: //正在下载， 由系统触发
                    long soFarSize = c.getLong(c.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    long totalSize = c.getLong(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    if (totalSize > 0) {
                        logDebug(String.format("total:%s soFar:%s ", totalSize, soFarSize) + soFarSize * 1.0f / totalSize);
                        if (downloadListener != null) {
                            downloadListener.onProgress(soFarSize, totalSize);
                        }
                    }


                            DownloadManager.Query query = new DownloadManager.Query();
                            Cursor cursor = mDownloadManager.query(query.setFilterById(mDownloadId));
                            if (cursor != null && cursor.moveToFirst()) {
                                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                                    // 成功后取消监听
                                    mTask.cancel();
                                }

                                int bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                                int bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                                int pro = (bytesDownloaded * 100) / bytesTotal;
                                float progress = (float)pro/100;
                                mActivity.runOnUiThread(new Runnable() {
                                    public void run() {
//

                                        DecimalFormat decimalFormat = new DecimalFormat("###");//.##
                                        String format = decimalFormat.format(pro);
                                        sweetAlertDialog.setTitleText(mActivity.getString(R.string.update_download)+" "+format+"%");
//                                Log.e("finalProgress",finalProgress+" - "+contentLength+" - "+size);
//                                sweetAlertDialog.getProgressHelper().set
                                        sweetAlertDialog.getProgressHelper().setInstantProgress(progress);
                                    }
                                });
//                                sweetAlertDialog.getProgressHelper().setInstantProgress((float)pro);
                            }


                    break;
                case DownloadManager.STATUS_SUCCESSFUL: //下载完成， 由系统触发
//                    installAPK();
                    sweetAlertDialog.dismissWithAnimation();
                    stopQueryProgress();

                    File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    totalSize = c.getLong(c.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    if (downloadListener != null) {
                        String fullName = downloadDir.getPath() + File.separator + fileName;
                        logDebug(fullName);
                        downloadListener.onFinish(fullName, totalSize);
                    }
                    installAPK();
                    break;
                case DownloadManager.STATUS_FAILED: //下载失败， 由系统触发
                    if (downloadListener != null) downloadListener.onFailed();
                    break;
            }
        } else {
            stopQueryProgress();
            if (downloadListener != null) downloadListener.onFailed();
        }
        closeCursor(c);
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    private void logDebug(String msg) {
        if (debug) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 移除下载并删除下载文件
     */
    public void removeDownload() {
        mDownloadManager.remove(mDownloadId);
        stopQueryProgress();
    }

    /**
     * 开始查询下载进度
     */
    private void startQueryProgress() {
        downloading = true;
        mHandler.post(mQueryProgressRunnable);
    }

    /**
     * 停止查询下载进度
     */
    private void stopQueryProgress() {
        downloading = false;
        mHandler.removeCallbacks(mQueryProgressRunnable);
    }

    public boolean isDownloading() {
        return downloading;
    }

    public void onDestroy() {
        try {
            stopQueryProgress(); //停止查询下载进度
            context.unregisterReceiver(mDownloadDetailsReceiver);
        } catch (Exception ex) {
            //java.lang.IllegalArgumentException: Receiver not registered:
        }
    }
    private void installAPK() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Android 7.0以上要使用FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            File file = new File(filePath);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, AppUtils.getAppPackageName() + ".fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(Environment.DIRECTORY_DOWNLOADS, fileName)), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
}
