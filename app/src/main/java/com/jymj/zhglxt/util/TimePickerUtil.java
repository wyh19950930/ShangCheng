package com.jymj.zhglxt.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import org.wangchenlong.datescroller.widget.DateScrollerDialog;
import org.wangchenlong.datescroller.widget.data.Type;
import org.wangchenlong.datescroller.widget.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimePickerUtil {


    private static final long HUNDRED_YEARS = 100L * 365 * 1000 * 60 * 60 * 24L; // 100年

    //    private TextView mTvTime;
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//yyyy-MM-dd
    private static long mLastTime = System.currentTimeMillis(); // 上次设置的开始时间
    private static long mLastFinishTime = System.currentTimeMillis(); // 上次设置的结束时间
//    public static OnDateRickerLinear monClickLinear;
    public static OnTimePickerLister monClickLinear;

    // 数据的回调
    private static OnDateSetListener mOnDateSetListener = new OnDateSetListener() {
        @Override public void onDateSet(DateScrollerDialog timePickerView, long milliseconds, long milliFinishseconds) {
            mLastTime = milliseconds;
            mLastFinishTime = milliFinishseconds;
            String text = getDateToString(milliseconds);
            String text2 = getDateToString(milliFinishseconds);
//            tvActMainQxzsjqj.setText(text + " - " + text2);
            monClickLinear.onClick(text + " - " + text2);

        }
    };
    public static void initTimePickerView(Activity activity, OnTimePickerLister onTimePickerLister) {
        monClickLinear = onTimePickerLister;
        // 选择日期
        DateScrollerDialog dialog = new DateScrollerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setTitleStringId("请选择日期")
                .setMinMilliseconds(System.currentTimeMillis() - HUNDRED_YEARS)
                .setMaxMilliseconds(System.currentTimeMillis() + HUNDRED_YEARS)
                // 上次设置的开始时间，结束时间
                .setCurMilliseconds(mLastTime, mLastFinishTime)
                .setCallback(mOnDateSetListener)
                .build();

        if (dialog != null) {
            if (!dialog.isAdded()) {
                dialog.show((activity.getFragmentManager()), "year_month_day");
            }
        }
    }


    private static String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    /*public OnTimePickerLister onTimePickerLister;


    public void setOnTimePickerLister(OnTimePickerLister onTimePickerLister) {
        this.onTimePickerLister = onTimePickerLister;
    }*/

    //初始化时间选择器  年 月
    /*public static void initTimePickerView(Activity activity,OnTimePickerLister onTimePickerLister) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                onTimePickerLister.onClick(getTime(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
        })
                .isDialog(true)
                .setType(new boolean []{true, true, false, false, false, false})//booleanArrayOf(true, true, false, false, false, false)
                .setItemVisibleCount(3)
                .setCancelColor(Color.parseColor("#999999"))
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = timePickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        timePickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        timePickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        timePickerView.show();
    }*/
    //时间格式转换
    private static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");//  yyyy-MM-dd HH:mm:ss
        return format.format(date);
    }
    //初始化时间选择器  年 月 日
    public static void initTimePickerViewNyr(Activity activity,String nyr,OnTimePickerLister onTimePickerLister) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Calendar selectedDate = Calendar.getInstance();
        if (!nyr.equals("")){
            String[] split = nyr.split("-");
            if (split.length==3){
                selectedDate.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, Integer.parseInt(split[2]));
            }
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 01, 01);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                onTimePickerLister.onClick(getTimeNyr(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
        })
                .isDialog(true)
                .setType(new boolean []{true, true, true, false, false, false})//booleanArrayOf(true, true, false, false, false, false)
                .setItemVisibleCount(3)
                .setCancelColor(Color.parseColor("#999999"))
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
//                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = timePickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        timePickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        timePickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        timePickerView.show();
    }
    //初始化时间选择器  年 月
    public static void initTimePickerViewNy(Activity activity,String nyr,OnTimePickerLister onTimePickerLister) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// HH:mm:ss
        Calendar selectedDate = Calendar.getInstance();
        if (!nyr.equals("")){
            String[] split = nyr.split("-");
            if (split.length==2){
                selectedDate.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 0);//Integer.parseInt(split[2])
            }
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 01, 01);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                onTimePickerLister.onClick(getTimeNy(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
        })
                .isDialog(true)
                .setType(new boolean []{true, true, false, false, false, false})//booleanArrayOf(true, true, false, false, false, false)
                .setItemVisibleCount(3)
                .setCancelColor(Color.parseColor("#999999"))
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
//                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = timePickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        timePickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        timePickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        timePickerView.show();
    }
    //初始化时间选择器  年
    public static void initTimePickerViewN(Activity activity,String nyr,OnTimePickerLister onTimePickerLister) {
        Calendar selectedDate = Calendar.getInstance();
        if (!nyr.equals("")){
            String[] split = nyr.split("-");
            if (split.length==1){
                selectedDate.set(Integer.parseInt(split[0])+1, 0, 0);//Integer.parseInt(split[2])
            }
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 01, 01);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));

        TimePickerView timePickerView = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                onTimePickerLister.onClick(getTimeN(date));
            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {

                    }
        })
                .isDialog(true)
                .setType(new boolean []{true, false, false, false, false, false})//booleanArrayOf(true, true, false, false, false, false)
                .setItemVisibleCount(3)
                .setCancelColor(Color.parseColor("#999999"))
                .setLineSpacingMultiplier(2.0f)
                .setDate(selectedDate)
//                .setRangDate(startDate,endDate)
                .isAlphaGradient(true)
                .build();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        params.leftMargin = 0;
        params.rightMargin = 0;
        ViewGroup contentContainer = timePickerView.getDialogContainerLayout();
        contentContainer.setLayoutParams(params);
        timePickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);
        timePickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        timePickerView.show();
    }
    //时间格式转换
    private static String getTimeNyr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//  yyyy-MM-dd HH:mm:ss
        return format.format(date);
    }
    //时间格式转换
    private static String getTimeNy(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");//  yyyy-MM-dd HH:mm:ss
        return format.format(date);
    }
    //时间格式转换
    private static String getTimeN(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");//  yyyy-MM-dd HH:mm:ss
        return format.format(date);
    }
    //点击回调
    public interface OnTimePickerLister{
        void onClick(String data);
    }

}
