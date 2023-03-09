package com.jymj.zhglxt.ui.presenter.shopping

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.*
import com.jymj.zhglxt.ui.bean.homepage.AddressInfo
import com.jymj.zhglxt.ui.bean.homepage.GwcList
import com.jymj.zhglxt.ui.bean.homepage.OrderDTO
import com.jymj.zhglxt.ui.bean.me.MallOrderInfo
import com.jymj.zhglxt.ui.contract.shopping.DdqrContract
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */

class DdqrPresenter : DdqrContract.Companion.Presenter() {
    override fun getCreateOrder(orderDTO: OrderDTO) {
        mModel.getCreateOrder(orderDTO).execute(object : BaseNet<BaseScRespose<MallOrderInfo>>() {
            override fun onStart(request: Request<BaseScRespose<MallOrderInfo>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<MallOrderInfo>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                mView.returnCreateOrder(body.data)
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

            override fun onError(response: Response<BaseScRespose<MallOrderInfo>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun addressList() {
        mModel.addressList().execute(object : BaseNet<BaseScRespose<List<AddressInfo>>>() {
            override fun onStart(request: Request<BaseScRespose<List<AddressInfo>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<List<AddressInfo>>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.msg!=null){
                                mView.returnAddressList(body.data)
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

            override fun onError(response: Response<BaseScRespose<List<AddressInfo>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    //删除购物车
    override fun getDeleteCart(ids: String) {
        mModel.getDeleteCart(ids).execute(object : BaseNet<BaseScRespose<String>>() {
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
                                mView.returnDeleteCart(body.msg)
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

            override fun onError(response: Response<BaseScRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    }

