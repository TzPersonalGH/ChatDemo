package com.example.administrator.loveclassdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.loveclassdemo.model.ContactListItem;
import com.example.administrator.loveclassdemo.widget.ContactListItemView;

import java.util.List;

/**
 * Created by Tz on 2017/9/30.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListItemViewHolder> {
    private Context mContext;
    private List<ContactListItem> mContactListItem;

    public ContactListAdapter(Context content, List<ContactListItem> listItem) {
        mContext = content;
        mContactListItem = listItem;
    }

    /**
     * 创建一个ViewHolder
     * @param parent
     * @param viewType  item的类型
     * @return
     */
    @Override
    public ContactListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactListItemViewHolder(new ContactListItemView(mContext));
    }

    /**
     * 绑定viewholder，拿到对应position位置的数据，来渲染holder hold住的view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ContactListItemViewHolder holder, int position) {
        holder.mContactListItemView.bindView(mContactListItem.get(position));

    }

    /**
     * 返回item个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mContactListItem.size();
    }

    public class ContactListItemViewHolder extends RecyclerView.ViewHolder {
        public ContactListItemView mContactListItemView;


        public ContactListItemViewHolder(ContactListItemView itemView) {
            super(itemView);
            mContactListItemView = itemView;
        }
    }

}
