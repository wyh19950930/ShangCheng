package com.jymj.zhglxt.ui.model.me

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.CommodityWriteOffContract
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
class CommodityWriteOffModel : CommodityWriteOffContract.Model{
    override fun getOrderMdse(mdseId: Long, orderId: Long, shopId: Long, stockId: Long, quantity: Int): PostRequest<BaseScRespose<String>> {
        val jsonObject = JSONObject()
        jsonObject.put("mdseId",mdseId)
        jsonObject.put("orderId",orderId)
        jsonObject.put("shopId",shopId)
        jsonObject.put("stockId",stockId)
        jsonObject.put("quantity",quantity)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseScRespose<String>>(ApiConstants.SC_VERIFICATION_ORDER_MDSE_URL).upJson(jsonObject)
    }

    override fun getOrderNo(no: String): GetRequest<BaseScRespose<MallOrderInfo>> {
        val jsonObject = HttpParams()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<MallOrderInfo>>(ApiConstants.SC_ORDER_NO_URL+"/"+no+"/info")
    }
}