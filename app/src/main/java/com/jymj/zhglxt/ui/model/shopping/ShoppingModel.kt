package com.jymj.zhglxt.ui.model.shopping

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.contract.shopping.ShoppingContract
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PutRequest
import com.setsuna.common.basebean.BaseScRespose
import org.json.JSONObject

/**
 * Created by setsuna on 18-3-23.
 */
class ShoppingModel : ShoppingContract.Model{
    override fun getMdseInfo(size: Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("size", size)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        var list = ArrayList<Int>()
        list.add(size)
        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }

    //修改购物车
    override fun getUpdateCart(mdseId: Long, quantity: Int, shopId: Long, stockId: Long,
                               shoppingCartId: Long): PutRequest<GwcList> {
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
        return OkGo.put<GwcList>(ApiConstants.SC_ADD_CART_URL).upJson(jsonObject)
    }

    //删除购物车
    override fun getDeleteCart(ids: String): DeleteRequest<BaseScRespose<String>> {
        val jsonObject = JSONObject()


        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.delete<BaseScRespose<String>>(ApiConstants.SC_ADD_CART_URL+"/"+ids)
    }

    override fun getCartPages(page:Int,size:Int): GetRequest<GwcList> {
        val jsonObject = HttpParams()
        jsonObject.put("size", size)
        jsonObject.put("page", page)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
//        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
        return OkGo.get<GwcList>(ApiConstants.SC_CART_PAGES_URL).params(jsonObject)
    }


}