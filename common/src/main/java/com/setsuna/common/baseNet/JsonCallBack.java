package com.setsuna.common.baseNet;

import android.os.Build;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.LogUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import android.util.Base64;

import androidx.annotation.RequiresApi;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/7/24.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    
    private Type type;
    private Class<T> clazz;
    
    public JsonCallBack() {}
    
    public JsonCallBack(Type type) {
        this.type = type;
    }
    
    public JsonCallBack(Class<T> clazz) {
        this.clazz = clazz;
    }
    

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        String token = AppCache.getInstance().getToken();

        HttpHeaders headers = request.getHeaders();

        /*String authorization = headers.get("Authorization");
        if (token!=null&&authorization!=null){
            token=authorization;
            AppCache.getInstance().setToken(authorization);
        }*/
        if (token!=null){
            if (token.contains("Bearer ")){//判断token是否包含Bearer
                request.headers("Authorization",token);
            }else {
                request.headers("Authorization", "Bearer "+token);
            }
        }else {
            request.headers("Authorization", "Basic "+encryptBASE64("user-android","jymj-app-android-secret"));
        }

    }
    
    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }
        
        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }
    
    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        Throwable exception=response.getException();
        if (exception!=null){
            exception.printStackTrace();
        }
        if (exception instanceof UnknownHostException || exception instanceof ConnectException){
            LogUtils.e("网络连接失败,请连接网络!");
            response.setException(new ConnectException("网络连接失败,请连接网络!!"));
        }else if (exception instanceof SocketTimeoutException){
            LogUtils.e("网络请求超时");
            response.setException(new SocketTimeoutException("网络请求超时!!"));
        }else if (exception instanceof HttpException){
            LogUtils.e("网络请求失败");
            response.setException(new HttpException("网络请求失败"));
        }else if (exception instanceof StorageException){
            LogUtils.e("sd卡不存在或没有获取权限!!");
            response.setException(new StorageException("sd卡不存在或没有获取权限!!"));
        }else if (exception instanceof IllegalStateException){
            LogUtils.e(exception.getMessage());
        }
    }
    public void onAuthorization(){}

    /**
     * BASE64编码
     */

    public  String encryptBASE64(String username, String password) {
        byte[] key = (username+":"+password).getBytes();
        return Base64.encodeToString(key,Base64.NO_WRAP);
    }
}
