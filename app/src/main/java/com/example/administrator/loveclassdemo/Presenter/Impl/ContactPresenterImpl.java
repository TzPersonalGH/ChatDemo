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
                    //同步方法，去子线程执行
                    List<String> username = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for (int i =0; i<username.size(); i++) {
                        ContactListItem item = new ContactListItem();
                        item.contact = username.get(i);
                        //创建ContactListItem添加到数据集合
                        mContactListItems.add(item);
                    }

                    Collections.sort(mContactListItems, new Comparator<ContactListItem>() {
                        @Override
                        public int compare(ContactListItem o1, ContactListItem o2) {
                            return o1.contact.charAt(0)-o2.contact.charAt(0);
                        }
                    });

                    //判断后一个item跟前一个item的首字符是否一致，如果一致，则不显示首字符
                    for (int i = 0; i < mContactListItems.size();i++) {
                        ContactListItem item = mContactListItems.get(i);
                        if (i > 0 && item.getFirstLetter().equals(mContactListItems.get(i - 1).getFirstLetter())) {
                            item.showFirstLetter = false;
                        }
                    }

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
