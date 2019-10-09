package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.WebView.WebViewActivity;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.HomeDataBean;
import cn.ifhu.dongjia.model.post.GoodDetailsGetBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.DeviceUtil;
import cn.ifhu.dongjia.utils.GlideRoundTransform;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 商品详情
 */
public class GoodDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_original_price)
    TextView tvOriginalPrice;
    @BindView(R.id.tv_current_price)
    TextView tvCurrentPrice;
    @BindView(R.id.tv_show_integral)
    TextView tvShowIntegral;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.options1)
    TextView options1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_service1)
    LinearLayout llService1;
    @BindView(R.id.ll_service2)
    LinearLayout llService2;
    @BindView(R.id.ll_service3)
    LinearLayout llService3;
    @BindView(R.id.ll_service4)
    LinearLayout llService4;
    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_goto_store)
    TextView tvGotoStore;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.options2)
    ImageView options2;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.options3)
    ImageView options3;
    @BindView(R.id.rl_shop_car)
    RelativeLayout rlShopCar;
    @BindView(R.id.tv_shop_cart)
    TextView tvShopCart;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.tv_full)
    TextView tvFull;


    //轮播图
    private List<GoodDetailsDataBean.PicListBean> banner_data = new ArrayList<>();

    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_good_details);
        ButterKnife.bind(this);
        initBanner();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        getGoods();
    }

    /**
     * banner图
     */
    public void initBanner() {
        GoodDetailsDataBean.PicListBean picListBean = new GoodDetailsDataBean.PicListBean();
        picListBean.getPic_url();
        banner_data.add(picListBean);
        banner_data.add(picListBean);
        xbanner.loadImage((banner, model, view, position) -> {
            HomeDataBean.BannerListBean listBean = ((HomeDataBean.BannerListBean) model);
            loadRoundImage((ImageView) view, listBean.getPic_url());
        });
        xbanner.setOnItemClickListener((banner, model, view, position) -> {
            HomeDataBean.BannerListBean listBean = ((HomeDataBean.BannerListBean) model);
            if (!StringUtils.isEmpty(listBean.getPic_url())) {
                WebViewActivity.start(this, listBean.getPage_url(), "");
            }
        });
    }

    public static void loadRoundImage(ImageView view, String url) {
        RequestOptions myOptions = new RequestOptions().optionalTransform
                (new GlideRoundTransform(DeviceUtil.dip2px(6f)
                        , GlideRoundTransform.CornerType.ALL));
        Glide.with(view.getContext()).load(url).apply(myOptions).into(view);
    }



    /**
     * 商品详情接口
     */
    public void getGoods() {
        GoodDetailsGetBean goodDetailsGetBean = new GoodDetailsGetBean();
        setLoadingMessageIndicator(true);
        goodDetailsGetBean.setId(id);
        RetrofitAPIManager.create(HomeService.class).Goods(goodDetailsGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<GoodDetailsDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<GoodDetailsDataBean> t) throws Exception {
                banner_data = t.getData().getPic_list();
                xbanner.setAutoPlayAble(banner_data.size() > 1);
                xbanner.setBannerData(banner_data);
            }
        });

    }

    @OnClick(R.id.tv_goto_store)
    public void onTvGotoStoreClicked() {
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_shop_car)
    public void onRlShopCarClicked() {
    }

    @OnClick(R.id.tv_shop_cart)
    public void onTvShopCartClicked() {
    }

    @OnClick(R.id.tv_buy)
    public void onTvBuyClicked() {
    }
}
