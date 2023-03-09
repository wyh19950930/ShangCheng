package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.HomePageContract
import com.jymj.zhglxt.login.contract.ShouSearchContract
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
class ShowSearchModel : ShouSearchContract.Model{
    /**
     * name:商品名称
     * properties：排序方式   price/价格   location/距离  salesVolume/销量
     */
    override fun getMdseInfo(name:String,properties:String,lat:Double,lon:Double,page:Int,size:Int,direction:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("name",name)
        jsonObject.put("properties",properties)
        jsonObject.put("lat",lat)
        jsonObject.put("lon",lon)
        jsonObject.put("page",page)
        jsonObject.put("size",size)
        jsonObject.put("direction",direction)

        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }
    override fun getMdseInfo1(size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("size",size)

        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }
}