package cn.ifhu.dongjia.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.model.data.CatListDataBean;

public class CategoryAdapter extends BaseAdapter {

    public List<CatListDataBean.ListBeanX> mDatas;
    public Context context;
    public int mCurPosition = 0;
    ItemOnclick itemOnclick;

    public CategoryAdapter(List<CatListDataBean.ListBeanX> mDatas, Context context, ItemOnclick itemOnclick) {
        this.mDatas = mDatas;
        this.context = context;
        this.itemOnclick = itemOnclick;
    }

    public void setData(List<CatListDataBean.ListBeanX> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_good_left, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.iv_line);
            viewHolder.textView = convertView.findViewById(R.id.tv_categroy);
            viewHolder.linearLayout = convertView.findViewById(R.id.ll_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mCurPosition == position) {
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.black));
            viewHolder.linearLayout.setBackgroundColor(Color.WHITE);
        } else {
            viewHolder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.category_color));
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.navigation_color));
        }

        viewHolder.textView.setText(mDatas.get(position).getName());
        viewHolder.linearLayout.setOnClickListener(v -> {
            mCurPosition = position;
            itemOnclick.onClickItem(position);
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
    }

    public interface ItemOnclick {
        /**
         * 点击事件
         *
         * @param position 位置
         */
        void onClickItem(int position);
    }


}
