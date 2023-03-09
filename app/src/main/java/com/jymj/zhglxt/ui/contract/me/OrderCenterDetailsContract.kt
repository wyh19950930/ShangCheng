package com.jymj.zhglxt.ui.contract.me

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.PageVO
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
interface OrderCenterDetailsContract {
    interface Model :BaseModel{

        fun getOrderDetails(orderId:Int): GetRequest<BaseScRespose<MallOrderInfo>>
    }
    interface View:BaseView{
        fun returnOrderDetails(message: MallOrderInfo)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getOrderDetails(orderId:Int)
        }
    }
}