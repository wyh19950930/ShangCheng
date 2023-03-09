package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.bean.OAuth2AccessToken
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.ui.bean.me.UserInfo
import com.jymj.zhglxt.ui.bean.user.ScUserEntity
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import com.setsuna.common.basebean.BaseScRespose

/**
 * Created by setsuna on 18-3-23.
 */
class LoginPresenter : LoginContract.Companion.Presenter() {
    override fun scUser(userId: Int) {
        mModel.scUser(userId).execute(object : BaseNet<BaseScRespose<UserInfo>>() {
            override fun onStart(request: Request<BaseScRespose<UserInfo>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<UserInfo>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                    mView.returnScUser(body.data)
                            }

                        }
                        "500" -> {
                            mView.showErrorTip(body.msg)
                            mView.changeUser()
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

            override fun onError(response: Response<BaseScRespose<UserInfo>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
                mView.stopLoading()
            }

        })
    }

    override fun sclogin(uName: String, uPwd: String, grant_type: String) {
        mModel.sclogin(uName, uPwd, grant_type).execute(object : BaseNet<OAuth2AccessToken>() {//LoginBean
        override fun onStart(request: Request<OAuth2AccessToken, out Request<Any, Request<*, *>>>?) {
            super.onStart(request)
            mView.showLoading("正在登录。。。")
        }

            override fun onSuccess(response: Response<OAuth2AccessToken>?) {
                val loginBean = response?.body()
                if (loginBean != null) {

                    when (loginBean.code) {
                        "200" -> {
                            if (loginBean.data!=null){
                                AppCache.getInstance().token=loginBean.data.access_token
                                val user = loginBean.data
                                if (user != null) {
                                    mView.returnsclogin(user)
                                }
                            }

                        }
                        "500" -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip("${loginBean.code}")//json.msg
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
                mView.stopLoading()
            }
            //java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<OAuth2AccessToken>?) {
                super.onError(response)
                mView.showErrorTip(response!!.exception.message)//java.net.ConnectException: 网络连接失败,请连接网络!!
                mView.stopLoading()
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
                mView.changeUser(response?.body()?.msg!!)
            }

            override fun onFinish() {
                super.onFinish()
                mView.showLoading("")
            }

            override fun onError(response: Response<BaseRespose<String>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

    override fun getUserRequest(uName: String, uPwd: String, deviceId: String) {
        mModel.login(uName, uPwd, deviceId).execute(object : BaseNet<LoginBean>() {//LoginBean
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
                           }
                        }
                        505 -> {
                            mView.showErrorTip(loginBean.msg)
                            mView.changeUser()
                        }
                        else -> {
                            mView.showErrorTip("${loginBean.code}")//json.msg
                        }
                    }
                } else {
                    mView.showErrorTip("网络错误")
                }
                mView.stopLoading()
            }
            //java.net.UnknownServiceException: CLEARTEXT communication to 192.168.3.14 not permitted by network security policy
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<LoginBean>?) {
                super.onError(response)
                mView.showErrorTip(response!!.exception.message)//java.net.ConnectException: 网络连接失败,请连接网络!!
                mView.stopLoading()
            }

        })

    }

}