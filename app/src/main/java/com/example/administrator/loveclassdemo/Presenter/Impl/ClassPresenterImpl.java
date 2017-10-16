package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ClassPresenter;
import com.example.administrator.loveclassdemo.model.ClassListItem;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ClassView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tz on 2017/10/16.
 */

public class ClassPresenterImpl implements ClassPresenter {
    private ClassView mClassView;
    private List<ClassListItem> mClassListItems;

    public ClassPresenterImpl(ClassView classView) {
        mClassView = classView;
        mClassListItems = new ArrayList<ClassListItem>();
    }

    @Override
    public void loadClass() {
        /**
         * 获取公开群列表
         * pageSize为要取到的群组的数量，cursor用于告诉服务器从哪里开始取
         * EMCursorResult<EMGroupInfo> result = EMClient.getInstance().groupManager().getPublicGroupsFromServer(pageSize, cursor);//需异步处理
         * List<EMGroupInfo> groupsList = List<EMGroupInfo> returnGroups = result.getData();
         * String cursor = result.getCursor();
         */
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //从服务器获取自己加入的和创建的群组列表，此api获取的群组sdk会自动保存到内存和db。
                try {
                    List<EMGroup> grouplist = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();//需异步处理
                    //没有异常，则加载群组成功，通知View层
                    for (int i = 0; i<grouplist.size(); i++) {
                        ClassListItem item = new ClassListItem();
                        item.className = String.valueOf(grouplist.get(i));
                        mClassListItems.add(item);
                    }
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mClassView.onLoadClassSuccess();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //出现异常，则加载群组失败，通知View层
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mClassView.onLoadClassFailed();
                        }
                    });
                }
            }
        });
    }

    @Override
    public List<ClassListItem> getDataList() {
        return mClassListItems;
    }
}
