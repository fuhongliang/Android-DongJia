package cn.ifhu.dongjia.base;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseSpecigication<E, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<E> mItems;
    protected LayoutInflater mInflater;

    protected boolean mDebug = false;
    public BaseSpecigication(Context context, List<E> list) {
        mContext = context;
        mItems = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        } else {
            return 0;
        }
    }

    public E getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 刷新列表
     * refreshList
     * @param list
     * @since 1.0
     */
    public void refreshList(List<E> list) {
        mItems = list;
        notifyDataSetChanged();
    }

    public void refreshList(List<E> list, int size) {
        if (size == 0) {
            mItems = list;
            notifyDataSetChanged();
        } else {
            int oldSize = getItemCount();
            mItems = list;
//            notifyItemRangeInserted(oldSize, list.size());
//            notifyItemChanged(0, oldSize);
            notifyDataSetChanged();
//            notifyItemRangeChanged(oldSize - 5, oldSize);
        }
    }

    /**
     * 删除一条数据
     * remove
     * @param e
     * @since 1.0
     */
    public boolean remove(E e) {
        int pos = mItems.indexOf(e);
        if (pos >= 0) {
            mItems.remove(e);
            notifyItemRemoved(pos);
            return true;
        }
        return false;
    }

    /**
     * 添加一条数据
     * add
     * @param e
     * @since 1.0
     */
    public void add(E e) {
        int size = mItems.size();
        mItems.add(e);
        notifyItemInserted(size );
    }

    public void addAll(List<E> list) {
        int size = mItems.size();
        mItems.addAll(list);
        notifyItemRangeInserted(size, list.size());
    }
}