package cn.ifhu.dongjia.activity.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import cn.ifhu.dongjia.adapter.SearchAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.LoadMoreScrollListener;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.SearchDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.HomeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.GridDividerItemDecoration;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.widget.FlowLayout;

/**
 * 搜索商品页面
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.options1)
    ImageView options1;
    @BindView(R.id.et_description)
    EditText etDescription;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.flow)
    FlowLayout flow;
    @BindView(R.id.options2)
    TextView options2;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rl_search)
    RecyclerView rlSearch;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;

    SearchAdapter searchAdapter;
    private List<SearchDataBean.ListBean> mDatas = new ArrayList<>();

    private List<String> list = new ArrayList<>();
    private FlowLayout flowLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        tvTitle.setText("搜索");
        searchAdapter = new SearchAdapter(mDatas, this);
        rlSearch.setNestedScrollingEnabled(false);
        rlSearch.setLayoutManager(new GridLayoutManager(this, 2));
        rlSearch.addItemDecoration(new GridDividerItemDecoration(12));
        rlSearch.setAdapter(searchAdapter);
        rlSearch.setOnScrollListener(new LoadMoreScrollListener(rlSearch));
        for (int i = 0; i < list.size(); i++) {
            list.add(etDescription.getText().toString());
        }
        
    }

    /**
     * 判空
     */
    public boolean checkEmpty() {
        if (StringUtils.isEmpty(etDescription.getText().toString())) {
            ToastHelper.makeText("请输入搜索内容").show();
            return false;
        }
        return true;
    }

    /**
     * 商品搜索接口
     */
    public void getSearchData() {
        setLoadingMessageIndicator(true);
        RetrofitAPIManager.create(HomeService.class).search(4, -1, -1, etDescription.getText().toString() + "", 1).
                compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<SearchDataBean>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity<SearchDataBean> t) throws Exception {
                mDatas = t.getData().getList();
                searchAdapter.setData(mDatas);
                flow.setVisibility(View.GONE);
                ll.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onIvBackClicked() {
        finish();
    }

    @OnClick(R.id.tv_search)
    public void onTvSearchClicked() {
        if (checkEmpty()) {
            getSearchData();
        }
    }

    @OnClick(R.id.iv_delete)
    public void onIvDeleteClicked() {
        etDescription.setText(" ");
    }
}
