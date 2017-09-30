package com.example.administrator.loveclassdemo.ui.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/9/25.
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG ="LoginActivity" ;

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

    @Override
    protected void init() {
        super.init();
    }


    @OnClick({R.id.login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Login();
                break;
            case R.id.new_user:
                goTo(RegisterActivity.class);
                break;
        }
    }

    /**
     * 登录到环信
     */
    private void Login() {
        showProgressDialog(getString(R.string.logining));
        String userName = mUser.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d(TAG,"OnSuccess"+Thread.currentThread().getName());
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d(TAG,"登录到环信服务器成功");
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        goTo(MainActivity.class);//登录成功，跳转到主界面
                    }
                });

            }

            @Override
            public void onError(int i, String s) {
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                });

                Log.d(TAG,"登录到环信服务器失败");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });


    }
}
