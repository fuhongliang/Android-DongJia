package cn.ifhu.dongjia.view.dialog;


import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;

/**
 * 连到goodDialog的商品属性
 * Created by wuhenzhizao on 2017/8/30.
 */
public interface OnSkuListener {
    /**
     * 属性取消选中
     *
     */
    void onUnselected();

    /**
     * 属性选中
     *
     */
    void onSelect();

    /**
     * sku选中
     *
     */
    void onSkuSelected();
}