package cn.ifhu.dongjia.activity.homeCase;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sunfusheng.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;

/**
 * 增加联系设计师
 */
public class CallDesignerActivity extends BaseActivity {
    @BindView(R.id.iv_avatar_bg)
    GlideImageView ivAvatarBg;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_avatar)
    GlideImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_store_time)
    TextView tvStoreTime;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.options1)
    LinearLayout options1;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_call_designer)
    RelativeLayout rlCallDesigner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_designer_btn);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_call_designer)
    public void onRlCallDesignerClicked() {
        goToActivity(AddCallDesignerActivity.class);
    }
}
