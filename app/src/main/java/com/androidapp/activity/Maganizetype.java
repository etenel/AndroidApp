package com.androidapp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.maganize.fragments.AuthorFragment;
import com.androidapp.maganize.fragments.TypeFragment;
import com.androidapp.product.model.adapter.ViewPagerAdapter;
import com.androidapp.util.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Maganizetype extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.bar)
    RelativeLayout bar;
    private List<Fragment> fragments;
    private String[] tags = Constants.MAGINIZE_TAG;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_maganizetype);
        BarUtils.setStatusBarColor(this,Color.WHITE);
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new TypeFragment());
        fragments.add(new AuthorFragment());
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, tags);
        viewpager.setAdapter(pagerAdapter);
        tab.setupWithViewPager(viewpager);
        tab.setTabTextColors(Color.LTGRAY, Color.BLACK);
        //tab.setBackgroundColor(Color.parseColor("#1C1C1C"));
        tab.post(() -> {
            BarUtils.setIndicator(tab, 70, 70);
        });
        viewpager.setCurrentItem(1);
    }

    @OnClick(R.id.bar)
    public void onViewClicked() {

        finish();
    }
}
