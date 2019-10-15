package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseLoadMoreAdapter;
import cn.ifhu.dongjia.model.data.GoodDetailsDataBean;

/**
 * 购物车适配器
 */
public class ShoppingCarAdapter extends BaseLoadMoreAdapter<GoodDetailsDataBean.AttrGroupListBean, ShoppingCarAdapter.ViewHolder> {
    private Context context;
    private RelativeLayout rlSelect;
    private boolean isSelectAll = false;
    private List<GoodDetailsDataBean.AttrGroupListBean> mDatas;


    public ShoppingCarAdapter(Context context, RelativeLayout rlSelect) {
        this.context = context;
        this.rlSelect = rlSelect;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_shop_cart_child,null));
    }

    @Override
    public List getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ShoppingCarAdapter.ViewHolder holder, int position) {

    }

    @Override
    public void setData(List data) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
