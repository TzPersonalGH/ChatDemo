package com.example.administrator.loveclassdemo.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Tz on 2017/9/26.
 */

public class QQApplication extends Application{

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

    }
}
