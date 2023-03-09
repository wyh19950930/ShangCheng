package com.jymj.zhglxt.ui.model.me

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.OrderCenterContract
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.me.DzglContract
import com.jymj.zhglxt.ui.contract.me.OrderCenterDetailsContract
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
class OrderCenterDetailsModel : OrderCenterDetailsContract.Model{
    override fun getOrderDetails(orderId: Int): GetRequest<BaseScRespose<MallOrderInfo>> {
        val jsonObject = HttpParams()
        jsonObject.put("orderId",orderId)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<MallOrderInfo>>(ApiConstants.SC_ORDER_INFO_URL+"/"+orderId+"/info")
    }




}