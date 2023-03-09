package com.jymj.zhglxt.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.api.ApiConstants.IS_ENCRYPT
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.PostRequest
import com.lzy.okgo.request.PutRequest
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseNet.JsonCallBack
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject

object OkGoUtil {
    /** put请求  */
    /**
     * url:请求接口
     * jsonObject:请求参数
     * tclass:请求数据回调实体
     * baseNet:回调接口
     */
    fun <T> putBody(url: String,jsonObject: JSONObject): PostRequest<String> {
        var parameter = encrypt(jsonObject.toString())
        return OkGo.post<String>(url).upJson(parameter)
    }
    fun <T> putBodyString(url: String,jsonObject: String): PostRequest<String> {
        var parameter = encrypt(jsonObject.toString())
        return OkGo.post<String>(url).upJson(parameter)
    }
    fun <T> put(url: String,jsonObject: JSONObject/*, tclass:Class<T>*/,baseNet:OnOKHttp<T>) {
        putBody<String>(url,jsonObject).execute(object :
                BaseNet<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                baseNet.onStart()
            }
            override fun onSuccess(response: Response<String>?) {
                if (response!=null){
                    if (response.body()!=null){
                        var decrypt = response.body();
                        if (IS_ENCRYPT){//如果需要解密传递这个参数
                            decrypt = AesEncryptUtils.decrypt(decrypt)
                        }
                        var qiTaFreeList:T = Gson().fromJson(decrypt, object: TypeToken<T>(){}.getType());
//                        var json:T = Gson().fromJson(decrypt, tclass)
                        baseNet.onSuccess(qiTaFreeList)
                    }else{
                    }
                }else{
                }
            }

            override fun onFinish() {
                super.onFinish()
                baseNet?.onFinish()//请求结束
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    if (response?.exception?.message!=null){
                        baseNet?.onError(response?.exception?.message)
                    }
                }else{
//                    baseNet?.onError(response?.exception?.message)
                }
            }

        })
    }
    fun <T> put(url: String,string: String, tclass:Class<T>,baseNet:OnOKHttp<T>) {
        putBodyString<String>(url,string).execute(object :
                JsonCallBack<String>(){
            override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                baseNet.onStart()
            }
            override fun onSuccess(response: Response<String>?) {
                var body = response?.body();
                    if (body!=null){
                        baseNet.onSuccess(decode<T>(body,tclass))
                    }else{
                        baseNet?.onError("数据为空")
                    }
            }
            override fun onFinish() {
                super.onFinish()
                baseNet?.onFinish()//请求结束
            }
            override fun onError(response: Response<String>?) {
                super.onError(response)
                if (response?.exception!=null){
                    if (response?.exception?.message!=null){
                        baseNet?.onError(response?.exception?.message)
                    }
                }else{
//                    baseNet?.onError(response?.exception?.message)
                }
            }

        })
    }
    private fun encrypt(parameter :String):String{//加密
        var ssss = parameter
        if (IS_ENCRYPT){
            val encrypt = AesEncryptUtils.encrypt(parameter)//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
            ssss = "{\"requestData\":\"" + encrypt + "\"}"
        }
        return ssss
    }
    private fun <T>decode(parameter :String,tclass:Class<T>):T{//解密
        var ssss = parameter
        if (IS_ENCRYPT){//如果需要解密传递这个参数
            ssss = AesEncryptUtils.decrypt(parameter)
        }
        return Gson().fromJson(ssss, tclass)
    }
/*
    fun put(urlFramenewJcxx: String, jsonObject: JSONObject, java: Class<BaseRespose<FrameJcxxBean.DataBean>>, onOKHttp: OnOKHttp<BaseRespose<FrameJcxxBean.DataBean>>) {

    }*/

    interface OnOKHttp<T>{
        fun onStart();
        fun onSuccess(response: T);
        fun onFinish();
        fun onError(response: String?);


    }

}