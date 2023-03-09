package com.jymj.zhglxt.ui.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.MeContract
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
class MeModel : MeContract.Model{

    override fun getOrderPages(str:String,page:Int,limit:Int): GetRequest<BaseScRespose<PageVO<MallOrderInfo>>> {
        val jsonObject = HttpParams()
        jsonObject.put("orderStatus",str)
        jsonObject.put("page",page)
        jsonObject.put("size",limit)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<PageVO<MallOrderInfo>>>(ApiConstants.SC_ORDER_PAGES_URL).params(jsonObject)
    }

}