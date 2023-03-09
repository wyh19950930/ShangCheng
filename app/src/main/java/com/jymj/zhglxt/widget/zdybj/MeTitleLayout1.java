package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jymj.zhglxt.R;

/**
 * @package com.jymj.htz1.widget
 * @fileName MeTitle
 * @date 2019/1/2314:44
 * @name qzw
 * 自定义输入框
 */

public class MeTitleLayout1 extends LinearLayout {
    private LinearLayout ll_item_my_title_name;
    private TextView tv_item_my_title_name;
    private TextView tv_item_my_title_name_add;
    private ImageView iv_item_my_title_name;
    private View view_item_my_title_name;
    private Context context;
    private OnTitleListLister onTitleListLister;

    public MeTitleLayout1(Context context) {
        this(context,null);
    }
    public MeTitleLayout1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public MeTitleLayout1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }

    public void setOnTitleListLister(OnTitleListLister onTitleListLister) {//点击事件回调
        this.onTitleListLister = onTitleListLister;
    }

    //初始化控件
    private void init(final Context context){
//        Log.i("", "init: ");
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_my_title_item1, this);
        ll_item_my_title_name = findViewById(R.id.ll_item_my_title_name1);
        tv_item_my_title_name = findViewById(R.id.tv_item_my_title_name1);//左侧名称
        tv_item_my_title_name_add = findViewById(R.id.tv_item_my_title_name_add1);//右侧按钮名称 与图标
        iv_item_my_title_name = findViewById(R.id.iv_item_my_title_name1);//右侧展开隐藏箭头
        view_item_my_title_name = findViewById(R.id.view_item_my_title_name1);//左侧方块
        ll_item_my_title_name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_item_my_title_name.getDrawable().getConstantState()
                        .equals(context.getDrawable(R.drawable.item_title_down).getConstantState())){
                    setImageView(R.drawable.back_left_icon);
                }else {
                    setImageView(R.drawable.item_title_down);
                }
                if (onTitleListLister!=null){
                    onTitleListLister.onClick("点击");
                }
            }
        });
        tv_item_my_title_name_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTitleListLister!=null){
                    onTitleListLister.onClick("添加");
                }
            }
        });

    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){
//        Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        String name=array.getString(R.styleable.attrs_info_attr_name);
        String leftname=array.getString(R.styleable.attrs_info_attr_leftname);
        String attrHidename=array.getString(R.styleable.attrs_info_attr_hidename);
        int arrayColor=array.getColor(R.styleable.attrs_info_attr_color,0);
        int src=array.getResourceId(R.styleable.attrs_info_attr_src,R.drawable.item_title_down);
        int leftsrc=array.getResourceId(R.styleable.attrs_info_attr_leftsrc,0);
        boolean attr_isshow_right=array.getBoolean(R.styleable.attrs_info_attr_isshow_right,true);
        tv_item_my_title_name.setText(name);
        iv_item_my_title_name.setImageResource(src);
        if (!attr_isshow_right){//判断如果返回false隐藏右侧箭头
            iv_item_my_title_name.setVisibility(View.GONE);
        }
        if (arrayColor!=0){//
            view_item_my_title_name.setVisibility(View.VISIBLE);
            view_item_my_title_name.setBackgroundColor(arrayColor);
        }else {
            LinearLayout.LayoutParams para=new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT );
            para.setMargins(0,0,0,0); //left,top,right,bottom
            tv_item_my_title_name.setLayoutParams(para);
            view_item_my_title_name.setVisibility(View.GONE);
           /* RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tv_item_my_title_name.getLayoutParams();
            layoutParams.leftMargin = 0;

            view_item_my_title_name.setVisibility(View.GONE);*/
        }
        if (leftname!=null||leftsrc!=0){
            tv_item_my_title_name_add.setVisibility(View.VISIBLE);
            if (leftsrc!=0){
                Drawable drawable = getResources().getDrawable(leftsrc);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置边界
                // param 左上右下
                tv_item_my_title_name_add.setCompoundDrawables(drawable,null,null,null);
//                tv_item_my_title_name_add.set(leftsrc,0,0,0);
            }
        }else {
            tv_item_my_title_name_add.setVisibility(View.GONE);
        }
        tv_item_my_title_name_add.setText(leftname);
        if (attrHidename!=null){
            tv_item_my_title_name_add.setVisibility(View.VISIBLE);
        }
        tv_item_my_title_name_add.setHint(attrHidename);
        array.recycle();
    }
    private void setImageView(int src){
        iv_item_my_title_name.setImageResource(src);
    }
    public void setIsVisibility(int isVisibility){
        tv_item_my_title_name_add.setVisibility(isVisibility);
    }
    public void setName(String name){
        tv_item_my_title_name.setText(name);
    }
    public String getAddText(){
        return tv_item_my_title_name_add.getText().toString();
    }
    public void setAddText(String addText){
        tv_item_my_title_name_add.setText(addText);
    }
    //点击回调
    public interface OnTitleListLister{
        void onClick(String name);
    }
}
