package com.setsuna.common.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * qzw
 *
 * @param <T>
 */
public abstract class Ipresenter<T> {
    public Context mContext;
    public T view;
    private WeakReference<T> mRefView;

    public Ipresenter(T view) {
        this.view = view;
        mRefView = new WeakReference<T>(view);
        //用来创建modle
        init();
    }

    protected abstract void init();

    public void destroy() {
        if (view != null) {
            mRefView.clear();
        }
    }

}
