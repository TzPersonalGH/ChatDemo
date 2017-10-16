package com.example.administrator.loveclassdemo.ui.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.CreateClassPresenter;
import com.example.administrator.loveclassdemo.Presenter.Impl.CreateClassPresenterImpl;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.view.CreateClassView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/10/16.
 */
public class CreateClassActivity extends BaseActivity implements CreateClassView{

    private CreateClassPresenter mCreateClassPresenter ;
    
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.class_name)
    EditText mClassName;
    @BindView(R.id.create)
    Button mCreate;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_createclass;
    }

    @Override
    protected void init() {
        super.init();
        mCreateClassPresenter = new CreateClassPresenterImpl(this);
        mBack.setVisibility(View.VISIBLE);
        String title = "班级创建";
        mTitle.setText(title);
    }

    @OnClick({R.id.back, R.id.create})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                goTo(MainActivity.class);
                break;
            case R.id.create:
                //开始创建
                StartCreate();
                break;
        }
    }

    private void StartCreate() {
        String className = mClassName.getText().toString().trim();
        //调用Presenter来完成创建班级业务逻辑
        mCreateClassPresenter.create(className);
    }

    @Override
    public void onStartCreate() {
        showProgressDialog(getString(R.string.creating));
    }

    @Override
    public void classNameIsNull() {
        Toast.makeText(this, "班级名称不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateClassSuccess() {
        Toast.makeText(this, "创建班级成功", Toast.LENGTH_SHORT).show();
        //隐藏进度条
        hideProgressDialog();
        //创建成功，跳转到主界面
        goTo(MainActivity.class);
    }

    @Override
    public void onCreateClassFailed() {
        Toast.makeText(this, "发送未知错误，创建班级失败", Toast.LENGTH_SHORT).show();
    }
}
