package com.androidapp.mainactivity.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.androidapp.R;
import com.androidapp.maganize.MaganizeFragment;
import com.androidapp.mainactivity.model.Fragments;
import com.androidapp.mainactivity.view.IMainActivity;
import com.androidapp.persion.PersionFragment;
import com.androidapp.product.ProductFragment;
import com.androidapp.share.ShareFragment;
import com.androidapp.talent.TalentFragment;

import java.util.List;

/**
 * Created by etenel on 2017/7/6.
 */

public class MainPresenter implements IMainPresenter {
    Context context;
    IMainActivity mainview;
    Fragments fragments;
    private List<Fragment> fragmentlist;

    public MainPresenter(Context context, IMainActivity mainActivity) {
        this.context = context;
        this.mainview = mainActivity;
        fragments = new Fragments();
    }


    @Override
    public void loadDatas() {

    }

    @Override
    public void onCheckedChanged(FragmentManager fragmentManager, FrameLayout fragment, int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hide(transaction);
        switch (id) {
            case R.id.rb_product:
                if (fragments.productFragment == null) {
                    fragments.productFragment = new ProductFragment();
                    transaction.add(R.id.fragment, fragments.productFragment);
                } else {
                    transaction.show(fragments.productFragment);
                }

                break;
            case R.id.rb_magazine:
                if (fragments.maganizeFragment == null) {
                    fragments.maganizeFragment = new MaganizeFragment();
                    transaction.add(R.id.fragment, fragments.maganizeFragment);
                } else {
                    transaction.show(fragments.maganizeFragment);
                }
                break;
            case R.id.rb_talent:
                if (fragments.talentFragment == null) {
                    fragments.talentFragment = new TalentFragment();
                    transaction.add(R.id.fragment, fragments.talentFragment);
                } else {
                    transaction.show(fragments.talentFragment);
                }
                break;
            case R.id.rb_share:
                if (fragments.shareFragment == null) {
                    fragments.shareFragment = new ShareFragment();
                    transaction.add(R.id.fragment, fragments.shareFragment);
                } else {
                    transaction.show(fragments.shareFragment);
                }

                break;
            case R.id.rb_persion:
                if (fragments.persionFragment == null) {
                    fragments.persionFragment = new PersionFragment();
                    transaction.add(R.id.fragment, fragments.persionFragment);
                } else {
                    transaction.show(fragments.persionFragment);
                }

                break;
        }
        transaction.commit();
    }


    @Override
    public void hide(FragmentTransaction transaction) {
        fragmentlist = fragments.getFragments();
        if (fragmentlist != null && fragmentlist.size() > 0) {
            if (fragments.productFragment != null) {
                transaction.hide(fragments.productFragment);
            }
            if (fragments.maganizeFragment != null) {
                transaction.hide(fragments.maganizeFragment);
            }
            if (fragments.talentFragment != null) {
                transaction.hide(fragments.talentFragment);
            }
            if (fragments.shareFragment != null) {
                transaction.hide(fragments.shareFragment);
            }
            if (fragments.persionFragment != null) {
                transaction.hide(fragments.persionFragment);
            }
        }
    }
}
