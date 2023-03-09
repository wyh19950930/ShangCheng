package com.jymj.zhglxt.login.contract

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
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
interface HomePageContract {
    interface Model :BaseModel{
        fun getMdseInfo(size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>>
    }
    interface View:BaseView{
        fun returnMdseInfo(user: List<MdseInfo>)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getMdseInfo(size:Int)//typeId（ 5热门产品 6为您优选）groupId（14卡产品）
        }
    }
}