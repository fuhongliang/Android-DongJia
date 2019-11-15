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

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
import cn.ifhu.dongjia.model.data.AddressBean;
import cn.ifhu.dongjia.model.data.OrderPayDataBean;
import cn.ifhu.dongjia.model.data.SubmitDataBean;
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;
import cn.ifhu.dongjia.model.get.OrderPayGetBean;
import cn.ifhu.dongjia.model.get.SubmitPreviewGetBean;
import cn.ifhu.dongjia.model.post.MchListPost;
import cn.ifhu.dongjia.model.post.SubmitPostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;
import cn.ifhu.dongjia.utils.GsonUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;

import static cn.ifhu.dongjia.MainActivity.APP_ID;

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
    @BindView(R.id.tv_addaddress)
    TextView tvAddaddress;

    //从商品详情传过来的json数据
    String goodsInfoData;
    //创建String接受数据
    //地址ID
    String address_id;
    //购物车ID
    List<MchListPost.MchListBean> mch_list;
    int payment = 0;
    //商品ID
    String goodsId;

    ConfirmOrderAdapter confirmOrderAdapter;
    List<SubmitPreviewDataBean.MchListBean> mchList = new ArrayList<>();

    SubmitPreviewDataBean mData = new SubmitPreviewDataBean();

    static final int REQUEST_CODE_B = 888;

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
        } catch (Exception ignored) {
        }
        getSubmitPreview();
        confirmOrderAdapter = new ConfirmOrderAdapter(mchList, this);
        rlStore.setNestedScrollingEnabled(false);
        rlStore.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rlStore.setAdapter(confirmOrderAdapter);
        rlStore.setOnScrollListener(new LoadMoreScrollListener(rlStore));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_B) {
                AddressBean addaddress = (AddressBean) data.getSerializableExtra("addressBean");
                setAddress(addaddress);
                if (addaddress != null) {
                    address.setVisibility(View.VISIBLE);
                    expand.setVisibility(View.VISIBLE);
                    rl.setVisibility(View.VISIBLE);
                    tvAddress.setVisibility(View.VISIBLE);
                    tvAddaddress.setVisibility(View.GONE);
                }
            }

        }
    }

    public void setAddress(AddressBean addressBean) {
        if (addressBean != null) {
            tvName.setText(addressBean.getName());
            tvPhone.setText(addressBean.getMobile());
            tvAddress.setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getDistrict() + addressBean.getDetail());
        }
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
            submitPreviewGetBean.setMch_list("");
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
                if (mData.getAddress() == null) {
                    address.setVisibility(View.GONE);
                    expand.setVisibility(View.GONE);
                    rl.setVisibility(View.GONE);
                    tvAddress.setVisibility(View.GONE);
                    tvAddaddress.setVisibility(View.VISIBLE);
                } else {
                    address.setVisibility(View.VISIBLE);
                    expand.setVisibility(View.VISIBLE);
                    rl.setVisibility(View.VISIBLE);
                    tvAddress.setVisibility(View.VISIBLE);
                    tvAddaddress.setVisibility(View.GONE);
                }
                setSubmitPreview();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 设置数据
     */
    public void setSubmitPreview() {
        setAddress(mData.getAddress());
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
                goodsId = listBeanList.get(j).getGoods_id() + "";
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
        submitPost();
    }

    /**
     * 调起支付接口
     */
    public void orderPayData(int order_id) {
        setLoadingMessageIndicator(true);
        OrderPayGetBean orderPayGetBean = new OrderPayGetBean();
        orderPayGetBean.setAccess_token(UserLogic.getUser().getAccess_token());
        orderPayGetBean.setApp_key("app");
        orderPayGetBean.setOrder_id(order_id);
        orderPayGetBean.setOrder_id_list("");
        orderPayGetBean.setPay_type("WECHAT_PAY");
        RetrofitAPIManager.create(HomeService.class).OrderPayData(orderPayGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<OrderPayDataBean>(true) {
            @Override
            protected void onApiComplete() {

            }

            @Override
            protected void onSuccees(BaseEntity<OrderPayDataBean> t) throws Exception {
                PayReq request = new PayReq();
                request.appId = t.getData().getAppid();
                request.partnerId = t.getData().getPartnerid();
                request.prepayId = t.getData().getPrepayid();
                request.packageValue = t.getData().getPackageX();
                request.nonceStr = t.getData().getNoncestr();
                request.timeStamp = t.getData().getTimestamp() + "";
                request.sign = t.getData().getSign();
                IWXAPI api = WXAPIFactory.createWXAPI(ConfirmOrderActivity.this, APP_ID, true);
                api.sendReq(request);
            }
        });
    }

    /**
     * 订单提交接口
     */
    public void submitPost() {
        setLoadingMessageIndicator(true);
        SubmitPostBean submitPostBean = new SubmitPostBean();
        submitPostBean.setAccess_token(UserLogic.getUser().getAccess_token());
        submitPostBean.setAddress_id(address_id);
        submitPostBean.setCart_id_list("");
        if (mch_list != null) {
            String mchListJson = GsonUtils.convertList2Json(mch_list);
            submitPostBean.setMch_list(mchListJson);
        } else {
            submitPostBean.setMch_list("");
        }
        submitPostBean.setPayment(payment + "");
        submitPostBean.setApp_key("app");
        if (goodsInfoData != null) {
            submitPostBean.setGoods_info(goodsInfoData);
        } else {
            submitPostBean.setGoods_info("");
        }
        RetrofitAPIManager.create(HomeService.class).submit(submitPostBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<SubmitDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<SubmitDataBean> t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
                orderPayData(t.getData().getOrder_id());
            }
        });
    }

    @OnClick(R.id.rl_address)
    public void onRlAddressClicked() {
        Intent intent = new Intent(ConfirmOrderActivity.this, AddressActivity.class);
        startActivityForResult(intent, REQUEST_CODE_B);
    }
}
