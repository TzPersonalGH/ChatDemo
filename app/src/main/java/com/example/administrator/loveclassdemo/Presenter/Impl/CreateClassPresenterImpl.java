package com.example.administrator.loveclassdemo.Presenter.Impl;

import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.CreateClassPresenter;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.CreateClassView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by Tz on 2017/10/16.
 */

public class CreateClassPresenterImpl implements CreateClassPresenter {
    private CreateClassView mCreateClassView;

    public CreateClassPresenterImpl(CreateClassView view){
        mCreateClassView = view;
    }


    @Override

    public void create(String className) {
        if (className.equals(null)) {
            mCreateClassView.classNameIsNull();
        } else {
            mCreateClassView.onStartCreate();
            createToE(className);
        }
    }

    private void createToE(final String className) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 创建群组
                 * @param groupName 群组名称
                 * @param desc 群组简介
                 * @param allMembers 群组初始成员，如果只有自己传空数组即可
                 * @param reason 邀请成员加入的reason
                 * @param option 群组类型选项，可以设置群组最大用户数(默认200)及群组类型@see {@link EMGroupStyle}
                 *               option.inviteNeedConfirm表示邀请对方进群是否需要对方同意，默认是需要用户同意才能加群的。
                 *               option.extField创建群时可以为群组设定扩展字段，方便个性化订制。
                 * @return 创建好的group
                 * @throws HyphenateException
                 * option里的GroupStyle分别为：

                EMGroupStylePrivateOnlyOwnerInvite——私有群，只有群主可以邀请人；
                EMGroupStylePrivateMemberCanInvite——私有群，群成员也能邀请人进群；
                EMGroupStylePublicJoinNeedApproval——公开群，加入此群除了群主邀请，只能通过申请加入此群；
                EMGroupStylePublicOpenJoin ——公开群，任何人都能加入此群。
                 */
                String desc = "班级群";
                String reason = "本班同学";
                String[] allMembers = new String[0];

                EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
                option.maxUsers = 200;
                option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;

                try {
                    EMClient.getInstance().groupManager().createGroup(className, desc, allMembers, reason, option);
                    //创建班级成功，则在主线程通过View创建成功
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mCreateClassView.onCreateClassSuccess();
                        }
                    });

                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //创建班级失败，则在主线程通知View创建失败
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mCreateClassView.onCreateClassFailed();
                        }
                    });
                }
            }
        });
    }
}
