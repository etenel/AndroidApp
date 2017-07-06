package com.androidapp.MainActivity.model;

import android.support.v4.app.Fragment;

import com.androidapp.maganize.MaganizeFragment;
import com.androidapp.persion.PersionFragment;
import com.androidapp.product.ProductFragment;
import com.androidapp.share.ShareFragment;
import com.androidapp.talent.TalentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etenel on 2017/7/6.
 */

public class Fragments {
    public ProductFragment productFragment;
    public PersionFragment persionFragment;
    public ShareFragment shareFragment;
    public TalentFragment talentFragment;
    public MaganizeFragment maganizeFragment;
    public List<Fragment> fragments;

    public Fragments() {
//        this.productFragment = new ProductFragment();
//        this.persionFragment = new PersionFragment();
//        this.shareFragment = new ShareFragment();
//        this.talentFragment = new TalentFragment();
//        this.maganizeFragment = new MaganizeFragment();
        initFragment();
    }

    private void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(productFragment);
        fragments.add(maganizeFragment);
        fragments.add(talentFragment);
        fragments.add(shareFragment);
        fragments.add(persionFragment);
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
