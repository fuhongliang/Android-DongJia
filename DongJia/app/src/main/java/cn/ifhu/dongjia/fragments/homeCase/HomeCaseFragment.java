package cn.ifhu.dongjia.fragments.homeCase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.homeCase.CallDesignerActivity;
import cn.ifhu.dongjia.adapter.HomeCaseAdpter;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.TopicListDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeCaseService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;

/**
 * 家装方案页面
 */
public class HomeCaseFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.rv_home_case)
    RecyclerView rvHomeCase;

    HomeCaseAdpter homeCaseAdpter;
    List<TopicListDataBean.ListBean> mData = new ArrayList<>();


    public static BaseFragment newInstance() {
        return new HomeCaseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_home_case, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText("家装方案");
        ivBack.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefreshLayout();
        homeCaseAdpter = new HomeCaseAdpter(mData, getContext(), new HomeCaseAdpter.OnClickItem() {
            @Override
            public void llHomeCase(int position) {
                Intent intent = new Intent(getContext(), CallDesignerActivity.class);
                intent.putExtra("id",mData.get(position).getId());
                startActivity(intent);
            }
        });
        rvHomeCase.setNestedScrollingEnabled(false);
        rvHomeCase.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        rvHomeCase.setAdapter(homeCaseAdpter);
        rvHomeCase.setOnScrollListener(new LoadMoreScrollListener(rvHomeCase));
        getTopicList(1);
    }

    @SuppressLint("ResourceAsColor")
    public void setRefreshLayout() {
        layoutSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        layoutSwipeRefresh.setOnRefreshListener(() -> {
            getTopicList(1);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getTopicList(1);
    }

    /**
     * 方案列表接口
     *
     * @param page
     */
    public void getTopicList(int page) {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(HomeCaseService.class).TopicList(4, -1, -1, page, 0, 0)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<TopicListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<TopicListDataBean> t) throws Exception {
                mData = t.getData().getList();
                homeCaseAdpter.setData(mData);
            }
        });
    }
}
