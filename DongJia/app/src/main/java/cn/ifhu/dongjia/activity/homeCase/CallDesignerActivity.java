package cn.ifhu.dongjia.activity.homeCase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.TopicDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeCaseService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.X5WebView;

/**
 * 联系设计师详情
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
    @BindView(R.id.tv_store_title)
    TextView tvStoreTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.options1)
    LinearLayout options1;
    @BindView(R.id.rl_call_designer)
    RelativeLayout rlCallDesigner;
    @BindView(R.id.web_content)
    X5WebView webContent;
    String id;
    //创建id接收数据传输到联系设计师
    String topic_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_designer_btn);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        ButterKnife.bind(this);
        id = getIntent().getStringExtra("id");
        getTopicShow();
    }

    /**
     * 方案详情
     */
    public void getTopicShow() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeCaseService.class).Topic(4, -1, -1, id)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<TopicDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<TopicDataBean> t) throws Exception {
                ivAvatarBg.load(t.getData().getCover_pic());
                ivAvatar.load(t.getData().getAuthor_logo());
                tvName.setText(t.getData().getAuthor());
                tvStoreTitle.setText(t.getData().getTitle());
                tvTime.setText(t.getData().getAddtime());
                topic_id = t.getData().getId();
                //webView设置数据
                webContent.loadData(t.getData().getContent(),"text/html", "UTF-8");
                webContent.getSettings().setUseWideViewPort(true);
                webContent.getSettings().setLoadWithOverviewMode(true);
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_call_designer)
    public void onRlCallDesignerClicked() {
        Intent intent = new Intent(this, AddCallDesignerActivity.class);
        intent.putExtra("topic_id", topic_id);
        startActivity(intent);
    }
}
