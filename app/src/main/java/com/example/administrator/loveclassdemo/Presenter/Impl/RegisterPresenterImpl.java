package com.example.administrator.loveclassdemo.Presenter.Impl;

import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.RegisterPresenter;
import com.example.administrator.loveclassdemo.ui.RegisterActivity;
import com.example.administrator.loveclassdemo.utils.StringUtils;
import com.example.administrator.loveclassdemo.view.RegisterView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Tz on 2017/9/26.
 */

public class RegisterPresenterImpl implements RegisterPresenter {
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
                    //密码一致，则注册到Bmob
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

    private void registerBmob(String userName, String password) {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName);
        bmobUser.setPassword(password);
        bmobUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    //注册成功，通知View层成功
                    mRegisterView.onRegisterSuccess();
                } else {
                    //注册失败，通知View层失败
                    mRegisterView.onRegisterFailed();
                }
            }
        });
    }
}
