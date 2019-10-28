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
import cn.ifhu.dongjia.model.data.AddressListDataBean;

public class AddressAdapter extends BaseLoadMoreAdapter<AddressListDataBean.ListBean, AddressAdapter.ViewHolder> {

//    private address address_id;
    private Context context;
    private List<AddressListDataBean.ListBean> mData;
    private OnClickItem onClickItem;

    public AddressAdapter(List<AddressListDataBean.ListBean> mData, Context context, OnClickItem onClickItem) {
        this.mData = mData;
        this.context = context;
        this.onClickItem = onClickItem;
    }

//    public void setAddress_id(address address_id){
//        this.address_id = address_id;
//    }
//

    @Override
    public void setData(List<AddressListDataBean.ListBean> data) {
        this.mData = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_my_address, null));
    }

    @Override
    public List<AddressListDataBean.ListBean> getDataList() {
        return mData;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mData.size()) return;
        holder.tvName.setText(mData.get(position).getName());
        holder.tvPhone.setText(mData.get(position).getMobile());
        holder.tvAddress.setText(mData.get(position).getAddress());
        if (onClickItem != null) {
            holder.rlDelete.setOnClickListener(v ->{
                        onClickItem.Delete(position);
//                        address_id.id(mData.get(position).getId());
                    }
            );

            holder.rlEdit.setOnClickListener(v ->
                    onClickItem.Edit(position));
        }
        holder.ivSelect.setOnClickListener(v -> {
            holder.ivSelect.setSelected(!holder.ivSelect.isSelected());
        });
        //String的不能用=号  int可以用= ，String用.equals
        if (mData.get(position).getIs_default().equals("0")){
            holder.ivSelect.setSelected(false);
        }else {
            holder.ivSelect.setSelected(true);
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
        @BindView(R.id.iv_select)
        ImageView ivSelect;
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

    // TODO: 2019-10-25 声明一个address
//    public interface address {
//        void id(String id);
//    }

}
