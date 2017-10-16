package com.example.administrator.loveclassdemo.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.loveclassdemo.Presenter.ClassPresenter;
import com.example.administrator.loveclassdemo.Presenter.Impl.ClassPresenterImpl;
import com.example.administrator.loveclassdemo.R;
import com.example.administrator.loveclassdemo.adapter.ClassListAdapter;
import com.example.administrator.loveclassdemo.view.ClassView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tz on 2017/10/15.
 */

public class ClassFragment extends BaseFragment implements ClassView {
    private ClassListAdapter mClassListAdapter;
    private ClassPresenter mClassPresenter;

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_class;
    }

    @Override
    protected void init() {
        super.init();
        mClassPresenter = new ClassPresenterImpl(this);
        mTitle.setText(R.string.Class);
        mAdd.setVisibility(View.VISIBLE);
        initRecyclerView();

        mClassPresenter.loadClass();
    }

    private void initRecyclerView() {
        mClassListAdapter = new ClassListAdapter(getContext());
        mRecyclerView.setHasFixedSize(true);//设置其有固定大小
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mClassListAdapter);

    }

}
