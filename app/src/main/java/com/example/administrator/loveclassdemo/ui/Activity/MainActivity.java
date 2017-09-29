package com.example.administrator.loveclassdemo.ui.Activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompatBase;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.factory.FragmentFactory;
import com.example.administrator.loveclassdemo.ui.Activity.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
    }

    private OnTabSelectListener mOnTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            //开始事务
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, FragmentFactory.getInstance().getFragment(tabId));
            //提交事务
            fragmentTransaction.commit();
        }
    };

}
