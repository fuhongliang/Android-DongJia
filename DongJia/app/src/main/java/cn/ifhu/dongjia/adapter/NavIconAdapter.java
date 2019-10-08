package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
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

public class NavIconAdapter extends BaseLoadMoreAdapter<HomeDataBean.NavIconListBean, NavIconAdapter.ViewHolder> {

    private List<HomeDataBean.NavIconListBean> mDatas;
    private Context mContext;

    public NavIconAdapter(List<HomeDataBean.NavIconListBean> mData, Context mContext) {
        this.mContext = mContext;
        this.mDatas = mData;
    }
    @Override
    public void setData(List<HomeDataBean.NavIconListBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(mContext, R.layout.item_home_classification, null));
    }

    @Override
    public List<HomeDataBean.NavIconListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;

        holder.ivNavPic.load(mDatas.get(position).getPic_url());
        holder.tvNavName.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mDatas == null){
            return 0;
        }
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_nav_pic)
        GlideImageView ivNavPic;
        @BindView(R.id.tv_nav_name)
        TextView tvNavName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
