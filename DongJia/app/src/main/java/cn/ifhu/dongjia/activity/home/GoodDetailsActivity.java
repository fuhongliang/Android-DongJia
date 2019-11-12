package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stx.xhb.xbanner.XBanner;
import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.GoodsRecommendAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean.AttrGroupListBean;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;
import cn.ifhu.dongjia.model.data.Sku;
import cn.ifhu.dongjia.model.get.GoodDetailsGetBean;
import cn.ifhu.dongjia.model.post.FavoriteAddPostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.DeviceUtil;
import cn.ifhu.dongjia.utils.GlideRoundTransform;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;
import cn.ifhu.dongjia.utils.X5WebView;
import cn.ifhu.dongjia.wxapi.WXLoginUtils;

/**
 * 商品详情
 */
public class GoodDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_show_integral)
    TextView tvShowIntegral;
    @BindView(R.id.tv_full)
    TextView tvFull;
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
    @BindView(R.id.options1)
    TextView options1;
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
    @BindView(R.id.wv_detail)
    X5WebView wvDetail;
    @BindView(R.id.rv_recommend_goods)
    RecyclerView rvRecommendGoods;
    @BindView(R.id.rl_specification)
    RelativeLayout rlSpecification;
    @BindView(R.id.tv_specification)
    TextView tvSpecification;


    //轮播图
    private List<GoodDetailsDataBean.PicListBean> banner_data = new ArrayList<>();
    //爆款热卖
    GoodsRecommendAdapter goodsRecommendAdapter;
    private List<GoodsRecommendDataBean.ListBean> mDatas = new ArrayList<>();
    //商品信息
    private GoodDetailsDataBean.MchBean mch;
    //商品规格
    private List<AttrGroupListBean> attrGroupList = new ArrayList<>();
    //商品id
    String id;

    //商品sku
    private GoodDialog dialog;

    //是否收藏商品  0否 1是
    int IsFavorite;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_good_details);
        //状态栏设置为透明
        //StatusBarUtil.setTranslucentForImageView(GoodDetailsActivity.this,0,ivBg);
        //状态栏设置为透明
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        ButterKnife.bind(this);
        initBanner();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        getGoods();
        //爆款热卖
        goodsRecommendAdapter = new GoodsRecommendAdapter(mDatas, this);
        rvRecommendGoods.setNestedScrollingEnabled(false);
        rvRecommendGoods.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvRecommendGoods.setAdapter(goodsRecommendAdapter);
        rvRecommendGoods.setOnScrollListener(new LoadMoreScrollListener(rvRecommendGoods));
    }

    /**
     * banner图
     */
    public void initBanner() {
        //banner图的数据
        xbanner.loadImage((banner, model, view, position) -> {
            GoodDetailsDataBean.PicListBean listBean = ((GoodDetailsDataBean.PicListBean) model);
            loadRoundImage((ImageView) view, listBean.getPic_url());
        });
        //banner图的点击事件
//        xbanner.setOnItemClickListener((banner, model, view, position) -> {
//            GoodDetailsDataBean.PicListBean listBean = ((GoodDetailsDataBean.PicListBean) model);
//            if (!StringUtils.isEmpty(listBean.getPic_url())) {
////                WebViewActivity.start(this, listBean.getPic_url(), "");
//            }
//        });
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
                mch = t.getData().getMch();
                attrGroupList = t.getData().getAttr_group_list();

                xbanner.setAutoPlayAble(banner_data.size() > 1);
                xbanner.setBannerData(banner_data);
                tvPrice.setText("￥" + t.getData().getPrice() + "");
                tvName.setText(t.getData().getName());
                ivAvatar.load(t.getData().getMch().getLogo());
                tvStoreName.setText(t.getData().getMch().getName());
                wvDetail.loadData(t.getData().getDetail(), "text/html", "UTF-8");
                IsFavorite = t.getData().getIs_favorite();

                //TODO:webView自适应手机屏幕
                wvDetail.getSettings().setUseWideViewPort(true);
                wvDetail.getSettings().setLoadWithOverviewMode(true);
                getRecommend();
                if (IsFavorite == 0){
                    options2.setImageResource(R.drawable.detail_ic_sc);
                }else {
                    options2.setImageResource(R.drawable.detail_ic_sc1);
                }
            }
        });
    }

    /**
     * 商品详情爆款推荐接口
     */
    public void getRecommend() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).GoodsRecommend(4, -1, -1, id)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<GoodsRecommendDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<GoodsRecommendDataBean> t) throws Exception {
                mDatas = t.getData().getList();
                goodsRecommendAdapter.setData(mDatas);
            }
        });
    }

    @OnClick(R.id.tv_goto_store)
    public void onTvGotoStoreClicked() {
        Intent intent = new Intent(GoodDetailsActivity.this, StoreHomeActivity.class);
        intent.putExtra("mch_id", mch.getId() + "");
        startActivity(intent);

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
        if (UserLogic.getUser() != null) {
        } else {
            WXLoginUtils.WxLogin(this);
        }
    }

    //收藏
    @OnClick(R.id.rl_collection)
    public void onRlCollectionClicked() {
        if (UserLogic.getUser() == null) {
            WXLoginUtils.WxLogin(this);
        } else {
            if (IsFavorite == 0) {
                getFavoriteAdd();
            } else {
                getFavoriteRemove();
            }
        }
    }

    /**
     * 添加收藏商品接口
     */
    public void getFavoriteAdd() {
        FavoriteAddPostBean favoriteAddPostBean = new FavoriteAddPostBean();
        favoriteAddPostBean.setAccess_token(UserLogic.getUser().getAccess_token());
        favoriteAddPostBean.setGoods_id(id);
        RetrofitAPIManager.create(HomeService.class).favoriteAdd(favoriteAddPostBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                options2.setImageDrawable(getResources().getDrawable(R.drawable.detail_ic_sc1));
                IsFavorite = 1;
            }

        });
    }

    /**
     * 取消收藏接口
     */
    public void getFavoriteRemove() {
        FavoriteAddPostBean favoriteAddPostBean = new FavoriteAddPostBean();
        favoriteAddPostBean.setAccess_token(UserLogic.getUser().getAccess_token());
        favoriteAddPostBean.setGoods_id(id);
        RetrofitAPIManager.create(HomeService.class).favoriteRemove(favoriteAddPostBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                options2.setImageDrawable(getResources().getDrawable(R.drawable.detail_ic_sc));
                IsFavorite = 0;
            }

        });
    }


    @OnClick(R.id.rl_specification)
    public void onRlSpecificationClicked() {
        showSkuDialog();
    }

    public void showSkuDialog() {
        if (dialog == null) {
            dialog = new GoodDialog(this);
            dialog.setData(attrGroupList(), id, new GoodDialog.Callback() {
                @Override
                public void onAdded(GoodsAttrInfoDataBean goodsAttrInfoDataBean, int quantity) {
                }
            });
        }
        dialog.show();
    }

    private List<AttrGroupListBean> attrGroupList() {
        return attrGroupList;
    }
}

