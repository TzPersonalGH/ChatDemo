package com.example.administrator.loveclassdemo.Presenter.Impl;

import android.util.Log;

import com.example.administrator.loveclassdemo.Presenter.RegisterPresenter;
import com.example.administrator.loveclassdemo.utils.StringUtils;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.RegisterView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Tz on 2017/9/26.
 */

public class RegisterPresenterImpl implements RegisterPresenter {
    private static final String TAG ="RegisterPresenterImpl" ;

    private RegisterView mRegisterView;

    public RegisterPresenterImpl(RegisterView view) {
        mRegisterView = view;
    }
    @Override
    public void register(String userName, String password, String confirmPassword) {
        //注册前判断用户名和密码是否合法
        if (StringUtils.isValidUserName(userName)) {
            //用户名合法，则继续判断密码
            if (StringUtils.isValidPassword(password)) {
                //密码合法则继续判断密码是否一致
                if (password.equals(confirmPassword)) {
                    //密码一致，则注册
                    //通知View开始注册
                    mRegisterView.onStartRegister();
                    registerBmob(userName, password);
                } else {
                    //确认密码出错，通知View层
                    mRegisterView.onConfirmPasswordError();
                }

            } else {
                //密码不合法，则通知View层
                mRegisterView.onPasswordError();
            }

        } else {
            //用户名不合法，则通知View层
            mRegisterView.onUserNameError();
        }
    }

    private void registerBmob(final String userName, final String password) {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(password);
        bmobUser.signUp(new SaveListener<BmobUser>() {
            /**
             * 主线程回调
             * @param bmobUser
             * @param e
             */
            @Override
            public void done(BmobUser bmobUser, BmobException e) {

                if (e == null) {
                    //注册成功，通知View层成功
                    //mRegisterView.onRegisterSuccess();
                    registerEaseMob(userName,password);
                } else {
                    //注册失败，通知View层失败
                    //mRegisterView.onRegisterFailed();
                }
            }
        });
    }

    private void registerEaseMob(final String userName,final String password) {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(userName,password);
                    Log.d(TAG,"run:注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.d(TAG,"run:注册失败");
                }
            }
        }).start();*/

        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(userName,password);
                    Log.d(TAG,"run:注册成功");
                    //在主线程通知View注册成功
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.d(TAG,"run:注册失败");
                    //在主线程通知View注册失败
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterView.onRegisterFailed();
                        }
                    });
                }
            }
        });


    }
}
