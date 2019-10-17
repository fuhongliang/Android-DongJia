package cn.ifhu.dongjia.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.AddressAdpter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.data.AddressDataBean;

/**
 * 收货地址页面
 */
public class AddressActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.rl_add_address)
    RelativeLayout rlAddAddress;

    AddressAdpter addressAdpter;
    private List<AddressDataBean.AddressListBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        tvTitle.setText("我的收货地址");
        addressAdpter = new AddressAdpter(mData, this, new AddressAdpter.OnClickItem() {
            @Override
            public void Delete(int position) {
                //TODO:删除地址接口
            }

            @Override
            public void Edit(int position) {
                goToActivity(EditAddressActivity.class);
            }
        });
        rvAddress.setNestedScrollingEnabled(false);
        rvAddress.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvAddress.setAdapter(addressAdpter);
        rvAddress.setOnScrollListener(new LoadMoreScrollListener(rvAddress));

    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_add_address)
    public void onRlOkClicked() {
        goToActivity(AddAddressActivity.class);
    }
}
