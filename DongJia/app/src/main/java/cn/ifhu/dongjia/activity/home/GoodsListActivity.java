package cn.ifhu.dongjia.activity.home;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.GoodsListAdapter;
import cn.ifhu.dongjia.adapter.MchListAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.GoodsListDataBean;
import cn.ifhu.dongjia.model.data.MchListDataBean;
import cn.ifhu.dongjia.model.get.GoodsListGetBean;
import cn.ifhu.dongjia.model.get.MchListGetBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;

public class GoodsListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.tv_goods)
    TextView tvGoods;
    @BindView(R.id.goods_view)
    View goodsView;
    @BindView(R.id.rl_goods)
    RelativeLayout rlGoods;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.store_view)
    View storeView;
    @BindView(R.id.rl_store)
    RelativeLayout rlStore;
    @BindView(R.id.tv_comprehensive)
    TextView tvComprehensive;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rl_price)
    RelativeLayout rlPrice;
    @BindView(R.id.tv_sales_volume)
    TextView tvSalesVolume;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.rv_mch)
    RecyclerView rvMch;
    @BindView(R.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R.id.tv_mch_comprehensive)
    TextView tvMchComprehensive;
    @BindView(R.id.tv_sales_volumes)
    TextView tvSalesVolumes;
    @BindView(R.id.rl_sales_volumes)
    RelativeLayout rlSalesVolumes;
    @BindView(R.id.tv_newest)
    TextView tvNewest;
    @BindView(R.id.ll_mch)
    LinearLayout llMch;

    //商品列表
    GoodsListAdapter goodsListAdapter;
    List<GoodsListDataBean.ListBean> goodsList = new ArrayList<>();

    //商家列表
    MchListAdapter mchListAdapter;
    List<MchListDataBean.ListBean> mchList = new ArrayList<>();

    String CatID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);
        ButterKnife.bind(this);
        tvTitle.setText("家居家装");
        CatID = getIntent().getStringExtra("CatId");
        getGoodsListShow(0, 0, 1);
        //商品列表
        goodsListAdapter = new GoodsListAdapter(goodsList, this, new GoodsListAdapter.OnClickItem() {
            @Override
            public void LlRecommend(int position) {

            }
        });
        rvGoods.setNestedScrollingEnabled(false);
        rvGoods.setLayoutManager(new GridLayoutManager(this, 2));
        rvGoods.addItemDecoration(new GridDividerItemDecoration(12));
        rvGoods.setAdapter(goodsListAdapter);
        rvGoods.setOnScrollListener(new LoadMoreScrollListener(rvGoods));

        //商家列表
        mchListAdapter = new MchListAdapter(mchList, this, new MchListAdapter.OnClickItem() {
            @Override
            public void llSuper(int position) {

            }

            @Override
            public void llStore(int position) {

            }
        });
        rvMch.setNestedScrollingEnabled(false);
        rvMch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvMch.setAdapter(mchListAdapter);
        rvMch.setOnScrollListener(new LoadMoreScrollListener(rvMch));

    }

    /**
     * 商品列表接口
     */
    public void getGoodsListShow(int GoodSort, int GoodSortType, int Page) {
        setLoadingMessageIndicator(true);
        GoodsListGetBean goodsListGetBean = new GoodsListGetBean();
        goodsListGetBean.setCat_id(CatID);
        goodsListGetBean.setSort(GoodSort);
        goodsListGetBean.setSort_type(GoodSortType);
        goodsListGetBean.setPage(Page);
        goodsListGetBean.setDistrict("陆丰市");
        RetrofitAPIManager.create(HomeService.class).GoodsList(goodsListGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<GoodsListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<GoodsListDataBean> t) throws Exception {
                goodsList = t.getData().getList();
                goodsListAdapter.setData(goodsList);

            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    //商品点击
    @OnClick(R.id.rl_goods)
    public void onRlGoodsClicked() {
        getGoodsListShow(0, 0, 1);
        goodsView.setVisibility(View.VISIBLE);
        storeView.setVisibility(View.GONE);
        rvGoods.setVisibility(View.VISIBLE);
        rvMch.setVisibility(View.GONE);
        goodsListAdapter.notifyDataSetChanged();
        llGoods.setVisibility(View.VISIBLE);
        llMch.setVisibility(View.GONE);

    }

    //店铺点击
    @OnClick(R.id.rl_store)
    public void onRlStoreClicked() {
        getMchListShow(1, 1);
        storeView.setVisibility(View.VISIBLE);
        goodsView.setVisibility(View.GONE);
        rvGoods.setVisibility(View.GONE);
        rvMch.setVisibility(View.VISIBLE);
        llGoods.setVisibility(View.GONE);
        llMch.setVisibility(View.VISIBLE);
    }

    /**
     * 商家列表接口
     */
    public void getMchListShow(int Sort, int Page) {
        setLoadingMessageIndicator(true);
        MchListGetBean mchListGetBean = new MchListGetBean();
        mchListGetBean.setCat_id(CatID);
        mchListGetBean.setDistrict("陆丰市");
        mchListGetBean.setPage(Page);
        mchListGetBean.setSort(Sort);
        RetrofitAPIManager.create(HomeService.class).MchList(mchListGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<MchListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<MchListDataBean> t) throws Exception {
                mchList = t.getData().getList();
                mchListAdapter.setData(mchList);
            }
        });
    }


    //综合点击
    @OnClick(R.id.tv_comprehensive)
    public void onTvComprehensiveClicked() {
        getGoodsListShow(0, 0, 1);
    }

    //价格点击
    @OnClick(R.id.rl_price)
    public void onRlPriceClicked() {
        // TODO: 2019-11-20 升降排序
        getGoodsListShow(1, 0, 1);
    }

    //销量点击
    @OnClick(R.id.tv_sales_volume)
    public void onTvSalesVolumeClicked() {
        getGoodsListShow(2, 0, 1);
    }

    //店铺综合
    @OnClick(R.id.tv_mch_comprehensive)
    public void onTvMchComprehensiveClicked() {
        getMchListShow(1, 1);

    }

    //店铺销量点击
    @OnClick(R.id.rl_sales_volumes)
    public void onRlSalesVolumesClicked() {
        getMchListShow(2, 1);
    }

    //最新
    @OnClick(R.id.tv_newest)
    public void onTvNewestClicked() {
        getMchListShow(3, 1);
    }
}
