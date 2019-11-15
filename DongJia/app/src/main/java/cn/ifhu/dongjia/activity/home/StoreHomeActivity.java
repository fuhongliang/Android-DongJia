package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.FeaturedBuyAdapter;
import cn.ifhu.dongjia.adapter.MchArticleAdapter;
import cn.ifhu.dongjia.adapter.ShopRecommendAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.MchArticleDataBean;
import cn.ifhu.dongjia.model.data.ShopDataBean;
import cn.ifhu.dongjia.model.get.ShopGetBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.DialogUtils;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 商家主页
 */
public class StoreHomeActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.options1)
    View options1;
    @BindView(R.id.iv_avatar_bg)
    GlideImageView ivAvatarBg;
    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.options2)
    TextView options2;
    @BindView(R.id.ll_star)
    LinearLayout llStar;
    @BindView(R.id.tv_information)
    TextView tvInformation;
    @BindView(R.id.options3)
    TextView options3;
    @BindView(R.id.rl_see_more)
    RelativeLayout rlSeeMore;
    @BindView(R.id.rl_more)
    RelativeLayout rlMore;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.rl_header_color)
    RelativeLayout rlHeaderColor;
    @BindView(R.id.tv_case_num)
    TextView tvCaseNum;
    @BindView(R.id.rv_case)
    RecyclerView rvCase;
    @BindView(R.id.rv_feature_buy)
    RecyclerView rvFeatureBuy;


    String mch_id;

    //爆款推荐
    ShopRecommendAdapter shopRecommendAdapter;

    private List<ShopDataBean.RecommendListBean> recommendList = new ArrayList<>();
    //商品精品案例
    MchArticleAdapter mchArticleAdapter;
    private List<MchArticleDataBean> mchDatas = new ArrayList<>();
    //精选必买
    FeaturedBuyAdapter featuredBuyAdapter;
    private List<ShopDataBean.GoodsListBean> featuredData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        ButterKnife.bind(this);
        tvTitle.setText("商品主页");
        Intent intent = getIntent();
        mch_id = intent.getStringExtra("mch_id");
        getShop(1);
        //爆款热卖
        shopRecommendAdapter = new ShopRecommendAdapter(recommendList, this, new ShopRecommendAdapter.OnClickItem() {
            @Override
            public void recommendGoods(int position) {

            }
        });
        rvRecommend.setNestedScrollingEnabled(false);
        rvRecommend.setLayoutManager(new GridLayoutManager(this, 3));
        rvRecommend.addItemDecoration(new GridDividerItemDecoration(12));
        rvRecommend.setAdapter(shopRecommendAdapter);
        rvRecommend.setOnScrollListener(new LoadMoreScrollListener(rvRecommend));
        //商品精品案例
        mchArticleAdapter = new MchArticleAdapter(mchDatas, this, new MchArticleAdapter.OnClickItem() {
            @Override
            public void llCase(int position) {

            }
        });
        rvCase.setNestedScrollingEnabled(false);
        rvCase.setLayoutManager(new GridLayoutManager(this, 2));
        rvCase.addItemDecoration(new GridDividerItemDecoration(12));
        rvCase.setAdapter(mchArticleAdapter);
        rvCase.setOnScrollListener(new LoadMoreScrollListener(rvCase));
        //精选必买
        featuredBuyAdapter = new FeaturedBuyAdapter(featuredData, this, new FeaturedBuyAdapter.OnClickItem() {
            @Override
            public void Recommend(int position) {

            }
        });
        rvFeatureBuy.setNestedScrollingEnabled(false);
        rvFeatureBuy.setLayoutManager(new GridLayoutManager(this, 2));
        rvFeatureBuy.setAdapter(featuredBuyAdapter);
        rvFeatureBuy.setOnScrollListener(new LoadMoreScrollListener(rvFeatureBuy));
    }


    /**
     * 商家主页接口
     */
    public void getShop(int page) {
        ShopGetBean shopGetBean = new ShopGetBean();
        shopGetBean.setPage(page + "");
        shopGetBean.setMch_id(mch_id);
        shopGetBean.setAccess_token(UserLogic.getUser().getAccess_token());
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).Shop(shopGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<ShopDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<ShopDataBean> t) throws Exception {

                recommendList = t.getData().getRecommend_list();
                shopRecommendAdapter.setData(recommendList);

                featuredData = t.getData().getGoods_list();
                featuredBuyAdapter.setData(featuredData);


                tvStoreName.setText(t.getData().getShop().getName());
                ivAvatar.load(t.getData().getShop().getLogo());
                ivAvatarBg.load(t.getData().getShop().getHeader_bg());
                tvInformation.setText(t.getData().getShop().getMch_desc());
                getMchArticle();
            }
        });
    }

    /**
     * 商品精品案例接口
     */
    public void getMchArticle() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).MchArticle(4, -1, -1, mch_id)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<List<MchArticleDataBean>>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<List<MchArticleDataBean>> t) throws Exception {
                mchDatas = t.getData();
                mchArticleAdapter.setData(mchDatas);
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_see_more)
    public void onRlSeeMoreClicked() {
    }

    @OnClick(R.id.rl_more)
    public void onRlMoreClicked() {
    }

}
