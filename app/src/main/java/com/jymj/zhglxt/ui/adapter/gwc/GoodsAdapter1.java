package com.jymj.zhglxt.ui.adapter.gwc;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.ui.bean.gwc.CarResponse;
import com.jymj.zhglxt.ui.bean.homepage.GwcList;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * 商品适配器
 * @author llw
 */
public class GoodsAdapter1 extends BaseQuickAdapter<GwcList.DataBean.ContentBean, BaseViewHolder> {

    private List<GwcList.DataBean.ContentBean> storeBean;

    public GoodsAdapter1(int layoutResId, @Nullable List<GwcList.DataBean.ContentBean> data) {
        super(layoutResId, data);
        storeBean = data;
    }



    /**
     * 控制商品是否选中
     */
    public void controlGoods(String shopId, boolean state) {

        //根据店铺id选中该店铺下所有商品
        for (GwcList.DataBean.ContentBean orderDataBean : storeBean) {
            //店铺id等于传递过来的店铺id  则选中该店铺下所有商品
            if (orderDataBean.getShoppingCartId().equals(shopId)) {
                    orderDataBean.setChecked(state);
                    //刷新
                    notifyDataSetChanged();
            }
        }
    }
    @Override
    protected void convert(BaseViewHolder helper, GwcList.DataBean.ContentBean item) {
        String type = "";
        if (item.getStockInfo().getSpecA()!=null){
            type = item.getStockInfo().getSpecA().getValue();
        }
        if (item.getStockInfo().getSpecB()!=null){
            type = type +" "+ item.getStockInfo().getSpecB().getValue();
        }
        if (item.getStockInfo().getSpecC()!=null){
            type = type +" "+ item.getStockInfo().getSpecC().getValue();
        }
        helper.setText(R.id.tv_good_name, item.getName())
                .setText(R.id.tv_good_color,type)
                .setText(R.id.tv_good_size,"")
                .setText(R.id.tv_goods_price,item.getPrice()+"")
                .setText(R.id.tv_goods_num,item.getQuantity()+"");
        ImageView goodImg = helper.getView(R.id.iv_goods);
        Glide.with(mContext).load(item.getPictureInfo().getUrl()).into(goodImg);
        ImageView checkedGoods = helper.getView(R.id.iv_checked_goods);
        //判断商品是否选中
        if (item.isChecked()) {
            checkedGoods.setImageDrawable(mContext.getDrawable(R.drawable.ic_checked));
        } else {
            checkedGoods.setImageDrawable(mContext.getDrawable(R.drawable.ic_check));
        }
        //添加点击事件
        helper.addOnClickListener(R.id.iv_checked_goods)//选中商品
                .addOnClickListener(R.id.tv_increase_goods_num)//增加商品
                .addOnClickListener(R.id.tv_reduce_goods_num);//减少商品

    }
}

