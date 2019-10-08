package cn.ifhu.dongjia.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    String color;
    int horizontalDividerHeight = 0;
    int verticalDividerHeight = 0;
    int space = 0;
    boolean isNeedRight = false;
    boolean isNeedLeft = false;
    boolean isNeedTop = false;
    boolean isNeedBottom = false;

    public GridDividerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        Paint paint = new Paint();
//        paint.setColor(Color.parseColor(color));
//        c.drawLine(0,0,10,space,paint);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childCount = parent.getChildCount();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int span = gridLayoutManager.getSpanCount();

        //为了Item大小均匀，将设定分割线平均分给左右两边Item各一半
        int offset = space / 2;
        for (int i = 0; i < childCount; i++) {
            //第一排，顶部不画
            if (i < span) {
                //最左边的，左边不画
                if (i % span == 0) {
                    outRect.set(0, 0, offset, 0);
                    //最右边，右边不画
                } else if (i % span == span - 1) {
                    outRect.set(offset, 0, 0, 0);
                } else {
                    outRect.set(offset, 0, offset, 0);
                }
            } else {
                //上下的分割线，就从第二排开始，每个区域的顶部直接添加设定大小，不用再均分了
                if (i % span == 0) {
                    outRect.set(0, space, offset, 0);
                } else if (i % span == span - 1) {
                    outRect.set(offset, space, 0, 0);
                } else {
                    outRect.set(offset, space, offset, 0);
                }
            }
        }

    }
}
