package cn.ifhu.dongjia.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.sunfusheng.GlideImageView;
import com.wuhenzhizao.titlebar.utils.AppUtils;


import java.util.List;
import java.util.Map;

import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.view.dialog.OnSkuListener;
import cn.ifhu.dongjia.view.dialog.SkuSelectScrollView;

/**
 * 商品选择属性
 */
public class GoodDialog extends Dialog {

    private GoodsAttrInfoDataBean goodsAttrInfoDataBean;
    private Context context;
    private Callback callback;
    SkuSelectScrollView scrollSkuList;
    private GoodsAttrInfoDataBean attrInfoData;
    private GoodDetailsDataBean.AttrGroupListBean attrGroupList;

    private String tvGoodNumberFormat;
    TextView tvDelete;
    TextView tvAdd;
    TextView tvGoodNumber;
    private GlideImageView ivlogo;

    public GoodDialog(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public GoodDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_goods, null, false);
        setContentView(view);
        ImageButton imageButton = view.findViewById(R.id.ib_sku_close);
        tvDelete = findViewById(R.id.tv_delete);
        tvAdd = findViewById(R.id.tv_add);
        tvGoodNumber = findViewById(R.id.tv_good_number);
        ivlogo = findViewById(R.id.iv_sku_logo);
        TextView tvInfo = view.findViewById(R.id.tv_sku_info);
        TextView tvQuantity = view.findViewById(R.id.tv_sku_quantity);
        Button btnSybmit = view.findViewById(R.id.btn_submit);
        scrollSkuList = view.findViewById(R.id.scroll_sku_list);
        //取消
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //减少
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt > 1) {
                    String newQuantity = String.valueOf(quantityInt - 1);
                    tvGoodNumber.setText(newQuantity);
//                    tvGoodNumber.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt - 1);
                }
            }
        });
        //添加
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity) || attrInfoData == null) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt < attrInfoData.getNum()) {
                    String newQuantity = String.valueOf(quantityInt + 1);
                    tvGoodNumber.setText(newQuantity);
//                    tvGoodNumber.setSelection(newQuantity.length());
                    updateQuantityOperator(quantityInt + 1);
                }
            }
        });
        //购买数量
        tvGoodNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != EditorInfo.IME_ACTION_DONE || attrInfoData == null) {
                    return false;
                }
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return false;
                }
                int quantityInt = Integer.valueOf(quantity);
                if (quantityInt <= 1) {
                    tvGoodNumber.setText("1");
                    updateQuantityOperator(1);
                } else if (quantityInt >= attrInfoData.getNum()) {
                    String newQuantity = String.valueOf(attrInfoData.getNum());
                    tvGoodNumber.setText(newQuantity);
                    updateQuantityOperator(attrInfoData.getNum());
                } else {
                    updateQuantityOperator(quantityInt);
                }
                return false;
            }
        });
        //商品属性
        scrollSkuList.setListener(new OnSkuListener() {
            @Override
            public void onUnselected() {
                attrInfoData = null;
                // TODO: 2019-10-28 默认图片
                tvGoodNumber.setText(String.format(tvGoodNumberFormat, attrInfoData.getNum()));
                String firstUnselectedAttributeName = scrollSkuList.getFirstUnelectedAttributeName();
                tvInfo.setText("请选择: " + firstUnselectedAttributeName);
                btnSybmit.setEnabled(false);
                String quantity = tvGoodNumber.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }

            @Override
            public void onSelect() {
                String firstUnselectedAttributeName = scrollSkuList.getFirstUnelectedAttributeName();
                tvInfo.setText("请选择: " + firstUnselectedAttributeName);
            }

            @Override
            public void onSkuSelected() {
                List<GoodsAttrInfoDataBean.AttrListBean> attrList = attrInfoData.getAttr_list();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < attrList.size(); i++) {
                    if (i != 0) {
                        builder.append("  ");
                    }
                    GoodsAttrInfoDataBean.AttrListBean attr = attrList.get(i);
                    builder.append("\"" + attr.getAttr_name() + "\"");
                }
                tvInfo.setText("已选: " + builder.toString());
                tvQuantity.setText(String.format(tvGoodNumberFormat, attrInfoData.getNum()));
                btnSybmit.setEnabled(true);
                String quantity = tvGoodNumber.getText().toString();
                if (!TextUtils.isEmpty(quantity)) {
                    updateQuantityOperator(Integer.valueOf(quantity));
                } else {
                    updateQuantityOperator(0);
                }
            }
        });
        //确定购买
        btnSybmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.valueOf(quantity);
                if (quantityInt > 0 && quantityInt <= attrInfoData.getNum()) {
                    callback.onAdded(attrInfoData, quantityInt);
                    dismiss();
                } else {
                    ToastHelper.makeText("商品数量超出库存，请修改数量").show();
                }
            }
        });
    }

    public void setData(Map<String, List<String>> dataMap, Callback callback) {
        this.callback = callback;
        scrollSkuList.setSkuList(dataMap);
    }

    public void updateQuantityOperator(int newQuantity) {
        if (attrInfoData == null) {
            tvDelete.setEnabled(false);
            tvAdd.setEnabled(false);
            tvGoodNumber.setEnabled(false);
        } else {
            if (newQuantity <= 1) {
                tvDelete.setEnabled(false);
                tvAdd.setEnabled(true);
            } else if (newQuantity >= attrInfoData.getNum()) {
                tvDelete.setEnabled(true);
                tvAdd.setEnabled(false);
            } else {
                tvDelete.setEnabled(true);
                tvAdd.setEnabled(true);
            }
            tvGoodNumber.setEnabled(true);
        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 解决键盘遮挡输入框问题
        Window window = getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.getDecorView().setPadding(0, 0, 0, 0);
        AppUtils.transparencyBar(getWindow());
    }

    public interface Callback {
        void onAdded(GoodsAttrInfoDataBean goodsAttrInfoDataBean, int quantity);
    }
}
