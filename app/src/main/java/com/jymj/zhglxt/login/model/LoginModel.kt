package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.bean.OAuth2AccessToken
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.ui.bean.me.UserInfo
import com.jymj.zhglxt.ui.bean.user.ScUserEntity
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class LoginModel : LoginContract.Model{
    //商城用户信息
    override fun scUser(userId: Int): GetRequest<BaseScRespose<UserInfo>> {
        val jsonObject = JSONObject()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<UserInfo>>(ApiConstants.SC_USERS_URL+userId+"/info")
    }

    //商城登录
    override fun sclogin(uName: String, uPwd: String, grant_type: String): PostRequest<OAuth2AccessToken> {
        val jsonObject = HttpParams()//LOGIN_URL
        jsonObject.put("username", "13582305284")//user
        jsonObject.put("password", "admin")//admin
        jsonObject.put("grant_type", "password")
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<OAuth2AccessToken>(ApiConstants.LOGIN_URL).params(jsonObject)
    }


    override fun login(uName: String, uPwd: String, deviceId: String): PostRequest<LoginBean> {
        val jsonObject = JSONObject()//LOGIN_URL
        jsonObject.put("username", uName)
        jsonObject.put("password", uPwd)//84b8adbdf3963643
        jsonObject.put("deviceCode", "866174010481386")//deviceIddeviceId
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<LoginBean>(ApiConstants.LOGIN_URL).upJson(sss)
    }

    //注册接口
    override fun changeUser(username: String, password: String, newPassword: String, deviceCode: String): PostRequest<BaseRespose<String>> {

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("newPassword", newPassword)
        jsonObject.put("deviceCode", "866174010481386")//deviceCode
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseRespose<String>>(ApiConstants.MODIFYPWD_URL).upJson(sss)

        /*return OkGo.post<BaseRespose<String>>(ApiConstants.MODIFYPWD_URL)
                .params("username",username)
                .params("password",password)
                .params("newPassword",newPassword)
                .params("deviceCode",deviceCode)//deviceCode*/
    }
    //登陆接口

    /*override fun login(uName: String, uPwd: String,deviceId:String): HttpParams {

        val params= HttpParams()
        params.put("username", uName)
        params.put("password", uPwd)
        params.put("deviceCode", deviceId)//deviceId
        return params
    }*/

}