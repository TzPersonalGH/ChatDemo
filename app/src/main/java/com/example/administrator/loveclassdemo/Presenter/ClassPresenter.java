package com.example.administrator.loveclassdemo.Presenter;

import com.example.administrator.loveclassdemo.model.ClassListItem;

import java.util.List;

/**
 * Created by Tz on 2017/10/16.
 */

public interface ClassPresenter {
    void loadClass();

    List<ClassListItem> getDataList();
}
