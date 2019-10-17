package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.AddressDataBean;

public class AddressAdpter extends BaseLoadMoreAdapter<AddressDataBean.AddressListBean, AddressAdpter.ViewHolder> {


    private Context context;
    private List<AddressDataBean.AddressListBean> mData;
    private OnClickItem onClickItem;

    public AddressAdpter(List<AddressDataBean.AddressListBean> mData, Context context, OnClickItem onClickItem) {
        this.mData = mData;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<AddressDataBean.AddressListBean> data) {
        this.mData = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_my_address, null));
    }

    @Override
    public List<AddressDataBean.AddressListBean> getDataList() {
        return mData;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mData.size()) return;
        holder.tvName.setText(mData.get(position).getName());
        holder.tvPhone.setText(mData.get(position).getPhone());
        holder.tvAddress.setText(mData.get(position).getAddress());
        if (onClickItem != null) {
            holder.rlDelete.setOnClickListener(v ->
                    onClickItem.Delete(position));
            holder.rlEdit.setOnClickListener(v ->
                    onClickItem.Edit(position));
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnClickItem {
        void Delete(int position);

        void Edit(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_select)
        TextView tvSelect;
        @BindView(R.id.delete)
        ImageView delete;
        @BindView(R.id.rl_delete)
        RelativeLayout rlDelete;
        @BindView(R.id.edit)
        ImageView edit;
        @BindView(R.id.rl_edit)
        RelativeLayout rlEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
