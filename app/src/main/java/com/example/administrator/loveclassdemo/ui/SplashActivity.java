package com.example.administrator.loveclassdemo.ui;

import android.content.Intent;
import android.os.Handler;

import com.example.administrator.loveclassdemo.MainActivity;
import com.example.administrator.loveclassdemo.R;

/**
 * Created by Tz on 2017/9/19.
 */

public class SplashActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private static final int DELAY = 2000;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    protected void init(){
        super.init();
        //检查登录状态
        if (checkLoginStatus()) {
            //已经登录则跳转到主界面
            navigateToMain();
        } else {
            //没有登录则延迟两秒，跳转到登录界面
            navigateToLogin();
        }


    }

    /**
     * 没登陆，跳转到登录界面
     */
    private void navigateToLogin() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },DELAY);
    }

    private boolean checkLoginStatus() {
        return false;
    }

    /**
     * 已登录，跳转到主界面
     */
    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
