package cn.ifhu.dongjia.activity.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sunfusheng.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;

public class GoodDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_avatar_bg)
    GlideImageView ivAvatarBg;
    @BindView(R.id.tv_original_price)
    TextView tvOriginalPrice;
    @BindView(R.id.tv_current_price)
    TextView tvCurrentPrice;
    @BindView(R.id.tv_show_integral)
    TextView tvShowIntegral;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.options1)
    TextView options1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_service1)
    LinearLayout llService1;
    @BindView(R.id.ll_service2)
    LinearLayout llService2;
    @BindView(R.id.ll_service3)
    LinearLayout llService3;
    @BindView(R.id.ll_service4)
    LinearLayout llService4;
    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_goto_store)
    TextView tvGotoStore;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.options2)
    ImageView options2;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.options3)
    ImageView options3;
    @BindView(R.id.rl_shop_car)
    RelativeLayout rlShopCar;
    @BindView(R.id.tv_shop_cart)
    TextView tvShopCart;
    @BindView(R.id.tv_buy)
    TextView tvBuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_good_details);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_goto_store)
    public void onTvGotoStoreClicked() {
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_shop_car)
    public void onRlShopCarClicked() {
    }

    @OnClick(R.id.tv_shop_cart)
    public void onTvShopCartClicked() {
    }

    @OnClick(R.id.tv_buy)
    public void onTvBuyClicked() {
    }
}
