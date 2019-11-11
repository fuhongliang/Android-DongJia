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
import cn.ifhu.dongjia.model.data.TopicListDataBean;

public class HomeCaseAdpter extends BaseLoadMoreAdapter<TopicListDataBean.ListBean, HomeCaseAdpter.ViewHolder> {


    private List<TopicListDataBean.ListBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    public HomeCaseAdpter(List<TopicListDataBean.ListBean> mDatas, Context context, OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<TopicListDataBean.ListBean> data) {
        this.mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_improvement_plan, null));
    }

    @Override
    public List<TopicListDataBean.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivPri.load(mDatas.get(position).getCover_pic());
        holder.tvDescription.setText(mDatas.get(position).getTitle());
        holder.ivAvatar.load(mDatas.get(position).getAuthor_logo());
        holder.tvName.setText(mDatas.get(position).getAddtime());
        if (onClickItem != null) {
            holder.llHomeCase.setOnClickListener(v -> onClickItem.llHomeCase(position));
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnClickItem {
        void llHomeCase(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pri)
        GlideImageView ivPri;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.iv_avatar)
        GlideImageView ivAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_home_case)
        LinearLayout llHomeCase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
