package cn.ifhu.dongjia.fragments.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.orhanobut.logger.Logger;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.WebView.WebViewActivity;
import cn.ifhu.dongjia.activity.home.GoodDetailsActivity;
import cn.ifhu.dongjia.activity.home.SearchActivity;
import cn.ifhu.dongjia.activity.home.StoreHomeActivity;
import cn.ifhu.dongjia.adapter.NavIconAdapter;
import cn.ifhu.dongjia.adapter.PanicBuyAdapter;
import cn.ifhu.dongjia.adapter.RecommendAdapter;
import cn.ifhu.dongjia.adapter.RecommendGoodsAdapter;
import cn.ifhu.dongjia.adapter.SuperAdapter;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.HomeDataBean;
import cn.ifhu.dongjia.model.data.RecommendDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.DeviceUtil;
import cn.ifhu.dongjia.utils.GlideRoundTransform;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;
import cn.ifhu.dongjia.utils.StringUtils;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.options1)
    ImageView options1;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.rv_panic_buy)
    RecyclerView rvPanicBuy;
    @BindView(R.id.rv_nav_icon)
    RecyclerView rvNavIcon;
    @BindView(R.id.rv_super_list)
    RecyclerView rvSuperList;
    @BindView(R.id.rv_recommend_goods)
    RecyclerView rvRecommendGoods;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    /**
     * 最多能显示五个
     */
    final int maxNavNumber = 5;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;


    //轮播图
    private List<HomeDataBean.BannerListBean> banner_data = new ArrayList<>();
    //分类
    NavIconAdapter newNavIconAdapter;
    private List<HomeDataBean.NavIconListBean> navData = new ArrayList<>();
    //限时抢购
    PanicBuyAdapter newPanicBuyAdapter;
    private List<HomeDataBean.MiaoshaBean.GoodsListBean> panicBuyData = new ArrayList<>();
    //爆款热卖
    RecommendGoodsAdapter newRecommendGoodsAdapter;
    private List<HomeDataBean.RecommendGoodsBean> recommendGoodsData = new ArrayList<>();
    //超级品牌
    SuperAdapter newSuperAdapter;
    private List<HomeDataBean.NewMchListBean> superData = new ArrayList<>();
    //懂家臻选
    RecommendAdapter newRecommendAdapter;
    private List<RecommendDataBean.ListBean> recommendData = new ArrayList<>();

    public static BaseFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefreshLayout();
        getData();
        getRecommend(1);
        initBanner();
        /**
         * 分类
         */
        newNavIconAdapter = new NavIconAdapter(navData, getActivity());
        rvNavIcon.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        rvNavIcon.setAdapter(newNavIconAdapter);
        rvNavIcon.setOnScrollListener(new LoadMoreScrollListener(rvNavIcon));
        /**
         * 限时抢购
         */
        newPanicBuyAdapter = new PanicBuyAdapter(panicBuyData, getActivity(), position -> {
            Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
            intent.putExtra("id",panicBuyData.get(position).getId());
            startActivity(intent);
        });
        rvPanicBuy.setNestedScrollingEnabled(false);
        rvPanicBuy.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        rvPanicBuy.setAdapter(newPanicBuyAdapter);
        rvPanicBuy.setOnScrollListener(new LoadMoreScrollListener(rvPanicBuy));
        /**
         * 爆款热卖
         */
        newRecommendGoodsAdapter = new RecommendGoodsAdapter(recommendGoodsData, getActivity(), position -> {
            Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
            intent.putExtra("id", recommendGoodsData.get(position).getId());
            startActivity(intent);
        });
        rvRecommendGoods.setNestedScrollingEnabled(false);
        //方格形布局
        rvRecommendGoods.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //垂直间距
        rvRecommendGoods.addItemDecoration(new GridDividerItemDecoration(12));
        rvRecommendGoods.setAdapter(newRecommendGoodsAdapter);
        rvRecommendGoods.setOnScrollListener(new LoadMoreScrollListener(rvRecommendGoods));
        /**
         * 超级品牌
         */
        newSuperAdapter = new SuperAdapter(superData, getActivity(), new SuperAdapter.OnClickItem() {
            @Override
            public void llSuper(int position) {
                Intent intent = new Intent(getActivity(), StoreHomeActivity.class);
                intent.putExtra("mch_id", superData.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void llStore(int position) {
                Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
                intent.putExtra("id", superData.get(position).getGoods_list().get(position).getId());
                startActivity(intent);
            }
        });
        rvSuperList.setNestedScrollingEnabled(false);
        //垂直布局
        rvSuperList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvSuperList.setAdapter(newSuperAdapter);
        rvSuperList.setOnScrollListener(new LoadMoreScrollListener(rvSuperList));
        /**
         * 懂家臻选
         */
        newRecommendAdapter = new RecommendAdapter(recommendData, getActivity(), new RecommendAdapter.OnClickItem() {
            @Override
            public void recommend(int position) {
                Intent intent = new Intent(getActivity(), GoodDetailsActivity.class);
                intent.putExtra("id", recommendData.get(position).getId());
                startActivity(intent);
            }
        });
        newRecommendAdapter.setLoadMordListener(loadIndex -> {
            getRecommend(loadIndex);
        });
        rvRecommend.setNestedScrollingEnabled(false);
        rvRecommend.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvRecommend.setAdapter(newRecommendAdapter);
        rvRecommend.setOnScrollListener(new LoadMoreScrollListener(rvRecommend));
        getData();
    }

    /**
     * banner图
     */
    public void initBanner() {
        HomeDataBean.BannerListBean bannerListBean = new HomeDataBean.BannerListBean();
        bannerListBean.getPic_url();
        banner_data.add(bannerListBean);
        banner_data.add(bannerListBean);
        xbanner.loadImage((banner, model, view, position) -> {
            HomeDataBean.BannerListBean listBean = ((HomeDataBean.BannerListBean) model);
            loadRoundImage((ImageView) view, listBean.getPic_url());
        });
        xbanner.setOnItemClickListener((banner, model, view, position) -> {
            HomeDataBean.BannerListBean listBean = ((HomeDataBean.BannerListBean) model);
            if (!StringUtils.isEmpty(listBean.getPic_url())) {
                WebViewActivity.start(getActivity(), listBean.getPage_url(), "");
            }
        });
    }

    public static void loadRoundImage(ImageView view, String url) {
        RequestOptions myOptions = new RequestOptions().optionalTransform
                (new GlideRoundTransform(DeviceUtil.dip2px(6f)
                        , GlideRoundTransform.CornerType.ALL));
        Glide.with(view.getContext()).load(url).apply(myOptions).into(view);
    }

    @SuppressLint("ResourceAsColor")
    public void setRefreshLayout() {
        layoutSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        layoutSwipeRefresh.setOnRefreshListener(() -> {
            getData();
            getRecommend(1);
        });
    }

    /**
     * 请求首页接口
     */
    public void getData() {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(HomeService.class).index(4, -1, -1, "android", "陆丰市")
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<HomeDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<HomeDataBean> t) throws Exception {
                banner_data = t.getData().getBanner_list();
                xbanner.setAutoPlayAble(banner_data.size() > 1);
                xbanner.setBannerData(banner_data);

                navData = t.getData().getNav_icon_list();
                newNavIconAdapter.setData(navData);

                panicBuyData = t.getData().getMiaosha().getGoods_list();
                newPanicBuyAdapter.setData(panicBuyData);

                recommendGoodsData = t.getData().getRecommend_goods();
                newRecommendGoodsAdapter.setData(recommendGoodsData);

                superData = t.getData().getNew_mch_list();
                newSuperAdapter.setData(superData);
                getRecommend(1);

            }
        });
    }

    /**
     * 懂家臻选
     */
    public void getRecommend(int pages) {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(HomeService.class).indexRecommend(pages, 4, -1, -1)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<RecommendDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<RecommendDataBean> t) throws Exception {
                if (pages == 1) {
                    recommendData.clear();
                    recommendData.addAll(t.getData().getList());
                    newRecommendAdapter.setData(recommendData);
                } else {
                    recommendData.addAll(t.getData().getList());
                    newRecommendAdapter.appendList(t.getData().getList());
                }
            }

            @Override
            protected void onAPIError() {
                super.onAPIError();
                Logger.d("onAPIError: ");
            }

            @Override
            protected void onCodeError(BaseEntity<RecommendDataBean> t) throws Exception {
                super.onCodeError(t);
                Logger.d("t.code" + t.code);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    /**
     * 搜索跳转
     */
    @OnClick(R.id.rl_search)
    public void onRlSearchClicked() {
        goToActivity(SearchActivity.class);
    }

}
