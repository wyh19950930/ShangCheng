package com.jymj.zhglxt.widget.destureview;

import android.content.Context;
import android.view.GestureDetector;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2022/3/29 9:46
 */
public class ScrollGestureManager extends GestureDetector {
    ScrollGestureManager(Context context, ScrollGestureListener scrollGestureListener) {
        super(context, scrollGestureListener);
    }
}
