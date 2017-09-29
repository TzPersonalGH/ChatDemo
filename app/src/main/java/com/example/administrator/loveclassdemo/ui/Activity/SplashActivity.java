package com.example.administrator.loveclassdemo.ui.Activity;

import android.os.Handler;

import com.example.administrator.loveclassdemo.Presenter.Impl.SplashPresenterImpl;
import com.example.administrator.loveclassdemo.Presenter.SplashPresenter;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.view.SplashView;

/**
 * Created by Tz on 2017/9/19.
 */

//此为MVP模式中的view
public class SplashActivity extends BaseActivity implements SplashView {
    private Handler mHandler = new Handler();
    private static final int DELAY = 2000;

    //持有一个Presenter层的应用，来调用P层的业务逻辑
    private SplashPresenter mSplashPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    protected void init(){
        super.init();
        //初始化Presenter
        mSplashPresenter = new SplashPresenterImpl(this);
        mSplashPresenter.checkLoginStatus();

    }

    /**
     * 没登陆，跳转到登录界面
     */
    private void navigateToLogin() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goTo(LoginActivity.class);
            }
        },DELAY);
    }

    /**
     * 已登录，跳转到主界面
     */
    private void navigateToMain() {
        goTo(MainActivity.class);
    }


    @Override
    public void onLoggedIn() {
        navigateToMain();
    }

    @Override
    public void onNotLogin() {
        navigateToLogin();
    }
}
