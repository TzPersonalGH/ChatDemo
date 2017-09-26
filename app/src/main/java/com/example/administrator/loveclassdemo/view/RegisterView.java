package com.example.administrator.loveclassdemo.view;

/**
 * Created by Tz on 2017/9/26.
 */

/**
 * 定义View层的业务逻辑
 */
public interface RegisterView {
    void onRegisterSuccess();

    void onRegisterFailed();

    void onUserNameError();

    void onPasswordError();

    void onConfirmPasswordError();

    void onStartRegister();
}
