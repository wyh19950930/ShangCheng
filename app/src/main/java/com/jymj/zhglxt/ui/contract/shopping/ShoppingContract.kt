package com.jymj.zhglxt.ui.contract.shopping

import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
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
interface ShoppingContract {
    interface Model :BaseModel{
        fun getMdseInfo(size:Int): GetRequest<BaseScRespose<PageVO<MdseInfo>>>
        fun getCartPages(page:Int,size:Int): GetRequest<GwcList>
        fun getDeleteCart(ids:String): DeleteRequest<BaseScRespose<String>>
        fun getUpdateCart(mdseId:Long,quantity:Int,shopId:Long,stockId:Long,shoppingCartId:Long):
                PutRequest<GwcList>
    }
    interface View:BaseView{
        fun returnMdseInfo(user: List<MdseInfo>)
        fun returnCartPages(msg: List<GwcList.DataBean.ContentBean>)
        fun returnDeleteCart(msg:String)
        fun returnUpdateCart(msg:String)
    }

    companion object {
        abstract class Presenter:BasePresenter<View,Model>(){
            abstract fun getMdseInfo(size:Int)//typeId（ 5热门产品 6为您优选）groupId（14卡产品）
            abstract fun getCartPages(page:Int,size:Int)
            abstract fun getDeleteCart(ids:String)
            abstract fun getUpdateCart(mdseId:Long,quantity:Int,shopId:Long,stockId:Long,shoppingCartId:Long)
        }
    }
}