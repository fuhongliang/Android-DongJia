package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.DistrivtListDataBean;

public class SelectCityAdapter extends BaseLoadMoreAdapter<DistrivtListDataBean, SelectCityAdapter.ViewHolder> {


    private List<DistrivtListDataBean> mDatas;
    private Context context;
    private OnClickItem onClickItem;

    public SelectCityAdapter(List<DistrivtListDataBean> mDatas, Context context,OnClickItem onClickItem) {
        this.mDatas = mDatas;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @Override
    public void setData(List<DistrivtListDataBean> data) {
        this.mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_city, null));
    }

    @Override
    public List<DistrivtListDataBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.tvCityName.setText(mDatas.get(position).getName());
        if (onClickItem != null){
            holder.tvCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.CityName(position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public interface OnClickItem {
        void CityName(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name)
        TextView tvCityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
