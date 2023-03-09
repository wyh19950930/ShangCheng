package com.jymj.zhglxt.login;

import android.util.Log;

import com.jymj.zhglxt.app.AppApplication;
import com.jymj.zhglxt.login.activity.LoginActivity;
import com.jymj.zhglxt.util.IsNetWorkUtils;
import com.jymj.zhglxt.util.SharedPreferencesUtils;
import com.jymj.zhglxt.util.SingleOnClickUtil;
import com.parse.codec.binary.Base64;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.ToastUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;


/**
 * 自动刷新token的拦截器
 *
 * @author shijiacheng
 * @version 1.0
 */

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private SharedPreferencesUtils sharedPreferencesUtils;
//    private String accessToken;
    private Request newRequest;
    private Response originalResponse;
//    private String refreshToken;

    @Override
    public Response intercept(Chain chain) throws IOException {
        sharedPreferencesUtils = new SharedPreferencesUtils(AppApplication.Companion.getBaseApplication(), "login");
        Request request = chain.request();

        if (!IsNetWorkUtils.isNetworkAvailable(AppApplication.Companion.getBaseApplication())) {
            ToastUtils.showShort("网络异常，请稍后再试");//getInstanc(MyAppliaction.mContext.getApplicationContext()).show("网络异常，请稍后再试");
        }

        String token = AppCache.getInstance().getToken();
//        accessToken = (String) sharedPreferencesUtils.getSharedPreference("token", null);
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        if (token != null && !token.equals("")) {//Authorization
            newRequest = request.newBuilder().header("Authorization", "Bearer " + token)//Bearer
                    .addHeader("Content-Type","text/html;charset=UTF-8")//application/json;charset=utf
                    .build();
        }else {
            newRequest = request.newBuilder().header("Authorization", "Bearer " +
                    encryptBASE64("user-android","jymj-app-android-secret"))//Bearer
                    .addHeader("Content-Type","text/html;charset=UTF-8")//application/json;charset=utf
                    .build();
        }
        // try the request
        if (newRequest != null /*&& !request.url().toString().contains("admin/oauth/token") */){
            originalResponse = chain.proceed(newRequest);
        } else {
            originalResponse = chain.proceed(request);
        }

        /**通过如下的办法曲线取到请求完成的数据
         *
         * 原本想通过  originalResponse.body().string()
         * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
         *
         * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
        ResponseBody responseBody = originalResponse.body();

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        /***************************************/
        int code = originalResponse.code();
        //取出本地的refreshToken
        synchronized (TokenInterceptor.class) {
            //根据和服务端的约定判断token过期
            if (code == 401) {
                //除了过期其他都跳到登录页面Access token expired
                ToastUtils.showShort("登录超时");
                LoginActivity.Companion.startAction(AppApplication.Companion.getBaseApplication());
                /*Intent intent1 = new Intent(MyAppliaction.mContext, LdrkActivity.class);
                MyAppliaction.mContext.startActivity(intent1);*/
                if (bodyString.contains("Access token expired")) {
                    String accessToken = getRefreshToken();
                    if (accessToken!=null){
                        Request newRequest1 = request.newBuilder().header("Authorization", "Bearer " + accessToken)
                                .build();
                        responseBody.close();
                        return chain.proceed(newRequest1);
                    }
                } else {
                    //清空登录消息
                    sharedPreferencesUtils.clear();
                    //跳登录界面
                    LoginActivity.Companion.startAction(AppApplication.Companion.getBaseApplication());
                    /*Intent intent = new Intent(MyAppliaction.mContext, LdrkActivity.class);
                    MyAppliaction.mContext.startActivity(intent);*/
                }
            }else if (code == 400 && bodyString.contains("Invalid refresh token")){
                jumpLanding();
            }else if (code == 500) {
                if (request.url().toString().contains("admin/oauth/token")) {
                    jumpLanding();
                }
                ToastUtils.showShort("服务器错误.");
            } else if (code == 478) {
                ToastUtils.showShort("验证码已过期，请重新登录.");
            } else if (code == 404) {
                ToastUtils.showShort("请求的资源并不存在.");
            } else if (code == 403) {
                ToastUtils.showShort("服务器错误.");
            }
        }
        return originalResponse;
    }

    private void jumpLanding() {
        if (!SingleOnClickUtil.isFastClick()) {
            //清空登录消息
            sharedPreferencesUtils.clear();
            //跳登录界面
            LoginActivity.Companion.startAction(AppApplication.Companion.getBaseApplication());
            /*Intent intent = new Intent(MyAppliaction.mContext, LdrkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MyAppliaction.mContext.startActivity(intent);*/
        }
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private String getRefreshToken() throws IOException {
        String refreshToken = (String) sharedPreferencesUtils.getSharedPreference("refresh_token", null);
        /*RefreshTokenModel refreshTokenModel = new RefreshTokenModel();
        // 通过获取token的接口，同步请求接口
        Call<RefreshTokenBean> refreshToken1 = refreshTokenModel.getRefreshToken(refreshToken);
        RefreshTokenBean body = refreshToken1.execute().body();
        String access_token = body.getAccess_token();
        String refresh_token = body.getRefresh_token();
        if (access_token != null && refresh_token != null) {
            sharedPreferencesUtils.put("access_token", access_token);
            sharedPreferencesUtils.put("refresh_token", refresh_token);
            //       sharedPreferencesUtils.put("user_id", newToken.getUserId());
        }*/
        return refreshToken;
    }
    /**
     * BASE64编码
     */
    public  String encryptBASE64(String username,String password) {
        byte[] key = (username+":"+password).getBytes();
        return  new String(Base64.encodeBase64(key));
    }

}
