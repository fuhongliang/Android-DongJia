package cn.ifhu.dongjia.fragments.shopCart;

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
import androidx.recyclerview.widget.RecyclerView;

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
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.net.ShopCartService;
import cn.ifhu.dongjia.utils.DialogUtils;
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
    @BindView(R.id.rv_cart)
    RecyclerView rvCart;
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


    private ShoppingCarAdapter shoppingCarAdapter;
    private Context context;
    private List<CartListDataBean.MchListBean> mData;

    boolean isSelect_shop = false;

    public static BaseFragment newInstance() {
        return new ShopCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText("购物车");
        ivBack.setVisibility(View.GONE);
        if (UserLogic.getUser() != null) {

            getCartList();
            initExpandableListView();
        }
        return view;
    }

    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        shoppingCarAdapter = new ShoppingCarAdapter(context, ivSelectAll, rlSelectAll, tvOk, tvDelete, tvPrice);
        elvShoppingCar.setAdapter(shoppingCarAdapter);
        //删除回调
        shoppingCarAdapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {
            @Override
            public void onDelete() {

            }
        });
        //修改商品数量回调
        shoppingCarAdapter.setOnChangeCountListener(new ShoppingCarAdapter.OnChangeCountListener() {
            @Override
            public void onChangeCount(String goods_id) {

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
    private void initDelete() {
        //判断是否有店铺或商品被选中
        //true为有，则需要刷新数据；反之，则不需要；
        boolean hasSelect = false;
        //创建临时的List，用于存储没有被选中的购物车数据
        List<CartListDataBean.MchListBean> datasTemp = new ArrayList<>();

        for (int i = 0; i < mData.size(); i++) {
            List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();

            if (isSelect_shop) {
                hasSelect = true;
                //跳出本次循环，继续下次循环。
                continue;
            } else {
                datasTemp.add(mData.get(i));
                datasTemp.get(datasTemp.size() - 1).setList(new ArrayList<>());
            }

            for (int y = 0; y < goods.size(); y++) {
                CartListDataBean.MchListBean.ListBeanX goodsList = goods.get(y);
                boolean isSelect = goodsList.isDisabled();

                if (isSelect) {
                    hasSelect = true;
                } else {
                    datasTemp.get(datasTemp.size() - 1).getList().add(goodsList);
                }
            }
        }

        if (hasSelect) {
//            showDeleteDialog(datasTemp);
        } else {
            ToastHelper.makeText("请选择要删除的商品").show();
        }
    }

    /**
     * 购物车列表接口
     */
    public void getCartList() {
        RetrofitAPIManager.create(ShopCartService.class).cartList(4, -1, -1, UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<CartListDataBean>(true) {
            @Override
            protected void onApiComplete() {

            }

            @Override
            protected void onSuccees(BaseEntity<CartListDataBean> t) throws Exception {
                shoppingCarAdapter.setData(mData);
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
        DialogUtils.showConfirmDialog("温馨提示", "是否删除该商品", getFragmentManager(), new ConfirmDialog.ButtonOnclick() {
            @Override
            public void cancel() {

            }

            @Override
            public void ok() {

            }
        });
    }
}
