package com.example.administrator.loveclassdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.loveclassdemo.widget.ContactListItemView;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactListAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ContactListAdapter(Context content) {
        mContext = content;
    }

    /**
     * 创建一个ViewHolder
     * @param parent
     * @param viewType  item的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactListItemViewHolder(new ContactListItemView(mContext));
    }

    /**
     * 绑定viewholder，拿到对应position位置的数据，来渲染holder hold住的view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /**
     * 返回item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return 30;
    }

    public class ContactListItemViewHolder extends RecyclerView.ViewHolder {
        public ContactListItemView mContactListItemView;


        public ContactListItemViewHolder(ContactListItemView itemView) {
            super(itemView);
            mContactListItemView = itemView;
        }
    }

}
