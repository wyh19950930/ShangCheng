package com.jymj.zhglxt.util;

import android.graphics.Bitmap;

import com.jymj.zhglxt.api.AppMenus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class BitMapToFile {
    //在这里抽取了一个方法   可以封装到自己的工具类中...
    public static File getFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        long l = System.currentTimeMillis();
        String time = l+"";
        String substring = time.substring(time.length() - 3, time.length());
        File file1 = new File(AppMenus.getInstance().getCardPath() + "jymj/tzrjhj/pic/");
        if (!file1.isDirectory()){
            file1.mkdirs();
        }
        File file = new File(AppMenus.getInstance().getCardPath() + "jymj/tzrjhj/pic/temp"+substring+".jpg");
        try {
            while (file.isFile()){
                substring = substring+=1;
                file = new File(AppMenus.getInstance().getCardPath() + "jymj/tzrjhj/pic/temp"+substring+".jpg");
            }
        }finally {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                InputStream is = new ByteArrayInputStream(baos.toByteArray());
                int x = 0;
                byte[] b = new byte[1024 * 100];
                while ((x = is.read(b)) != -1) {
                    fos.write(b, 0, x);
                }
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return file;
    }
}
