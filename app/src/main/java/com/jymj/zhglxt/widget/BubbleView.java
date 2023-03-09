package com.jymj.zhglxt.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * @package com.jymj.htz1.widget
 * @fileName BubbleView
 * @date 2019/3/2611:12
 * @name qzw
 */

public class BubbleView extends View {

    Paint paint;

    int textWidth;

    int rectHeight =80; //矩形高

    int circleR = rectHeight/2;//圆弧半径

    String textPre ="";//前部分距离你 附近有

    int textPreWidth;

    String textEnd ="";//后部分米

    int textNumWidth;

    String textNum ="";

    String text;

    private int fontSize =30;//字体大小

    private int padding =20;//内容边距

    private int bottomLine =26;//等腰直角三角形底边长

    int width;//view总宽

    int height = padding + rectHeight + bottomLine/2 + padding; //view总高

    private Path path;

    private String distance;
    private OnClicklinester onClickLinester;
    private Canvas canvas;
    private int color= Color.argb(0, 255,255,255);//="#13AFFF"56,169,0

    public OnClicklinester getOnClickLinester() {
        return onClickLinester;
    }

    public void setOnClickLinester(OnClicklinester onClickLinester) {
        this.onClickLinester = onClickLinester;
    }

    public BubbleView(Context context) {

        super(context);

        init();

    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        init();

    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        init();

    }


    public void init() {

        resetPaint();

        this.setLayerType(View.LAYER_TYPE_SOFTWARE,paint);

        paint.setShadowLayer(10F,1F,1F, Color.GRAY);

        text = textPre + textNum + textEnd;

        textWidth = (int) paint.measureText(text);

        textPreWidth = (int) paint.measureText(textPre);

        textNumWidth = (int) paint.measureText(textNum);

        path =new Path();

    }

    public String getDistance(){

        return distance;

    }

    public void setText(String textNum){

        this.textNum = textNum;

        init();

        invalidate();

    }

    public void resetPaint() {

        //初始化画笔

        paint =new Paint();

        //设置抗锯齿

        paint.setAntiAlias(true);

        //设置填充

        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //设置防抖动

        paint.setDither(true);

        paint.setTextSize(fontSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        init();
        drawRect(canvas);

    }
    public void setColor(int color) {
        this.color = color;
        init();
        invalidate();
    }

    public void setTextColor(int color) {
        this.color = color;
        init();
        invalidate();
    }


    private void drawRect(Canvas canvas) {

        paint.setColor(color);//333333 Color.parseColor(color)

        RectF rectF =new RectF(padding, padding, padding + circleR*2, padding + circleR*2);

        path.arcTo(rectF, 270, -180,false);

        path.lineTo(width/2 - bottomLine/2, padding + circleR*2);

        path.lineTo(width/2, padding + circleR*2 + bottomLine/2);

        path.lineTo(width/2 + bottomLine/2, padding + circleR*2);

        path.lineTo(padding + circleR + textWidth, padding + circleR *2);

        RectF rectF1 =new RectF(padding + circleR + textWidth - circleR, padding, padding + circleR + textWidth + circleR, padding + circleR*2);

        path.arcTo(rectF1, 90, -180,false);

        path.lineTo(padding + circleR, padding);

        canvas.drawPath(path, paint);

        resetPaint();

        paint.setColor(Color.WHITE);//333333

        Rect bounds =new Rect();

        paint.getTextBounds(text, 0, text.length(), bounds);

        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();

        int baseline = (height - bottomLine/2 - fontMetrics.descent + fontMetrics.ascent) /2 - fontMetrics.ascent;

        canvas.drawText(textPre, width/2-textWidth/2, baseline, paint);

        canvas.drawText(textEnd, width/2-textWidth/2 + textPreWidth + textNumWidth, baseline, paint);

        resetPaint();

        paint.setColor(Color.WHITE);//ff9500  ffffff  3C3F41

        canvas.drawText(textNum, width/2-textWidth/2 + textPreWidth, baseline, paint);

    }

    //事件分发
    //dispatchTouchEvent->onInterceptTouchEvent->onTouchEvent
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //请求所有父控件及祖宗控件不要拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
       /* switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                onClickLinester.onClick();
                break;
        }*/
        return super.dispatchTouchEvent(ev);
    }

    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = padding + circleR + textWidth + circleR + padding;

        setMeasuredDimension(width, height);

    }

}
