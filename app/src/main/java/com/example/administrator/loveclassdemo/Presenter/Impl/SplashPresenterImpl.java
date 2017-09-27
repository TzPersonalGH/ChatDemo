package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.SplashPresenter;
import com.example.administrator.loveclassdemo.view.SplashView;
import com.hyphenate.chat.EMClient;

/**
 * Created by Tz on 2017/9/25.
 */

public class SplashPresenterImpl implements SplashPresenter {
    //持有一个View层的引用，通知View层结果
    private SplashView mSplashView;

    public SplashPresenterImpl(SplashView view) {
        mSplashView = view;
    }


    /**
     * 检查登录状态，通知View层是否已经登录
     */
    @Override

    public void checkLoginStatus() {
        if (isLoggedIn()) {
            //通知View层，已经登录
            mSplashView.onLoggedIn();
        } else {
            //通知View层，没有登录了
            mSplashView.onNotLogin();
        }
    }

    /**
     * 判断是否登录
     * @return
     */
    public boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore() && EMClient.getInstance().isConnected();
    }
}
