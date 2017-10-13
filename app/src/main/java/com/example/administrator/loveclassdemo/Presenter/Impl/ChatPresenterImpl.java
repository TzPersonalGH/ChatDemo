package com.example.administrator.loveclassdemo.Presenter.Impl;

import com.example.administrator.loveclassdemo.Presenter.ChatPresenter;
import com.example.administrator.loveclassdemo.view.ChatView;

/**
 * Created by Tz on 2017/10/13.
 */

public class ChatPresenterImpl implements ChatPresenter {
    private ChatView mChatView;

    public ChatPresenterImpl(ChatView chatView) {
        mChatView = chatView;
    }

    @Override

    public void sendMessage(String content, String message) {

    }
}
