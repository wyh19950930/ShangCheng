package com.jymj.zhglxt.widget;

import android.os.Build;

import com.jymj.zhglxt.app.AppApplication;


/**
 * @package com.jymj.htz1.widget
 * @fileName GetMyHeight
 * @date 2019/1/2313:52
 * @name qzw
 */
public class GetMyHeight {

    private static volatile GetMyHeight getMyHeight = null;
    private GetMyHeight() {
    }
    public static GetMyHeight getGetMyHeight(){
        if (getMyHeight==null){
            synchronized (GetMyHeight.class){
                if (null == getMyHeight){
                    getMyHeight = new GetMyHeight();
                    return getMyHeight;
                }
            }
        }
        return getMyHeight;
    }
    //Get status bar height
    public static int getStatusBarHeight() {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int resId = AppApplication.getAppContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resId > 0) {
                result = AppApplication.getAppContext().getResources().getDimensionPixelOffset(resId);
            }
        }
        return result;
    }
}
