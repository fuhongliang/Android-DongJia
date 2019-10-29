package cn.ifhu.dongjia.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wuhenzhizao.sku.widget.SkuMaxHeightScrollView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.utils.ViewUtils;


public class SkuSelectScrollView extends SkuMaxHeightScrollView implements SkuItemLayout.OnSkuItemSelectListener {
    private LinearLayout skuContainerLayout;
    private List<GoodsAttrInfoDataBean.AttrListBean> attrList;
    private OnSkuListener listener;                    // sku选中状态回调接口


    public SkuSelectScrollView(Context context) {
        super(context);
        init(context, null);
    }

    public SkuSelectScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setFillViewport(true);
        setOverScrollMode(OVER_SCROLL_NEVER);
        skuContainerLayout = new LinearLayout(context, attrs);
        skuContainerLayout.setId(ViewUtils.generateViewId());
        skuContainerLayout.setOrientation(LinearLayout.VERTICAL);
        skuContainerLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        addView(skuContainerLayout);
    }

    /**
     * 设置属性
     *
     * @param dataMap
     */
    public void setSkuList(Map<String, List<String>> dataMap) {
        // 清空sku视图
        skuContainerLayout.removeAllViews();
        int index = 0;
        for (Iterator<Map.Entry<String, List<String>>> it = dataMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, List<String>> entry = it.next();
            // 构建sku视图
            SkuItemLayout itemLayout = new SkuItemLayout(getContext());
            itemLayout.setId(ViewUtils.generateViewId());
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            itemLayout.buildItemLayout(index++, entry.getKey(), entry.getValue());
            itemLayout.setListener(this);
            skuContainerLayout.addView(itemLayout);
            //初始化状态下，所有属性信息设置为空
//            attrList.add(new GoodsAttrInfoDataBean.AttrListBean(entry.getKey(),""));
        }

    }

    @Override
    public void onSelect() {

    }


    /**
     * 设置监听接口
     *
     * @param listener {@link OnSkuListener}
     */
    public void setListener(OnSkuListener listener) {
        this.listener = listener;
    }

    /**
     * 获取第一个未选中的属性名
     *
     * @return
     */
    public String getFirstUnelectedAttributeName() {
        for (int i = 0; i < skuContainerLayout.getChildCount(); i++) {
            SkuItemLayout itemLayout = (SkuItemLayout) skuContainerLayout.getChildAt(i);
            if (!itemLayout.isSelected()) {
                return itemLayout.getAttributeName();
            }
        }
        return "";
    }


    /**
     * 重置所有属性的选中状态
     */
    private void clearAllLayoutStatus() {
        for (int i = 0; i < skuContainerLayout.getChildCount(); i++) {
            SkuItemLayout itemLayout = (SkuItemLayout) skuContainerLayout.getChildAt(i);
            itemLayout.clearItemViewStatus();
        }
    }


}