package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.bean.OAuth2AccessToken
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.me.UserInfo
import com.jymj.zhglxt.ui.bean.user.ScUserEntity
import com.lzy.okgo.request.GetRequest
import com.setsuna.common.base.BaseModel
import com.setsuna.common.base.BasePresenter
import com.setsuna.common.base.BaseView
import com.lzy.okgo.request.PostRequest
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
interface LoginContract {
    interface Model :BaseModel{
//        fun login(uName:String,uPwd:String,deviceId:String): HttpParams
        fun sclogin(uName:String,uPwd:String,grant_type:String):PostRequest<OAuth2AccessToken>
        fun scUser(userId:Int): GetRequest<BaseScRespose<UserInfo>>



        fun login(uName:String,uPwd:String,deviceId:String):PostRequest<LoginBean>
        fun changeUser(username:String,password:String,newPassword:String,deviceCode:String):PostRequest<BaseRespose<String>>
    }
    interface View:BaseView{
        fun returnsclogin(user: OAuth2AccessToken.DataBean)
        fun returnScUser(user: UserInfo)

        fun returnUser(user: User)
        fun changeUser()
        fun changeUser(msg:String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun sclogin(uName:String,uPwd:String,grant_type:String)//商城登录
            abstract fun scUser(userId:Int)//商城用户信息


            abstract fun getUserRequest(uName:String,uPwd:String,deviceId:String)
            abstract fun changeUser(username:String,password:String,newPassword:String,deviceCode:String)
        }
    }
}