package com.jymj.zhglxt.login.contract

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
interface CommodityWriteOffContract {
    interface Model :BaseModel{

        fun getOrderNo(no:String): GetRequest<BaseScRespose<MallOrderInfo>>
        fun getOrderMdse(mdseId:Long,orderId:Long,shopId:Long,stockId:Long,quantity:Int): PostRequest<BaseScRespose<String>>
    }
    interface View:BaseView{
        fun returnOrderNo(message: MallOrderInfo)
        fun returnOrderMdse(message: String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getOrderNo(no:String)
            abstract fun getOrderMdse(mdseId:Long,orderId:Long,shopId:Long,stockId:Long,quantity:Int)
        }
    }
}