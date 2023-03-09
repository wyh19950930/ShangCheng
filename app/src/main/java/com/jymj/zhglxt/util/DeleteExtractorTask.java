package com.jymj.zhglxt.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.setsuna.common.commonutils.FileUtils;

import java.io.File;
import java.util.ArrayList;
//删除缓存的异步弹窗
public class DeleteExtractorTask extends AsyncTask<Void, Integer, Long> {
    private Context mContext;
    private final ProgressDialog mDialog;
    private int total;
    private int curPosi;
    private String path;
    private ArrayList<String> fileList = new ArrayList<String>();

    public DeleteExtractorTask(String filePath, int total, Context context) {
        if (context != null) {
            mDialog = new ProgressDialog(context);
        } else {
            mDialog = null;
        }
        mContext = context;
        this.total = getFilse(new File(filePath)).size();//total
        this.path = filePath;
    }

    @Override
    protected Long doInBackground(Void... voids) {
        publishProgress(0, total);
        deleteDirectory(path);
        return null;
    }

    @Override
    protected void onPreExecute() {
        if (mDialog != null) {
            mDialog.setTitle("共" + total + "个文件");
            mDialog.setMessage("正在删除");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.show();
        }
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if (mDialog != null && mDialog.isShowing()) {
//            FileUtils.mkdirFiles();
            mDialog.dismiss();
        }
        if (isCancelled())
            return;

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (mDialog == null)
            return;
        if (values.length > 1) {
            int max = values[1];
            mDialog.setMax(max);
        } else {
            mDialog.setProgress(values[0].intValue());
        }
    }

    private boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                curPosi++;
                publishProgress(curPosi);
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                curPosi++;
                publishProgress(curPosi);
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    private ArrayList<String> getFilse(File tempFile ) {
        try {
            if (!tempFile.exists()) return new ArrayList();
            if (tempFile.isDirectory()) {
                File[] files = tempFile.listFiles();
                if (files == null || files.length == 0) {
                    //                    return tempFile.delete();
                }
                for (File file : files) {
                    if (file.isFile()) {
                        fileList.add(file.getName());
                        //                        file.delete();
                    } else if (file.isDirectory()) {
                        getFilse(file);
                    }
                }
                return fileList;
            } else {
                return fileList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList();
    }
}
