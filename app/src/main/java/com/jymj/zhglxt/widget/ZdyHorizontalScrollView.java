package com.jymj.zhglxt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class ZdyHorizontalScrollView extends HorizontalScrollView {
    public ZdyHorizontalScrollView(Context context) {
        super(context);
    }

    public ZdyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZdyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private float lastX, lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //父层ViewGroup不要拦截点击事件
        /*for (int i = 0; i < getChildCount(); i++) {
            //requestDisallowInterceptTouchEvent
            getChildAt(i).touchev(true);
        }*/
//        getParent().requestDisallowInterceptTouchEvent(false);
        /*switch(ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //必须返回false，否则子控件永远无法拿到焦点
                return false;
            case MotionEvent.ACTION_MOVE:
                if(事件交给子控件的条件) {
                    return false;
                } else {
                    return super.onInterceptTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                //必须返回false,否则子控件永远无法拿到焦点
                return false;
            default:
                return super.onInterceptTouchEvent(ev);
        }*/
        return super.dispatchTouchEvent(ev);
    }

}
