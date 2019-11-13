package cn.ifhu.dongjia.activity.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.OrderDetailDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.OrderServer;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 订单详情页面
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.useless1)
    ImageView useless1;
    @BindView(R.id.tv_delete_name)
    TextView tvDeleteName;
    @BindView(R.id.tv_delivery_time)
    TextView tvDeliveryTime;
    @BindView(R.id.rl_delivery)
    RelativeLayout rlDelivery;
    @BindView(R.id.useless3)
    ImageView useless3;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.iv_store_pic)
    GlideImageView ivStorePic;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R.id.tv_pay_mode)
    TextView tvPayMode;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_delete_price)
    TextView tvDeletePrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.tv_numbering)
    TextView tvNumbering;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_confirm_receipt)
    TextView tvConfirmReceipt;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    int OrderId;
    List<OrderDetailDataBean.GoodsListBean> goodsList = new ArrayList<>();
    OrderDetailDataBean mDatas;
    @BindView(R.id.tv_call_store)
    TextView tvCallStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        OrderId = getIntent().getIntExtra("Order_id", 0);
//        OrderId = getDataInt();
        getOrderDetail();
    }

    /**
     * 订单详情接口
     */
    public void getOrderDetail() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(OrderServer.class).orderDetail(4, -1, -1, UserLogic.getUser().getAccess_token(), OrderId)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<OrderDetailDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<OrderDetailDataBean> t) throws Exception {
                mDatas = t.getData();
                goodsList = t.getData().getGoods_list();
                setOrderDetailShow();
            }
        });
    }

    /**
     * 设置数据
     */
    public void setOrderDetailShow() {
        tvStatus.setText(mDatas.getStatus());
        //地址信息
        tvAddress.setText(mDatas.getAddress());
        tvUserName.setText(mDatas.getName());
        tvPhone.setText(mDatas.getMobile());
        //商家信息
        for (int i = 0; i < goodsList.size(); i++) {
            ivStorePic.load(goodsList.get(i).getGoods_pic());
            tvStoreName.setText(goodsList.get(i).getName());
        }

        //商品信息
        llGoods.removeAllViews();
        for (int i = 0; i < mDatas.getGoods_list().size(); i++) {
            OrderDetailDataBean.GoodsListBean goodsListBean = mDatas.getGoods_list().get(i);
            StringBuilder attr = new StringBuilder();
            for (int j = 0; j < goodsListBean.getAttr().size(); j++) {
                OrderDetailDataBean.GoodsListBean.AttrBean attrList = mDatas.getGoods_list().get(i).getAttr().get(j);
                attr.append(attrList.getAttr_group_name()).append(":").append(attrList.getAttr_name()).append(" ");
            }
            View view = gernerateGoodsView(goodsListBean.getGoods_pic(), goodsListBean.getName(), attr.toString(), goodsListBean.getTotal_price(), goodsListBean.getNum() + "");
            llGoods.addView(view);
        }
        //支付信息
        tvPayMode.setText(mDatas.getMobile());
        tvOrderPrice.setText(mDatas.getTotal_price() + "");
        tvDeletePrice.setText(mDatas.getExpress_price() + "");
        tvPrice.setText(mDatas.getGoods_total_price() + "");
        tvRemarks.setText(mDatas.getContent());
        //订单信息
        tvNumbering.setText(mDatas.getExpress_no());
        tvOrderTime.setText(mDatas.getAddtime());
    }

    public void setOrderProcess() {
        if (mDatas.getStatus_code() == 0) {
            //订单待付款
            tvPay.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
        } else if (mDatas.getStatus_code() == 1) {
            //订单待发货
            rlDelivery.setVisibility(View.VISIBLE);
            tvCancel.setVisibility(View.VISIBLE);
            tvCallStore.setVisibility(View.VISIBLE);
        } else if (mDatas.getStatus_code() == 2) {
            //订单待收货
            rlDelivery.setVisibility(View.VISIBLE);
            tvConfirmReceipt.setVisibility(View.VISIBLE);
        } else if (mDatas.getStatus_code() == 3) {
            //订单已完成
            rlDelivery.setVisibility(View.VISIBLE);

        } else if (mDatas.getStatus_code() == 4) {
            //订单售后
        } else if (mDatas.getStatus_code() == 5) {
            //订单已取消
        } else if (mDatas.getStatus_code() == 6) {
            //全部订单
        }
    }

    /**
     * 生成xml文件
     *
     * @param goodsPic
     * @param goodsName
     * @param attr
     * @param price
     * @param number
     * @return
     */
    public View gernerateGoodsView(String goodsPic, String goodsName, String attr, String price, String number) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_order_group, null, false);
        ((GlideImageView) view.findViewById(R.id.iv_good_pic)).load(goodsPic);
        ((TextView) view.findViewById(R.id.tv_good_name)).setText(goodsName);
        ((TextView) view.findViewById(R.id.tv_specification)).setText(attr);
        ((TextView) view.findViewById(R.id.tv_goods_price)).setText("￥" + price);
        ((TextView) view.findViewById(R.id.tv_goods_num)).setText("x" + number);
        return view;
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    //确认订单
    @OnClick(R.id.tv_confirm_receipt)
    public void onTvConfirmReceiptClicked() {
    }

    //立即支付
    @OnClick(R.id.tv_pay)
    public void onTvPayClicked() {
    }

    //取消订单
    @OnClick(R.id.tv_cancel)
    public void onTvCancelClicked() {
    }

    //联系卖家
    @OnClick(R.id.tv_call_store)
    public void onTvCallStoreClicked() {
    }
}
