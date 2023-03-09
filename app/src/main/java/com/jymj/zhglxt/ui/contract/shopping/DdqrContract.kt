package com.jymj.zhglxt.ui.contract.shopping

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.homepage.OrderDTO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.DeleteRequest
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface DdqrContract {
    interface Model :BaseModel{
        fun getCreateOrder(orderDTO: OrderDTO): PostRequest<BaseScRespose<MallOrderInfo>>
        fun addressList(): GetRequest<BaseScRespose<List<AddressInfo>>>
        fun getDeleteCart(ids:String): DeleteRequest<BaseScRespose<String>>
    }
    interface View:BaseView{
        fun returnCreateOrder(message: MallOrderInfo)
        fun returnAddressList(message:List<AddressInfo>)
        fun returnDeleteCart(msg:String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getCreateOrder(orderDTO: OrderDTO)
            abstract fun addressList()
            abstract fun getDeleteCart(ids:String)
        }
    }
}