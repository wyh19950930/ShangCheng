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
interface RetreatContract {
    interface Model :BaseModel{

        fun getMdseInfo(size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>>
        fun getMembersUserInfo(userId:Int): GetRequest<BaseScRespose<MemberInfo>>
        fun addShoppingCart(mdseId:Long,quantity:Int,shopId:Long,stockId:Long,shoppingCartId:Long):
                PostRequest<BaseScRespose<String>>
    }
    interface View:BaseView{
        fun returnMdseInfo(user: List<MdseInfo>)
        fun returnAddShoppingCart(msg: String)
        fun returnMembersUserInfo(msg: MemberInfo)
    }

    companion object {
        abstract class Presenter:BasePresenter<View, Model>(){
            abstract fun getMdseInfo(size:Int)//typeId（ 5热门产品 6为您优选）groupId（14卡产品）
            abstract fun addShoppingCart(mdseId:Long,quantity:Int,shopId:Long,stockId:Long,shoppingCartId:Long)
            abstract fun getMembersUserInfo(userId:Int)


        }
    }
}