package com.jymj.zhglxt.login.presenter

import com.jymj.zhglxt.api.ApiConstants
import com.jymj.zhglxt.bean.KdQueryBean
import com.jymj.zhglxt.login.bean.BaseNet
import com.jymj.zhglxt.login.bean.LoginBean
import com.jymj.zhglxt.login.contract.*
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.basebean.BaseRespose
import org.json.JSONObject
import java.lang.Exception

/**
 * Created by setsuna on 18-3-23.
 */

class WlxqPresenter : WlxqContract.Companion.Presenter() {
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
    //快递查询
    override fun getKuaiDiQuery(type: String, postid: String) {
//        val params = mModel.login(uName, uPwd, deviceId)
        val jsonObject = HttpParams()
        jsonObject.put("type", type)//应该是快递公司名称
        jsonObject.put("postid", postid)//这个是应该是快递编号

        //https://github.com/kuaidi100-api
        //https://poll.kuaidi100.com/poll/query.do?type=JD&postid=0092198586855
        //http://www.kuaidi100.com/query?type=jingdong&postid=JD0092198586855

        //https://blog.csdn.net/weixin_43841577/article/details/89377811  //快递公司编码
        //http://www.kuaidi100.com/query?type=JD&postid=0092198586855
        //LoginBean  ApiConstants.KUAIDI_QUERY
        OkGo.post<KdQueryBean>(ApiConstants.KUAIDI_QUERY).params(jsonObject).execute(object : BaseNet<KdQueryBean>() {
            override fun onStart(request: Request<KdQueryBean, out Request<Any, Request<*, *>>>?) {
                super.onStart(request)
                mView.showLoading("正在登录。。。")
            }

            override fun onSuccess(response: Response<KdQueryBean>?) {
                val loginBean = response?.body()
                val loginBean1 = response?.body()
                /*if (loginBean != null) {
                    when (loginBean.code) {
                        0 -> {

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
                }*/
            }
            override fun onFinish() {
                super.onFinish()
                mView.stopLoading()
            }
            override fun onError(response: Response<KdQueryBean>?) {
                super.onError(response)
                try {
                    mView.showErrorTip(response?.exception!!.message)
                }catch (e: Exception){
                    mView.showErrorTip("接口报错")
                }
            }
        })
    }

}