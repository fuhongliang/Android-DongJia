package cn.ifhu.dongjia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ifhu.dongjia.R;
import cn.ifhu.dongjia.base.BaseFragment;

/**
 * 我的页面
 */
public class MeFragment extends BaseFragment {
    Unbinder unbinder;

    public static BaseFragment newInstance() {
        return new MeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}
