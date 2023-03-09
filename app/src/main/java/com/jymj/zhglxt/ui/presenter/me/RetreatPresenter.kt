package com.jymj.zhglxt.ui.presenter.me

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.CommodityDetailsContract
import com.jymj.zhglxt.login.contract.RetreatContract
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
import com.jymj.zhglxt.ui.bean.homepage.MemberInfo
import com.jymj.zhglxt.ui.bean.homepage.PageVO
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */

class RetreatPresenter : RetreatContract.Companion.Presenter() {
    //会员信息
    override fun getMembersUserInfo(userId: Int) {
        mModel.getMembersUserInfo(userId).execute(object : BaseNet<BaseScRespose<MemberInfo>>() {
            override fun onStart(request: Request<BaseScRespose<MemberInfo>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<MemberInfo>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                mView.returnMembersUserInfo(body.data)
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

            override fun onError(response: Response<BaseScRespose<MemberInfo>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    //添加购物车
    override fun addShoppingCart(mdseId: Long, quantity: Int, shopId: Long, stockId: Long, shoppingCartId: Long) {
        mModel.addShoppingCart(mdseId,quantity,shopId,stockId,shoppingCartId).execute(object : BaseNet<BaseScRespose<String>>() {
            override fun onStart(request: Request<BaseScRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<String>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.msg!=null){
                                mView.returnAddShoppingCart(body.msg)
                            }

                        }
                        "500" -> {
                            mView.showErrorTip(body.msg)
                        }
                        else -> {
                            mView.showErrorTip("${body.code}")//json.msg
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

            override fun onError(response: Response<BaseScRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getMdseInfo(size:Int) {
        mModel.getMdseInfo(size).execute(object : BaseNet<BaseScRespose<PageVO<MdseInfo>>>() {
            override fun onStart(request: Request<BaseScRespose<PageVO<MdseInfo>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<PageVO<MdseInfo>>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                mView.returnMdseInfo(body.data.content)
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

            override fun onError(response: Response<BaseScRespose<PageVO<MdseInfo>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
}