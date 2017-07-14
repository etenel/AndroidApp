package com.androidapp.share;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.product.model.adapter.ViewPagerAdapter;
import com.androidapp.share.fragments.BSRecommendFragment;
import com.androidapp.share.fragments.StoryFragment;
import com.androidapp.util.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/6.
 */

public class ShareFragment extends BaseFragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] tags = {"推荐", "段子"};
    private ViewPagerAdapter pagerAdapter;
    private List<Fragment> frameLayouts;

    @Override
    public int getlayoutid() {
        return R.layout.fragment_share;
    }

    @Override
    public void initData() {
        BarUtils.setStatusBarColor(getActivity(), getResources().getColor(R.color.colorAccent));
        BarUtils.setTranslucentForCoordinatorLayout(getActivity(), 0);
        frameLayouts = new ArrayList<>();
        frameLayouts.add(new BSRecommendFragment());
        frameLayouts.add(new StoryFragment());
        pagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), frameLayouts, tags);
        viewpager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
        tablayout.setSelectedTabIndicatorColor(Color.WHITE);
        tablayout.post(() -> {
            BarUtils.setIndicator(tablayout, 70, 70);
        });
    }


}
