package com.jymj.zhglxt.bean;

import java.util.List;

/**
 * 快递查询
 */
public class KdQueryBean {

    /**
     * message : ok
     * nu : 0092198586855
     * ischeck : 0
     * condition : 00
     * com : jd
     * status : 200
     * state : 0
     * data : [{"time":"2022-12-28 06:32:22","ftime":"2022-12-28 06:32:22","context":"[广东省 深圳市 龙华区]快件离开【深圳转运中心】已发往【深圳市罗湖笋岗网点】","location":"广东省 深圳市 龙华区"},{"time":"2022-12-28 05:21:23","ftime":"2022-12-28 05:21:23","context":"[广东省 深圳市 龙华区]快件到达【深圳转运中心】","location":"广东省 深圳市 龙华区"},{"time":"2022-12-28 05:18:19","ftime":"2022-12-28 05:18:19","context":"[广东省 深圳市 龙华区]【深圳龙华区观澜网点】的陈月媚(0755-61670079)已取件。投诉电话：0755-61670079","location":"广东省 深圳市 龙华区"},{"time":"2022-12-28 04:33:31","ftime":"2022-12-28 04:33:31","context":"[广东省 深圳市 龙华区]快件离开【观湖集散点】已发往【深圳转运中心】","location":"广东省 深圳市 龙华区"}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2022-12-28 06:32:22
         * ftime : 2022-12-28 06:32:22
         * context : [广东省 深圳市 龙华区]快件离开【深圳转运中心】已发往【深圳市罗湖笋岗网点】
         * location : 广东省 深圳市 龙华区
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public DataBean(String time, String ftime, String context, String location) {
            this.time = time;
            this.ftime = ftime;
            this.context = context;
            this.location = location;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
