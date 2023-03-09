package com.jymj.zhglxt.ui.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jymj.zhglxt.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: Mr.haozi
 * @CreateDate: 2023/1/4 14:06
 */
public class SpecsGroupAdapter extends RecyclerView.Adapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<String> spec_key;
    private List<Integer> hashMap;
    private OnItemClick onItemClick;

    public SpecsGroupAdapter(Context context, List<String> spec_key, List<Integer> hashMap) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.spec_key = spec_key;
        this.hashMap = hashMap;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_com_det_gwc_item, parent,false);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holder myHolder = (Holder)holder;
        myHolder.tv.setText(spec_key.get(position));
        switch (hashMap.get(position)) {
            case 0:
                myHolder.tv.setBackgroundDrawable(setShape("#000000", "#ffffff"));
                myHolder.tv.setTextColor(Color.parseColor("#333333"));
                break;
            case 1:
                myHolder.tv.setBackgroundDrawable(setShape("#ff5000", "#ffffff"));
                myHolder.tv.setTextColor(Color.parseColor("#ff5000"));
                break;
            case 2:
                myHolder.tv.setBackgroundDrawable(setShape("#999999", "#ffffff"));
                myHolder.tv.setTextColor(Color.parseColor("#cccccc"));
                break;
        }
        myHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spec_key.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item_com_det_gwc_fl);
        }
    }

    //设置
    private GradientDrawable setShape(String stroke, String fill) {
        int strokeWidth = 2; // 2 边框宽度
        int roundRadius = 5; // 5 圆角半径
        int strokeColor = Color.parseColor(stroke);//边框颜色
        int fillColor = Color.parseColor(fill);//内部填充颜色

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }

    public interface OnItemClick{
        void onClick(int position);
    }
}
