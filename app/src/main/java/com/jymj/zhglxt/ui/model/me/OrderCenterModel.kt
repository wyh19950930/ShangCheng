package com.jymj.zhglxt.ui.model.me

import com.google.gson.JsonObject
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.OrderCenterContract
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.me.DzglContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.lzy.okgo.request.PutRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class OrderCenterModel : OrderCenterContract.Model{
    override fun putOrderPages(ids: String, type: String): PutRequest<BaseScRespose<String>> {
        val jsonObject = JSONObject()
        jsonObject.put("orderId",ids)
        jsonObject.put("statusEnum",type)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.put<BaseScRespose<String>>(ApiConstants.SC_order_URL).upJson(jsonObject)
    }

    override fun deleteOrderPages(ids: String): DeleteRequest<BaseScRespose<String>> {
        val jsonObject = HttpParams()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.delete<BaseScRespose<String>>(ApiConstants.SC_order_URL+"/"+ids)
    }

    override fun getOrderPages(str:String,mdseName:String,page:Int,limit:Int): GetRequest<BaseScRespose<PageVO<MallOrderInfo>>> {
        val jsonObject = HttpParams()
        jsonObject.put("orderStatus",str)
        jsonObject.put("page",page)
        jsonObject.put("size",limit)
        jsonObject.put("mdseName",mdseName)
        jsonObject.put("direction",2)
        jsonObject.put("properties","orderId")
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<PageVO<MallOrderInfo>>>(ApiConstants.SC_ORDER_PAGES_URL).params(jsonObject)
    }


}