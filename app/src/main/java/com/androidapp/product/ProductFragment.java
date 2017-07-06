package com.androidapp.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.R;
import com.androidapp.product.fragments.BrandFragment;
import com.androidapp.product.fragments.GiftFragment;
import com.androidapp.product.fragments.HomeFragment;
import com.androidapp.product.fragments.TopicFragment;
import com.androidapp.product.fragments.TypeFragment;
import com.androidapp.product.model.Constants;
import com.androidapp.product.model.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etenel on 2017/7/6.
 */

public class ProductFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private String[] tags = Constants.product_tag;
    private List<Fragment> frameLayouts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View productview = inflater.inflate(R.layout.fragment_product, null);
        tabLayout = productview.findViewById(R.id.tablayout);
        viewPager = productview.findViewById(R.id.viewpager);
        initData();
        return productview;
    }


    public void initData() {
        frameLayouts = new ArrayList<>();
        frameLayouts.add(new TypeFragment());
        frameLayouts.add(new BrandFragment());
        frameLayouts.add(new HomeFragment());
        frameLayouts.add(new TopicFragment());
        frameLayouts.add(new GiftFragment());
        pagerAdapter = new ViewPagerAdapter(getFragmentManager(), frameLayouts, tags);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setCurrentItem(2);
    }


}
