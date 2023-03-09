package com.jymj.zhglxt.api;

import com.jymj.zhglxt.login.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @package com.example.mygengdizhiliang.login
 * @fileName api
 * @date 2019/4/19:38
 * @name qzw
 */
public interface MyApi {
    @POST(ApiConstants.LOGIN_URL)
    Observable<LoginBean>
    getLogin(@Query("username") String name,@Query("password") String password,@Query("deviceId") String deviceId);

}
