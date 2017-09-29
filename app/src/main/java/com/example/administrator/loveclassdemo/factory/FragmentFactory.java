package com.example.administrator.loveclassdemo.factory;

import android.support.v4.app.Fragment;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.ui.fragment.ContactFragment;
import com.example.administrator.loveclassdemo.ui.fragment.ConversationFragment;
import com.example.administrator.loveclassdemo.ui.fragment.MyFragment;

/**
 * Created by Tz on 2017/9/29.
 */

public class FragmentFactory {
    private static FragmentFactory sFragmentFactory;
    private Fragment mContactFragment;
    private Fragment mMyFragment;
    private Fragment mConversationFragment;

    private FragmentFactory() {}

    /**
     * 单例模式
     */
    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                sFragmentFactory = new FragmentFactory();
            }
        }
        return sFragmentFactory;
    }

    /**
     * 根据不同的tabId来生成不同的fragment
     *
     * @param tabId
     * @return
     */
    public Fragment getFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_conversation:
                return getConversationFragment();
            case R.id.tab_contacts:
                return getContactFragment();
            case R.id.tab_my:
                return getMyFragment();
        }
        return null;
    }

    public Fragment getConversationFragment() {
        if (mConversationFragment == null) {
            mConversationFragment = new ConversationFragment();
        }
        return mConversationFragment;
    }

    public Fragment getContactFragment() {
        if (mContactFragment == null) {
            mContactFragment = new ContactFragment();
        }
        return mContactFragment;
    }

    public Fragment getMyFragment() {
        if (mMyFragment == null) {
            mMyFragment = new MyFragment();
        }
        return mMyFragment;
    }
}
