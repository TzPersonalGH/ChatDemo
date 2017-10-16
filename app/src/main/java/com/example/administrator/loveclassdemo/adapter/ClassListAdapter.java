package com.example.administrator.loveclassdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.loveclassdemo.model.ClassListItem;
import com.example.administrator.loveclassdemo.widget.ClassListItemView;

import java.util.List;


/**
 * Created by Tz on 2017/10/16.
 */

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassListItemViewHolder>{
    private Context mContext;
    private List<ClassListItem> mClassListItems;
    public ClassListAdapter(Context context, List<ClassListItem> listItems) {
        mContext = context;
        mClassListItems = listItems;
    }


    @Override
    public ClassListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassListItemViewHolder(new ClassListItemView(mContext));
    }

    @Override
    public void onBindViewHolder(ClassListItemViewHolder holder, int position) {
        holder.mClassListItemView.bindView(mClassListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mClassListItems.size();
    }

    public class ClassListItemViewHolder extends RecyclerView.ViewHolder {

        public ClassListItemView mClassListItemView;

        public ClassListItemViewHolder(ClassListItemView itemView) {
            super(itemView);
            mClassListItemView = itemView;
        }
    }

}
