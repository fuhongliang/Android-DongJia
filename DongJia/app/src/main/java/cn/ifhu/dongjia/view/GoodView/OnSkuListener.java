package cn.ifhu.dongjia.view.GoodView;


import cn.ifhu.dongjia.model.data.Sku;
import cn.ifhu.dongjia.model.data.SkuAttribute;

/**
 * @author madreain
 * @date 2019-07-27.
 * module：
 * description：
 */
public interface OnSkuListener {
    /**
     * 属性取消选中
     *
     * @param unselectedAttribute
     */
    void onUnselected(SkuAttribute unselectedAttribute);

    /**
     * 属性选中
     *
     * @param selectAttribute
     */
    void onSelect(SkuAttribute selectAttribute);

    /**
     * sku选中
     *
     * @param sku
     */
    void onSkuSelected(Sku sku);
}