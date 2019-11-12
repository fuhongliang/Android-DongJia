package cn.ifhu.dongjia.activity.homeCase;

import android.content.Intent;
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
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.TopicDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeCaseService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;

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
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_call_designer)
    RelativeLayout rlCallDesigner;

    String id;
    //创建id接收数据传输到联系设计师
    String topic_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_designer_btn);
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
                tvContent.setText(t.getData().getContent());
                topic_id = t.getData().getId();
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_call_designer)
    public void onRlCallDesignerClicked() {
        Intent intent = new Intent(this,AddCallDesignerActivity.class);
        intent.putExtra("topic_id",topic_id);
        startActivity(intent);
    }
}
