package cn.ifhu.dongjia.fragments.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import cn.ifhu.dongjia.activity.order.OrderDetailsActivity;
import cn.ifhu.dongjia.adapter.OrderListAdapter;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.OrderDetailDataBean;
import cn.ifhu.dongjia.model.data.OrderListDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.OrderServer;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 已取消订单页面
 */
public class CancelOrderFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    OrderListAdapter orderListAdapter;
    List<OrderListDataBean.ListBean> listBeans = new ArrayList<>();

    public static CancelOrderFragment newInstance() {
        return new CancelOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefreshLayout();
        getOrderList();
        setRefreshLayout();
        orderListAdapter = new OrderListAdapter(listBeans, getContext(), new OrderListAdapter.OnClickItem() {
            @Override
            /**
             * 取消订单接口
             */
            public void TvCancel(int position) {
                setLoadingMessageIndicator(true);
                RetrofitAPIManager.create(OrderServer.class).orderRevoke(4, -1, -1, UserLogic.getUser().getAccess_token(), listBeans.get(position).getOrder_id())
                        .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<OrderDetailDataBean>(true) {
                    @Override
                    protected void onApiComplete() {
                        setLoadingMessageIndicator(false);
                    }

                    @Override
                    protected void onSuccees(BaseEntity<OrderDetailDataBean> t) throws Exception {
                        ToastHelper.makeText(t.getMessage()).show();
                        getOrderList();
                    }
                });
            }

            @Override
            public void TvDelete(int position) {

            }

            @Override
            public void TvPay(int position) {

            }

            @Override
            public void TvConfirm(int position) {

            }

        });
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvOrder.setAdapter(orderListAdapter);
        rvOrder.setOnScrollListener(new LoadMoreScrollListener(rvOrder));

    }
    @Override
    public void onResume() {
        super.onResume();
        getOrderList();
    }
    /**
     * * 订单列表接口
     */
    public void getOrderList() {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(OrderServer.class).orderList(4, -1, -1, UserLogic.getUser().getAccess_token(), 5)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<OrderListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<OrderListDataBean> t) throws Exception {
                listBeans = t.getData().getList();
                orderListAdapter.setData(listBeans);
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    public void setRefreshLayout() {
        layoutSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        layoutSwipeRefresh.setOnRefreshListener(() -> {
            getOrderList();
        });
    }
}
