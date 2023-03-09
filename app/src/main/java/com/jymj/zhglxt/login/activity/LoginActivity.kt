package com.jymj.zhglxt.login.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import com.jymj.zhglxt.R
import com.jymj.zhglxt.api.AppMenus
import com.jymj.zhglxt.login.bean.User
import com.jymj.zhglxt.login.bean.UserListBean
import com.jymj.zhglxt.login.contract.LoginContract
import com.jymj.zhglxt.login.model.LoginModel
import com.jymj.zhglxt.login.presenter.LoginPresenter
import com.jymj.zhglxt.ui.activity.FirstActivity
import com.setsuna.common.base.BaseActivity
import com.setsuna.common.baseapp.AppCache
import com.setsuna.common.commonutils.SPUtils
import com.setsuna.common.commonutils.ToastUtils
import com.setsuna.common.commonwidget.LoadingDialog
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jymj.zhglxt.app.AppApplication
import com.jymj.zhglxt.login.bean.OAuth2AccessToken
import com.jymj.zhglxt.ui.bean.me.UserInfo
import com.jymj.zhglxt.ui.bean.user.ScUserEntity
import com.jymj.zhglxt.util.*
import com.setsuna.common.base.BaseModel
import com.setsuna.common.commonutils.AppUtils
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA
import io.reactivex.internal.util.HalfSerializer.onComplete
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity<LoginPresenter, LoginModel>(), LoginContract.View {
    //用户信息
    override fun returnScUser(user: UserInfo) {
        AppCache.getInstance().userId = user.userId.toLong()
        AppCache.getInstance().name = user.username
        AppCache.getInstance().nickName = user.nickName
        AppCache.getInstance().mobile = user.mobile
        AppCache.getInstance().avatarUrl = user.avatarUrl
        bt_login_act_login.progress = 0
        startActivity(intent1)
        clearActivity()
    }

    //登录成功调用用户接口
    override fun returnsclogin(user: OAuth2AccessToken.DataBean) {
        AppCache.getInstance().userId = user.userId.toLong()
        mPresenter.scUser(user.userId)

        SPUtils.setSharedBooleanData(this, "islogin", true)
//        bt_login_act_login.isEnabled = true
        if (cb_act_login.isChecked) {
            SPUtils.setSharedStringData(this, "uname", edt_username_act_login.text.toString())
            SPUtils.setSharedStringData(this, "unpwd", edt_password_act_login.text.toString())
            SPUtils.setSharedBooleanData(this, "remember", true)
        } else {
            SPUtils.setSharedStringData(this, "uname", "")
            SPUtils.setSharedStringData(this, "unpwd", "")
            SPUtils.setSharedBooleanData(this, "remember", false)
        }




    }

    private var mShareAPI : UMShareAPI?= null
    private var platform : SHARE_MEDIA?= null

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    companion object {
        /**
         * 入口
         * @param activity
         */

        fun startAction(activity: Context) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            /*activity.overridePendingTransition(R.anim.fade_in,
                    R.anim.fade_out)*/
        }
    }

    override fun initPresenter() {
        mPresenter.setVM(this,mModel)
    }
    var isFirst = true
    var isUpdate = true
    override fun initViews() {
        AppMenus.getInstance().cardPath = GetFileUtil.getSDCardPath();

//        checkUpdate()

//        UpdateUtils().checkUpdate(this)//app更新
        tv_act_login_title1.setText(AppUtils.getAppName())

        iv_wx_login.setOnClickListener {
            /*platform = SHARE_MEDIA.WEIXIN
            mShareAPI!!.doOauthVerify(this, platform,
                    umAuthListener)*/
            if (AppApplication.api != null && AppApplication.api!!.isWXAppInstalled) {
                val req = SendAuth.Req()
                req.scope = "snsapi_userinfo"
                //    req.state = "wechat_sdk_demo_test";
                req.state = "wechat_sdk_demo"
                //            req.state = "wxdemo";
                //            req.state = "diandi_wx_login";

                //             req.state = "wechat_sdk_xb_live_state";//官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
                AppApplication.api!!.sendReq(req)
                finish()
            } else {
                Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show()
            }
        }

    }
    var intent1: Intent? = null
    override fun initDatas() {
        intent1 = Intent(this, FirstActivity::class.java)//跳转菜单

    }





    var count = 0;
    //页面加载完毕
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (isFirst){
            CheckUpdateUtil.checkUpdate(this, object:CheckUpdateUtil.OnCheckLinear{
                override fun onIsUpdate(boolean: Boolean) {
                    isFirst = false
                    isUpdate = boolean
                    checkLogin(hasFocus)
                }
            })//app更新
        }else{
            checkLogin(hasFocus)
        }

//        isUpdate = false
//        checkLogin(hasFocus)

    }

    private fun checkLogin(hasFocus: Boolean) {
        if (hasFocus && count == 0) {
            count++
            // 调用对话框部分
            val uname = SPUtils.getSharedStringData(this, "uname")
            val unpwd = SPUtils.getSharedStringData(this, "unpwd")
            val sharedBooleanData = SPUtils.getSharedBooleanData(this, "islogin")
            if (!uname.equals("")&&uname != null)
                edt_username_act_login.text = Editable.Factory.getInstance().newEditable(uname)//普通赋值
            if (!uname.equals("")&&unpwd != null)
                edt_password_act_login.text = Editable.Factory.getInstance().newEditable(unpwd)//普通赋值
            if (sharedBooleanData != null) {
                if (sharedBooleanData) {
                    val sharedBooleanData1 = SPUtils.getSharedBooleanData(this, "remember")
                    if (sharedBooleanData1 != null) {
                        cb_act_login.isChecked = sharedBooleanData1
                    }
                    if (SingleOnClickUtil.isFastClick()) {
//                        bt_login_act_login.isEnabled = false
                        val deviceId = PhoneUtil.getDeviceId(this)
                        val uname = edt_username_act_login.text.toString().trim()
                        val upwd = edt_password_act_login.text.toString().trim()
                        if (uname.isEmpty() || upwd.isEmpty()) {
                            ToastUtils.showLong("请正确填写用户或密码!")
                            bt_login_act_login.isEnabled = true
                        } else {
                            if (!isUpdate){
                                bt_login_act_login.isEnabled = false
//                                mPresenter.getUserRequest(uname, upwd, deviceId)
                                mPresenter.sclogin(uname,upwd,"password")
                            }

                        }
                    }
                } else {
                    //记录以往登陆过的账号
                    val sharedStringData = SPUtils.getSharedStringData(this, "userList")
                        val arrayList = ArrayList<UserListBean>()
                        if (!sharedStringData.equals("")){
                            val gson = Gson()
                            val list:List<UserListBean> = gson.fromJson(sharedStringData, object : TypeToken<List<UserListBean>>() {}.type)
                            arrayList.addAll(list)
                        }
                        if (arrayList.size>0){
//                            initPopuSelectNum(arrayList)

                        }
                }
            }
        }
    }

    //已弃用
    override fun returnUser(user: User) {
//        UserListBean
        val sharedStringData = SPUtils.getSharedStringData(this, "userList")
        var arrayList = ArrayList<UserListBean>()
        if (!sharedStringData.equals("")){
            val gson = Gson()
            val list:List<UserListBean> = gson.fromJson(sharedStringData, object : TypeToken<List<UserListBean>>() {}.type)
            arrayList.addAll(list)
        }
        val userListBean = UserListBean()
        userListBean.setUname(edt_username_act_login.text.toString())
        userListBean.setUnpwd(edt_password_act_login.text.toString())
        userListBean.setXzqmc(user.xzqmc)
        /*if (!Gson().toJson(arrayList).contains(Gson().toJson(userListBean))){
            arrayList.add(userListBean)
        }*/

        arrayList = addUser(arrayList,userListBean)
        SPUtils.setSharedStringData(this,"userList",Gson().toJson(arrayList))
        AppCache.getInstance().type = user.type!!//用于区分用户类型
        AppMenus.getInstance().menuBeans1.clear()//防止退出登陆时 登陆其他账号权限混乱
        AppMenus.getInstance().menus.clear()//防止退出登陆时 登陆其他账号菜单混乱
        if (user.menus!=null){
            for (i in user.menus){
                if (i.parentName.contains("app")){
                    AppMenus.getInstance().menuBeans1.add(i)
                }
                if (i.parentName.contains("app菜单管理")){
                    AppMenus.getInstance().menus.add(i)
                }
            }
        }

        SPUtils.setSharedBooleanData(this, "islogin", true)
//        bt_login_act_login.isEnabled = true
        if (cb_act_login.isChecked) {
            SPUtils.setSharedStringData(this, "uname", edt_username_act_login.text.toString())
            SPUtils.setSharedStringData(this, "unpwd", edt_password_act_login.text.toString())
            SPUtils.setSharedBooleanData(this, "remember", true)
        } else {
            SPUtils.setSharedStringData(this, "uname", "")
            SPUtils.setSharedStringData(this, "unpwd", "")
            SPUtils.setSharedBooleanData(this, "remember", false)
        }
        AppCache.getInstance().userId = user.userId
        AppCache.getInstance().code = user.code
        AppCache.getInstance().duties = user.rz
        AppCache.getInstance().cunCode = user.code
        AppCache.getInstance().xzqName = user.xzqmc
        AppCache.getInstance().name = user.username
        AppCache.getInstance().phone = user.mobile
        AppCache.getInstance().zmmc = user.typeText
        AppCache.getInstance().loginCenter = user.center
        AppCache.getInstance().xzCenter = user.center
        AppMenus.getInstance().textCenter = user.center//测试使用
        AppCache.getInstance().password = edt_password_act_login.text.toString()
        bt_login_act_login.progress = 0
//        var intent = Intent(this, CbbsjsxActivity::class.java)//跳转菜单
//        var intent = Intent(this, XmIssueActivity::class.java)//跳转菜单

//        startActivity(intent)
        startActivity(intent1)
//        finish()
        clearActivity()
    }

    fun addUser(userlist:ArrayList<UserListBean>,userListBean:UserListBean):ArrayList<UserListBean>{
        var isUpdate = false
        for (i in userlist){
            if (userListBean.getUname().equals(i.getUname())){
                isUpdate = true
                i.setXzqmc(userListBean.getXzqmc())
                i.setUnpwd(userListBean.getUnpwd())
                i.setUname(userListBean.getUname())
            }
        }
        if (!isUpdate){
            userlist.add(userListBean)
        }
        return userlist
    }

    override fun changeUser() {

    }

    override fun changeUser(msg: String) {

    }

    override fun showLoading(title: String?) {
        bt_login_act_login.progress = 50
        bt_login_act_login.loadingText = title
        LoadingDialog.showDialogForLoading(this)
    }

    override fun stopLoading() {
        bt_login_act_login.progress = 0
        bt_login_act_login.normalText = "登录系统"
        LoadingDialog.cancelDialogForLoading()
    }

    override fun showErrorTip(msg: String?) {

        bt_login_act_login.progress = -1
        bt_login_act_login.errorText = msg
        bt_login_act_login.isEnabled = true
        if (msg.equals("'''")){
            ToastUtils.showShort("请求失败")
        }else{
            ToastUtils.showShort(msg)
        }
        LoadingDialog.cancelDialogForLoading()
    }

    fun doLogin(view: View) {
        if (SingleOnClickUtil.isFastClick()){
        /*var intent = Intent(this,LdrkActivity::class.java)
            startActivity(intent)*/
        bt_login_act_login.isEnabled = false
            val deviceId = PhoneUtil.getDeviceId(this)
            val uname = edt_username_act_login.text.toString().trim()
            val upwd = edt_password_act_login.text.toString().trim()
            if (uname.isEmpty() || upwd.isEmpty()) {
                ToastUtils.showLong("请正确填写用户或密码!")
                bt_login_act_login.isEnabled = true
            } else {
//                mPresenter.getUserRequest(uname, upwd, deviceId)
                mPresenter.sclogin(uname,upwd,"password")
            }
        }
    }

    var uid = ""
    private var umAuthListener = object: UMAuthListener{
        override fun onComplete(p0: SHARE_MEDIA?, p1: Int, data: MutableMap<String, String>?) {

            if(platform == SHARE_MEDIA.WEIXIN){
                //unionid:（6.2以前用unionid）uid
                 uid = data!!.get("unionid").toString()
            }else{
                 uid = data!!.get("uid").toString()
            }
            if(uid != ""){
                //如果uid不为空即回调授权成功，则可以调接口告诉后台此时的第三方uid，后台判断此唯一标识值是否存在即判断用户是否用
                //第三方登录过，如果登录过直接进入主界面， 没有登录过则后台存储该值并进入注册界面进行手机号绑定注册

            }else{
                ToastUtils.showShort("暂无法使用该登录方式")
            }
            ToastUtils.showShort("授权成功")
            Log.d("user info", "user info:" + data.toString());
        }

        override fun onCancel(p0: SHARE_MEDIA?, p1: Int) {
            ToastUtils.showShort("取消登录")
        }

        override fun onError(p0: SHARE_MEDIA?, p1: Int, p2: Throwable?) {
            ToastUtils.showShort("失败：" + p2!!.message)
        }

        override fun onStart(p0: SHARE_MEDIA?) {
        }

    }



}