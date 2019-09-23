package cn.ifhu.dongjia.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.ifhu.dongjia.utils.Utils;
import cn.ifhu.dongjia.view.dialog.loading.LoadingDialog;

import static cn.ifhu.dongjia.utils.Constants.DATA;


/**
 * @author tony
 */
@Keep
public abstract class BaseFragment extends Fragment {

    /**
     * 跳转到页面 cls
     * @param cls 目标页面
     */
    public void goToActivity(Class<?> cls){
        Intent intent = new Intent(getHoldingActivity(),cls);
        startActivity(intent);
    }


    /**
     * 携带数据跳转
     * @param cls 目标页面
     * @param data 携带的数据，DATA
     */
    public void goToActivity(Class<?> cls,String data){
        Intent intent = new Intent(getHoldingActivity(),cls);
        intent.putExtra(DATA,data);
        startActivity(intent);
    }

    /**
     * 携带数据跳转
     * @param cls 目标页面
     * @param data 携带的数据，DATA
     */
    public void goToActivity(Class<?> cls,int data){
        Intent intent = new Intent(getHoldingActivity(),cls);
        intent.putExtra(DATA,data);
        startActivity(intent);
    }

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 获取宿主Activity
     *
     * @return BaseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment, frameId);

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment, frameId);
    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }


    /**
     * 移除Fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }

    /**
     * 是否显示加载提示
     * @param active 是否激活
     */
    public void setLoadingMessageIndicator(boolean active) {
        if (active) {
            LoadingDialog.progressShow(getHoldingActivity());
        } else {
            LoadingDialog.progressCancel();
        }
    }

}
