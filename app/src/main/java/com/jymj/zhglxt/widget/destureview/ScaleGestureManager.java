package com.jymj.zhglxt.widget.destureview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/3/29 9:46
 */
public class ScaleGestureManager extends ScaleGestureDetector {
    ScaleGestureManager(Context context, ScaleGestureListener scaleGestureListener) {
        super(context, scaleGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
