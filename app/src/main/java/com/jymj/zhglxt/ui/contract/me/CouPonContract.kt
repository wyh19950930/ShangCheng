package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.login.bean.User
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface CouPonContract {
    interface Model :BaseModel{
        fun login(uName:String,uPwd:String,deviceId:String): HttpParams
        fun changeUser(username:String,password:String,newPassword:String,deviceCode:String):PostRequest<BaseRespose<String>>
        fun getWclCount(): PostRequest<BaseRespose<Int>>
    }
    interface View:BaseView{
        fun returnUser(user: User)
        fun changeUser()
        fun changeUser(msg:String)
        fun returnWclCount(msg: Int)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getUserRequest(uName:String,uPwd:String,deviceId:String)
            abstract fun changeUser(username:String,password:String,newPassword:String,deviceCode:String)
            abstract fun getWclCount()
        }
    }
}