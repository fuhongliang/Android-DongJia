package cn.ifhu.dongjia.activity.me;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.FavoriteListAdpter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.FavoriteListDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.MeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 收藏页面
 */
public class ContentActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;


    FavoriteListAdpter favoriteListAdpter;
    List<FavoriteListDataBean.ListBean> mData = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        tvTitle.setText("我的收藏");
        favoriteListAdpter = new FavoriteListAdpter(mData, this);
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setLayoutManager(new GridLayoutManager(this, 2));
        rvContent.setAdapter(favoriteListAdpter);
        rvContent.setOnScrollListener(new LoadMoreScrollListener(rvContent));
        getFavoriteList(1);
    }

    /**
     * 我的收藏接口
     *
     * @param page 分页
     */
    public void getFavoriteList(int page) {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(MeService.class).favoriteList(4, -1, -1, UserLogic.getUser().getAccess_token(), page)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<FavoriteListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<FavoriteListDataBean> t) throws Exception {
                mData = t.getData().getList();
                favoriteListAdpter.setData(mData);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    public void setRefreshLayout() {
        layoutSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        layoutSwipeRefresh.setOnRefreshListener(() -> {
            getFavoriteList(1);
        });
    }


    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }
}
