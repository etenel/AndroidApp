package com.androidapp.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.androidapp.R;
import com.androidapp.product.model.adapter.ViewPagerAdapter;
import com.androidapp.share.fragments.BSRecommendFragment;
import com.androidapp.share.fragments.StoryFragment;
import com.androidapp.util.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaisiRecommend extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_baisi_recommend);
        ButterKnife.bind(this);
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.colorAccent));
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        initData();

    }

    private void initData() {
        frameLayouts = new ArrayList<>();
        frameLayouts.add(new BSRecommendFragment());
        frameLayouts.add(new StoryFragment());
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), frameLayouts, tags);
        viewpager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
        tablayout.setSelectedTabIndicatorColor(Color.WHITE);
        tablayout.post(() -> {
            BarUtils.setIndicator(tablayout, 70, 70);
        });
    }
}
