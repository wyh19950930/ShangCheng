package com.jymj.zhglxt.ui.model.homepage

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.HomePageContract
import com.jymj.zhglxt.login.contract.SpflContract
import com.jymj.zhglxt.ui.bean.homepage.GroupListBean
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
class SpflModel : SpflContract.Model{
    override fun getMdseInfo(groupId:Int,page:Int,size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>> {

        val jsonObject = HttpParams()
        jsonObject.put("groupId",groupId)
        jsonObject.put("page",page)
        jsonObject.put("size",size)

        return OkGo.get<BaseScRespose<PageVO<MdseInfo>>>(ApiConstants.SC_MDSE_PAGES_URL).params(jsonObject)
    }
    override fun getGroupList(mallId:Int): GetRequest<BaseScRespose<ArrayList<GroupListBean>>> {
        val jsonObject = HttpParams()
        jsonObject.put("mallId",27)
        return OkGo.get<BaseScRespose<ArrayList<GroupListBean>>>(ApiConstants.SC_MDSE_LISTS_URL).params(jsonObject)
    }
}