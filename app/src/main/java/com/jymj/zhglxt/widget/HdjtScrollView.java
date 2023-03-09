package com.jymj.zhglxt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class HdjtScrollView extends ScrollView {

    private boolean ishd;

    public HdjtScrollView(Context context) {
        super(context);
    }

    public HdjtScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HdjtScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /*if (t==0){
            ishd = false;
        }*/
        if (oldt < t && ((t - oldt) > 15)) {// 向上
            Log.e("wangly", "距离："+(oldt < t) +"---"+(t - oldt));
            Log.d("TAG","向上滑动");

        }  else if (oldt > t && (oldt - t) > 15) {// 向下
            Log.e("wangly", "距离："+(oldt > t) +"---"+(oldt - t));
            Log.d("TAG"," 向下滑动");

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}