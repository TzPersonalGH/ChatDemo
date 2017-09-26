package com.example.administrator.loveclassdemo.Presenter.Impl;

import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.RegisterPresenter;
import com.example.administrator.loveclassdemo.ui.RegisterActivity;
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
