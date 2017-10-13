package com.example.administrator.loveclassdemo.Presenter;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * Created by Tz on 2017/10/13.
 */

public interface ChatPresenter {
    void sendMessage(String content, String message);

    List<EMMessage> getMessageList();
}
