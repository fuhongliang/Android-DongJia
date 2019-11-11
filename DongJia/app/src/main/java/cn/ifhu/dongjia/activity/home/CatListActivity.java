package cn.ifhu.dongjia.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.CategoryAdapter;
import cn.ifhu.dongjia.adapter.ProductAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.CatListDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;

/**
 * 分类列表页面
 */
public class CatListActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.rv_product)
    RecyclerView rvProduct;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.lv_category)
    ListView lvCategory;
    //分类ID
    public String mCatId = "0";
    //选中
    public int classPosition = 0;
    CategoryAdapter categoryAdapter;
    List<CatListDataBean.ListBeanX> listBeanData = new ArrayList<>();

    ProductAdapter productAdapter;

    List<CatListDataBean.ListBeanX.ListBean> listData = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
        tvTitle.setText("分类列表");
        getCatListData();
        //商品左边
        categoryAdapter = new CategoryAdapter(listBeanData, this, position -> {
            mCatId = listBeanData.get(position).getId();
            classPosition = position;
            categoryAdapter.notifyDataSetChanged();
            showGoodslist();
        });
        lvCategory.setAdapter(categoryAdapter);

        //商品右边
        productAdapter = new ProductAdapter(listData, this, position -> {
//            for (int i = 0; i < listBeanData.size(); i++) {
//                mCatId = Integer.parseInt(listBeanData.get(i).getId());
//            }
            Intent intent = new Intent(this, GoodsListActivity.class);
            intent.putExtra("CatId", mCatId);
            startActivity(intent);
        });
        rvProduct.setNestedScrollingEnabled(false);
        rvProduct.setLayoutManager(new GridLayoutManager(this, 3));
        rvProduct.setAdapter(productAdapter);
        rvProduct.setOnScrollListener(new LoadMoreScrollListener(rvProduct));
    }

    /**
     * 分类列表接口
     */
    public void getCatListData() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).catList(4, -1, -1, 0)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<CatListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<CatListDataBean> t) throws Exception {
                listBeanData = t.getData().getList();
                categoryAdapter.setData(listBeanData);
                showGoodslist();
                mCatId = listBeanData.get(classPosition).getId();
            }
        });
    }

    public void showGoodslist() {
        if (classPosition < listBeanData.size()) {
            listData = listBeanData.get(classPosition).getList();
        }
        productAdapter.setData(listData);
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

}
