package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.*
import com.jymj.zhglxt.ui.bean.homepage.GroupListBean
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

class SpflPresenter : SpflContract.Companion.Presenter() {
    override fun getMdseInfo(groupId:Int,page:Int,size:Int) {
        mModel.getMdseInfo(groupId,page,size).execute(object : BaseNet<BaseScRespose<PageVO<MdseInfo>>>() {
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
    //分组
    override fun getGroupList(mallId:Int) {
        mModel.getGroupList(mallId).execute(object : BaseNet<BaseScRespose<ArrayList<GroupListBean>>>() {
            override fun onStart(request: Request<BaseScRespose<ArrayList<GroupListBean>>, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
//                mView.showLoading("正在登录。。。")
            }
            override fun onSuccess(response: Response<BaseScRespose<ArrayList<GroupListBean>>>?) {
                val body = response?.body()
                if (body != null) {

                    when (body.code) {
                        "200" -> {
                            if (body.data!=null){
                                mView.returnGroupList(body.data)
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

            override fun onError(response: Response<BaseScRespose<ArrayList<GroupListBean>>>?) {
                super.onError(response)
                mView.showErrorTip(response?.exception?.message)
            }

        })
    }

}