package com.jymj.zhglxt.ui.bean.homepage;

import java.io.Serializable;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/12/29 15:01
 */
public class GeoPoint implements Serializable {
    private double lat;
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
