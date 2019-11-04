package cn.ifhu.dongjia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;

public class ConfirmOrderAdapter extends BaseLoadMoreAdapter<SubmitPreviewDataBean.MchListBean, ConfirmOrderAdapter.ViewHolder> {


    private List<SubmitPreviewDataBean.MchListBean> mDatas;
    private Context context;

    public ConfirmOrderAdapter(List<SubmitPreviewDataBean.MchListBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public void setData(List<SubmitPreviewDataBean.MchListBean> data) {
        this.mDatas = data;
        resetLodingMore();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return new ViewHolder(View.inflate(context, R.layout.item_mch, null));
    }

    @Override
    public List<SubmitPreviewDataBean.MchListBean> getDataList() {
        return mDatas;
    }

    @Override
    public void bindOtherViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == mDatas.size()) return;
        holder.ivMchLogo.load(mDatas.get(position).getLogo());
        holder.tvMchName.setText(mDatas.get(position).getName());

        for (int i = 0; i < mDatas.get(position).getList().size(); i++) {
            SubmitPreviewDataBean.MchListBean.ListBean list = mDatas.get(position).getList().get(i);
            StringBuilder attr = new StringBuilder();
            for (int j = 0; j < mDatas.get(position).getList().get(i).getAttr_list().size(); j++) {
                SubmitPreviewDataBean.MchListBean.ListBean.AttrListBean attrListBean = mDatas.get(position).getList().get(i).getAttr_list().get(j);
                attr.append(attrListBean.getAttr_group_name()).append(":").append(attrListBean.getAttr_name()).append(" ");
            }

            View view = gernerateGoodsInfoView(list.getGoods_pic(), list.getGoods_name(), attr.toString(), list.getPrice(), list.getNum() + "");

            holder.llStore.addView(view);
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //代码中动态获取xml文件
    public View gernerateGoodsInfoView(String goodsPicture, String goodsName, String attr, String price, String number) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_store, null, false);
        ((GlideImageView) view.findViewById(R.id.iv_store_pic)).load(goodsPicture);
        ((TextView) view.findViewById(R.id.tv_store_name)).setText(goodsName);
        ((TextView) view.findViewById(R.id.tv_specification)).setText(attr);
        ((TextView) view.findViewById(R.id.tv_store_price)).setText("￥"+price);
        ((TextView) view.findViewById(R.id.tv_store_number)).setText("x"+number);
        return view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mch_logo)
        GlideImageView ivMchLogo;
        @BindView(R.id.tv_mch_name)
        TextView tvMchName;
        @BindView(R.id.ll_logo)
        LinearLayout llLogo;
        @BindView(R.id.ll_store)
        LinearLayout llStore;
        @BindView(R.id.et_description)
        EditText etDescription;
        @BindView(R.id.ll_mch)
        LinearLayout llMch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
