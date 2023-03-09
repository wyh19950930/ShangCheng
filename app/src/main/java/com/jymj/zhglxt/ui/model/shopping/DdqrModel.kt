package com.jymj.zhglxt.ui.model.shopping

import com.google.gson.Gson
import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.homepage.OrderDTO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.shopping.DdqrContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseScRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class DdqrModel : DdqrContract.Model{
    override fun getCreateOrder(orderDTO: OrderDTO): PostRequest<BaseScRespose<MallOrderInfo>> {
        val gson = Gson()
        val entity = gson.toJson(orderDTO)
        val encrypt = AesEncryptUtils.encrypt(entity.toString())
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseScRespose<MallOrderInfo>>(ApiConstants.SC_order_URL).upJson(entity)
    }
    override fun addressList(): GetRequest<BaseScRespose<List<AddressInfo>>> {
        val jsonObject = HttpParams()
        jsonObject.put("","")
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<List<AddressInfo>>>(ApiConstants.SC_ADDRESS_LIST_URL)
    }
    //删除购物车
    override fun getDeleteCart(ids: String): DeleteRequest<BaseScRespose<String>> {
        val jsonObject = JSONObject()


        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.delete<BaseScRespose<String>>(ApiConstants.SC_ADD_CART_URL+"/"+ids)
    }

}