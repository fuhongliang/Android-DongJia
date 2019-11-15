package cn.ifhu.dongjia.activity.order;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.fragments.order.AfterSaleOrderFragment;
import cn.ifhu.dongjia.fragments.order.AllOrderFragment;
import cn.ifhu.dongjia.fragments.order.CancelOrderFragment;
import cn.ifhu.dongjia.fragments.order.FinishedOrderFragment;
import cn.ifhu.dongjia.fragments.order.UnPayingOrderFragment;
import cn.ifhu.dongjia.fragments.order.UnReceiptOrderFragment;
import cn.ifhu.dongjia.fragments.order.UnShippingOrderFragment;
import cn.ifhu.dongjia.view.dialog.RVPIndicator;

/**
 * 全部订单页面
 */
public class OrderListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.rvp_indicator)
    RVPIndicator rvpIndicator;
    @BindView(R.id.indicator)
    LinearLayout indicator;
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    private List<String> list = Arrays.asList("全部", "待付款", "待发货", "待收货", "售后", "已完成", "已取消");

    private List<Fragment> mFragmentsArrayList = new ArrayList<>();
    FragmentPagerAdapter mAdpter;
    int positon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ButterKnife.bind(this);
        tvTitle.setText("我的订单");
        mFragmentsArrayList.add(AllOrderFragment.newInstance());
        mFragmentsArrayList.add(UnPayingOrderFragment.newInstance());
        mFragmentsArrayList.add(UnShippingOrderFragment.newInstance());
        mFragmentsArrayList.add(UnReceiptOrderFragment.newInstance());
        mFragmentsArrayList.add(AfterSaleOrderFragment.newInstance());
        mFragmentsArrayList.add(FinishedOrderFragment.newInstance());
        mFragmentsArrayList.add(CancelOrderFragment.newInstance());
        initViewPager();

    }

    public void initViewPager() {
        mAdpter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentsArrayList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentsArrayList.size();
            }
        };
        rvpIndicator.setTitleList(list);
        vpContent.setOffscreenPageLimit(7);
        vpContent.setAdapter(mAdpter);
        //取出索引
        positon = getIntent().getIntExtra("position",0);
        rvpIndicator.setViewPager(vpContent, positon);
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }
}
