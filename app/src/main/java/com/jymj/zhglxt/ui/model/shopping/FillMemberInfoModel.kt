package com.jymj.zhglxt.login.model

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.contract.CommodityDetailsContract
import com.jymj.zhglxt.login.contract.FillMemberInfoContract
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
class FillMemberInfoModel : FillMemberInfoContract.Model {


    //会员信息
    override fun addMembersUserInfo(name: String, mobile: String, idNumber: String, address: String, email: String)
            : PostRequest<BaseScRespose<MemberInfo>> {
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("mobile", mobile)
        jsonObject.put("idNumber", idNumber)
        jsonObject.put("address", address)
        jsonObject.put("email", email)

        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<BaseScRespose<MemberInfo>>(ApiConstants.SC_MEMBERS_URL).upJson(jsonObject)
    }



}