package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.RecommendDataBean;

public class RecommendAdapter extends BaseLoadMoreAdapter<RecommendDataBean.ListBean, RecommendAdapter.ViewHolder> {


    private List<RecommendDataBean.ListBean> mDatas;
    private Context mContext;

    public RecommendAdapter(List<RecommendDataBean.ListBean> mDatas, Context mContext) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public void setData(List<RecommendDataBean.ListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(mContext, R.layout.item_home_selection, null));
    }

    @Override
    public List<RecommendDataBean.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivCoverPic.load(mDatas.get(position).getCover_pic());
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvPrice.setText("￥"+mDatas.get(position).getPrice());
        holder.tvOriginalPrice.setText("￥"+mDatas.get(position).getOriginal_price());
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvShowIntegral.setText(mDatas.get(position).getShow_integral()+"人已买");
    }


    @Override
    public int getItemCount() {
        return mDatas.size() < PAGESIZE ? mDatas.size() : mDatas.size() + 1;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
