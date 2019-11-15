package cn.ifhu.dongjia.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
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


import java.util.ArrayList;
import java.util.List;

import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;
import cn.ifhu.dongjia.model.post.AddCartPostData;
import cn.ifhu.dongjia.model.post.AttrBeanPost;
import cn.ifhu.dongjia.model.post.GoodsInfoPost;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.ShopCartService;
import cn.ifhu.dongjia.utils.GsonUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;
import cn.ifhu.dongjia.view.dialog.OnSkuListener;
import cn.ifhu.dongjia.view.dialog.SkuItemView;
import cn.ifhu.dongjia.view.dialog.SkuSelectScrollView;

/**
 * 商品选择属性
 */
public class GoodDialog extends Dialog {

    GoodsAttrInfoDataBean attrInfoData;
    private Context context;
    private Callback callback;
    SkuSelectScrollView scrollSkuList;

    TextView tvDelete;
    TextView tvAdd;
    TextView tvGoodNumber;
    TextView tvPrice;
    TextView tvInfo;
    TextView tvQuantity;
    GlideImageView ivLogo;
    private GlideImageView ivlogo;
    //商品详情里传过来的商品id
    private String good_id;
    //商品详情传过来的图片url;
    private String url;
    //已选的属性id
    int[] attrId;
    String[] attrString;
    List<GoodDetailsDataBean.AttrGroupListBean> attrGroupList;
    List<AttrBeanPost> attrBeanPostList = new ArrayList<>();


    public GoodDialog(@NonNull Context context) {
        this(context, R.style.CommonBottomDialogStyle);
    }

    public GoodDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        initView();
    }

    //初始化
    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_goods, null, false);
        setContentView(view);
        ImageButton imageButton = view.findViewById(R.id.ib_sku_close);
        tvDelete = findViewById(R.id.tv_delete);
        tvAdd = findViewById(R.id.tv_add);
        tvGoodNumber = findViewById(R.id.tv_good_number);
        ivlogo = findViewById(R.id.iv_sku_logo);
        tvInfo = view.findViewById(R.id.tv_sku_info);
        tvPrice = findViewById(R.id.tv_sku_selling_price);
        tvQuantity = view.findViewById(R.id.tv_sku_quantity);
        Button btnSybmit = view.findViewById(R.id.btn_submit);
        Button btnShopCart = view.findViewById(R.id.btn_shop_cart);
        scrollSkuList = view.findViewById(R.id.scroll_sku_list);
        //取消
        imageButton.setOnClickListener(v -> dismiss());
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
            }

            @Override
            public void onSelect() {
            }

            @Override
            public void onSkuSelected(int position, SkuItemView view) {
                attrId[position] = Integer.parseInt(view.getAttributeId());
                attrString[position] = view.getAttributeValue();

                StringBuilder attrInfo = new StringBuilder("已选: ");
                for (String attr : attrString) {
                    attrInfo.append(attr);
                }
                tvInfo.setText(attrInfo);
                //数组转换为Gson数据
                String attrData = GsonUtils.convertObject2Json(attrId);
                //商品属性数组
                attrGroupList.get(position);
                /**
                 * 商品属性选择接口
                 */
                RetrofitAPIManager.create(HomeService.class).GoodsAttrInfo(4, -1, -1, good_id, attrData)
                        .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<GoodsAttrInfoDataBean>(true) {
                    @Override
                    protected void onApiComplete() {
                    }

                    @Override
                    protected void onSuccees(BaseEntity<GoodsAttrInfoDataBean> t) throws Exception {
                        tvQuantity.setText("库存: " + t.getData().getNum() + "");
                        tvPrice.setText("￥" + t.getData().getPrice());
                        if (t.getData().getPic().equals("")){
                         ivlogo.load(url);
                        }else {
                            ivlogo.load(t.getData().getPic());
                        }
                        attrInfoData = t.getData();
                    }
                });
            }
        });
        //加入购物车
        btnShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.valueOf(quantity);
                if (attrInfoData == null){
                    ToastHelper.makeText("请选择商品规格").show();
                    return;
                }
                if (quantityInt > 0 && quantityInt <= attrInfoData.getNum()) {
                    callback.onAdded(attrInfoData, quantityInt);
                    dismiss();
                } else {
                    ToastHelper.makeText("商品数量超出库存，请修改数量").show();
                }
                for (int i = 0; i < attrId.length; i++) {
                    AttrBeanPost attrBeanPost = new AttrBeanPost();
                    attrBeanPost.setAttr_group_id(attrGroupList.get(i).getAttr_group_id());
                    attrBeanPost.setAttr_group_name(attrGroupList.get(i).getAttr_group_name());
                    attrBeanPost.setAttr_id(attrId[i]);
                    attrBeanPost.setAttr_name(attrString[i]);
                    //加入购物车数组、声明一个数组来保存对象上传
                    attrBeanPostList.add(attrBeanPost);
                }
                    getGoodsCart(quantity);

            }
        });
        //立即购买
        btnSybmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantity = tvGoodNumber.getText().toString();
                if (TextUtils.isEmpty(quantity)) {
                    return;
                }
                int quantityInt = Integer.valueOf(quantity);
                if (attrInfoData == null){
                    ToastHelper.makeText("请选择商品规格").show();
                    return;
                }
                if (quantityInt > 0 && quantityInt <= attrInfoData.getNum()) {
                    callback.onAdded(attrInfoData, quantityInt);
                    getGoodsInfo();
                } else {
                    ToastHelper.makeText("商品数量超出库存，请修改数量").show();
                }

            }
        });
    }

    /**
     * 添加购物车接口
     */
    public void getGoodsCart(String quantity) {
        AttrBeanPost attrBeanPost = new AttrBeanPost();
        String attr = GsonUtils.convertObject2Json(attrBeanPost);
        AddCartPostData addCartPostData = new AddCartPostData();
        addCartPostData.setAccess_token(UserLogic.getUser().getAccess_token());
        addCartPostData.setAttr(attr);
        addCartPostData.setGoods_id(good_id);
        addCartPostData.setNum(quantity);
        RetrofitAPIManager.create(ShopCartService.class).addCart(addCartPostData)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
            }

        });

    }

    /**
     * 立即购买接口
     */
    public void getGoodsInfo() {
        GoodsInfoPost goodsInfoPost = new GoodsInfoPost();
        goodsInfoPost.setGoods_id(good_id);
        goodsInfoPost.setNum(Integer.parseInt(tvGoodNumber.getText().toString()));
        List<GoodsInfoPost.AttrBean> attrBeanList = new ArrayList<>();
        GoodsInfoPost.AttrBean attrBean = new GoodsInfoPost.AttrBean();
        for (int i = 0; i < attrId.length; i++) {
            attrBean.setAttr_group_id(attrGroupList.get(i).getAttr_group_id());
            attrBean.setAttr_group_name(attrGroupList.get(i).getAttr_group_name());
            attrBean.setAttr_id(attrId[i]);
            attrBean.setAttr_name(attrString[i]);
            //加入购物车数组、声明一个数组来保存对象上传
            attrBeanList.add(attrBean);
        }
        goodsInfoPost.setAttr(attrBeanList);
        String goodsInfoData = GsonUtils.convertObject2Json(goodsInfoPost);
        dismiss();
        Intent intent = new Intent(context,ConfirmOrderActivity.class);
        intent.putExtra("goodsInfoData",goodsInfoData);
        context.startActivity(intent);

    }

    /**
     * 设置数据
     *
     * @param attrGroupList 商品详情里的规格数据
     * @param id            商家ID
     * @param callback      回调数据
     */
    public void setData(List<GoodDetailsDataBean.AttrGroupListBean> attrGroupList, String id,String url, Callback callback) {
        this.callback = callback;
        this.good_id = id;
        this.attrGroupList = attrGroupList;
        this.url = url;
        //初始化
        attrId = new int[attrGroupList.size()];
        attrString = new String[attrGroupList.size()];
        ivlogo.load(url);
        scrollSkuList.setSkuList(attrGroupList);

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
