package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.*
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose

/**
 * Created by setsuna on 18-3-23.
 */

class CouPonPresenter : CouPonContract.Companion.Presenter() {
    //未处理
    override fun getWclCount() {
        mModel.getWclCount().execute(object : BaseNet<BaseRespose<Int>>() {
            override fun onStart(request: Request<BaseRespose<Int>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<Int>>?) {
                val body = response?.body()
//                val body1 = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.returnWclCount(body.data)
                        }
                        500 -> {
                            mView.showErrorTip("数据异常")
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                }
            }

            override fun onFinish() {
                super.onFinish()
//                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<Int>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })

    }
    override fun changeUser(username: String, password: String, newPassword: String, deviceCode: String) {
        mModel.changeUser(username, password, newPassword, deviceCode).execute(object : BaseNet<BaseRespose<String>>() {
            override fun onStart(request: Request<BaseRespose<String>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseRespose<String>>?) {
                val body = response?.body()
                if (body != null) {
                    when (body.code) {
                        0 -> {
                            mView.changeUser(body.msg!!)
                        }
                        else -> {
                            mView.showErrorTip(body.msg)
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
            }

            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }
    override fun getUserRequest(uName: String, uPwd: String, deviceId: String) {
        val params = mModel.login(uName, uPwd, deviceId)
        //LoginBean
        OkGo.post<LoginBean>(ApiConstants.LOGIN_URL).params(params).execute(object : BaseNet<LoginBean>() {
            override fun onStart(request: Request<LoginBean, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: Response<LoginBean>?) {
                val loginBean = response?.body()
                if (loginBean != null) {
                    when (loginBean.code) {
                        0 -> {
                            AppCache.getInstance().token=loginBean.token
                            val user = loginBean.user
                            if (user != null) {
                                mView.returnUser(user)
                                //queryByCunEnviorSupvs
                                var sss="{}"
                                /*OkGo.post<String>(ApiConstants.queryByCunEnviorSupvs).upJson(sss).execute(object : BaseNet<String>(){
                                    override fun onSuccess(response: Response<String>?) {
                                        ToastUtils.showShort("")
                                    }
                                })*/
                            }
                        }
                        505 -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip(loginBean.msg)
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
            }
//java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<LoginBean>?) {
                super.onError(response)
                mView.showErrorTip("网络错误")
            }
        })
    }

}