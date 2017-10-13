package com.example.administrator.loveclassdemo.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tz on 2017/10/13.
 */
public class ChatActivity extends BaseActivity {
    private String mContact;

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
        mBack.setVisibility(View.VISIBLE);
        mContact = getIntent().getStringExtra("contact");
        String title = String.format(getString(R.string.chat_title),mContact);
        mTitle.setText(title);
        mMessage.addTextChangedListener(mTextWatcher);//监听输入框的变化
    }

    @OnClick({R.id.send ,R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                break;
            case R.id.back:
                finish();
                break;
        }
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

}
