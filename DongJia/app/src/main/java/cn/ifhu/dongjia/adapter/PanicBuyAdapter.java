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
import cn.ifhu.dongjia.utils.DateUtil;
import cn.ifhu.dongjia.utils.Utils;

public class PanicBuyAdapter extends BaseLoadMoreAdapter<HomeDataBean.MiaoshaBean.GoodsListBean, PanicBuyAdapter.ViewHolder> {


    private List<HomeDataBean.MiaoshaBean.GoodsListBean> mDatas;
    private Context mContext;
    public OnClickItem onClickItem;

    public PanicBuyAdapter(List<HomeDataBean.MiaoshaBean.GoodsListBean> mDatas, Context mContext, OnClickItem onClickItem) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<HomeDataBean.MiaoshaBean.GoodsListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(mContext, R.layout.item_home_limited_time, null));
    }

    @Override
    public List<HomeDataBean.MiaoshaBean.GoodsListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;

        holder.ivPic.load(mDatas.get(position).getPic());
        //String类型转换long类型
        holder.tvStartTime.setText("距结束" + DateUtil.getLongToTimeString(Long.valueOf(mDatas.get(position).getStart_time())));
        holder.tvName.setText(mDatas.get(position).getName());
        holder.tvPrice.setText("￥"+Utils.getPrettyNumber(mDatas.get(position).getPrice()));
        holder.tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvMiaoShaPrice.setText("￥"+Utils.getPrettyNumber(mDatas.get(position).getMiaosha_price()));
        if (onClickItem != null) {
            holder.llBuy.setOnClickListener(v ->
                    onClickItem.panicBuy(position));
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
        void panicBuy(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        GlideImageView ivPic;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_miao_sha_price)
        TextView tvMiaoShaPrice;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.ll_buy)
        LinearLayout llBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
