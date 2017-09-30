package com.example.administrator.loveclassdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.loveclassdemo.R;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactListItemView extends RelativeLayout {

    public ContactListItemView(Context context) {
        this(context,null);
    }

    public ContactListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        //将view_contact_list_item解析出来后，添加到ContactListItemView
        LayoutInflater.from(getContext()).inflate(R.layout.view_contact_list_item,this);
    }
}
