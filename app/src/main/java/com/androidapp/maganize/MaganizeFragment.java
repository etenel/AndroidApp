package com.androidapp.maganize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.R;

/**
 * Created by etenel on 2017/7/6.
 */

public class MaganizeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View productview = inflater.inflate(R.layout.fragment_maganize, null);
        return productview;
    }
}
