package com.jymj.zhglxt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/8/5 16:19
 */
public class EnableLinearLayout extends LinearLayout {
    boolean isNoClick = false;
    /***
     * 设置是否可以点击
     * @param isNoClick   true:不能点击  false:可以点击
     */
    public void setNoClick(boolean isNoClick) {
        this.isNoClick = isNoClick;
    }

    public EnableLinearLayout(Context context) {
        super(context);
    }

    public EnableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isNoClick) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
