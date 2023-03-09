package com.jymj.zhglxt.util.gwc;

/**
 * 商品回调接口
 * @Author: Mr.haozi
 * @CreateDate: 2022/12/22 14:34
 */
public interface GoodsCallback {
    /**
     * 选中店铺
     * @param shopId 店铺id
     * @param state 是否选中
     */
    void checkedStore(int shopId,boolean state);

    /**
     * 计算价格
     */
    void calculationPrice();

}
