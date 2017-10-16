package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ClassPresenter;
import com.example.administrator.loveclassdemo.view.ClassView;

/**
 * Created by Tz on 2017/10/16.
 */

public class ClassPresenterImpl implements ClassPresenter {
    private ClassView mClassView;

    public ClassPresenterImpl(ClassView classView) {
        mClassView = classView;
    }

    @Override
    public void loadClass() {

    }
}
