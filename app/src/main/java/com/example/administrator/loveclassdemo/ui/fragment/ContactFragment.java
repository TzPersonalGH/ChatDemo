package com.example.administrator.loveclassdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.adapter.ContactListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tz on 2017/9/29.
 */

public class ContactFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mContectListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mTitle.setText(R.string.contacts);
        mAdd.setVisibility(View.VISIBLE);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mContectListAdapter = new ContactListAdapter(getContext());
        mRecyclerView.setHasFixedSize(true);//设置RecyclerView有固定的大小
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mContectListAdapter);
    }
}
