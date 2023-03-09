package com.jymj.zhglxt.widget.zdybj;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MeItemLayout extends LinearLayout {
    private TextView tv_act_my_item_key;
    private EditText et_act_my_item_value;
    private Context context;

    public MeItemLayout(Context context) {
        this(context,null);
    }
    public MeItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
        getValues(attrs);
    }
    public MeItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        getValues(attrs);
    }
    //初始化控件
    private void init(final Context context){
        Log.i("", "init: ");
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_my_item, this);
        tv_act_my_item_key = findViewById(R.id.tv_act_my_item_key);
        et_act_my_item_value = findViewById(R.id.et_act_my_item_value);
    }
    //获取布局里边传值
    public void getValues(AttributeSet attrs){
        Log.i("", "getValues: ");
        TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.attrs_info);
        String key=array.getString(R.styleable.attrs_info_attr_key);
        String hint=array.getString(R.styleable.attrs_info_attr_hint);
//        String value=array.getString(R.styleable.attrs_info_attr_value);
        tv_act_my_item_key.setText(key);
        et_act_my_item_value.setHint(hint);
        /*String text=array.getString(R.styleable.attrs_info_attr_text);
        meTitleTvRight.setText(text);*/
        array.recycle();
    }
    //给输入框赋值
    public void setValueText(String value){
        et_act_my_item_value.setText(value);
    }
    //给输入框赋值
    public void setValueKey(String value){
        tv_act_my_item_key.setText(value);
    }
    //获取输入框控件
    public void setFocusable1(){
        et_act_my_item_value.setFocusable(false);
    }

    /*public void setMyLayout(int resId,String name){
        myItemId.setImageResource(resId);
        myItemTv.setText(name);

    }
    public void addMyLayout(int resId, String name, String yin){
        myItemId.setImageResource(resId);
        myItemTv.setText(name);
        myItemTvYin.setVisibility(View.VISIBLE);
        myItemTvYin.setText(yin);
    }
    public TextView setNotIcon(String name, String yin){
        myItemTv.setText(name);
        myItemId.setVisibility(View.GONE);
        myItemTvYin.setVisibility(View.VISIBLE);
        myItemTvYin.setText(yin);
        return myItemTvYin;
    }*/

}
