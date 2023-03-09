package com.setsuna.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.setsuna.common.R;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.baseapp.AppManager;
import com.setsuna.common.commonutils.TUtil;
import com.setsuna.common.commonutils.ToastUtils;
import com.setsuna.common.commonwidget.LoadingDialog;
import com.setsuna.common.commonwidget.StatusBarCompat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基类
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleActivity extends BaseActivity<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleActivity extends BaseActivity {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {//AppCompatActivity
    public T mPresenter;
    public E mModel;
    public Context mContext;
    private boolean isConfigChange = false;
    public FragmentManager mFragmentManager;
    public Activity mSelf;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止截屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        mSelf=this;
        mFragmentManager = this.getSupportFragmentManager();
        isConfigChange = false;
        doBeforeSetcontentView();
        
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        setContentView(getLayoutId());
        this.initPresenter();
        this.initViews();
        this.initDatas();
        AppCache.getInstance().getActivities().add(this);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //设置昼夜主题
        initTheme();
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

    }

    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    public abstract void initViews();

    //初始化data
    public abstract void initDatas();

    protected void clearActivity(){
        for (int i = 0; i < AppCache.getInstance().getActivities().size(); i++) {
            if (AppCache.getInstance().getActivities().get(i)!=null){
                AppCache.getInstance().getActivities().get(i).finish();
            }
        }
        AppCache.getInstance().getActivities().clear();
    }
    protected void clearActivity(Activity activity){
        Iterator<Activity> it1 = AppCache.getInstance().getActivities().iterator();
        while(it1.hasNext()){
            Activity next = it1.next();
            if (next!=null&&next!=activity){
                next.finish();
                it1.remove();
            }
        }
    }

    /**
     * 设置主题
     */
    private void initTheme() {
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtils.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUtils.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUtils.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUtils.showLong(text);
    }


    //--------------------快捷操作Fragment
    public void addFrag(int container, BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(container, baseFragment, baseFragment.mTag);
        transaction.commit();
    }

    public void removeFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (baseFragment.isAdded()){
            transaction.remove(baseFragment);
            transaction.commit();
        }
    }

    public void replaceFrag(int container, BaseFragment newFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(container, newFragment, newFragment.mTag);
        transaction.commit();
    }

    public void hideFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(baseFragment);
        transaction.commit();
    }

    public void showFrag(BaseFragment baseFragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.show(baseFragment);
        transaction.commit();
    }


    //--------------------快捷操作Fragment


    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (mPresenter != null)
            mPresenter.onDestroy();*/
        if (!isConfigChange) {
            AppManager.getAppManager().finishActivity(this);
        }
    }
}
