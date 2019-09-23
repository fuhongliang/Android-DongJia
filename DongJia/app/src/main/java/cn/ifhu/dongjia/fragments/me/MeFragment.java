package cn.ifhu.dongjia.fragments.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.me.AboutUsActivity;
import cn.ifhu.dongjia.activity.me.AddressActivity;
import cn.ifhu.dongjia.base.BaseFragment;

/**
 * 我的页面
 */
public class MeFragment extends BaseFragment {
    Unbinder unbinder;

    public static BaseFragment newInstance() {
        return new MeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //我的售后
    @OnClick(R.id.ll_after_sale)
    public void onLlAfterSaleClicked() {

    }

    //我的收藏
    @OnClick(R.id.ll_content)
    public void onLlContentClicked() {
    }

    //收货地址
    @OnClick(R.id.ll_address)
    public void onLlAddressClicked() {
        goToActivity(AddressActivity.class);
    }

    //关于我们
    @OnClick(R.id.ll_about_us)
    public void onLlAboutUsClicked() {
        goToActivity(AboutUsActivity.class);
    }

    //全部订单
    @OnClick(R.id.rl_order)
    public void onRlOrderClicked() {
    }

    //待付款
    @OnClick(R.id.ll_wait_payment)
    public void onLlWaitPaymentClicked() {
    }

    //待发货
    @OnClick(R.id.ll_wait_ship)
    public void onLlWaitShipClicked() {
    }

    //待收货
    @OnClick(R.id.ll_wait_receipt)
    public void onLlWaitReceiptClicked() {
    }

    //已完成
    @OnClick(R.id.ll_completed)
    public void onLlCompletedClicked() {
    }
}
