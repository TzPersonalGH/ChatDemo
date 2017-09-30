package com.example.administrator.loveclassdemo.Presenter;

import com.example.administrator.loveclassdemo.model.ContactListItem;

import java.util.List;

/**
 * Created by Tz on 2017/9/30.
 */

public interface ContactPresenter {
    void loadContact();

    List<ContactListItem> getDataList();
}
