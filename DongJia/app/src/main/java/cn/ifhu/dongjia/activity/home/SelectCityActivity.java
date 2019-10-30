package cn.ifhu.dongjia.activity.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.adapter.SelectCityAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.DistrivtListDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;

/**
 * 选择城市页面
 */
public class SelectCityActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.rl_city)
    RecyclerView rlCity;

    SelectCityAdapter selectCityAdapter;
    private List<DistrivtListDataBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_select_city);
        ButterKnife.bind(this);
        tvTitle.setText("选择城市");
        getSelectCityData();
        selectCityAdapter = new SelectCityAdapter(mDatas, this, new SelectCityAdapter.OnClickItem() {
            @Override
            public void CityName(int position) {
                tvCityName.setText(mDatas.get(position).getName());
            }
        });
        rlCity.setNestedScrollingEnabled(false);
        rlCity.setLayoutManager(new GridLayoutManager(this, 4));
        rlCity.addItemDecoration(new GridDividerItemDecoration(12));
        rlCity.setAdapter(selectCityAdapter);
        rlCity.setOnScrollListener(new LoadMoreScrollListener(rlCity));
    }

    /**
     * 选择城市接口
     */
    public void getSelectCityData() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).districtList(4, -1, -1)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<List<DistrivtListDataBean>>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<List<DistrivtListDataBean>> t) throws Exception {
                mDatas = t.getData();
                selectCityAdapter.setData(mDatas);
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

}
