package com.jymj.zhglxt.ui.contract.me

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.AddAddressBean
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.lzy.okgo.request.PutRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface DzglContract {
    interface Model :BaseModel{
        fun addressList(): GetRequest<BaseScRespose<List<AddressInfo>>>
        fun addAddress(name:String,mobile:String,region:String,detailedAddress:String,status:Boolean):PostRequest<AddAddressBean>
        fun updateAddress(name:String,mobile:String,region:String,detailedAddress:String,status:Boolean,dzglIdType:Long): PutRequest<AddAddressBean>
        fun deleteAddress(ids:String): DeleteRequest<BaseScRespose<String>>
        fun addressInfo(addressId:Long):GetRequest<AddAddressBean>
    }
    interface View:BaseView{
        fun returnAddressList(message:List<AddressInfo>)
        fun returnAddAddress(message:String)
        fun returnUpdateAddress(message:String)
        fun returnDeleteAddress(message:String)
        fun returnAddressInfo(message:AddAddressBean)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun addressList()
            abstract fun addAddress(name:String,mobile:String,region:String,detailedAddress:String,status:Boolean)
            abstract fun updateAddress(name:String,mobile:String,region:String,detailedAddress:String,status:Boolean,dzglIdType:Long)
            abstract fun deleteAddress(ids:String)
            abstract fun addressInfo(addressId:Long)
        }
    }
}