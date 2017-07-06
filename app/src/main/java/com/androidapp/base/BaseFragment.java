package com.androidapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by etenel on 2017/7/6.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getlayoutid(), null);
        bind = ButterKnife.bind(this, view);
        initTitle();
        initData();
        return view;
    }

    public abstract void initData() ;

    public void initTitle() {

    }

    public int getlayoutid() {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
