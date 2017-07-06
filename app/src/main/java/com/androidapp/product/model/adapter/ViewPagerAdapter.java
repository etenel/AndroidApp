package com.androidapp.product.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by etenel on 2017/7/6.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private final List<Fragment> frameLayouts;
    private final String[] titles;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> frameLayouts, String[] tags) {
        super(fm);
        this.frameLayouts=frameLayouts;
        this.titles=tags;
    }

    @Override
    public Fragment getItem(int position) {
        return frameLayouts.get(position);
    }

    @Override
    public int getCount() {
        return frameLayouts==null?0:frameLayouts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
