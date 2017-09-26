package com.example.administrator.loveclassdemo.app;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BuildConfig;

/**
 * Created by Tz on 2017/9/26.
 */

public class LCApplication extends Application{

    /**
     *当应用进程启动时，会调用onCreate方法
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化一些全局变量
        //初始化第三方的SDK
        //getApplicationContext获取application的上下文（context）
        //它的生命周期要比activity的上下文要长
        Bmob.initialize(getApplicationContext(),"2aee7a758b4a55b85ceded9351a11c17");

        initEaseMob();

    }

    private void initEaseMob() {
        //创建环信配置
        EMOptions options = new EMOptions();
        //是否默认接受好友请求
        options.setAcceptInvitationAlways(true);

        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);

        if (BuildConfig.DEBUG) {
            EMClient.getInstance().setDebugMode(true);
        }



    }
}
