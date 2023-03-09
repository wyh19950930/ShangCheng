package com.setsuna.common.basebean;

import java.io.Serializable;

/**
 * des:封装服务器返回数据
 * Created by setsuna
 * on 2016.09.9:47
 */
public class BaseRespose<T> implements Serializable {
    public Integer code;
    public String msg;

    public T data;


    public Integer getCode() {
        return code==null?-1:code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg==null?"":msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
