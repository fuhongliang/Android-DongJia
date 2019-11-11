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
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeCaseService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.ToastHelper;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_designer);
        ButterKnife.bind(this);
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
    public void getCreateUserMessage(){
        setLoadingMessageIndicator(true);
        CreateUserMessageGetBean createUserMessageGetBean = new CreateUserMessageGetBean();
        RetrofitAPIManager.create(HomeCaseService.class).CreateUserMessage(createUserMessageGetBean.getPostParam())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver(true) {
            @Override
            protected void onApiComplete() {

            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {

            }

            @Override
            public void onNext(Object o) {

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

        }
    }
}
