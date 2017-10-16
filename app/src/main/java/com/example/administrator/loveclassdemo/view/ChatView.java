package com.example.administrator.loveclassdemo.view;

/**
 * Created by Tz on 2017/10/13.
 */

public interface ChatView {
    void onSendMessageSuccess();

    void onSendMessageFailed();

    void onStartSendMessage();

    void hideEditTextContent();
}
