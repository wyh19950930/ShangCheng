package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
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
interface FillMemberInfoContract {
    interface Model :BaseModel{

        fun addMembersUserInfo(name:String,mobile:String,idNumber:String,address:String,email:String)
                : PostRequest<BaseScRespose<MemberInfo>>
    }
    interface View:BaseView{
        fun returnMembersUserInfo(msg: MemberInfo)
    }

    companion object {
        abstract class Presenter:BasePresenter<View, Model>(){
            abstract fun addMembersUserInfo(name:String,mobile:String,idNumber:String,address:String,email:String)


        }
    }
}