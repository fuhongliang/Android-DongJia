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
import cn.ifhu.dongjia.model.data.MchArticleDataBean;

public class MchArticleAdapter extends BaseLoadMoreAdapter<MchArticleDataBean, MchArticleAdapter.ViewHolder> {



    private List<MchArticleDataBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    public MchArticleAdapter(List<MchArticleDataBean> mDatas, Context context, OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<MchArticleDataBean> data) {
        mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_home_case, null));
    }

    @Override
    public List<MchArticleDataBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivAvatar.load(mDatas.get(position).getCover_pic());
        holder.tvContent.setText(mDatas.get(position).getTitle());
        if (onClickItem != null){
            holder.llCase.setOnClickListener(v ->
                    onClickItem.llCase(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnClickItem {
        void llCase(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        GlideImageView ivAvatar;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_case)
        LinearLayout llCase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
