
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
-keep com.setsuna.common.basebean.** { *; }
-keep com.setsuna.common.commonwidget.**{*;}
#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}



#gson
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

#easyadapter
-dontwarn com.yuyh.easyadapter.**
-keep class com.yuyh.easyadapter.** {*;}


#-------------------------------------------------------------------------


#---------------------------------3.与js互相调用的类------------------------
#TODO 我的工程里没有。。。
#-------------------------------------------------------------------------


#---------------------------------4.反射相关的类和方法-----------------------

#----------------------------------------------------------------------------


#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
#代码混淆压缩比，在0和7之间，默认为5，一般不需要改
-optimizationpasses 5

#混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

#指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses

#指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

#不做预校验，preverify是proguard的4个步骤之一，Android不需要preverify，去掉这一步可加快混淆速度
-dontpreverify

#有了verbose这句话，混淆后就会生成映射文件，包含有类名->混淆后类名的映射关系，然后使用printmapping指定映射文件的名称
-verbose
-printmapping proguardMapping.txt

#指定混淆时采用的算法，后面的参数是一个过滤器，这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/arithmetic, !field/*, !class/merging/*

#保护代码中的Annotation不被混淆，这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*

#避免混淆泛型，这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature

#抛出异常时保留代码行号，在第6章异常分析中我们提到过
-keepattributes SourceFile, LineNumberTable

#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
#忽略警告
-dontwarn com.lippi.recorder.utils**
#保留一个完整的包
-keep class com.lippi.recorder.utils.** {
*;
}
-keep class  com.lippi.recorder.utils.AudioRecorder{*;}

#保留了继承自Activity、Application这些类的子类，因为这些子类，都有可能被外部调用
#比如说，第一行就保证了所有Activity的子类不要被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

#保留在Activity中的方法参数是view的方法，从而我们在layout里面编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

#枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#保留自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#保留Parcelable序列化的类不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

#保留Serializable序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#对于R（资源）下的所有类及其方法，都不能被混淆
-keep class **.R$* {
    *;
}

#对于带有回调函数onXXEvent的，不能被混淆
-keepclassmembers class * {
    void * (**On*Event);
}

#webview
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}

# 内部类混淆配置
-keep class com.manjay.housebox.activity.CityListActivity$*{
        <fields>;
        <methods>;
}
-keepclassmembers class com.manjay.housebox.activity.CityListActivity$*{*;}
-keep class com.manjay.housebox.map.MapActivity$*{
        <fields>;
        <methods>;
}
-keepclassmembers class com.manjay.housebox.map.MapActivity$*{*;}

#----------------------------------------------------------------------------
#忽略警告
-dontwarn com.parse.**
#基线包使用，生成mapping.txt
-printmapping mapping.txt
#生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下
#修复后的项目使用，保证混淆结果一致
#-applymapping mapping.txt
#hotfix
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}
#防止inline

#友盟统计
-dontoptimize
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.jymj.xztcpro.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#友盟推送
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}
-dontwarn com.daimajia.**
-dontwarn com.nineoldandroids.**
-dontwarn com.squareup.**
-dontwarn jcifs.http.**
-dontwarn org.codehaus.**
-dontwarn com.esri.**
-keep class com.esri.** {*;}
-keep class com.daimajia.** {*;}
-keep class com.squareup.** {*;}
-keep class com.nineoldandroids.** {*;}
-keep class jcifs.http.** {*;}
-keep class org.codehaus.** {*;}

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
 }

 #okhttp
 -dontwarn okhttp3.**
 -keep class okhttp3.**{*;}

 #okio
 -dontwarn okio.**
 -keep class okio.**{*;}
 #greenDao
 -keep class org.greenrobot.greendao.**{*;}
 -dontwarn org.greenrobot.greendao.**
 -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
 public static java.lang.String TABLENAME;
 }
 -keep class **$Properties
 #photoview
 -dontwarn cn.bluemobi.**
 -keep class cn.bluemobi.**{*;}

 #萤石
 -dontwarn com.ezviz.player.**
 -keep class com.ezviz.player.** { *;}

 -dontwarn com.ezviz.statistics.**
 -keep class com.ezviz.statistics.** { *;}

 -dontwarn com.ezviz.stream.**
 -keep class com.ezviz.stream.** { *;}

 -dontwarn com.hik.**
 -keep class com.hik.** { *;}

 -dontwarn com.hikvision.**
 -keep class com.hikvision.** { *;}

 -dontwarn com.videogo.**
 -keep class com.videogo.** { *;}

 -dontwarn com.videogo.**
 -keep class org.MediaPlayer.PlayM4.** { *;}

 #Gson混淆配置
 -keepattributes Annotation
 -keep class sun.misc.Unsafe { *; }
 -keep class com.idea.fifaalarmclock.entity.*
 -keep class com.google.gson.stream.* { *; }

#高德地图
#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

-dontwarn com.amap.**
#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

-keep class android.databinding.**{*;}
-keep class android.databinding.**{*;}

#百度文字识别
-keep class com.baidu.ocr.sdk.**{*;}
-dontwarn com.baidu.ocr.**