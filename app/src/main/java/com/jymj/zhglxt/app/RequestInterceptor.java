package com.jymj.zhglxt.app;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.jymj.zhglxt.util.AesEncryptUtils;
import com.setsuna.common.commonutils.EncodeUtils;
import com.setsuna.common.commonutils.EncryptUtils;
import com.setsuna.common.commonutils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import androidx.annotation.RequiresApi;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.jymj.zhglxt.api.ApiConstants.IS_ENCRYPT;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/4/7 9:45
 */
/**
 * Time:2020/10/27
 * Author:xin.chen
 * Description:加解密拦截器
 **/
public  class RequestInterceptor implements Interceptor {

    private static final String TAG = "ResponseInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //返回request
        Request request = chain.request();
        //字符集
        Charset charset = Charset.forName("UTF-8");
        //返回url
        HttpUrl url = request.url();
        //http://192.168.3.138:8089/interface/login   //@get @delete 时候需要拼接在请求地址后面  ?userName=xiaoming&userPassword=12345
        String scheme = url.scheme();//  http  https
        String host = url.host();//   192.168.3.138
        int port = url.port();//   8089
        String path = url.encodedPath();//  /interface/login
        //String query = url.encodedQuery();//  userName=xiaoming&userPassword=12345
        String method = request.method().toLowerCase().trim();//请求方式例如：get delete put post
        String originalPath = scheme + "://" + host + ":" + port + path;

        //如果请求的不是服务端的接口，不加密  可以根据个人需要调整粒度
        /*  这是示例：    Api.APP_DOMAIN   就是自己定义baseUrl
        public interface Api {
            String APP_DOMAIN = "http://192.168.3.138:8089";
            int RequestSuccess = 10000;
        }*/

        //如果请求方式是Get或者Delete，此时请求数据是拼接在请求地址后面的
        // @get @delete 时候需要拼接在请求地址后面  ?userName=xiaoming&userPassword=12345
        if (method.equals("get") || method.equals("delete")) {
            String query = url.encodedQuery();// query 是获取到的拼接字符串 类似 userName=xiaoming&userPassword=12345
            /*如果有请求数据 则加密*/
            /*if (query != null) {
                String encryptQuery = "对 query 进行加密 规则自己定义";
                //拼接加密后的url，参数字段自己跟后台商量，这里我用param，后台拿到数据先对param进行解密，解密后的数据就是请求的数据
                String newUrl = originalPath + "?param=$encryptqueryparamNames";
                //构建新的请求
                request = request.newBuilder().url(newUrl).build();
            }*/
        } else {
            //不是Get和Delete请求时，则请求数据在请求体中
            RequestBody requestBody = request.body();
            //判断类型
            MediaType contentType = requestBody.contentType();

            if (contentType != null) {
                charset = contentType.charset(charset);
                /*如果是二进制上传  则不进行加密*/
                if (contentType.type().toLowerCase().equals("multipart")) {
                    return chain.proceed(request);
                }
            }


            // 获取请求的数据
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String requestData = URLDecoder.decode(buffer.readString(charset).trim(), "utf-8");
            String encryptData ="";
            //这里调用加密的方法，自行修改
            /*String base64Encode = EncodeUtils.base64Encode2String(requestData.getBytes());
            String encryptHmacSHA256 = EncryptUtils.encryptHmacSHA256ToString(base64Encode, AppConstants.HMACSHA_256);
            String encryptData = base64Encode + "." + encryptHmacSHA256;*/
            if (IS_ENCRYPT){
                if (requestData.contains("requestData")){
                    encryptData = requestData;
                }else {
                    try {
                        String encrypt = AesEncryptUtils.encrypt(requestData);
                        encryptData = "{\"requestData\":\"" + encrypt + "\"}";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                encryptData = encryptData.replace(" ","+");
            }else {
                if (requestData.contains("requestData")){
                    requestData = requestData.replace(" ","+");
                    try {
                        String replace = requestData.replace("{\"requestData\":\"", "");
                        String substring = replace.substring(0, replace.length() - 2);
                        String encrypt = AesEncryptUtils.decrypt(substring);
                        encryptData = encrypt;//"{\"requestData\":\"" + encrypt + "\"}";
//                        ToastUtils.showShort(encrypt);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    encryptData = requestData;
                }
            }


            //构建新的请求体
            RequestBody newRequestBody = RequestBody.create(contentType, encryptData);
            //构建新的requestBuilder
            Request.Builder newRequestBuilder = request.newBuilder();
            //根据请求方式构建相应的请求
            switch (method) {
                case "post":
                    newRequestBuilder.post(newRequestBody);
                    break;
                case "put":
                    newRequestBuilder.put(newRequestBody);
                    break;
                default:
                    break;
            }
            request = newRequestBuilder.build();

        }

        return chain.proceed(request);
    }

}
