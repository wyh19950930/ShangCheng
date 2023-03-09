package com.jymj.zhglxt.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.amap.api.maps2d.model.UrlTileProvider;
import com.setsuna.common.baseapp.BaseApplication;
import com.setsuna.common.commonutils.FileUtils;
import com.setsuna.common.commonutils.NetWorkUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zkj on 2017/08/02
 */

public class HeritageScopeTileProvider extends UrlTileProvider {

    private String mRootUrl;
    //默认瓦片大小
    private static int titleSize = 512;//a=6378137±2（m）  256
    //基本参数
//    private final double initialResolution= 156543.03392804062;//2*Math.PI*6378137/titleSize;
    private final double initialResolution= 2*Math.PI*6378137/titleSize;
//    private final double originShift      = 20037508.342789244;//2*Math.PI*6378137/2.0; 周长的一半
    private final double originShift      = 2*Math.PI*6378137/2.0;

    private final double HALF_PI = Math.PI / 2.0;
    private final double RAD_PER_DEGREE = Math.PI / 180.0;
    private final double HALF_RAD_PER_DEGREE = Math.PI / 360.0;
    private final double METER_PER_DEGREE = originShift / 180.0;//一度多少米
    private final double DEGREE_PER_METER = 180.0 / originShift;//一米多少度
    public  String ALBUM_PATH = "";

    public HeritageScopeTileProvider(String rootUrl, String path) {
        super(titleSize, titleSize);
        //地址写你自己的wms地址
//        mRootUrl = "http://xxxxxx自己的/wms?LAYERS=cwh:protect_region_38_20160830&FORMAT=image%2Fpng&TRANSPARENT=TRUE&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&SRS=EPSG%3A900913&BBOX=";
        mRootUrl=rootUrl;
        ALBUM_PATH=path;
    }

    public HeritageScopeTileProvider(int i, int i1) {
        super(i, i1);
    }
//http://139.224.52.12/geoserver/dx/wms?service=WMS&version=1.1.0&request=GetMap&TRANSPARENT=TRUE&layers=dx:dx_tdly&styles=&viewparams=code:&srs=EPSG:3857&format=image%2Fpng&bbox=1.2944152117924888E7,4843050.112148767,1.295393605754539E7,4852834.05176927&WIDTH=256&HEIGHT=256
    @Override
    public URL getTileUrl(int x, int y, int level) {
        /*String url = mRootUrl + TitleBounds(x, y, level);
        try {
            return new URL(url);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
        String url = mRootUrl + TitleBounds(x, y, level);
        try {
            String mFileDirName;
            String mFileName;
            mFileDirName = String.format("L%02d/", level + 1);
            mFileName = String.format("%s", TileXYToQuadKey(x, y, level));//为了不在手机的图片中显示,下载的图片取消jpg后缀,文件名自己定义,写入和读取一致即可,由于有自己的bingmap图源服务,所以此处我用的bingmap的文件名
            String LJ = ALBUM_PATH + mFileDirName + mFileName;
            if (FileUtils.isFileExists(LJ)) {//判断本地是否有图片文件,如果有返回本地url,如果没有,缓存到本地并返回googleurl
                return new URL("file://" + LJ);
            } else {
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
        return null;
    }


    /**
     * 根据像素、等级算出坐标
     *
     * @param p
     * @param zoom
     * @return
     */
    private double Pixels2Meters(int p, int zoom) {
        return p * Resolution(zoom) - originShift;
    }

    /**
     * 根据瓦片的x/y等级返回瓦片范围
     *
     * @param tx
     * @param ty
     * @param zoom
     * @return
     */
    private String TitleBounds(int tx, int ty, int zoom) {
        double minX = Pixels2Meters(tx * titleSize, zoom);
        double maxY = -Pixels2Meters(ty * titleSize, zoom);
        double maxX = Pixels2Meters((tx + 1) * titleSize, zoom);
        double minY = -Pixels2Meters((ty + 1) * titleSize, zoom);

        //转换成经纬度
       /* minX=Meters2Lon(minX);
        minY=Meters2Lat(minY);
        maxX=Meters2Lon(maxX);
        maxY=Meters2Lat(maxY);
        PositionModel position1 = PositionUtil.gcj_To_Gps84(minY,minX);
        minX = position1.getWgLon();
        minY = position1.getWgLat();
        PositionModel position2 = PositionUtil.gcj_To_Gps84(maxY,maxX);
        maxX = position2.getWgLon();
        maxY = position2.getWgLat();

        minX=Lon2Meter(minX);
        minY=Lat2Meter(minY);
        maxX=Lon2Meter(maxX);
        maxY=Lat2Meter(maxY);*/

        return minX + "," + Double.toString(minY) + "," + Double.toString(maxX) + "," + Double.toString(maxY) + "&WIDTH=256&HEIGHT=256";
    }
    /**
     * 计算分辨率
     *
     * @param zoom
     * @return
     */
    private double Resolution(int zoom) {
        return initialResolution / (Math.pow(2, zoom));
    }

    /**
     * X米转经纬度
     */
    private double Meters2Lon(double mx) {
        double lon = mx * DEGREE_PER_METER;
        return lon;
    }
    /**
     * Y米转经纬度
     */
    private double Meters2Lat(double my) {
        double lat = my * DEGREE_PER_METER;
        lat = 180.0 / Math.PI * (2 * Math.atan(Math.exp(lat * RAD_PER_DEGREE)) - HALF_PI);
        return lat;
    }
    /**
     * X经纬度转米
     */
    public double Lon2Meter(double lon) {
        double mx = lon * METER_PER_DEGREE;
        return mx;
    }
    /**
     * Y经纬度转米
     */
    public double Lat2Meter(double lat) {
        double my = Math.log(Math.tan((90 + lat) * HALF_RAD_PER_DEGREE)) / (RAD_PER_DEGREE);
        my = my * METER_PER_DEGREE;
        return my;
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
