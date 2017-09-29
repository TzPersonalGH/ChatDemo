package com.example.administrator.loveclassdemo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/9/29.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.logout)
    Button mLogout;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init() {
        super.init();
        mTitle.setText(R.string.my);
        String logout = String.format(getString(R.string.logout), EMClient.getInstance().getCurrentUser());
        mLogout.setText(logout);
    }

    @OnClick({R.id.back, R.id.add, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.add:
                break;
            case R.id.logout:
                break;
        }
    }
}
