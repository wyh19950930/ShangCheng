package com.jymj.zhglxt.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.login.TokenInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @package com.example.mygengdizhiliang.utils
 * @fileName NetUtil
 * @date 2019/4/18:51
 * @name qzw
 */
public class NetUtil {
    private String base_ULR;
    private final Retrofit mRetrofit;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
    public NetUtil() {
        mRetrofit = buildRetrofit();
    }
    /*ClearableCookieJar cookieJar =
            new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppApplication.baseApplication));
*/
    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
//                .cookieJar(cookieJar)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .build();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .client(buildOkHttpClient())
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())// 增加返回值为String的支持
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
