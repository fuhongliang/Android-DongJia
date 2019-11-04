package cn.ifhu.dongjia.adapter;

import android.content.Context;
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
import cn.ifhu.dongjia.model.data.CatListDataBean;

public class ProductAdapter extends BaseLoadMoreAdapter<CatListDataBean.ListBeanX.ListBean, ProductAdapter.ViewHolder> {


    private List<CatListDataBean.ListBeanX.ListBean> mDatas;
    private Context context;

    public ProductAdapter(List<CatListDataBean.ListBeanX.ListBean> mDatas, Context context) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public void setData(List<CatListDataBean.ListBeanX.ListBean> data) {
        this.mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_good_right, null));
    }

    @Override
    public List<CatListDataBean.ListBeanX.ListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivGoodPic.load(mDatas.get(position).getPic_url());
        holder.tvName.setText(mDatas.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_good_pic)
        GlideImageView ivGoodPic;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
