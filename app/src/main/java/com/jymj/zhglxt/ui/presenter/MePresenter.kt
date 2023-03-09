package com.jymj.zhglxt.ui.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.MeContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */

class MePresenter : MeContract.Companion.Presenter() {
    //订单各状态列表
    override fun getOrderPages(str:String,page:Int,limit:Int) {
        mModel.getOrderPages(str,page,limit).execute(object : BaseNet<BaseScRespose<PageVO<MallOrderInfo>>>() {
            override fun onStart(request: Request<BaseScRespose<PageVO<MallOrderInfo>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<PageVO<MallOrderInfo>>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                mView.returnOrderPages(body.data.content,str)
                            }

                        }
                        "500" -> {
                            mView.showErrorTip(body.msg)
                        }
                        else -> {
                            mView.showErrorTip("${body.msg}")//json.msg
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
                mView.stopLoading()
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseScRespose<PageVO<MallOrderInfo>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
}