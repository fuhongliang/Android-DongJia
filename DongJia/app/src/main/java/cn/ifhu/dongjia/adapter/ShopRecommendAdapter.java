package cn.ifhu.dongjia.adapter;

import android.content.Context;
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
import cn.ifhu.dongjia.model.data.ShopDataBean;

public class ShopRecommendAdapter extends BaseLoadMoreAdapter<ShopDataBean.RecommendListBean, ShopRecommendAdapter.ViewHolder> {

    private List<ShopDataBean.RecommendListBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    public ShopRecommendAdapter(List<ShopDataBean.RecommendListBean>mDatas,Context context,OnClickItem onClickItem){
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }
    @Override
    public void setData(List<ShopDataBean.RecommendListBean> data) {
        this.mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_best_selling, null));
    }

    @Override
    public List<ShopDataBean.RecommendListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivStorePic.load(mDatas.get(position).getCover_pic());
        holder.tvOriginalPrice.setText(mDatas.get(position).getOriginal_price());
        holder.tvPrice.setText(mDatas.get(position).getPrice());
        holder.tvStoreName.setText(mDatas.get(position).getName());
        if (onClickItem != null) {
            holder.llRecommendGoods.setOnClickListener(v ->
                    onClickItem.recommendGoods(position));
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnClickItem {
        void recommendGoods(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_store_pic)
        GlideImageView ivStorePic;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_original_price)
        TextView tvOriginalPrice;
        @BindView(R.id.ll_recommend_goods)
        LinearLayout llRecommendGoods;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
