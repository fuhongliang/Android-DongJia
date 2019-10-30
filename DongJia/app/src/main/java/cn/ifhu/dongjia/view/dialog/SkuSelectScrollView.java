package cn.ifhu.dongjia.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.wuhenzhizao.sku.widget.SkuMaxHeightScrollView;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
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
     * @param attrGroupList
     */
    public void setSkuList(List<GoodDetailsDataBean.AttrGroupListBean> attrGroupList) {
        // 清空sku视图
        skuContainerLayout.removeAllViews();
        int index = 0;
        for (GoodDetailsDataBean.AttrGroupListBean it :attrGroupList ) {
            // 构建sku视图
            SkuItemLayout itemLayout = new SkuItemLayout(getContext());
            itemLayout.setId(ViewUtils.generateViewId());
            itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            itemLayout.buildItemLayout(index++, it.getAttr_group_name(), it.getAttr_list());
            itemLayout.setListener(this);
            skuContainerLayout.addView(itemLayout);
        }

    }

    @Override
    public void onSelect(int position,SkuItemView view) {
        //打印
        Log.d("分组位置position = ",position+"属性id =" + view.getAttributeId()+"属性值="+view.getAttributeValue());
        ((SkuItemLayout)skuContainerLayout.getChildAt(position)).clearItemViewStatus();
        ((SkuItemLayout)skuContainerLayout.getChildAt(position)).optionItemViewSelectStatus(view.getAttributeValue());
        listener.onSkuSelected(position,view);
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


//    /**
//     * 重置所有属性的选中状态
//     */
//    private void clearAllLayoutStatus() {
//        for (int i = 0; i < skuContainerLayout.getChildCount(); i++) {
//            SkuItemLayout itemLayout = (SkuItemLayout) skuContainerLayout.getChildAt(i);
//            itemLayout.clearItemViewStatus();
//        }
//    }


}