package com.jymj.zhglxt.ui.model.me

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
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
class DzglModel : DzglContract.Model{

    override fun addressInfo(addressId:Long): GetRequest<AddAddressBean> {

        val jsonObject = HttpParams()
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<AddAddressBean>(ApiConstants.SC_ADD_ADDRESS_URL+"/"+addressId+"/info")
    }

    override fun addressList(): GetRequest<BaseScRespose<List<AddressInfo>>> {
        val jsonObject = HttpParams()
        jsonObject.put("","")
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.get<BaseScRespose<List<AddressInfo>>>(ApiConstants.SC_ADDRESS_LIST_URL)
    }

    override fun addAddress(name: String, mobile: String, region: String, detailedAddress: String,
                            status: Boolean): PostRequest<AddAddressBean> {

        val jsonObject = JSONObject()
        jsonObject.put("name",name)
        jsonObject.put("mobile",mobile)
        jsonObject.put("region",region)
        jsonObject.put("detailedAddress",detailedAddress)
        jsonObject.put("status",if (status)1 else 2)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.post<AddAddressBean>(ApiConstants.SC_ADD_ADDRESS_URL).upJson(jsonObject)
    }

    override fun updateAddress(name: String, mobile: String, region: String, detailedAddress: String,
                               status: Boolean, dzglIdType: Long): PutRequest<AddAddressBean> {

        val jsonObject = JSONObject()
        jsonObject.put("name",name)
        jsonObject.put("mobile",mobile)
        jsonObject.put("region",region)
        jsonObject.put("detailedAddress",detailedAddress)
        jsonObject.put("status",if (status)1 else 2)
        jsonObject.put("addressId",dzglIdType)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.put<AddAddressBean>(ApiConstants.SC_ADD_ADDRESS_URL).upJson(jsonObject)
    }
    override fun deleteAddress(ids: String): DeleteRequest<BaseScRespose<String>> {

        val jsonObject = JSONObject()
        jsonObject.put("ids",ids)
        val encrypt = AesEncryptUtils.encrypt(jsonObject.toString())//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
        var sss = "{\"requestData\":\"" + encrypt + "\"}"
        return OkGo.delete<BaseScRespose<String>>(ApiConstants.SC_ADD_ADDRESS_URL+"/"+ids)
    }
}