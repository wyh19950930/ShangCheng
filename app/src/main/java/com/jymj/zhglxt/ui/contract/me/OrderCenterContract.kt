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
import com.lzy.okgo.request.PutRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface OrderCenterContract {
    interface Model :BaseModel{

        fun getOrderPages(str:String,mdseName:String,page:Int,limit:Int): GetRequest<BaseScRespose<PageVO<MallOrderInfo>>>
        fun deleteOrderPages(ids:String): DeleteRequest<BaseScRespose<String>>//删除订单
        fun putOrderPages(ids:String,type:String): PutRequest<BaseScRespose<String>>//更新订单
    }
    interface View:BaseView{
        fun returnOrderPages(message: List<MallOrderInfo>,type:String)
        fun returnDeleteOrderPages(message:String)
        fun returnPutOrderPages(message:String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getOrderPages(str:String,mdseName:String,page:Int,limit:Int)
            abstract fun deleteOrderPages(ids:String)
            abstract fun putOrderPages(ids:String,type:String)
        }
    }
}