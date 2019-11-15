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
import cn.ifhu.dongjia.model.data.ShopDataBean;
import cn.ifhu.dongjia.utils.Utils;

public class FeaturedBuyAdapter extends BaseLoadMoreAdapter<ShopDataBean.GoodsListBean, FeaturedBuyAdapter.ViewHolder> {

    private List<ShopDataBean.GoodsListBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    @Override
    public void setData(List<ShopDataBean.GoodsListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    public FeaturedBuyAdapter(List<ShopDataBean.GoodsListBean> mDatas, Context context, OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_selection, null));
    }

    @Override
    public List<ShopDataBean.GoodsListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivCoverPic.load(mDatas.get(position).getCover_pic());
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvOriginalPrice.setText("￥" + Utils.getPrettyNumber(mDatas.get(position).getOriginal_price()));
        holder.tvOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvPrice.setText("￥" + Utils.getPrettyNumber(mDatas.get(position).getPrice()));
        holder.tvShowIntegral.setText(mDatas.get(position).getGoods_num()+"人购买");
        if (onClickItem != null){
            holder.llRecommend.setOnClickListener(v ->
                    onClickItem.Recommend(position));
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnClickItem {
        void Recommend(int position);
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
