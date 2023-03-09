package com.jymj.zhglxt.app

import android.app.Activity
import android.content.Context
import android.provider.UserDictionary.Words.APP_ID
import androidx.core.content.FileProvider
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.MemoryCookieStore
import com.setsuna.common.baseapp.BaseApplication
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


/**
 * Created by setsuna on 2018/3/7.
 */
public class AppApplication : BaseApplication() {
//    var mContext: Context? = null
//    public  var st=""
    public var  baseApplication: Context = this;

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
        val builder = OkHttpClient.Builder()
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS * 2, TimeUnit.MILLISECONDS)
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS * 2, TimeUnit.MILLISECONDS)
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS * 2, TimeUnit.MILLISECONDS)
        builder.addInterceptor(RequestInterceptor())
        builder.addInterceptor(ResponseInterceptor())
        builder.cookieJar(CookieJarImpl(MemoryCookieStore()))
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3).okHttpClient = builder.build()


        api = WXAPIFactory.createWXAPI(this, APP_ID, false)//true
        api!!.registerApp(APP_ID)

        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true)
        //友盟预初始化
        UMConfigure.preInit(this,"63ab9aa7d64e6861390819d7","Umeng")

        UMConfigure.init(this,"63ab9aa7d64e6861390819d7","Umeng",UMConfigure.DEVICE_TYPE_PHONE,"")
        val FileProvider = "com.jymj.dlxc.fileprovider"
        PlatformConfig.setWeixin("wx10e3d1292c7f5247", "f3036b75d3680949505fcff5f52c9b06")
        PlatformConfig.setWXFileProvider(FileProvider)
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba")
        PlatformConfig.setQQFileProvider(FileProvider)
        PlatformConfig.setSinaWeibo("1592659361", "e3a275448f4a3e93c28a96ae0bb5d0ba", "http://open.weibo.com")
        PlatformConfig.setSinaFileProvider(FileProvider)

        UMShareAPI.get(this)
        /*//初始化进度管理流程图数据库
        WBSDB.init()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        //取消严格模式 FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
        if (!BuildConfig.LOG_DEBUG){
            SophixManager.getInstance().queryAndLoadNewPatch()
        }else{
            BlockCanary.install(this, BlockCanaryContext()).start()
        }*/

    }

    /*override fun initOthers() {
        super.initOthers()
        //初始化进度管理流程图数据库
        WBSDB.init()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        //取消严格模式 FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
        }
        if (!BuildConfig.LOG_DEBUG){
            SophixManager.getInstance().queryAndLoadNewPatch()
        }else{
            BlockCanary.install(this, BlockCanaryContext()).start()
        }
    }*/

    companion object {

        var  baseApplication: Context? = null
        val MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION"
        val KEY_MESSAGE = "message"
        val KEY_EXTRAS = "extras"
        var api: IWXAPI?=null
        var APP_ID = "wx10e3d1292c7f5247"
        //最小宽度px
        val MIN_WIDTH = 200
        //Glide加cookie
        private val mGlideUrl: GlideUrl? = null
        @JvmStatic fun getGlideUrl(url: String): GlideUrl {
            if (mGlideUrl == null) {
                val allCookie = OkGo.getInstance().cookieJar.cookieStore.allCookie
                val builder = LazyHeaders.Builder()
                builder.addHeader("Cookie") {
                    val sb = StringBuilder()
                    for (c in allCookie) {
                        sb.append(c.name()).append("=").append(c.value()).append(";domain=").append(c.domain())
                    }
                    sb.toString()
                }
                return GlideUrl(url, builder.build())
            }
            return mGlideUrl
        }
    }

}