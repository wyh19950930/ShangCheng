package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.HomePageContract
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.util.AesEncryptUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose
import org.json.JSONObject
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by setsuna on 18-3-23.
 */
class HomePageModel : HomePageContract.Model{
    override fun getMdseInfo(size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("size",size)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        var list = ArrayList<Int>()
        list.add(size)
        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }
}