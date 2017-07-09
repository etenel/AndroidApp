package com.androidapp.maganize.fragments;

import android.support.v7.widget.RecyclerView;

import com.androidapp.R;
import com.androidapp.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/9.
 */

public class TypeFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    public void initData() {

    }

    @Override
    public int getlayoutid() {
        return R.layout.maganize_type;
    }

}
