package cn.ifhu.dongjia.activity.homeCase;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.get.CreateUserMessageGetBean;
import cn.ifhu.dongjia.model.post.CreateUserMessagePostBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeCaseService;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 增加联系设计师
 */
public class AddCallDesignerActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    String topic_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_designer);
        ButterKnife.bind(this);
//        topic_id = getIntent().getStringExtra("topic_id");
        tvTitle.setText("联系设计师");
    }

    /**
     * 判空
     */
    public boolean checkEmpty() {
        if (StringUtils.isEmpty(etName.getText().toString())) {
            ToastHelper.makeText("请输入收货人姓名").show();
            return false;
        }
        if (StringUtils.isEmpty(etPhone.getText().toString())) {
            ToastHelper.makeText("请输入手机号码").show();
            return false;
        }
        if (StringUtils.isEmpty(etContent.getText().toString())) {
            ToastHelper.makeText("请输入留言内容").show();
            return false;
        }
        return true;
    }

    /**
     * 联系设计师
     */
    public void getCreateUserMessage() {
        CreateUserMessagePostBean createUserMessagePostBean = new CreateUserMessagePostBean();
        createUserMessagePostBean.setAccess_token(UserLogic.getUser().getAccess_token());
        createUserMessagePostBean.setContact(etName.getText().toString());
        createUserMessagePostBean.setMessage(etContent.getText().toString());
        createUserMessagePostBean.setTelephone(etPhone.getText().toString());
        createUserMessagePostBean.setTopic_id(topic_id = getIntent().getStringExtra("topic_id"));
        RetrofitAPIManager.create(HomeCaseService.class).CreateUserMessagePost(createUserMessagePostBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
            }

            @Override
            protected void onSuccees(BaseEntity<Object> t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.tv_ok)
    public void onTvOkClicked() {
        if (checkEmpty()) {
            getCreateUserMessage();
        }
    }
}
