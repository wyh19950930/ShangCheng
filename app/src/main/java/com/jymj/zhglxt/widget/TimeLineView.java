package com.jymj.zhglxt.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jymj.zhglxt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

/**
 * TimeLineView
 *
 * @author WrBug
 * @since 2017/2/21
 *
 */
public class TimeLineView extends View {
    private Paint mPaint;
    /**
     * 状态文本
     */
    private List<String> mPointTxt;
    /**
     * 步数
     */
    private float mStep = 1;
    /**
     * 圆形x坐标组
     */
    private int[] mXpoints;
    private float[] fXpoints;
    private int mPreLineColor;
    private int mStartedLineColor;

    private int mStartedCircleColor;
    private int mUnderwayCircleColor;
    private int mPreCircleColor;

    private int mStartedStringColor;
    private int mUnderwayStringColor;
    private int mPreStringColor;

    private int mRadius = 10;
    private float mTextSize = 20;
    private float mLineWidth = 5;
    private OnStepChangedListener mOnStepChangedListener;
    private Builder mBuilder;
    private int index;
    private int width;
    private int height;
    private float textHeight;
    private float textWidth;

    public TimeLineView(Context paramContext) {
        this(paramContext, null);
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public TimeLineView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
        initAttrs(paramAttributeSet);
    }

    private void initAttrs(AttributeSet paramAttributeSet) {
        if (paramAttributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.TimeLineView);
            mStartedLineColor = typedArray.getColor(R.styleable.TimeLineView_startedLineColor, Color.BLACK);
            mPreLineColor = typedArray.getColor(R.styleable.TimeLineView_preLineColor, Color.GRAY);

            mStartedCircleColor = typedArray.getColor(R.styleable.TimeLineView_startedCircleColor, Color.BLACK);
            mUnderwayCircleColor = typedArray.getColor(R.styleable.TimeLineView_underwayCircleColor, Color.BLACK);
            mPreCircleColor = typedArray.getColor(R.styleable.TimeLineView_preCircleColor, Color.GRAY);

            mStartedStringColor = typedArray.getColor(R.styleable.TimeLineView_startedStringColor, Color.BLACK);
            mUnderwayStringColor = typedArray.getColor(R.styleable.TimeLineView_underwayStringColor, Color.BLACK);
            mPreStringColor = typedArray.getColor(R.styleable.TimeLineView_preStringColor, Color.GRAY);
            mTextSize = typedArray.getDimension(R.styleable.TimeLineView_textSize, 20);
            mRadius = (int) typedArray.getDimension(R.styleable.TimeLineView_tlradius, 10);
            mLineWidth = typedArray.getDimension(R.styleable.TimeLineView_lineWidth, 5);
            typedArray.recycle();
        }
    }


    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mStartedLineColor = Color.BLUE;
        this.mPreLineColor = Color.GRAY;
        this.mStartedCircleColor = mStartedLineColor;
        this.mUnderwayCircleColor = mStartedCircleColor;
        this.mPreCircleColor = mPreLineColor;
        this.mStartedStringColor = mStartedLineColor;
        this.mUnderwayStringColor = mStartedStringColor;
        this.mPreStringColor = mPreLineColor;
        this.mPointTxt = new ArrayList();
        this.mPointTxt.add("step 1");
        this.mPointTxt.add("step 2");
        this.mPointTxt.add("step 3");
        this.mBuilder = new Builder();
    }

    public Builder builder() {
        return mBuilder;
    }

    public void setPointStrings(@NonNull List<String> pointStringList, @FloatRange(from = 1.0) float step) {
        if (pointStringList == null || pointStringList.isEmpty()) {
            mPointTxt.clear();
            mStep = 0;
        } else {
            mPointTxt = new ArrayList(pointStringList);
            mStep = Math.min(step, mPointTxt.size());
        }
        invalidate();
    }

    public void setPointStrings(@NonNull String[] pointStringList, @FloatRange(from = 1.0) float step) {
        if (pointStringList == null) {
            mPointTxt.clear();
            mStep = 0;
            invalidate();
        } else {
            setPointStrings(new ArrayList<>(Arrays.asList(pointStringList)), step);
        }
    }

    public void setStep(@IntRange(from = 1) int step) {
        this.mStep = Math.min(step, this.mPointTxt.size());
        invalidate();
    }

    public boolean nextStep() {
        if (mStep + 1 > mPointTxt.size()) {
            return false;
        } else {
            mStep++;
            invalidate();
            return true;
        }
    }

    public float getStep() {
        return mStep;
    }

    public void setOnStepChangedListener(OnStepChangedListener listener) {
        this.mOnStepChangedListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initCalc();
        drawCircle(canvas, 1, this.mPointTxt.get(0), this.mXpoints[0]);
        textWidth =mPaint.measureText("M");
        if (mOnStepChangedListener != null) {
//            mOnStepChangedListener.onchanged(this, (int) (this.mStep-0.5), this.mPointTxt.get((int)(mStep - 1.5)));
        }
        boolean needContinue=false;
        for (int i = 1; i < mPointTxt.size(); i++) {
            if(this.fXpoints[i]!=0 && this.mStep!=1){
                drawLine(canvas, true, this.mXpoints[(i - 1)], this.fXpoints[i]);
                if(i==mPointTxt.size()-1){
                    drawLine(canvas, false, (int) this.fXpoints[i]-mRadius*2, this.mXpoints[i]);
                }
                needContinue=true;
            }else{
                if(needContinue){
                    needContinue=false;
                    drawLine(canvas, this.mStep > i, (int) this.fXpoints[i-1]-mRadius*3, this.mXpoints[i]);
                }else{
                    drawLine(canvas, this.mStep > i, this.mXpoints[(i - 1)], this.mXpoints[i]);
                }

            }

            drawCircle(canvas, i + 1, this.mPointTxt.get(i), this.mXpoints[i]);
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
        textHeight = width /7f-2;
        mPaint.setTextSize(textHeight);
        mPaint.setTextSize(textHeight);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
//                if(y> height -1* textHeight&&y< height -1* textHeight){
                    index = (int) (x/(width /mPointTxt.size()));
                    //此处有增加，当屏幕被点击后，将参数传入。
                    if(mOnStepChangedListener!=null){
                        if (index<mPointTxt.size()){
                            mOnStepChangedListener.onchanged(new TimeLineView(getContext()),index, mPointTxt.get(index));
                        }
                    }
                    invalidate();
                    return true;
//                }
//                break;

            case MotionEvent.ACTION_UP:
                index =-1;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }
    private void initCalc() {
        int len = this.mPointTxt.size();
        this.mXpoints = new int[len];
        this.fXpoints=new float[len];
        if (len > 1) {
            int strlen = (int) (getWordCount(this.mPointTxt.get(0)) * this.mTextSize);
            this.mXpoints[0] = Math.max(strlen, this.mRadius);
            this.fXpoints[0] = Math.max(strlen, this.mRadius);
            strlen = (int) (getWordCount(this.mPointTxt.get(len - 1)) * this.mTextSize);
            this.mXpoints[len - 1] = getWidth() - Math.max(strlen, this.mRadius);
            int dx = (this.mXpoints[len - 1] - this.mXpoints[0]) / (len - 1);
            float offset = this.mStep % 1;
            if(this.mStep-offset==len - 1 && this.mStep!=len - 1){
                this.fXpoints[len - 1] = (int) (mXpoints[0] + dx * (len - 2)+offset*dx);
            }
            for (int i = 1; i < this.mXpoints.length - 1; i++) {
                if(i==(int)(this.mStep-offset) && mStep > i){
                    this.fXpoints[i] = (int) (mXpoints[0] + dx * (i-1)+offset*dx);
                }else{
                    this.fXpoints[i]=0;
                }
                this.mXpoints[i] =  mXpoints[0] + dx * i;
            }
        }
    }

    private float getWordCount(String s) {
        if (TextUtils.isEmpty(s)) {
            return 0;
        }
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length / 4.0f;
    }

    private void drawCircle(Canvas canvas, int drawStep, String text, int dx) {
        this.mPaint.setColor(this.mStep == drawStep ? mUnderwayCircleColor : this.mStep > drawStep ? mStartedCircleColor : mPreCircleColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(4.0F);
        canvas.drawCircle(dx, getHeight() - this.mRadius - 1, this.mRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(dx, getHeight() - this.mRadius - 1, this.mRadius - 5, this.mPaint);
        this.mPaint.setColor(this.mStep == drawStep ? mUnderwayStringColor : this.mStep > drawStep ? mStartedStringColor : mPreStringColor);
        this.mPaint.setTextSize(this.mTextSize);
        canvas.drawText(text, dx - getWordCount(text) * this.mTextSize, getHeight() - this.mRadius * 2 - 20, this.mPaint);

    }

    private void drawLine(Canvas paramCanvas, boolean isStart, int startX, float endX) {
        this.mPaint.setColor(isStart ? mStartedLineColor : mPreLineColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(mLineWidth);
        paramCanvas.drawLine(this.mRadius * 1.2F + startX, getHeight() - this.mRadius - 1, endX - this.mRadius * 1.2F, getHeight() - this.mRadius - 1, this.mPaint);
    }

    public interface OnStepChangedListener {
        void onchanged(TimeLineView view, int step, String stepStr);
    }

    public class Builder {

        private Builder() {
        }

        /**
         * 状态文本
         *
         * @param pointStringList
         * @param step
         * @return
         */
        public Builder pointStrings(@NonNull List<String> pointStringList, @IntRange(from = 1) int step) {
            if (pointStringList == null || pointStringList.isEmpty()) {
                mPointTxt.clear();
                mStep = 0;
            } else {
                mPointTxt = new ArrayList(pointStringList);
                mStep = Math.min(step, mPointTxt.size());
            }
            return this;
        }

        /**
         * 状态文本
         *
         * @param pointStringList
         * @param step
         * @return
         */
        public Builder pointStrings(@NonNull String[] pointStringList, @IntRange(from = 1) int step) {
            if (pointStringList == null) {
                mPointTxt.clear();
                mStep = 0;
                return this;
            } else {
                return pointStrings(new ArrayList<>(Arrays.asList(pointStringList)), step);
            }
        }

        /**
         * 文本大小
         *
         * @param px
         * @return
         */
        public Builder textSize(float px) {
            mTextSize = px;
            return this;
        }

        /**
         * 未开始状态线条颜色
         *
         * @param preLineColor
         * @return
         */
        public Builder preLineColor(@ColorInt int preLineColor) {
            mPreLineColor = preLineColor;
            return this;
        }

        /**
         * 已进行状态线条颜色
         *
         * @param startedLineColor
         * @return
         */
        public Builder startedLineColor(@ColorInt int startedLineColor) {
            mStartedLineColor = startedLineColor;
            return this;
        }


        /**
         * 未开始状态圆颜色
         *
         * @param preCircleColor
         * @return
         */
        public Builder preCircleColor(@ColorInt int preCircleColor) {
            mPreCircleColor = preCircleColor;
            return this;
        }


        /**
         * 进行中状态圆颜色
         *
         * @param underwayCircleColor
         * @return
         */
        public Builder underwayCircleColor(@ColorInt int underwayCircleColor) {
            mUnderwayCircleColor = underwayCircleColor;
            return this;
        }

        /**
         * 已进行状态圆颜色
         *
         * @param startedCircleColor
         * @return
         */
        public Builder startedCircleColor(@ColorInt int startedCircleColor) {
            mStartedCircleColor = startedCircleColor;
            return this;
        }


        /**
         * 未开始状态文本颜色
         *
         * @param preStringColor
         * @return
         */
        public Builder preStringColor(@ColorInt int preStringColor) {
            mPreStringColor = preStringColor;
            return this;
        }


        /**
         * 进行中状态文本颜色
         *
         * @param underwayStringColor
         * @return
         */
        public Builder underwayStringColor(@ColorInt int underwayStringColor) {
            mUnderwayStringColor = underwayStringColor;
            return this;
        }

        /**
         * 已进行状态文本颜色
         *
         * @param startedStringColor
         * @return
         */
        public Builder startedStringColor(@ColorInt int startedStringColor) {
            mStartedStringColor = startedStringColor;
            return this;
        }

        /**
         * 圆半径
         *
         * @param px
         * @return
         */
        public Builder radius(int px) {
            mRadius = px;
            return this;
        }

        /**
         * 线条宽度
         *
         * @param lineWidth
         * @return
         */
        public Builder lineWidth(float lineWidth) {
            mLineWidth = lineWidth;
            return this;
        }

        /**
         * 重新绘制
         */
        public void load() {
            invalidate();
        }
    }
}
