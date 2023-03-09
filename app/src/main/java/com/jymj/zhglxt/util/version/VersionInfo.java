package com.jymj.zhglxt.util.version;

/**
 * Created by Rayhahah on 2017/4/11.
 */

public class VersionInfo {

    /*
    *
     * msg : null
     * code : 0
     * data : {"msg":"更新日志：\\n修复了测算表的一些错误\\n修复了定位问题","path":"http://39.105.122.55/M00/00/02/fwAAAVuYePuANw1RAFW02GZscB4427.apk"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg == null?"网络异常":msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msg : 更新日志：\n修复了测算表的一些错误\n修复了定位问题
         * path : http://39.105.122.55/M00/00/02/fwAAAVuYePuANw1RAFW02GZscB4427.apk
         */

        private String msg;
        private String path;

        public String getMsg() {
            return msg == null?"网络异常":msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getPath() {
            return path==null?"":path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
