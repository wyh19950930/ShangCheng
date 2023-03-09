package com.setsuna.common.base;

/**
 * des:baseview
 * Created by setsuna
 * on 2016.07.11:53
 */
public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
