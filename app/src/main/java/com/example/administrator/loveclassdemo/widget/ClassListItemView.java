package com.example.administrator.loveclassdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.model.ClassListItem;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Tz on 2017/10/16.
 */

public class ClassListItemView extends RelativeLayout {
    @BindView(R.id.class_name)
    TextView mClassName;

    public ClassListItemView(Context context) {
        this(context, null);
    }

    public ClassListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_class_list_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(ClassListItem classListItem) {
        mClassName.setText(classListItem.className);

    }
}
