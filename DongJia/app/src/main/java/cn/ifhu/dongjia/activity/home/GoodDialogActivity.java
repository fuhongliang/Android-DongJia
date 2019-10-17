package cn.ifhu.dongjia.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.sunfusheng.GlideImageView;
import com.sunfusheng.progress.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.model.data.ProductData;
import cn.ifhu.dongjia.model.data.Sku;
import cn.ifhu.dongjia.model.data.SkuAttribute;
import cn.ifhu.dongjia.view.GoodView.OnSkuListener;
import cn.ifhu.dongjia.view.GoodView.SkuSelectScrollView;

/**
 * 商品规格选择购买商品
 */
public class GoodDialogActivity extends Dialog {

    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_in_stock)
    TextView tvInStock;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.btn_submit)
    TextView btnSubmit;
    @BindView(R.id.scroll_sku_list)
    SkuSelectScrollView scrollSkuList;
    @BindView(R.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R.id.tv_good_number)
    TextView tvGoodNumber;


    private Context context;
    private Sku selectedSku;
    private ProductData product;
    private List<Sku> skuList;
    private Callback callback;
    private String priceFormat;
    private String stockQuantityFormat;


    public GoodDialogActivity(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public GoodDialogActivity(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.activity_good_dialog, null);
        Window window = this.getWindow();
        window.setContentView(view);
        ButterKnife.bind(this, view);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //减少
        ivDelete.setOnClickListener(new View.OnClickListener() {
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
        //增加
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity) || selectedSku == null) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt < selectedSku.getStockQuantity()) {
                    String newQuantity = String.valueOf(quantityInt + 1);
                    tvGoodNumber.setText(newQuantity);
                    updateQuantityOperator(quantityInt + 1);
                }
            }
        });
        //输入
        tvGoodNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != EditorInfo.IME_ACTION_DONE || selectedSku == null) {
                    return false;
                }
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return false;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt <= 1) {
                    tvGoodNumber.setText("1");
//                    tvGoodNumber.setSelection(1);
                    updateQuantityOperator(1);
                } else if (quantityInt >= selectedSku.getStockQuantity()) {
                    String newQuantity = String.valueOf(selectedSku.getStockQuantity());
                    tvGoodNumber.setText(newQuantity);
//                    tvGoodNumber.setSelection(newQuantity.length());
                    updateQuantityOperator(selectedSku.getStockQuantity());
                } else {
//                    tvGoodNumber.setSelection(quantity.length());
                    updateQuantityOperator(quantityInt);
                }
                return false;
            }
        });
        //sku整体的点击事件
        scrollSkuList.setOnSkuListener(new OnSkuListener() {
            @Override
            public void onUnselected(SkuAttribute unselectedAttribute) {
                selectedSku = null;
                //默认第一张照片
                GlideApp.with(context).load(product.getPictureUrl()).into(ivAvatar);
                tvInStock.setText(String.format(stockQuantityFormat, product.getStockQuantity()));
                String selected = getSelected();
                //设置回掉
                callback.onSelect(selected);
                //增加前缀
                if (isHaveSelect()) {
                    selected = "已选： " + selected;
                } else {
                    selected = "请选择： " + selected;
                    tvPrice.setText(String.format(priceFormat, product.getMinPrice()));
                }
                tvInfo.setText(selected);
                btnSubmit.setEnabled(false);
                tvGoodNumber.setText("1");
                updateQuantityOperator(1);
            }

            @Override
            public void onSelect(SkuAttribute selectAttribute) {
                String selected = getSelected();
                //设置回掉
                callback.onSelect(selected);
                //增加前缀
                if (isHaveSelect()) {
                    selected = "已选： " + selected;
                } else {
                    selected = "请选择： " + selected;
                    tvPrice.setText(String.format(priceFormat, product.getMinPrice()));
                }
                tvInfo.setText(selected);
                tvGoodNumber.setText("1");
                updateQuantityOperator(1);
            }

            @Override
            public void onSkuSelected(Sku sku) {
                selectedSku = sku;
                GlideApp.with(context).load(selectedSku.getPictureUrl()).into(ivAvatar);
                List<SkuAttribute> attributeList = selectedSku.getAttributes();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < attributeList.size(); i++) {
                    if (i != 0) {
                        builder.append("　");
                    }
                    SkuAttribute attribute = attributeList.get(i);
                    builder.append("\"" + attribute.getValue() + "\"");
                }
                tvInfo.setText("已选：" + builder.toString());
                setHaveSelect(true);
                //设置价格
                tvPrice.setText(String.format(priceFormat, sku.getPrice()));
                callback.onSelect(builder.toString());
                tvInStock.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
                btnSubmit.setEnabled(true);
                if (sku.getStockQuantity() >= 1) {
                    tvGoodNumber.setText("1");
                    updateQuantityOperator(1);
                } else {
                    tvGoodNumber.setText("0");
                    updateQuantityOperator(0);
                }
            }
        });
        //添加
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.parseInt(quantity);
                if (quantityInt > 0 && quantityInt <= selectedSku.getStockQuantity()) {
                    callback.onAdded(selectedSku, quantityInt);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "商品数量超出库存，请修改数量", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * 设置数据
     *
     * @param product
     * @param callback
     */
    public void setData(final ProductData product, Callback callback) {
        this.product = product;
        this.skuList = product.getSkus();
        this.callback = callback;
        priceFormat = context.getString(R.string.comm_price_format);
        stockQuantityFormat = context.getString(R.string.sku_stock);
        updateSkuData();
        updateQuantityOperator(1);
    }

    /**
     * 更新数据
     */
    private void updateSkuData() {
        scrollSkuList.setSkuList(product.getSkus());
        //默认选择第一个
        Sku firstSku = product.getSkus().get(0);
        if (firstSku.getStockQuantity() > 0) {
            selectedSku = firstSku;
            // 选中第一个sku
            scrollSkuList.setSelectedSku(selectedSku);
            GlideApp.with(context).load(product.getPictureUrl()).into(ivAvatar);
            tvPrice.setText(String.format(priceFormat, selectedSku.getPrice()));
            tvInStock.setText(String.format(stockQuantityFormat, selectedSku.getStockQuantity()));
            btnSubmit.setEnabled(selectedSku.getStockQuantity() > 0);
            List<SkuAttribute> attributeList = selectedSku.getAttributes();
            StringBuilder builder = new StringBuilder();
            int attributeListSize = attributeList.size();
            for (int i = 0; i < attributeListSize; i++) {
                if (i != 0) {
                    builder.append("　");
                }
                SkuAttribute attribute = attributeList.get(i);
                builder.append("\"" + attribute.getValue() + "\"");
            }
            tvInfo.setText("已选：" + builder.toString());
            setHaveSelect(true);
            callback.onSelect(builder.toString());
        } else {
            GlideApp.with(context).load(product.getPictureUrl()).into(ivAvatar);
            tvPrice.setText(String.format(priceFormat, product.getMinPrice()));
            tvInStock.setText(String.format(stockQuantityFormat, product.getStockQuantity()));
            btnSubmit.setEnabled(false);
            tvInfo.setText("请选择：" + skuList.get(0).getAttributes().get(0).getKey());
            setHaveSelect(false);
            callback.reUnSelect();
        }
    }

    /**
     * 获取已选中的
     *
     * @return
     */
    public boolean isHaveSelect;

    public boolean isHaveSelect() {
        return isHaveSelect;
    }

    public void setHaveSelect(boolean haveSelect) {
        isHaveSelect = haveSelect;
    }

    /**
     * 获取显示的内容
     *
     * @return
     */
    private String getSelected() {
        List<SkuAttribute> skuAttributeList = scrollSkuList.getSelectedAttributeList();
        if (skuAttributeList != null) {
            //是否有属性是选中的
            setHaveSelect(false);
            StringBuilder stringBuilder = new StringBuilder();
            int skuAttributeListSize = skuAttributeList.size();
            for (int i = 0; i < skuAttributeListSize; i++) {
                SkuAttribute skuAttribute = skuAttributeList.get(i);
                if (i != 0) {
                    stringBuilder.append("  ");
                }
                if (TextUtils.isEmpty(skuAttribute.getValue())) {
                    stringBuilder.append(skuAttribute.getKey());
                } else {
                    stringBuilder.append(skuAttribute.getValue());
                    setHaveSelect(true);
                }
            }
            return stringBuilder.toString();
        } else
            return null;
    }

    private void updateQuantityOperator(int newQuantity) {
        if (selectedSku == null) {
            ivDelete.setEnabled(false);
            ivAdd.setEnabled(false);
            tvGoodNumber.setEnabled(false);
        } else {
            if (newQuantity <= 1) {
                ivDelete.setEnabled(false);
                ivAdd.setEnabled(true);
            } else if (newQuantity >= selectedSku.getStockQuantity()) {
                ivDelete.setEnabled(true);
                ivAdd.setEnabled(false);
            } else {
                ivDelete.setEnabled(true);
                ivAdd.setEnabled(true);
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
    }

    public interface Callback {
        //添加购物车
        void onAdded(Sku sku, int quantity);

        //已选
        void onSelect(String selected);

        //恢复默认未选
        void reUnSelect();
    }

}
