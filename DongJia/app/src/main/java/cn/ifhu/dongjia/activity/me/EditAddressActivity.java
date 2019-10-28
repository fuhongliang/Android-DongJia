package cn.ifhu.dongjia.activity.me;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.model.BaseEntity;
import cn.ifhu.dongjia.model.data.DistrictDataBean;
import cn.ifhu.dongjia.model.post.AddressSaveDataBean;
import cn.ifhu.dongjia.net.BaseObserver;
import cn.ifhu.dongjia.net.MeService;
import cn.ifhu.dongjia.net.RetrofitAPIManager;
import cn.ifhu.dongjia.net.SchedulerUtils;
import cn.ifhu.dongjia.utils.Constants;
import cn.ifhu.dongjia.utils.IrReference;
import cn.ifhu.dongjia.utils.StringUtils;
import cn.ifhu.dongjia.utils.ToastHelper;
import cn.ifhu.dongjia.utils.UserLogic;

/**
 * 编辑地址
 */
public class EditAddressActivity extends BaseActivity {
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
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_house_number)
    EditText etHouseNumber;
    @BindView(R.id.swh_auto_print)
    Switch swhAutoPrint;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    /**
     * 设置地址默认为0
     * 0为否
     * 1为是
     */
    int is_default;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private boolean isLoaded = false;
    private List<DistrictDataBean> province;
    //省市区id
    private int province_id;
    private int city_id;
    private int area_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        tvTitle.setText("编辑地址");
        getDistrict();
        settingSwitch();
    }
    /**
     * 是否选择默认地址
     */
    public void settingSwitch() {
        swhAutoPrint.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                IrReference.getInstance().setBoolean(Constants.Shake, true);
                is_default = 1;
            } else {
                IrReference.getInstance().setBoolean(Constants.Shake, false);
                is_default = 0;
            }
        });

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
        addressSaveDataBean.setAddress_id(getDATA());
        addressSaveDataBean.setAccess_token(UserLogic.getUser().getAccess_token());
        addressSaveDataBean.setName(etName.getText().toString());
        addressSaveDataBean.setMobile(etPhone.getText().toString());
        addressSaveDataBean.setDetail(etHouseNumber.getText().toString());
        addressSaveDataBean.setProvince_id(province_id);
        addressSaveDataBean.setCity_id(city_id);
        addressSaveDataBean.setDistrict_id(area_id);
        addressSaveDataBean.setIs_default(is_default+"");

        RetrofitAPIManager.create(MeService.class).addressSave(addressSaveDataBean)
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<Object>(true) {
            @Override
            protected void onApiComplete() {
                setLoadingMessageIndicator(false);
            }

            @Override
            protected void onSuccees(BaseEntity t) throws Exception {
                ToastHelper.makeText(t.getMessage()).show();
                finish();
            }
        });
    }
    /**
     * 地区列表接口
     */
    public void getDistrict() {
        RetrofitAPIManager.create(MeService.class).district(4, -1, -1, UserLogic.getUser().getAccess_token())
                .compose(SchedulerUtils.ioMainScheduler()).subscribe(new BaseObserver<List<DistrictDataBean>>(true) {
            @Override
            protected void onApiComplete() {

            }

            @Override
            protected void onSuccees(BaseEntity<List<DistrictDataBean>> t) throws Exception {
                province = t.getData();
                initCityData();
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
        if (isLoaded) {
        } else {
            showPickerView();
        }

    }

    public void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1) : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options3Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                tvAddress.setText(tx);
                province_id = province.get(options1).getId();
                city_id = province.get(options1).getList().get(options2).getId();
                area_id = province.get(options1).getList().get(options2).getList().get(options3).getId();
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

         /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public void initCityData() {

        ArrayList<String> provinceList = new ArrayList<>();//该省列表
        for (DistrictDataBean districtDataBean : province) {//遍历该省
            provinceList.add(districtDataBean.getName());
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (DistrictDataBean.ListBeanX city : districtDataBean.getList()) {//遍历该城市
                String cityName = city.getName();
                cityList.add(cityName);
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                for (DistrictDataBean.ListBeanX.ListBean area : city.getList()) {//遍历该地区
                    city_AreaList.add(area.getName());
                }

                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
        /**
         * 添加省份数据
         */
        options1Items = provinceList;
    }
}
