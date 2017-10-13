package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ChatPresenter;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ChatView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

/**
 * Created by Tz on 2017/10/13.
 */

public class ChatPresenterImpl implements ChatPresenter {
    private ChatView mChatView;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
    }

    @Override

    public void sendMessage(final String content, final String username) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为文字内容，username为聊天对象
                EMMessage message = EMMessage.createTxtSendMessage(content, username);
                message.setMessageStatusCallback(mEMCallBack);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        });
    }

    private EMCallBack mEMCallBack = new EMCallBack() {
        @Override
        public void onSuccess() {
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    mChatView.onSendMessageFailed();
                }
            });
        }

        @Override
        public void onProgress(int i, String s) {

        }
    };

}
