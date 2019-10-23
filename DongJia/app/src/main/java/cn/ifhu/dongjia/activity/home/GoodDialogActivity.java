package cn.ifhu.dongjia.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.model.data.GoodsAttrInfoDataBean;

public class GoodDialogActivity extends Dialog {


    private Context context;
    private GoodsAttrInfoDataBean attrList;
    public GoodDialogActivity(@NonNull Context context) {
        super(context, R.style.CommonBottomDialogStyle);
    }

    public GoodDialogActivity(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }
}
