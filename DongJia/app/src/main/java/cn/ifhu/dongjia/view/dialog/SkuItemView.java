package cn.ifhu.dongjia.view.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.Nullable;

import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.utils.ScreenUtils;


/**
 * Created by wuhenzhizao on 2017/7/31.
 */

public class SkuItemView extends TextView {
    private String attributeValue;
    private String attributeId;

    public SkuItemView(Context context) {
        super(context);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SkuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.sku_item_selector);
        setTextColor(getResources().getColorStateList(R.color.sku_item_text_selector));
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 11);
        setSingleLine();
        setGravity(Gravity.CENTER);
        setPadding(ScreenUtils.dp2PxInt(context, 10), 0, ScreenUtils.dp2PxInt(context, 10), 0);

        setMinWidth(ScreenUtils.dp2PxInt(context, 45));
        setMaxWidth(ScreenUtils.dp2PxInt(context, 200));
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeValue(String attributeValue, String attributeId) {
        this.attributeValue = attributeValue;
        this.attributeId = attributeId;
        setText(attributeValue);
    }
}
