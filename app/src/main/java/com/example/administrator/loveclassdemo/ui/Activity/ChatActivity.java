package com.example.administrator.loveclassdemo.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.ChatPresenter;
import com.example.administrator.loveclassdemo.Presenter.Impl.ChatPresenterImpl;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.adapter.MessageListAdapter;
import com.example.administrator.loveclassdemo.utils.ThreadUtils;
import com.example.administrator.loveclassdemo.view.ChatView;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/10/13.
 */
public class ChatActivity extends BaseActivity implements ChatView{
    private String mContact;
    private ChatPresenter mChatPresenter;
    private MessageListAdapter mMessageListAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.message)
    EditText mMessage;
    @BindView(R.id.send)
    Button mSend;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        super.init();
        mChatPresenter = new ChatPresenterImpl(this);
        mBack.setVisibility(View.VISIBLE);
        mContact = getIntent().getStringExtra("contact");
        String title = String.format(getString(R.string.chat_title),mContact);
        mTitle.setText(title);
        mMessage.addTextChangedListener(mTextWatcher);//监听输入框的变化
        initRecyclerView();
        EMClient.getInstance().chatManager().addMessageListener(mEMMessageListener);
    }

    private void initRecyclerView() {
        mMessageListAdapter = new MessageListAdapter(this, mChatPresenter.getMessageList());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMessageListAdapter);
    }

    @OnClick({R.id.send ,R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                sendMessage();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    /**
     * 调用Presenter来发送一条消息
     */
    private void sendMessage() {
        hideKeyBoard();
        String content = mMessage.getText().toString().trim();
        mChatPresenter.sendMessage(content,mContact);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //如果用户输入的文本长度不大于0，则发送按钮应该是disable
            //大于0则是enable
            mSend.setEnabled(s.toString().length()>0);
        }
    };

    @Override
    public void onSendMessageSuccess() {
        Toast.makeText(this, "发送消息成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendMessageFailed() {
        Toast.makeText(this, "发送消息失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartSendMessage() {
        mMessageListAdapter.notifyDataSetChanged();
        //滚动RecyclerView到底部
        mRecyclerView.smoothScrollToPosition(mChatPresenter.getMessageList().size()-1);
    }

    @Override
    public void hideEditTextContent() {
        mMessage.setText("");
    }

    private EMMessageListener mEMMessageListener = new EMMessageListener() {
        /**
         * 在子线程回调
         * @param list
         */
        @Override
        public void onMessageReceived(final List<EMMessage> list) {
            //在主线程刷新列表
            ThreadUtils.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    mMessageListAdapter.addMessage(list.get(0));
                    mRecyclerView.smoothScrollToPosition(mChatPresenter.getMessageList().size()-1);
                }
            });
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };


}
