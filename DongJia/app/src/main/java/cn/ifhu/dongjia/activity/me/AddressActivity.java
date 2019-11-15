package cn.ifhu.dongjia.activity.me;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.AddressAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.AddressBean;
import cn.ifhu.dongjia.model.data.AddressListDataBean;
import cn.ifhu.dongjia.model.data.SubmitPreviewDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.MeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.DialogUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;
import cn.ifhu.dongjia.view.dialog.nicedialog.ConfirmDialog;

/**
 * 收货地址页面
 */
public class AddressActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.rl_add_address)
    RelativeLayout rlAddAddress;



    AddressAdapter addressAdapter;
    private List<AddressBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        tvTitle.setText("我的收货地址");
        if (UserLogic.getUser() != null) {
            getAddressListData();
        }
        addressAdapter = new AddressAdapter(mData, this, new AddressAdapter.OnClickItem() {
            @Override
            public void Delete(int position) {
                DialogUtils.showConfirmDialog("温馨提示", "是否删除该地址", getSupportFragmentManager(), new ConfirmDialog.ButtonOnclick() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void ok() {
                        RetrofitAPIManager.create(MeService.class).addressDelete(Integer.valueOf(mData.get(position).getId()), 4, -1, -1, UserLogic.getUser().getAccess_token())
                                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
                            @Override
                            protected void onApiComplete() {

                            }

                            @Override
                            protected void onSuccees(BaseEntity t) throws Exception {
                                ToastHelper.makeText(t.getMessage()).show();
                                getAddressListData();
                            }
                        });
                    }
                });
            }

            @Override
            public void Edit(int position) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                AddressBean listBean = mData.get(position);
                intent.putExtra("bean",listBean);
                startActivity(intent);

            }

            @Override
            public void RlAddress(int position) {
                Intent intent = new Intent();
                intent.putExtra("addressBean",mData.get(position));
                setResult(RESULT_OK,intent);
                finish();
            }

        });
        rvAddress.setNestedScrollingEnabled(false);
        rvAddress.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvAddress.setAdapter(addressAdapter);
        rvAddress.setOnScrollListener(new LoadMoreScrollListener(rvAddress));

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressListData();
    }


    /**
     * 地址列表接口
     */
    public void getAddressListData() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(MeService.class).addressList(4, -1, -1, UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<AddressListDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<AddressListDataBean> t) throws Exception {
                mData = t.getData().getList();
                addressAdapter.setData(mData);
            }

        });
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.rl_add_address)
    public void onRlOkClicked() {
        goToActivity(AddAddressActivity.class);
    }
}
