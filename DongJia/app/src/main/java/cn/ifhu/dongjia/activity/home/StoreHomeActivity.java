package cn.ifhu.dongjia.activity.home;

import android.os.Bundle;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;

public class StoreHomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        ButterKnife.bind(this);
    }
}
