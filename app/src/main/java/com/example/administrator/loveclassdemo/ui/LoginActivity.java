package com.example.administrator.loveclassdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/9/25.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.user)
    EditText mUser;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.new_user)
    TextView mNewUser;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    public void init() {
        ButterKnife.bind(this);
    }


    @OnClick(R.id.new_user)
    public void onClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
