package cn.ifhu.dongjia.activity.me;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.DistrictDataBean;
import cn.ifhu.dongjia.model.post.AddressSaveDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.MeServive;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 新增地址页面
 */
public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.swh_auto_print)
    Switch swhAutoPrint;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_house_number)
    EditText etHouseNumber;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    /**
     * 设置地址默认为0
     * 0为否
     * 1为是
     */
    int is_default = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        tvTitle.setText("新增地址");
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
        if (StringUtils.isEmpty(tvAddress.getText().toString())) {
            ToastHelper.makeText("请选择地址").show();
            return false;
        }
        if (StringUtils.isEmpty(etHouseNumber.getText().toString())) {
            ToastHelper.makeText("请输入详情地址").show();
            return false;
        }
        return true;
    }

    /**
     * 新增地址接口
     */
    public void getAddress() {
        setLoadingMessageIndicator(true);
        AddressSaveDataBean addressSaveDataBean = new AddressSaveDataBean();

        addressSaveDataBean.setAccess_token(UserLogic.getUser().getAccess_token());
        addressSaveDataBean.setIs_default(is_default + "");
        addressSaveDataBean.setName(etName.getText().toString());
        addressSaveDataBean.setMobile(etPhone.getText().toString());
        addressSaveDataBean.setDetail(etHouseNumber.getText().toString());

        RetrofitAPIManager.create(MeServive.class).addressSave(addressSaveDataBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
            }
        });
    }

    public void getDistrict() {
        RetrofitAPIManager.create(MeServive.class).district(4,-1,-1,UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<DistrictDataBean>(true) {
            @Override
            protected void onApiComplete() {

            }

            @Override
            protected void onSuccees(BaseEntity<DistrictDataBean> t) throws Exception {
                for (int i = 0; i < t.getData().getList().size(); i++) {

                }
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
            getAddress();
        }
    }

    @OnClick(R.id.tv_right_text)
    public void onTvRightTextClicked() {
    }

    @OnClick(R.id.tv_address)
    public void onTvAddressClicked() {

    }
}
