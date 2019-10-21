package cn.ifhu.dongjia.fragments.shopCart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseFragment;

/**
 * 购物车页面
 */

public class ShopCartFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_avatar)
    RecyclerView ivAvatar;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.iv_isSelect)
    ImageView ivIsSelect;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.options1)
    RelativeLayout options1;


    public static BaseFragment newInstance() {
        return new ShopCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText("购物车");
        ivBack.setVisibility(View.GONE);
        return view;
    }

    @OnClick(R.id.tv_ok)
    public void onTvOkClicked() {

    }

    @OnClick(R.id.iv_isSelect)
    public void onIvIsSelectClicked() {
    }

    @OnClick(R.id.rl_select)
    public void onRlSelectClicked() {
    }
}
