package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ContactPresenter;
import com.example.administrator.loveclassdemo.model.ContactListItem;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ContactView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactPresenterImpl implements ContactPresenter{
    private ContactView mContactView;
    private List<ContactListItem> mContactListItems;

    public ContactPresenterImpl(ContactView contactView) {
        mContactView = contactView;
        mContactListItems = new ArrayList<ContactListItem>();
    }

    @Override
    public void loadContact() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步方法，去子线程
                    List<String> username = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (int i =0; i<username.size(); i++) {
                        ContactListItem item = new ContactListItem();
                        item.contact = username.get(i);
                        mContactListItems.add(item);
                    }

                    Collections.sort(mContactListItems, new Comparator<ContactListItem>() {
                        @Override
                        public int compare(ContactListItem o1, ContactListItem o2) {
                            return o1.contact.charAt(0)-o2.contact.charAt(0);
                        }
                    });

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

    @Override
    public List<ContactListItem> getDataList() {
        return mContactListItems;
    }
}
