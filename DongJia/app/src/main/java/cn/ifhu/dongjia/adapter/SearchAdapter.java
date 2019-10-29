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
import cn.ifhu.dongjia.model.data.SearchDataBean;

public class SearchAdapter extends BaseLoadMoreAdapter<SearchDataBean.ListBean, SearchAdapter.ViewHolder> {


    private List<SearchDataBean.ListBean> mDatas;
    private Context context;

    public SearchAdapter(List<SearchDataBean.ListBean> mDatas, Context context) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public void setData(List<SearchDataBean.ListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_selection, null));
    }

    @Override
    public List<SearchDataBean.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivCoverPic.load(mDatas.get(position).getPic_url());
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvOriginalPrice.setText(mDatas.get(position).getPrice());
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
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
