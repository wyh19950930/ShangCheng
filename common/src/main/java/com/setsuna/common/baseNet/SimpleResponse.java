package com.setsuna.common.baseNet;


import com.setsuna.common.basebean.BaseRespose;

import java.io.Serializable;

public class SimpleResponse implements Serializable {
    
    private static final long serialVersionUID = -1477609349345966116L;
    
    public int code;
    public String msg;
    
    public BaseRespose toBaseRespose() {
        BaseRespose lzyResponse = new BaseRespose();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}
