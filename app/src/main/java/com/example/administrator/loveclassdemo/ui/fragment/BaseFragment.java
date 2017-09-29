package com.example.administrator.loveclassdemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Tz on 2017/9/19.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutResId(), null);
        ButterKnife.bind(this, root);
        init();
        return root;
    }

    /**
     * 初始化方法，初始化功能，子类也可以覆写，实现自己的初始化
     */
    protected void init() {

    }

    /**
     *子类必须实现这个方法，返回Activity的布局
     * @return
     */
    public abstract int getLayoutResId();


}
