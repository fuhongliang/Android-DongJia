package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.GoodsRecommendDataBean;

public class GoodsRecommendAdapter extends BaseLoadMoreAdapter<GoodsRecommendDataBean.ListBean, GoodsRecommendAdapter.ViewHolder> {
    private List<GoodsRecommendDataBean.ListBean> mDatas;
    private Context context;

    public GoodsRecommendAdapter(List<GoodsRecommendDataBean.ListBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public void setData(List<GoodsRecommendDataBean.ListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_best_selling, null));
    }

    @Override
    public List<GoodsRecommendDataBean.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull GoodsRecommendAdapter.ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.tvStoreName.setText(mDatas.get(position).getName());
        holder.tvPrice.setText(mDatas.get(position).getPrice());
        holder.ivStorePic.load(mDatas.get(position).getPic_url());
        holder.tvOriginalPrice.setText(mDatas.get(position).getOriginal_price());
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
