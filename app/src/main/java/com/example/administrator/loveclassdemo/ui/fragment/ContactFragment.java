package com.example.administrator.loveclassdemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.loveclassdemo.Presenter.ContactPresenter;
import com.example.administrator.loveclassdemo.Presenter.Impl.ContactPresenterImpl;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.adapter.ContactListAdapter;
import com.example.administrator.loveclassdemo.view.ContactView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tz on 2017/9/29.
 */

public class ContactFragment extends BaseFragment implements ContactView{
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mContactListAdapter;
    private ContactPresenter mContactPresenter;
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mContactPresenter = new ContactPresenterImpl(this);
        mTitle.setText(R.string.contacts);
        mAdd.setVisibility(View.VISIBLE);
        initRecyclerView();

        mContactPresenter.loadContact();
    }

    private void initRecyclerView() {
        mContactListAdapter = new ContactListAdapter(getContext(),mContactPresenter.getDataList());
        mRecyclerView.setHasFixedSize(true);//设置RecyclerView有固定的大小
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mContactListAdapter);
    }

    @Override
    public void onLoadContactSuccess() {
        Toast.makeText(getContext(), getString(R.string.load_contacts_success), Toast.LENGTH_SHORT).show();
        //刷新列表
        mContactListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLoadContactFailed() {
        Toast.makeText(getContext(), getString(R.string.load_contacts_failed), Toast.LENGTH_SHORT).show();

    }
}
