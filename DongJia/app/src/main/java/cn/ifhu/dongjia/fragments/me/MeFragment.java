package cn.ifhu.dongjia.fragments.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunfusheng.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.me.AboutUsActivity;
import cn.ifhu.dongjia.activity.me.AddressActivity;
import cn.ifhu.dongjia.activity.me.ContentActivity;
import cn.ifhu.dongjia.activity.order.OrderListActivity;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.MeDataBean;
import cn.ifhu.dongjia.model.data.UserDataBean;
import cn.ifhu.dongjia.model.get.UserPostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.MeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.UserService;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;
import cn.ifhu.dongjia.wxapi.WXLoginUtils;

/**
 * 我的页面
 */
public class MeFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_order)
    RelativeLayout rlOrder;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.ll_wait_payment)
    LinearLayout llWaitPayment;
    @BindView(R.id.ll_wait_ship)
    LinearLayout llWaitShip;
    @BindView(R.id.ll_wait_receipt)
    LinearLayout llWaitReceipt;
    @BindView(R.id.ll_completed)
    LinearLayout llCompleted;
    @BindView(R.id.dongjia)
    TextView dongjia;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.ll_after_sale)
    LinearLayout llAfterSale;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.rl_noLogin)
    RelativeLayout rlNoLogin;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.tv_my_scores)
    TextView tvMyScores;
    @BindView(R.id.tv_my_balance)
    TextView tvMyBalance;
    @BindView(R.id.tv_my_furniture)
    TextView tvMyFurniture;
    @BindView(R.id.ll_furniture)
    LinearLayout llFurniture;
    @BindView(R.id.ll_coupon)
    LinearLayout llCoupon;
    @BindView(R.id.rl_order2)
    RelativeLayout rlOrder2;


    public static BaseFragment newInstance() {
        return new MeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        isLogin();
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        isLogin();
    }

    /**
     * 登录接口
     */
    public void login(String code) {
        UserLogin(code);
    }

    public void UserLogin(String code) {
        UserPostBean userPostBean = new UserPostBean();
        userPostBean.setCode(code);
        RetrofitAPIManager.create(UserService.class).login(userPostBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<UserDataBean>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity<UserDataBean> t) throws Exception {
                //TODO:保存用户信息
                UserLogic.saveUser(t.getData());
                isLogin();
                getMyData();
            }
        });
    }

    /**
     * 登录
     */
    public void isLogin() {
        if (UserLogic.getUser() != null) {
            //登录状态
            rlNoLogin.setVisibility(View.GONE);
            rlLogin.setVisibility(View.VISIBLE);
            tvLogout.setVisibility(View.VISIBLE);
            ivAvatar.setVisibility(View.VISIBLE);
            rlOrder2.setVisibility(View.VISIBLE);
            /**
             * loadCircle:圆角图片
             */
            ivAvatar.loadCircle(UserLogic.getUser().getAvatar_url(), R.drawable.me_tx_moren);
            tvName.setText(UserLogic.getUser().getNickname());
        } else {
            //未登录
            rlNoLogin.setVisibility(View.VISIBLE);
            rlLogin.setVisibility(View.GONE);
            tvLogout.setVisibility(View.GONE);
            ivAvatar.setVisibility(View.GONE);
            rlOrder2.setVisibility(View.GONE);
        }
    }

    /**
     * 我的接口
     */
    public void getMyData() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(MeService.class).Me(4, -1, -1, UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<MeDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<MeDataBean> t) throws Exception {

            }
        });
    }

    //我的优惠券
    @OnClick(R.id.ll_coupon)
    public void onLlCouponClicked() {
    }

    //我的售后
    @OnClick(R.id.ll_after_sale)
    public void onLlAfterSaleClicked() {

    }

    //我的收藏
    @OnClick(R.id.ll_content)
    public void onLlContentClicked() {
        if (UserLogic.getUser() != null) {
            goToActivity(ContentActivity.class);
        } else {
            ToastHelper.makeText("请登录您的账号").show();
        }
    }

    //收货地址
    @OnClick(R.id.ll_address)
    public void onLlAddressClicked() {
        if (UserLogic.getUser() != null) {
            goToActivity(AddressActivity.class);
        } else {
            ToastHelper.makeText("请登录您的账号").show();
        }
    }

    //关于我们
    @OnClick(R.id.ll_about_us)
    public void onLlAboutUsClicked() {
        goToActivity(AboutUsActivity.class);
    }

    //全部订单
    @OnClick(R.id.rl_order)
    public void onRlOrderClicked() {
        goToActivity(OrderListActivity.class);
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

    //登录
    @OnClick(R.id.tv_login)
    public void onTvLoginClicked() {
        WXLoginUtils.WxLogin(getActivity());
    }


}
