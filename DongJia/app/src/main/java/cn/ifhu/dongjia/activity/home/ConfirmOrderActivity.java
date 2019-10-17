package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sunfusheng.GlideImageView;
import com.yalantis.ucrop.view.GestureCropImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.me.AddressActivity;
import cn.ifhu.dongjia.base.BaseActivity;

public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.view_bg)
    View viewBg;
    @BindView(R.id.address)
    ImageView address;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.expand)
    ImageView expand;
    @BindView(R.id.iv_mch_logo)
    GlideImageView ivMchLogo;
    @BindView(R.id.tv_mch_name)
    TextView tvMchName;
    @BindView(R.id.ll_logo)
    LinearLayout llLogo;
    @BindView(R.id.iv_store_pic)
    GestureCropImageView ivStorePic;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_specification)
    TextView tvSpecification;
    @BindView(R.id.tv_store_price)
    TextView tvStorePrice;
    @BindView(R.id.tv_store_number)
    TextView tvStoreNumber;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.tv_mch_price)
    TextView tvMchPrice;
    @BindView(R.id.tv_freight)
    TextView tvFreight;
    @BindView(R.id.zong)
    TextView zong;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        tvTitle.setText("确认订单");
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.ll_logo)
    public void onLlLogoClicked() {
        goToActivity(StoreHomeActivity.class);
    }

    @OnClick(R.id.tv_ok)
    public void onTvOkClicked() {

    }

    @OnClick(R.id.rl_address)
    public void onRlAddressClicked() {
        goToActivity(AddressActivity.class);
    }
}
