package cn.ifhu.dongjia.fragments.shopCart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.ShoppingCarAdapter;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.CartListDataBean;
import cn.ifhu.dongjia.model.post.EditCartPostDataBean;
import cn.ifhu.dongjia.model.post.SettlementPost;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.ShopCartService;
import cn.ifhu.dongjia.utils.DialogUtils;
import cn.ifhu.dongjia.utils.GsonUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;
import cn.ifhu.dongjia.view.dialog.nicedialog.ConfirmDialog;

/**
 * 购物车页面
 */

public class ShopCartFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.iv_Select_all)
    ImageView ivSelectAll;
    @BindView(R.id.rl_select_all)
    RelativeLayout rlSelectAll;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.options1)
    RelativeLayout options1;
    @BindView(R.id.elv_shopping_car)
    ExpandableListView elvShoppingCar;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rl_total_price)
    RelativeLayout rlTotalPrice;
    @BindView(R.id.layout_swipe_refresh)
    SwipeRefreshLayout layoutSwipeRefresh;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_settlement)
    TextView tvSettlement;


    private ShoppingCarAdapter shoppingCarAdapter;
    private Context context;
    private List<CartListDataBean.MchListBean> mData;

    private List<CartListDataBean.ListBean> listDatas;


    private List<List<Boolean>> mDataIsSelect;

    public static BaseFragment newInstance() {
        return new ShopCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText("购物车");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRefreshLayout();
        ivBack.setVisibility(View.GONE);
        if (UserLogic.getUser() != null) {
            initExpandableListView();
            getCartList();
        }
        initExpandableListViewData(mData);
    }

    @SuppressLint("ResourceAsColor")
    public void setRefreshLayout() {
        layoutSwipeRefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);

        layoutSwipeRefresh.setOnRefreshListener(() -> {
            getCartList();
        });
    }

    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        shoppingCarAdapter = new ShoppingCarAdapter(getContext(), ivSelectAll, rlSelectAll, tvOk, tvDelete, tvPrice, tvEdit, tvSettlement);
        elvShoppingCar.setAdapter(shoppingCarAdapter);
        //删除回调
        shoppingCarAdapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {
            @Override
            public void onDelete() {
                DialogUtils.showConfirmDialog("温馨提示", "是否删除该地址", getFragmentManager(), new ConfirmDialog.ButtonOnclick() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void ok() {
                        initDelete();
                        getCartList();
                    }
                });
            }
        });
        //修改商品数量回调
        shoppingCarAdapter.setOnChangeCountListener(new ShoppingCarAdapter.OnChangeCountListener() {
            @Override
            public void onChangeCount(String cartList) {
                EditCartPostDataBean editCartPostDataBean = new EditCartPostDataBean();
                editCartPostDataBean.setAccess_token(UserLogic.getUser().getAccess_token());
                editCartPostDataBean.setCart_id_list(cartList);
                RetrofitAPIManager.create(ShopCartService.class).editCart(editCartPostDataBean)
                        .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
                    @Override
                    protected void onApiComplete() {

                    }

                    @Override
                    protected void onSuccees(BaseEntity t) throws Exception {
                        ToastHelper.makeText(t.getMessage()).show();
                    }

                });
            }
        });
        //编辑回调
        shoppingCarAdapter.setmOnEdit(new ShoppingCarAdapter.OnEdit() {
            @Override
            public void onEdit() {
                tvEdit.setVisibility(View.GONE);
                tvSettlement.setVisibility(View.GONE);
                tvDelete.setVisibility(View.VISIBLE);
                tvOk.setVisibility(View.VISIBLE);
            }
        });
        //结算回调
        shoppingCarAdapter.setOnSettlement(new ShoppingCarAdapter.OnSettlement() {
            @Override
            public void OnSettlement() {
//                //创建一个对象去接收数据
//                SettlementPost settlementPost = new SettlementPost();
//                //遍历数据
//                for (int i = 0; i < mData.size(); i++) {
//                    List<CartListDataBean.MchListBean.ListBeanX> listData = mData.get(i).getList();
//                    for (int j = 0; j < listData.size(); j++) {
//                        settlementPost.setGoods_id(listData.get(j).getGoods_id()+"");
//                        settlementPost.setNum(listData.get(j).getNum());
//                        List<CartListDataBean.MchListBean.ListBeanX.AttrListBeanX> attrListData = listData.get(j).getAttr_list();
//                        for (int k = 0; k < attrListData.size(); k++) {
//                        }
//
//                    }
//                }

            }
        });
        //完成回调
        shoppingCarAdapter.setmOnOk(new ShoppingCarAdapter.OnOk() {
            @Override
            public void OnOK() {
                tvEdit.setVisibility(View.VISIBLE);
                tvSettlement.setVisibility(View.VISIBLE);
                tvDelete.setVisibility(View.GONE);
                tvOk.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
    private void initExpandableListViewData(List<CartListDataBean.MchListBean> datas) {
        if (datas != null && datas.size() > 0) {
            //刷新数据时，保持当前位置
            shoppingCarAdapter.setData(datas);

            //使所有组展开
            for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                elvShoppingCar.expandGroup(i);
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener((parent, v, groupPosition, id) -> true);

            elvShoppingCar.setVisibility(View.VISIBLE);
            rlTotalPrice.setVisibility(View.VISIBLE);
        } else {
            elvShoppingCar.setVisibility(View.GONE);
        }
    }

    /**
     * 判断是否要弹出删除的dialog
     * 通过bean类中的DatasBean的isSelect_shop属性，判断店铺是否被选中；
     * GoodsBean的isSelect属性，判断商品是否被选中，
     */
    public void initDelete() {
        //创建临时的List
        List<String> datasTemp = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            List<CartListDataBean.MchListBean.ListBeanX> good = mData.get(i).getList();
            for (int j = 0; j < good.size(); j++) {
                boolean isSelect = good.get(j).isDisabled();
                if (isSelect) {
                    datasTemp.add(good.get(j).getCart_id() + "");
                }
            }
        }
        //List<String> 转换 json字符串
        String datas = GsonUtils.convertObject2Json(datasTemp);
        /**
         * 删除商品接口
         */
        RetrofitAPIManager.create(ShopCartService.class).cartDelete(4, -1, -1, UserLogic.getUser().getAccess_token(), datas)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
            }
        });
    }

    /**
     * 购物车列表接口
     */
    public void getCartList() {
        layoutSwipeRefresh.setRefreshing(true);
        RetrofitAPIManager.create(ShopCartService.class).cartList(4, -1, -1, UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<CartListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                layoutSwipeRefresh.setRefreshing(false);
            }

            @Override
            protected void onSuccees(BaseEntity<CartListDataBean> t) throws Exception {
                mData = t.getData().getMch_list();
                shoppingCarAdapter.setData(mData);
                initExpandableListViewData(mData);
                listDatas = t.getData().getList();
            }

        });
    }

    @OnClick(R.id.tv_ok)
    public void onTvOkClicked() {

    }

    @OnClick(R.id.rl_select_all)
    public void onRlSelectAllClicked() {
    }

    @OnClick(R.id.tv_delete)
    public void onTvDeleteClicked() {

    }

    @OnClick(R.id.tv_right_text)
    public void onTvRightTextClicked() {
    }
}
