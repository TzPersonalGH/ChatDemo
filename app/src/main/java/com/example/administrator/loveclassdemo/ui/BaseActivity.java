package com.example.administrator.loveclassdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 封装公共的功能
 * Created by Tz on 2017/9/19.
 */

public abstract class BaseActivity extends AppCompatActivity{
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        init();
    }

    /**
     * 初始化方法，初始化功能，子类也可以覆写，实现自己的初始化
     */
    protected void init() {

    }

    /**
     *子类必须实现这个方法，返回Activity的布局
     * @return
     */
    public abstract int getLayoutResId();

    public void goTo(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent );
        finish();
    }

    public void showProgressDialog(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        mProgressDialog.hide();
    }

}
