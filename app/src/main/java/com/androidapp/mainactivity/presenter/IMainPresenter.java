package com.androidapp.mainactivity.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

/**
 * Created by etenel on 2017/7/6.
 */

public interface IMainPresenter {
    void loadDatas();
    void onCheckedChanged(FragmentManager fragmentManager, FrameLayout fragment, int id);
    void hide(FragmentTransaction transaction);
}
