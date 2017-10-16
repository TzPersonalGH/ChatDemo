package com.example.administrator.loveclassdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.loveclassdemo.R;


/**
 * Created by Tz on 2017/10/16.
 */

public class ClassListItemView extends RelativeLayout {
    public ClassListItemView(Context context) {
        this(context,null);
    }

    public ClassListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_class_list_item, this);
    }
}
