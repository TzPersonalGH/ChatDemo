package com.example.administrator.loveclassdemo.ui.Activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.Impl.RegisterPresenterImpl;
import com.example.administrator.loveclassdemo.Presenter.RegisterPresenter;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.view.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/9/25.
 */
public class RegisterActivity extends BaseActivity implements RegisterView{
    private RegisterPresenter registerPresenter ;

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
        registerPresenter = new RegisterPresenterImpl(this);
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

        //隐藏软键盘
        hideKeyBoard();

        //调用Presenter来完成注册业务逻辑
        registerPresenter.register(userName,password,confirmPassword);

    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        //隐藏进度条
        hideProgressDialog();
        //注册成功，跳转到登录界面
        goTo(LoginActivity.class);
    }

    @Override
    public void onRegisterFailed() {
        Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNameError() {
        mUser.setError(getString(R.string.user_name_error));
    }

    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void onConfirmPasswordError() {
        mConfirmPassword.setError(getString(R.string.confirm_password_error));
    }

    @Override
    public void onStartRegister() {
        showProgressDialog(getString(R.string.registering));
    }
}
