package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.MainActivity;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.activity.order.OrderDetailsActivity;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.OrderListDataBean;

public class OrderListAdapter extends BaseLoadMoreAdapter<OrderListDataBean.ListBean, OrderListAdapter.ViewHolder> {



    private List<OrderListDataBean.ListBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    public OrderListAdapter(List<OrderListDataBean.ListBean>mDatas,Context context, OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<OrderListDataBean.ListBean> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_order_child, null));
    }

    @Override
    public List<OrderListDataBean.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivStorePic.load(mDatas.get(position).getMch().getLogo());
        holder.tvStoreName.setText(mDatas.get(position).getMch().getName());
        holder.tvStatus.setText(mDatas.get(position).getStatus());

        holder.llOrder.removeAllViews();

        for (int i = 0; i < mDatas.get(position).getGoods_list().size(); i++) {
            OrderListDataBean.ListBean.GoodsListBean goodsList = mDatas.get(position).getGoods_list().get(i);
            StringBuilder attr = new StringBuilder();
            for (int j = 0; j < mDatas.get(position).getGoods_list().get(i).getAttr_list().size(); j++) {
                OrderListDataBean.ListBean.GoodsListBean.AttrListBean attrList = mDatas.get(position).getGoods_list().get(i).getAttr_list().get(j);
                attr.append(attrList.getAttr_group_name()).append(":").append(attrList.getAttr_name()).append(" ");
            }
            View view = gernerateGoodsView(mDatas.get(position).getOrder_id(),goodsList.getGoods_pic(), goodsList.getGoods_name(), attr.toString(), goodsList.getPrice(), goodsList.getNum() + "");
            holder.llOrder.addView(view);
        }
        if (onClickItem != null) {
            holder.tvCancel.setOnClickListener(v -> onClickItem.TvCancel(position));
        }
        if (onClickItem != null) {
            holder.llOrder.setOnClickListener(v -> onClickItem.LlOrder(position));
        }
    }

    /**
     * 动态获取xml文件
     *
     * @param goodsPic  商品图片
     * @param goodsName 商品名称
     * @param attr      商品规格
     * @param price     商品价格
     * @param number    商品数量
     * @return
     */
    public View gernerateGoodsView(int orderId,String goodsPic, String goodsName, String attr, String price, String number) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_group, null, false);
        ((GlideImageView) view.findViewById(R.id.iv_good_pic)).load(goodsPic);
        ((TextView) view.findViewById(R.id.tv_good_name)).setText(goodsName);
        ((TextView) view.findViewById(R.id.tv_specification)).setText(attr);
        ((TextView) view.findViewById(R.id.tv_goods_price)).setText("￥" + price);
        ((TextView) view.findViewById(R.id.tv_goods_num)).setText("x" + number);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("orderId",orderId);
                context.startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + 1;
    }

    public interface OnClickItem {
        void TvCancel(int position);

        void LlOrder(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_store_pic)
        GlideImageView ivStorePic;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.ll_order)
        LinearLayout llOrder;
        @BindView(R.id.tv_cancel)
        TextView tvCancel;
        @BindView(R.id.tv_call_store)
        TextView tvCallStore;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.tv_pay)
        TextView tvPay;
        @BindView(R.id.tv_confirm)
        TextView tvConfirm;
        @BindView(R.id.ll_goods)
        LinearLayout llGoods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
