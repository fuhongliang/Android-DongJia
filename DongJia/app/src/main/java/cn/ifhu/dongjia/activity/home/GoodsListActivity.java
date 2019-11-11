package cn.ifhu.dongjia.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    //商品列表
    GoodsListAdapter goodsListAdapter;
    List<GoodsListDataBean.ListBean> goodsList = new ArrayList<>();

    //商家列表
    MchListAdapter mchListAdapter;
    List<MchListDataBean.ListBean> mchList = new ArrayList<>();

    String CatID;
    String sort;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);
        ButterKnife.bind(this);
        tvTitle.setText("家居家装");
        CatID = getIntent().getStringExtra("CatId");
        getGoodsListShow();
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
        rvMch.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rvMch.setAdapter(mchListAdapter);
        rvMch.setOnScrollListener(new LoadMoreScrollListener(rvMch));

    }

    /**
     * 商品列表接口
     */
    public void getGoodsListShow() {
        setLoadingMessageIndicator(true);
        GoodsListGetBean goodsListGetBean = new GoodsListGetBean();
        goodsListGetBean.setCat_id(CatID);
//        if (sort.equals("0")){
//            goodsListGetBean.setSort(sort);
//        }else if (sort.equals("1")){
//            goodsListGetBean.setSort(sort);
//        }else {
//            goodsListGetBean.setSort(sort);
//        }
        goodsListGetBean.setSort(0);
        goodsListGetBean.setSort_type(0);
        goodsListGetBean.setPage(1);
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
        getGoodsListShow();
        goodsView.setVisibility(View.VISIBLE);
        storeView.setVisibility(View.GONE);
        rvGoods.setVisibility(View.VISIBLE);
        rvMch.setVisibility(View.GONE);
        goodsListAdapter.notifyDataSetChanged();
    }

    //店铺点击
    @OnClick(R.id.rl_store)
    public void onRlStoreClicked() {
        getMchListShow();
        storeView.setVisibility(View.VISIBLE);
        goodsView.setVisibility(View.GONE);
        rvGoods.setVisibility(View.GONE);
        rvMch.setVisibility(View.VISIBLE);
    }

    public void getMchListShow() {
        setLoadingMessageIndicator(true);
        MchListGetBean mchListGetBean = new MchListGetBean();
        mchListGetBean.setCat_id(CatID);
        mchListGetBean.setDistrict("陆丰市");
        mchListGetBean.setPage(1);
        mchListGetBean.setSort(1);
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
    }

    //价格点击
    @OnClick(R.id.rl_price)
    public void onRlPriceClicked() {
    }

    //销量点击
    @OnClick(R.id.tv_sales_volume)
    public void onTvSalesVolumeClicked() {
    }
}
