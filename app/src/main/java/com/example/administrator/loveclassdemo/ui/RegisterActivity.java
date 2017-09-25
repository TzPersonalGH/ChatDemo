package com.example.administrator.loveclassdemo.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.loveclassdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/9/25.
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.user)
    EditText mUser;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.register)
    Button mRegister;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        super.init();
    }

    @OnClick(R.id.register)
    public void onClick() {
        //开始注册
        register();
    }

    private void register() {
        String userName = mUser.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

    }
}
