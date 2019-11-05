package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
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
import cn.ifhu.dongjia.activity.me.AddressActivity;
import cn.ifhu.dongjia.adapter.ConfirmOrderAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;
import cn.ifhu.dongjia.model.get.SubmitPreviewGetBean;
import cn.ifhu.dongjia.model.post.MchListPost;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.GsonUtils;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 下单预览页面
 */
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
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rl_store)
    RecyclerView rlStore;
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


    //从商品详情传过来的json数据
    String goodsInfoData;
    //创建String接受数据
    //地址ID
    String address_id;
    //购物车ID
    List<MchListPost.MchListBean> mch_list;
    int payment;

    ConfirmOrderAdapter confirmOrderAdapter;
    List<SubmitPreviewDataBean.MchListBean> mchList = new ArrayList<>();

    SubmitPreviewDataBean mData = new SubmitPreviewDataBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        tvTitle.setText("确认订单");
        Intent intent = getIntent();
        goodsInfoData = intent.getStringExtra("goodsInfoData");
        try {
            mch_list = ((MchListPost) intent.getSerializableExtra("mch_list")).getMchListBeans();
        } catch (Exception ignored){}
        getSubmitPreview();
        confirmOrderAdapter = new ConfirmOrderAdapter(mchList, this);
        rlStore.setNestedScrollingEnabled(false);
        rlStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rlStore.setAdapter(confirmOrderAdapter);
        rlStore.setOnScrollListener(new LoadMoreScrollListener(rlStore));
    }

    /**
     * 下单预览接口
     */
    public void getSubmitPreview() {
        setLoadingMessageIndicator(true);
        SubmitPreviewGetBean submitPreviewGetBean = new SubmitPreviewGetBean();
        submitPreviewGetBean.setAccess_token(UserLogic.getUser().getAccess_token());
        if (goodsInfoData != null) {
            submitPreviewGetBean.setGoods_info(goodsInfoData);
        } else {
            submitPreviewGetBean.setGoods_info("");
        }
        submitPreviewGetBean.setCart_id_list("");
        if (mch_list != null) {
            String mchListJson = GsonUtils.convertList2Json(mch_list);
            submitPreviewGetBean.setMch_list(mchListJson);
        } else {
            submitPreviewGetBean.setCart_id_list("");
        }
        RetrofitAPIManager.create(HomeService.class).submitPreview(submitPreviewGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<SubmitPreviewDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<SubmitPreviewDataBean> t) throws Exception {
                mData = t.getData();
                mchList = t.getData().getMch_list();
                confirmOrderAdapter.setData(mchList);
                setSubmitPreview();
            }
        });
    }

    /**
     * 设置数据
     */
    public void setSubmitPreview() {
        tvName.setText(mData.getAddress().getName());
        tvPhone.setText(mData.getAddress().getMobile());
        tvAddress.setText(mData.getAddress().getProvince() + mData.getAddress().getCity() + mData.getAddress().getDistrict() + mData.getAddress().getDetail());
        address_id = mData.getAddress().getId();
        double pri = mData.getTotal_price();
        double mchTotalPrice = 0;
        double mchExpressPrice = 0;
        for (int i = 0; i < mchList.size(); i++) {
            mchList = mData.getMch_list();
            mchTotalPrice += mchList.get(i).getTotal_price();
            mchExpressPrice += mchList.get(i).getExpress_price();
            MchListPost.MchListBean mchListPost = new MchListPost.MchListBean();
            mchListPost.setId(mchList.get(i).getId());
            List<SubmitPreviewDataBean.MchListBean.ListBean> listBeanList = mchList.get(i).getList();
            List<Integer> cartIdList = new ArrayList<>();
            for (int j = 0; j < listBeanList.size(); j++) {
                cartIdList.add(listBeanList.get(j).getCat_id());
            }
            mchListPost.setCart_id_list(cartIdList);
        }
        double totalPrice = pri + mchTotalPrice + mchExpressPrice;

        tvTotal.setText("￥" + totalPrice + "");
    }


    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }


    @OnClick(R.id.tv_ok)
    public void onTvOkClicked() {

    }

    @OnClick(R.id.rl_address)
    public void onRlAddressClicked() {
        goToActivity(AddressActivity.class);
    }
}
