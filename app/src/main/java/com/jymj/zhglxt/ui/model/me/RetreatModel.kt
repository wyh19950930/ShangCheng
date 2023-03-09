package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.CommodityDetailsContract
import com.jymj.zhglxt.login.contract.RetreatContract
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
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
class RetreatModel : RetreatContract.Model {
    //会员信息
    override fun getMembersUserInfo(userId: Int): GetRequest<BaseScRespose<MemberInfo>> {
        val jsonObject = HttpParams()

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<MemberInfo>>(ApiConstants.SC_MEMBERS_USER_URL+"/"+userId+"/info")
    }

    override fun addShoppingCart(mdseId:Long,quantity:Int,shopId:Long,stockId:Long,shoppingCartId:Long):
            PostRequest<BaseScRespose<String>> {
        val jsonObject = JSONObject()
        jsonObject.put("mdseId", mdseId)
        jsonObject.put("quantity", quantity)
        jsonObject.put("shopId", shopId)
        jsonObject.put("stockId", stockId)
        if (shoppingCartId!=0L){
            jsonObject.put("shoppingCartId", shoppingCartId)
        }


        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseScRespose<String>>(ApiConstants.SC_ADD_CART_URL).upJson(jsonObject)
    }

    override fun getMdseInfo(size: Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("size", size)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        var list = ArrayList<Int>()
        list.add(size)
        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }

}