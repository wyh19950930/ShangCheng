package com.jymj.zhglxt.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amap.api.maps2d.model.UrlTileProvider;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jymj.zhglxt.app.AppApplication;
import com.jymj.zhglxt.util.GetFileUtil;
import com.setsuna.common.baseapp.BaseApplication;
import com.setsuna.common.commonutils.FileUtils;
import com.setsuna.common.commonutils.NetWorkUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TDTOSMTileProvider extends UrlTileProvider {
    public static final String OSM_BZDT = "osm_bzdt";
    public static final String OSM_YXT = "osm_yxt";
    public String ALBUM_PATH = "";
    private String type = OSM_BZDT;
//    String baseurl;

    /**
     * 天地图切片提供者
     * @param type  地图类型
     * @param path  缓存路径
     */
    public TDTOSMTileProvider(String type, String path) {
        super(256, 256);
        this.type = type;
//        baseurl = "http://t1.tianditu.com/DataServer?tk=2877dbc929a5fbba4917e4cbb6dbc06a&T=" + type;
        if (type.equals(OSM_BZDT)||type.equals("")){
            ALBUM_PATH= GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmKong5/";//path
        }else {
            ALBUM_PATH= GetFileUtil.getSDCardPath() + "BMS/map/zhglxt/osmYxy5/";//path
        }

    }

    /**
     *
     * @param x  x坐标
     * @param y  y坐标
     * @param zoom  缩放级别
     * @return
     */
    @Override
    public URL getTileUrl(int x, int y, int zoom) {
//        String url=baseurl+"&X="+x+"&Y="+y+"&L="+zoom;
//        String url="http://c.tile.openstreetmap.org/"+zoom+"/"+x+"/"+y+".png";
//        String url = "https://tile-b.openstreetmap.fr/hot/" + zoom + "/" + x + "/" + y + ".png";
        String url = "";
        if (type.equals(OSM_BZDT)||type.equals("")){
            url = "https://a.tile.openstreetmap.fr/osmfr/" + zoom + "/" + x + "/" + y + ".png";
        }else {
            url = "https://a.tile.openstreetmap.fr/osmfr/" + zoom + "/" + x + "/" + y + ".png";
        }
//        String url = "https://tile.openstreetmap.org/" + zoom + "/" + x + "/" + y + ".png";
        //https://tile-a.openstreetmap.fr/hot/2/2/2.png
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
        /*try {
            String mFileDirName;
            String mFileName;
            mFileDirName = String.format("L%02d/", zoom + 1);
            mFileName = String.format("%s", TileXYToQuadKey(x, y, zoom));//为了不在手机的图片中显示,下载的图片取消jpg后缀,文件名自己定义,写入和读取一致即可,由于有自己的bingmap图源服务,所以此处我用的bingmap的文件名
            String LJ = ALBUM_PATH + mFileDirName + mFileName;
            if (FileUtils.isFileExists(LJ)) {//判断本地是否有图片文件,如果有返回本地url,如果没有,缓存到本地并返回googleurl
                return new URL("file://" + LJ);
            } else {
                String filePath = String.format(url, x, y, zoom);
                Bitmap mBitmap;
                //mBitmap = BitmapFactory.decodeStream(getImageStream(filePath));//不知什么原因导致有大量的图片存在坏图,所以重写InputStream写到byte数组方法
                if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())){
                    mBitmap = getImageBitmap(getImageStream(url));
                    try {
                        saveFile(mBitmap, mFileName, mFileDirName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return new URL(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public Bitmap getImageBitmap(InputStream imputStream) {
        // 将所有InputStream写到byte数组当中
        byte[] targetData = null;
        byte[] bytePart = new byte[4096];
        while (true) {
            try {
                int readLength = imputStream.read(bytePart);
                if (readLength == -1) {
                    break;
                } else {
                    byte[] temp = new byte[readLength + (targetData == null ? 0 : targetData.length)];
                    if (targetData != null) {
                        System.arraycopy(targetData, 0, temp, 0, targetData.length);
                        System.arraycopy(bytePart, 0, temp, targetData.length, readLength);
                    } else {
                        System.arraycopy(bytePart, 0, temp, 0, readLength);
                    }
                    targetData = temp;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 指使Bitmap通过byte数组获取数据
        Bitmap bitmap = BitmapFactory.decodeByteArray(targetData, 0, targetData.length);
        return bitmap;
    }

    public InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存文件
     */
    public void saveFile(final Bitmap bm, final String fileName, final String fileDirName) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (bm != null) {
                        File dirFile = new File(ALBUM_PATH + fileDirName);
                        if (!dirFile.exists()) {
                            dirFile.mkdir();
                        }
                        File myCaptureFile = new File(ALBUM_PATH + fileDirName + fileName);
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        bos.flush();
                        bos.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String TileXYToQuadKey(int x, int y, int zoom) {
        return x + "-" + y + "-" + zoom;
    }

}
