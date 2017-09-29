package com.example.administrator.loveclassdemo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.ui.Activity.LoginActivity;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.hyphenate.EMCallBack;
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

    @OnClick(R.id.logout)
    public void onClick(View view) {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        goTo(LoginActivity.class);
                    }
                });

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

        }

}
