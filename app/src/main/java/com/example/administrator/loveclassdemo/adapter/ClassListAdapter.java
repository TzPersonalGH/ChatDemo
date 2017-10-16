package com.example.administrator.loveclassdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.loveclassdemo.widget.ClassListItemView;


/**
 * Created by Tz on 2017/10/16.
 */

public class ClassListAdapter extends RecyclerView.Adapter{
    private Context mContext;
    public ClassListAdapter(Context context) {
        mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassListItemViewHolder(new ClassListItemView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ClassListItemViewHolder extends RecyclerView.ViewHolder {

        public ClassListItemView mClassListItemView;

        public ClassListItemViewHolder(ClassListItemView itemView) {
            super(itemView);
            mClassListItemView = itemView;
        }
    }

}
