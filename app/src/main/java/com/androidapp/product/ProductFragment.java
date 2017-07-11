package com.androidapp.product;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.activity.ShopCartActivity;
import com.androidapp.constant.Constants;
import com.androidapp.product.fragments.BrandFragment;
import com.androidapp.product.fragments.GiftFragment;
import com.androidapp.product.fragments.HomeFragment;
import com.androidapp.product.fragments.TopicFragment;
import com.androidapp.product.fragments.TypeFragment;
import com.androidapp.product.model.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by etenel on 2017/7/6.
 */

public class ProductFragment extends Fragment {
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageButton right;
    Unbinder unbinder;
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
        unbinder = ButterKnife.bind(this, productview);
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
        tabLayout.setTabTextColors(Color.LTGRAY, Color.WHITE);
        tabLayout.setBackgroundColor(Color.parseColor("#1C1C1C"));
        viewPager.setCurrentItem(2);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:

                break;
            case R.id.right:
                Intent intent = new Intent(getContext(), ShopCartActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
        }
    }
}
