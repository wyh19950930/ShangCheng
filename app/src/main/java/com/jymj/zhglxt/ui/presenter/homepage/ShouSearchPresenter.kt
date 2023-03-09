package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.HomePageContract
import com.jymj.zhglxt.login.contract.ShouSearchContract
import com.jymj.zhglxt.ui.bean.homepage.MdseInfo
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

class ShouSearchPresenter : ShouSearchContract.Companion.Presenter() {

        override fun getMdseInfo(name:String,properties:String,lat:Double,lon:Double,page:Int,size:Int,direction:Int) {
            mModel.getMdseInfo(name,properties,lat,lon,page,size,direction).execute(object : BaseNet<BaseScRespose<PageVO<MdseInfo>>>() {
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
        override fun getMdseInfo1(size:Int) {
            mModel.getMdseInfo1(size).execute(object : BaseNet<BaseScRespose<PageVO<MdseInfo>>>() {
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
                                    mView.returnMdseInfo1(body.data.content)
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