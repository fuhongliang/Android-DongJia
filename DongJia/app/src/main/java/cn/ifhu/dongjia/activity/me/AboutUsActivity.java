package cn.ifhu.dongjia.activity.me;

import android.os.Bundle;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;

/**
 * 关于我们页面
 */
public class AboutUsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
    }
}
