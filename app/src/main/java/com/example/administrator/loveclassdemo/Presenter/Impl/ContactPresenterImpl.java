package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ContactPresenter;
import com.example.administrator.loveclassdemo.view.ContactView;

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

    }
}
