package com.setsuna.common.baseNet;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016/9/1.
 */
public abstract class NetCallback<T> extends AbsCallback<T> {

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        Gson gson=new Gson();
        ResponseBody body=response.body();
        if (body==null)return null;
        T data=null;
//        JsonReader jsonReader=new JsonReader(body.string());
        String result=body.string();
        result = result.substring(1, result.length() - 1);
        result=result.replaceAll("\\\\\"","\"");
        Type type=((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        data=gson.fromJson(result,type);
        return data;
    }
}
