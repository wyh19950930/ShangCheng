package com.jymj.zhglxt.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class OptimizedRecyclerViewZhen extends RecyclerView {

    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    int  mTouchSlop;//获取滑动多大距离可以滑动屏幕
    int  mStartX;
    int  mStartY;
    int  mScrollPointerId;

    public OptimizedRecyclerViewZhen(@NonNull Context context) {
        super(context);
    }

    public OptimizedRecyclerViewZhen(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public OptimizedRecyclerViewZhen(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        final int actionIndex = ev.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollPointerId = ev.getPointerId(0);

                 mStartX = (int) (ev.getX() + 0.5f);
                 mStartY = (int) (ev.getY() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                try {
                    final int index = ev.findPointerIndex(mScrollPointerId);

                    final int x = (int) (ev.getX(index) + 0.5f);
                    final int y = (int) (ev.getY(index) + 0.5f);
                    float distanceX = Math.abs(x - mStartX);
                    float distanceY = Math.abs(y - mStartY);
                    if (distanceX > mTouchSlop && distanceX > distanceY) { //当横向滑动距离大于纵向滑动距离则不拦截事件
//                    ToastUtils.showShort("左右滑动");
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        final int actionIndex = ev.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollPointerId = ev.getPointerId(0);

                mStartX = (int) (ev.getX() + 0.5f);
                mStartY = (int) (ev.getY() + 0.5f);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mScrollPointerId = ev.getPointerId(actionIndex);    //新落下的手指为滑动监听手指
                mStartX = (int) (ev.getX(actionIndex) + 0.5f);
                mStartY = (int) (ev.getY(actionIndex) + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                final int index = ev.findPointerIndex(mScrollPointerId);

                final int x = (int) (ev.getX(index) + 0.5f);
                final int y = (int) (ev.getY(index) + 0.5f);
                float distanceX = Math.abs(x - mStartX);
                float distanceY = Math.abs(y - mStartY);
                if (distanceX > mTouchSlop && distanceX > distanceY) { //当横向滑动距离大于纵向滑动距离则不拦截事件
                    return false;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onPointerUp(ev);
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void onPointerUp(MotionEvent e) {
        final int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == mScrollPointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mScrollPointerId = e.getPointerId(newIndex);
            mStartX = (int) (e.getX(newIndex) + 0.5f);
            mStartY = (int) (e.getY(newIndex) + 0.5f);
        }
    }*/
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        /*int action = event.getAction();
        final int actionIndex = event.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScrollPointerId = event.getPointerId(0);

                mStartX = (int) (event.getX() + 0.5f);
                mStartY = (int) (event.getY() + 0.5f);
                break;
            case MotionEvent.ACTION_MOVE:
                try {
                    final int index = event.findPointerIndex(mScrollPointerId);

                    final int x = (int) (event.getX(index) + 0.5f);
                    final int y = (int) (event.getY(index) + 0.5f);
                    float distanceX = Math.abs(x - mStartX);
                    float distanceY = Math.abs(y - mStartY);
                    if (distanceX > mTouchSlop && distanceX > distanceY) { //当横向滑动距离大于纵向滑动距离则不拦截事件
//                    ToastUtils.showShort("左右滑动");
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    }else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }*/
        //父层ViewGroup不要拦截点击事件
        getParent().requestDisallowInterceptTouchEvent(true);

        //上是 1 左是0下是2右是3

        //上是 1 左是0下是2右是3
        /*int width = getWidth();
        int height = getHeight();
        float x = event.getX() / width;
        float y = event.getY() / height;
        // Direction will be [0,1,2,3] depending on quadrant
        int direction = 0;
        direction = (x > y) ? 1 : 0;
        direction |= (x > 1 - y) ? 2 : 0;
        if (direction==1||direction==2){
            //通知父控件不要干扰,即屏蔽父控件的该事件以及该事件之后的一切action
            getParent().requestDisallowInterceptTouchEvent(true);
        }else{
            getParent().requestDisallowInterceptTouchEvent(false);
        }*/
        return super.dispatchTouchEvent(event);//super.dispatchTouchEvent(event)
    }


}
