package cn.ifhu.dongjia.fragments.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.sunfusheng.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.me.AboutUsActivity;
import cn.ifhu.dongjia.activity.me.AddressActivity;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.UserDataBean;
import cn.ifhu.dongjia.model.post.UserPostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.UserService;
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

    public void login(String code) {
        UserLogin(code);

    }

    /**
     * 登录接口
     */
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
            }
        });
    }

    public void isLogin() {
        if (UserLogic.getUser() != null) {
            rlNoLogin.setVisibility(View.GONE);
            rlLogin.setVisibility(View.VISIBLE);
            tvLogout.setVisibility(View.VISIBLE);
            /**
             * loadCircle:圆角图片
             */
            ivAvatar.loadCircle(UserLogic.getUser().getAvatar_url(), R.drawable.me_tx_moren);
            tvName.setText(UserLogic.getUser().getNickname());
        } else {
            rlNoLogin.setVisibility(View.VISIBLE);
            rlLogin.setVisibility(View.GONE);
            tvLogout.setVisibility(View.GONE);
        }
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

    //登录
    @OnClick(R.id.tv_login)
    public void onTvLoginClicked() {
        WXLoginUtils.WxLogin(getActivity());
    }


}
