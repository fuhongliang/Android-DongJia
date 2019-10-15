package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

/**
 * 超级品牌
 */
public class SuperAdapter extends BaseLoadMoreAdapter<HomeDataBean.NewMchListBean, SuperAdapter.ViewHolder> {



    private List<HomeDataBean.NewMchListBean> mDatas;
    private Context mContext;
    private OnClickItem onClickItem;

    @Override
    public void setData(List<HomeDataBean.NewMchListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    public SuperAdapter(List<HomeDataBean.NewMchListBean> mData, Context mContext, OnClickItem onClickItem) {
        this.mDatas = mData;
        this.mContext = mContext;
        this.onClickItem = onClickItem;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(mContext, R.layout.item_home_super, null));
    }

    @Override
    public List<HomeDataBean.NewMchListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivMchLogo.load(mDatas.get(position).getMch_logo());
        holder.tvStoreName.setText(mDatas.get(position).getMch_name());
        holder.tvGoodsCount.setText("共" + mDatas.get(position).getGoods_count() + "件商品");

        if (mDatas.get(position).getGoods_list() != null && mDatas.get(position).getGoods_list().size() > 0) {
            switch (mDatas.size()) {
                case 0:
                    holder.llStore1.setVisibility(View.INVISIBLE);
                    holder.llStore2.setVisibility(View.INVISIBLE);
                    holder.llStore3.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    holder.llStore1.setVisibility(View.VISIBLE);
                    holder.llStore2.setVisibility(View.INVISIBLE);
                    holder.llStore3.setVisibility(View.INVISIBLE);
                    holder.ivCoverPic0.load(mDatas.get(position).getGoods_list().get(0).getCover_pic());
                    holder.tvPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getPrice());
                    holder.tvOriginalPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getOriginal_price());
                    holder.tvOriginalPrice0.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    break;
                case 2:
                    holder.llStore1.setVisibility(View.VISIBLE);
                    holder.llStore2.setVisibility(View.VISIBLE);
                    holder.llStore3.setVisibility(View.INVISIBLE);
                    holder.ivCoverPic0.load(mDatas.get(position).getGoods_list().get(0).getCover_pic());
                    holder.tvPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getPrice());
                    holder.tvOriginalPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getOriginal_price());
                    holder.tvOriginalPrice0.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                    holder.ivCoverPic1.load(mDatas.get(position).getGoods_list().get(1).getCover_pic());
                    holder.tvPrice1.setText("￥" + mDatas.get(position).getGoods_list().get(1).getPrice());
                    holder.tvOriginalPrice1.setText("￥" + mDatas.get(position).getGoods_list().get(1).getOriginal_price());
                    holder.tvOriginalPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    break;
                case 3:
                    holder.llStore1.setVisibility(View.VISIBLE);
                    holder.llStore2.setVisibility(View.VISIBLE);
                    holder.llStore3.setVisibility(View.VISIBLE);
                    holder.ivCoverPic0.load(mDatas.get(position).getGoods_list().get(0).getCover_pic());
                    holder.tvPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getPrice());
                    holder.tvOriginalPrice0.setText("￥" + mDatas.get(position).getGoods_list().get(0).getOriginal_price());
                    holder.tvOriginalPrice0.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                    holder.ivCoverPic1.load(mDatas.get(position).getGoods_list().get(1).getCover_pic());
                    holder.tvPrice1.setText("￥" + mDatas.get(position).getGoods_list().get(1).getPrice());
                    holder.tvOriginalPrice1.setText("￥" + mDatas.get(position).getGoods_list().get(1).getOriginal_price());
                    holder.tvOriginalPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                    holder.ivCoverPic2.load(mDatas.get(position).getGoods_list().get(2).getCover_pic());
                    holder.tvPrice2.setText("￥" + mDatas.get(position).getGoods_list().get(2).getPrice());
                    holder.tvOriginalPrice2.setText("￥" + mDatas.get(position).getGoods_list().get(2).getOriginal_price());
                    holder.tvOriginalPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    break;
                default:
                    break;
            }
        }
        if (onClickItem != null) {
            holder.llSuper.setOnClickListener(v ->
                    onClickItem.llSuper(position));
        }
        if (onClickItem != null) {
            holder.llStore.setOnClickListener(v ->
                    onClickItem.llStore(position));
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
        void llSuper(int position);

        void llStore(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mch_logo)
        GlideImageView ivMchLogo;
        @BindView(R.id.tv_store_name)
        TextView tvStoreName;
        @BindView(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @BindView(R.id.iv_cover_pic0)
        GlideImageView ivCoverPic0;
        @BindView(R.id.tv_price0)
        TextView tvPrice0;
        @BindView(R.id.tv_original_price0)
        TextView tvOriginalPrice0;
        @BindView(R.id.ll_store1)
        LinearLayout llStore1;
        @BindView(R.id.iv_cover_pic1)
        GlideImageView ivCoverPic1;
        @BindView(R.id.tv_price1)
        TextView tvPrice1;
        @BindView(R.id.tv_original_price1)
        TextView tvOriginalPrice1;
        @BindView(R.id.ll_store2)
        LinearLayout llStore2;
        @BindView(R.id.iv_cover_pic2)
        GlideImageView ivCoverPic2;
        @BindView(R.id.tv_price2)
        TextView tvPrice2;
        @BindView(R.id.tv_original_price2)
        TextView tvOriginalPrice2;
        @BindView(R.id.ll_store3)
        LinearLayout llStore3;
        @BindView(R.id.ll_store)
        LinearLayout llStore;
        @BindView(R.id.rl_mch_logo)
        RelativeLayout rlMchLogo;
        @BindView(R.id.ll_super)
        LinearLayout llSuper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
