package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunfusheng.GlideImageView;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.home.ConfirmOrderActivity;
import cn.ifhu.dongjia.model.data.CartListDataBean;
import cn.ifhu.dongjia.model.post.CartListPost;
import cn.ifhu.dongjia.model.post.MchListPost;
import cn.ifhu.dongjia.utils.GsonUtils;
import cn.ifhu.dongjia.utils.ToastHelper;

import static cn.ifhu.dongjia.R.drawable.gwc_ic_xuanze;
import static cn.ifhu.dongjia.R.drawable.gwc_ic_xuanze1;

/**
 * 购物车的adapter
 * 因为使用的是ExpandableListView，所以继承BaseExpandableListAdapter
 */
public class ShoppingCarAdapter extends BaseExpandableListAdapter {


    /**
     * 声明
     */
    private List<CartListDataBean.MchListBean> mData;
    //店铺选择
//    private boolean isSelect_shop = false;
    //全部选中
    private boolean isSelectAll = false;
    private double total_price;

    /**
     * 声明控件
     */
    private Context context;
    private ImageView ivSelectAll;
    private RelativeLayout rlSelectAll;
    private TextView tvOk;
    private TextView tvDelete;
    private TextView tvPrice;
    private TextView tvEdit;
    private TextView tvSettlement;

    public ShoppingCarAdapter(Context context, ImageView ivSelectAll, RelativeLayout rlSelectAll, TextView tvOk, TextView tvDelete, TextView tvPrice, TextView tvEdit, TextView tvSettlement) {
        this.context = context;
        this.ivSelectAll = ivSelectAll;
        this.rlSelectAll = rlSelectAll;
        this.tvOk = tvOk;
        this.tvDelete = tvDelete;
        this.tvPrice = tvPrice;
        this.tvEdit = tvEdit;
        this.tvSettlement = tvSettlement;
    }

    /**
     * 塞入数据
     * notifyDataSetChanged()刷新数据
     *
     * @param data 需要刷新的数据
     */
    public void setData(List<CartListDataBean.MchListBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    //获取分组的个数
    public int getGroupCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        } else {
            return 0;
        }
    }

    @Override
    //获取指定的分组数据
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    //获取指定分组的ID,这个ID必须的唯一的
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    // 获取显示指定分组的视图
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopping_car_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //商家数据声明
        CartListDataBean.MchListBean mchList = mData.get(groupPosition);
        //赋值
        groupViewHolder.ivAvatar.load(mchList.getLogo());
        groupViewHolder.tvStoreName.setText(mchList.getName());


        //店铺内的商品都选中的时候，店铺的也要选中
        for (int i = 0; i < mchList.getList().size(); i++) {
            CartListDataBean.MchListBean.ListBeanX goodsList = mchList.getList().get(i);
            boolean isSelect = goodsList.isDisabled();
            if (isSelect) {
                mchList.setIsSelect_shop(true);
            } else {
                mchList.setIsSelect_shop(false);
                break;
            }
        }
        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        boolean isSelect_shop = mchList.getIsSelect_shop();
        if (isSelect_shop) {
            groupViewHolder.ivSelect.setSelected(true);
        } else {
            groupViewHolder.ivSelect.setSelected(false);
        }

        //店铺选择框的点击事件
        groupViewHolder.rlStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mchList.setIsSelect_shop(!isSelect_shop);

                List<CartListDataBean.MchListBean.ListBeanX> goodsList = mchList.getList();
                for (int i = 0; i < goodsList.size(); i++) {
                    CartListDataBean.MchListBean.ListBeanX listBeanX = goodsList.get(i);
                    listBeanX.setDisabled(!isSelect_shop);
                }
                notifyDataSetChanged();
            }
        });
        //当所有的选择框都是选中的时候，全选也要选中
        w:
        for (int i = 0; i < mData.size(); i++) {
            List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();
            for (int y = 0; y < goods.size(); y++) {

                CartListDataBean.MchListBean.ListBeanX goodsBean = goods.get(y);
                boolean isSelect = goodsBean.isDisabled();
                if (isSelect) {
                    isSelectAll = true;
                } else {
                    isSelectAll = false;
                    break w;//根据标记，跳出嵌套循环
                }
            }
        }
        //全选
        if (isSelectAll) {
            ivSelectAll.setSelected(true);
        } else {
            ivSelectAll.setSelected(false);
        }
        //全选点击事件
        rlSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = !isSelectAll;
                if (isSelectAll) {
                    for (int i = 0; i < mData.size(); i++) {
                        List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();
                        for (int j = 0; j < goods.size(); j++) {
                            CartListDataBean.MchListBean.ListBeanX goodsList = goods.get(j);
                            goodsList.setDisabled(true);
                        }
                    }
                } else {
                    for (int i = 0; i < mData.size(); i++) {
                        List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();
                        for (int j = 0; j < goods.size(); j++) {
                            CartListDataBean.MchListBean.ListBeanX goodsList = goods.get(j);
                            goodsList.setDisabled(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });
        //合计计算
        total_price = 0.0;
        tvPrice.setText("￥0.00");
        for (int i = 0; i < mData.size(); i++) {
            List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();
            for (int j = 0; j < goods.size(); j++) {
                CartListDataBean.MchListBean.ListBeanX goodsList = goods.get(j);
                boolean isSelect = goodsList.isDisabled();
                if (isSelect) {
                    String num = goodsList.getNum() + "";
                    String price = goodsList.getUnitPrice() + "";

                    double v = Double.parseDouble(num);
                    double v1 = Double.parseDouble(price);

                    total_price = total_price + v * v1;

                    //让Double 类型完整显示，不用科学计数法显示大写字母E
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    tvPrice.setText("￥" + decimalFormat.format(total_price));
                }
            }
        }
        //结算点击事件
        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建临时的list，用于存储被选中的商品
                List<CartListDataBean.MchListBean.ListBeanX> temp = new ArrayList<>();
                for (int i = 0; i < mData.size(); i++) {
                    List<CartListDataBean.MchListBean.ListBeanX> goods = mData.get(i).getList();
                    for (int j = 0; j < goods.size(); j++) {
                        CartListDataBean.MchListBean.ListBeanX goodsList = goods.get(j);
                        boolean isSelect = goodsList.isDisabled();
                        if (isSelect) {
                            temp.add(goodsList);
                        }
                    }
                }
                //如果有被选中的
                if (temp != null && temp.size() > 0) {
//                    ToastHelper.makeText("跳转到确认订单页面,完成后续订单流程").show();
                    gotoConfirm();
                } else {
                    ToastHelper.makeText("请选择要购买的商品").show();
                }
            }
        });
        //删除点击事件
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDeleteListener != null) {
                    mDeleteListener.onDelete();
                }
            }
        });
        //编辑点击事件
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEditListener != null) {
                    mOnEditListener.onEdit();
                }
            }
        });
        //完成点击事件
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnOkListener != null) {
                    mOnOkListener.OnOK();
                }
            }
        });
        return convertView;

    }
    //去购物车结算
    public void gotoConfirm() {
        MchListPost mch = new MchListPost();
        List<MchListPost.MchListBean> list = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            MchListPost.MchListBean mchListPost = new MchListPost.MchListBean();

            List<Integer> cart_id_list = new ArrayList<>();
            mchListPost.setId(mData.get(i).getId());
            for (CartListDataBean.MchListBean.ListBeanX listBean : mData.get(i).getList()) {
                cart_id_list.add(listBean.getCart_id());
            }
            mchListPost.setCart_id_list(cart_id_list);
            list.add(mchListPost);
        }
        mch.setMchListBeans(list);
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("mch_list", mch);
        context.startActivity(intent);
    }

    static class GroupViewHolder {
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.iv_avatar)
        GlideImageView ivAvatar;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.rl_store)
        RelativeLayout rlStore;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //--------------------------------

    /**
     * 子选项
     *
     * @param groupPosition
     * @return
     */
    @Override
    //获取指定分组中的子选项的个数
    public int getChildrenCount(int groupPosition) {
        if (mData.get(groupPosition).getList() != null && mData.get(groupPosition).getList().size() > 0) {
            return mData.get(groupPosition).getList().size();
        } else {
            return 0;
        }
    }

    @Override
    //获取指定分组中的指定子选项数据
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getList().get(childPosition);
    }


    @Override
    //获取子选项的ID，这个ID必须是唯一的
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    //取得显示给定分组给定子位置的数据用的视图
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shop_cart_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //声明数据
        CartListDataBean.MchListBean mchList = mData.get(groupPosition);
        CartListDataBean.MchListBean.ListBeanX goodsList = mchList.getList().get(childPosition);
        boolean isSelect = goodsList.isDisabled();
        String goods_id = goodsList.getGoods_id() + "";

        // 2019-10-23 塞入数据
        childViewHolder.ivGoodPic.load(goodsList.getGoods_pic());
        childViewHolder.tvGoodName.setText(goodsList.getGoods_name());
        List<CartListDataBean.MchListBean.ListBeanX.AttrListBeanX> attrList = goodsList.getAttr_list();
        for (int i = 0; i < attrList.size(); i++) {
            childViewHolder.tvSpecification.setText(attrList.get(i).getAttr_group_name() + attrList.get(i).getAttr_name());
        }
        childViewHolder.tvNumber.setText(goodsList.getNum() + "");
        childViewHolder.tvPrice.setText(goodsList.getUnitPrice() + "");
//        //商品是否被选中
        if (isSelect) {
            childViewHolder.ivSelect.setSelected(true);
        } else {
            childViewHolder.ivSelect.setSelected(false);
        }
        //商品选择框的点击事件
        childViewHolder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsList.setDisabled(!isSelect);
                if (!isSelect == false) {
                    mchList.setIsSelect_shop(false);
                }
                notifyDataSetChanged();
            }
        });

        //加号点击事件
        childViewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGoodsNumber(goodsList, 1);
            }
        });
        //减号点击事件
        childViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsList.getNum() > 1) {
                    editGoodsNumber(goodsList, -1);
                } else {
                    ToastHelper.makeText("商品不能再减少了").show();
                }
            }
        });

        return convertView;
    }

    /**
     * 编辑商品
     *
     * @param goodsList    商品数据
     * @param changeNumber 加减数量
     */
    public void editGoodsNumber(CartListDataBean.MchListBean.ListBeanX goodsList, int changeNumber) {
        List<CartListPost> cartListPostList = new ArrayList<>();
        CartListPost cartListPost = new CartListPost();
        String cartId = goodsList.getCart_id() + "";
        cartListPost.setCart_id(cartId);
        cartListPost.setNum((goodsList.getNum() + changeNumber) + "");
        cartListPostList.add(cartListPost);
        // 转换json数据
        String cartList = GsonUtils.convertObject2Json(cartListPostList);
        //回调请求后台接口实现数量的加减
        if (mChangeCountListener != null) {
            mChangeCountListener.onChangeCount(cartList);
        }
        // TODO: 2019-11-04 回调成功再去刷新数据
        goodsList.setNum(goodsList.getNum() + changeNumber);
        notifyDataSetChanged();
    }


    static class ChildViewHolder {
        @BindView(R.id.iv_select)
        ImageView ivSelect;
        @BindView(R.id.iv_good_pic)
        GlideImageView ivGoodPic;
        @BindView(R.id.tv_good_name)
        TextView tvGoodName;
        @BindView(R.id.tv_specification)
        TextView tvSpecification;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.iv_add)
        ImageView ivAdd;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //----------------------------------------------------------------------------------------------
    @Override
    //指定位置上的子元素是否可选中
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    //分组和子选项是否持有稳定的ID,就是说底层数据的改变会不会影响到他们
    public boolean hasStableIds() {
        return false;
    }

    //删除回调
    public interface OnDeleteListener {
        void onDelete();
    }

    private OnDeleteListener mDeleteListener;

    public void setOnDeleteListener(OnDeleteListener listener) {
        mDeleteListener = listener;
    }

    //修改商品数量的回调
    public interface OnChangeCountListener {
        void onChangeCount(String goods_id);
    }

    public void setOnChangeCountListener(OnChangeCountListener listener) {
        mChangeCountListener = listener;
    }

    private OnChangeCountListener mChangeCountListener;

    //编辑回调
    public interface OnEdit {
        void onEdit();
    }

    private OnEdit mOnEditListener;

    public void setmOnEdit(OnEdit mOnEdit) {
        mOnEditListener = mOnEdit;
    }

    //结算回调
    public interface OnSettlement {
        void OnSettlement();
    }

    private OnSettlement mOnSettlementListener;

    public void setOnSettlement(OnSettlement mOnSettlement) {
        mOnSettlementListener = mOnSettlement;
    }

    //完成回调
    public interface OnOk {
        void OnOK();
    }

    private OnOk mOnOkListener;

    public void setmOnOk(OnOk mOnOk) {
        mOnOkListener = mOnOk;
    }
}
