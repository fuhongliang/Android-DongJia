package cn.ifhu.dongjia;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.ifhu.dongjia.activity.LoginActivity;
import cn.ifhu.dongjia.adapter.FragmentAdapter;
import cn.ifhu.dongjia.base.BaseActivity;
import cn.ifhu.dongjia.base.BaseFragment;
import cn.ifhu.dongjia.base.ViewManager;
import cn.ifhu.dongjia.fragments.HomeCaseFragment;
import cn.ifhu.dongjia.fragments.HomeFragment;
import cn.ifhu.dongjia.fragments.MeFragment;
import cn.ifhu.dongjia.fragments.ShopCartFragment;
import cn.ifhu.dongjia.model.MessageEvent;
import cn.ifhu.dongjia.utils.MchInfoLogic;

import static cn.ifhu.dongjia.utils.Constants.LOGOUT;
import static cn.ifhu.dongjia.utils.Constants.UNNORMALORDER;
import static cn.ifhu.dongjia.utils.Constants.UNSHIPPINGORDER;
import static cn.ifhu.dongjia.utils.Constants.UNPAYORDER;

public class MainActivity extends BaseActivity {
    private ViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        resetToDefaultIcon();
        return setCurrentItemIcon(item);
    };

    public boolean setCurrentItemIcon(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.navigation_home) {
            item.setIcon(R.drawable.bar_ic_sy_mian);
            mPager.setCurrentItem(0, false);
            return true;
        } else if (i == R.id.navigation_case) {
            item.setIcon(R.drawable.bar_ic_jz_mian);
            mPager.setCurrentItem(1, false);
            return true;
        } else if (i == R.id.navigation_cart) {
            item.setIcon(R.drawable.bar_ic_gwc_mian);
            mPager.setCurrentItem(2, false);
//            ((GoodsFragment) mFragments.get(2)).getCatsData();
            return true;
        } else if (i == R.id.navigation_me) {
            item.setIcon(R.drawable.bar_ic_wd_mian);
            mPager.setCurrentItem(3, false);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        navigation.setItemIconTintList(null);
        navigation.setSelectedItemId(R.id.navigation_home);
        Resources resource = getBaseContext().getResources();
        ColorStateList csl = resource.getColorStateList(R.color.bottom_navigation_color);
        navigation.setItemTextColor(csl);
        //注册监听蓝牙
        registerReceiver(mReceiver, makeFilter());

//        if (!MchInfoLogic.getMchTel().equals("") ) {
//            Log.e("JIGUANG-JPush", "设置别名" + MchInfoLogic.getMchTel());
//            JPushInterface.setAlias(this, SEQUENCE, MchInfoLogic.getMchTel());
//        }

    }

    private void resetToDefaultIcon() {
        MenuItem home = navigation.getMenu().findItem(R.id.navigation_home);
        MenuItem orders = navigation.getMenu().findItem(R.id.navigation_case);
        MenuItem goods = navigation.getMenu().findItem(R.id.navigation_cart);
        MenuItem settings = navigation.getMenu().findItem(R.id.navigation_me);

        home.setIcon(R.drawable.bar_ic_sy_xian);
        orders.setIcon(R.drawable.bar_ic_jz_xian);
        goods.setIcon(R.drawable.bar_ic_gwc_xian);
        settings.setIcon(R.drawable.bar_ic_wd_xian);
    }
    private void initViewPager() {
        ViewManager.getInstance().addFragment(0, HomeFragment.newInstance());
        ViewManager.getInstance().addFragment(1, HomeCaseFragment.newInstance());
        ViewManager.getInstance().addFragment(2, ShopCartFragment.newInstance());
        ViewManager.getInstance().addFragment(3, MeFragment.newInstance());
        mFragments = ViewManager.getInstance().getAllFragment();
        mPager = findViewById(R.id.container_pager);
        mPager.setOffscreenPageLimit(4);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                resetToDefaultIcon();
                setCurrentItemIcon(navigation.getMenu().getItem(i));
                navigation.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void logout() {
        MchInfoLogic.loginOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mReceiver);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        switch (messageEvent.getMessage()) {
            case LOGOUT:
                logout();
            case UNSHIPPINGORDER:
            case UNNORMALORDER:
            case UNPAYORDER:
                resetToDefaultIcon();
                setCurrentItemIcon(navigation.getMenu().getItem(1));
                navigation.getMenu().getItem(1).setChecked(true);
                break;
            default:
        }
    }


    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Log.e("TAG", "TURNING_ON");
                            break;
                        case BluetoothAdapter.STATE_ON:
//                            AudioUtil.getInstance(MainActivity.this).openRawMusicS(MainActivity.this,false,R.raw.ring);
                            Log.e("TAG", "STATE_ON");
                            break;
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Log.e("TAG", "STATE_TURNING_OFF");
                            break;
                        case BluetoothAdapter.STATE_OFF:
//                            AudioUtil.getInstance(MainActivity.this).openRawMusicS(MainActivity.this,false,R.raw.ring);
                            Log.e("TAG", "STATE_OFF");
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
