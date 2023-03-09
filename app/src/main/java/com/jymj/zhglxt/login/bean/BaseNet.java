package com.jymj.zhglxt.login.bean;

import android.content.Intent;

import com.jymj.zhglxt.app.AppApplication;
import com.jymj.zhglxt.login.activity.LoginActivity;
import com.lzy.okgo.model.Response;
import com.setsuna.common.baseNet.JsonCallBack;
import com.setsuna.common.commonutils.ToastUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 本系统中没有添加401验证
 * Created by setsuna on 2018/3/8.
 */

public abstract class BaseNet<T> extends JsonCallBack<T> {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public void onAuthorization() {
        super.onAuthorization();
//        LoginActivity.Companion.startAction(AppManager.getAppManager().currentActivity());
    }
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody responseBody = response.body();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        if (bodyString.contains("\"code\":401")){
            Intent intentmain = new Intent(AppApplication.getAppContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            AppApplication.getAppContext().startActivity(intentmain);
//            LoginActivity.Companion.startAction(AppManager.getAppManager().currentActivity());
        }
        return super.convertResponse(response);
    }
    private String inputStreamToString(InputStream is) {
        String s = "";
        String line = "";

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        while (true) {
            try {
                if (!((line = rd.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            s += line; }

        return s;

    }
    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        Throwable exception = response.getException();
        String message = exception.getMessage();
        if (message!=null)
        if (message.contains("用户授权信息已过期")){
            ToastUtils.showShort("用户授权信息已过期,请重新登录");
            Intent intentmain = new Intent(AppApplication.getAppContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            AppApplication.getAppContext().startActivity(intentmain);
//            LoginActivity.Companion.startAction(AppManager.getAppManager().currentActivity());
//            LoginActivity.Companion.startAction(AppManager.getAppManager().currentActivity());

        }
    }
}
