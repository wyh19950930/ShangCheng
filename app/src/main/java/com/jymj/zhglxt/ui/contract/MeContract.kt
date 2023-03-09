package com.jymj.zhglxt.ui.contract

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.GetRequest
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface MeContract {
    interface Model :BaseModel{
        fun getOrderPages(str:String,page:Int,limit:Int): GetRequest<BaseScRespose<PageVO<MallOrderInfo>>>
    }
    interface View:BaseView{
        fun returnOrderPages(message: List<MallOrderInfo>,type:String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getOrderPages(str:String,page:Int,limit:Int)
        }
    }
}