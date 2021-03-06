package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ChatPresenter;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ChatView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tz on 2017/10/13.
 */

public class ChatPresenterImpl implements ChatPresenter {
    private ChatView mChatView;
    private List<EMMessage> mEMMessages;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
        mEMMessages = new ArrayList<EMMessage>();
    }

    @Override

    public void sendMessage(final String content, final String username) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为文字内容，username为聊天对象
                EMMessage message = EMMessage.createTxtSendMessage(content, username);
                message.setMessageStatusCallback(mEMCallBack);
                mEMMessages.add(message);
                //当发送一条消息时，通知View层刷新列表，显示一条正在发送的消息
                ThreadUtils.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.onStartSendMessage();
                        mChatView.hideEditTextContent();
                    }
                });
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);

            }
        });
    }

    @Override
    public List<EMMessage> getMessageList() {
        return mEMMessages;
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
