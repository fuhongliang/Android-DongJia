package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.GoodsListDataBean;

public class GoodsListAdapter extends BaseLoadMoreAdapter<GoodsListDataBean.ListBean, GoodsListAdapter.ViewHolder> {

    private List<GoodsListDataBean.ListBean> mData;
    private Context context;
    private OnClickItem onClickItem;

    public GoodsListAdapter(List<GoodsListDataBean.ListBean> mData, Context context, OnClickItem onClickItem) {
        this.context = context;
        this.mData = mData;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<GoodsListDataBean.ListBean> data) {
        this.mData = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_selection, null));
    }

    @Override
    public List<GoodsListDataBean.ListBean> getDataList() {
        return mData;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mData.size()) return;
        holder.ivCoverPic.load(mData.get(position).getPic_url());
        holder.tvName.setText(mData.get(position).getName());
        holder.tvOriginalPrice.setText("￥"+mData.get(position).getOriginal_price());
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvPrice.setText("￥"+mData.get(position).getPrice());
        holder.tvShowIntegral.setText(mData.get(position).getShow_integral()+"已买");
        if (onClickItem != null){
            holder.llRecommend.setOnClickListener(v -> onClickItem.LlRecommend(position));
        }
    }


    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public interface OnClickItem {
        void LlRecommend(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cover_pic)
        GlideImageView ivCoverPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_original_price)
        TextView tvOriginalPrice;
        @BindView(R.id.tv_show_integral)
        TextView tvShowIntegral;
        @BindView(R.id.ll_recommend)
        LinearLayout llRecommend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
