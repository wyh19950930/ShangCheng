package com.jymj.zhglxt.app;

import android.util.Log;

import com.jymj.zhglxt.util.AesEncryptUtils;
import com.setsuna.common.commonutils.EncodeUtils;

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
 * @Author: Mr.haozi
 * @CreateDate: 2022/4/7 14:13
 */
public class ResponseInterceptor implements Interceptor {
    private static final String TAG = "ResponseInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //返回request
        Request request = chain.request();
        //返回response
        Response response = chain.proceed(request);
        //isSuccessful () ; 如果代码在[200..300]中，则返回true，这意味着请求已成功接收、理解和接受。
        if (response.isSuccessful()) {
            //返回ResponseBody
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                try {
                    //获取bodyString
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer = source.buffer();
                    Charset charset = Charset.forName("UTF-8");
                    MediaType contentType = responseBody.contentType();
                    if (contentType != null) {
                        charset = contentType.charset(charset);
                    }
                    String bodyString = buffer.clone().readString(charset);
                    //我这里是base64解码  具体情况自己定义
                    //base64解码
                    String responseData = null;
                    try {
                        responseData = AesEncryptUtils.decrypt(bodyString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //生成新的ResponseBody
                    ResponseBody newResponseBody = ResponseBody.create(contentType, responseData.trim());
                    //response
                    response = response.newBuilder().body(newResponseBody).build();
//                    Log.i(TAG, "解密后数据: " + responseData);

                } catch (IOException e) {
                    //如果发生异常直接返回
                    e.printStackTrace();
                    return response;
                }
            } else {
                Log.i(TAG, "onHttpResultResponse: 响应体为空");
            }
        }
        return response;
    }

}
