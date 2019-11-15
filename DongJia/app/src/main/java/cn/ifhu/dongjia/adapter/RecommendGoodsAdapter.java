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
import cn.ifhu.dongjia.model.data.HomeDataBean;
import cn.ifhu.dongjia.utils.Utils;

/**
 * 首页爆款热卖适配器
 */
public class RecommendGoodsAdapter extends BaseLoadMoreAdapter<HomeDataBean.RecommendGoodsBean, RecommendGoodsAdapter.ViewHolder> {

    private List<HomeDataBean.RecommendGoodsBean> mDatas;
    private Context mContext;
    private OnClickItem onClickItem;

    @Override
    public void setData(List<HomeDataBean.RecommendGoodsBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    public RecommendGoodsAdapter(List<HomeDataBean.RecommendGoodsBean> mDatas, Context mContext, OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(mContext, R.layout.item_home_best_selling, null));
    }

    @Override
    public List<HomeDataBean.RecommendGoodsBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;

        holder.ivStorePic.load(mDatas.get(position).getCover_pic());
        holder.tvStoreName.setText(mDatas.get(position).getName());
        holder.tvPrice.setText("￥" + Utils.getPrettyNumber(mDatas.get(position).getPrice()));
        holder.tvOriginalPrice.setText("￥" + Utils.getPrettyNumber(mDatas.get(position).getOriginal_price()));
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (onClickItem != null) {
            holder.llRecommendGoods.setOnClickListener(v ->
                    onClickItem.recommendGoods(position));
        }
    }


    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }
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
            ButterKnife.bind(this, itemView);
        }
    }
}
