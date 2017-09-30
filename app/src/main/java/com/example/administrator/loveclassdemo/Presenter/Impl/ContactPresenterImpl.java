package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ContactPresenter;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ContactView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactPresenterImpl implements ContactPresenter{
    private ContactView mContactView;

    public ContactPresenterImpl(ContactView contactView) {
        mContactView = contactView;
    }

    @Override
    public void loadContact() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步方法，去子线程
                    List<String> username = EMClient.getInstance().contactManager().getAllContactsFromServer();

                    //没有异常，通知View层加载联系人数据成功
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //出现异常，通知View层加载失败
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactFailed();
                        }
                    });
                }
            }
        });
    }
}
