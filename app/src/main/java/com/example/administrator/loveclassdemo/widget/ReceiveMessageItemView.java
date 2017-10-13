package com.example.administrator.loveclassdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.loveclassdemo.R;

/**
 * Created by Tz on 2017/10/13.
 */

public class ReceiveMessageItemView extends RelativeLayout {
    public ReceiveMessageItemView(Context context) {
        this(context,null);
    }

    public ReceiveMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_receive_message, this);
    }
}
