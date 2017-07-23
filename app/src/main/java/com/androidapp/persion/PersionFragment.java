package com.androidapp.persion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.persion.fragments.BilibiliFragment;
import com.androidapp.persion.view.CircleImageView;
import com.androidapp.persion.view.PreferenceUtil;
import com.androidapp.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by etenel on 2017/7/6.
 */

public class PersionFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)

    DrawerLayout drawerLayout;
    Unbinder unbinder;
    private BilibiliFragment bilibiliFragment;
    private List<Fragment> fragments;

    private int currentTabIndex;

    private int index;

    private long exitTime;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View productview = inflater.inflate(R.layout.fragment_persion, null);
        unbinder = ButterKnife.bind(this, productview);
        productview.setFocusable(true);
        productview.setOnKeyListener(backlistener);
        initdata();
        return productview;
    }

    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    private View.OnKeyListener backlistener = (view, i, keyEvent) -> {
        if (i == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(drawerLayout.getChildAt(1))) {
                drawerLayout.closeDrawers();
            } else {
                if (bilibiliFragment != null) {
                    if (bilibiliFragment.isOpenSearchView()) {
                        bilibiliFragment.closeSearchView();
                    } else {
                        exitApp();
                    }
                } else {
                    exitApp();
                }
            }
        }

        return true;
    };

    private void initdata() {
        //初始化Fragment
        initFragments();
        //初始化侧滑菜单
        initNavigationView();
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(
                R.id.user_avatar_view);
        TextView mUserName = (TextView) headerView.findViewById(R.id.user_name);
        TextView mUserSign = (TextView) headerView.findViewById(R.id.user_other_info);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置头像
        mUserAvatarView.setImageResource(R.drawable.ic_avatar1);
        //设置用户名 签名
        mUserName.setText(getResources().getText(R.string.hotbitmapgg));
        mUserSign.setText(getResources().getText(R.string.about_user_head_layout));
        //设置日夜间模式切换
        mSwitchMode.setOnClickListener(v -> switchNightMode());

        boolean flag = PreferenceUtil.getBoolean(Constants.SWITCH_MODE_KEY, false);
        if (flag) {
            mSwitchMode.setImageResource(R.drawable.ic_switch_daily);
        } else {
            mSwitchMode.setImageResource(R.drawable.ic_switch_night);
        }
    }

    //日夜间模式切换
    private void switchNightMode() {
        boolean isNight = PreferenceUtil.getBoolean(Constants.SWITCH_MODE_KEY, false);
        if (isNight) {
            //日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferenceUtil.putBoolean(Constants.SWITCH_MODE_KEY, false);
        } else {
            //夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferenceUtil.putBoolean(Constants.SWITCH_MODE_KEY, true);
        }
        getActivity().recreate();
    }

    private void initFragments() {
        bilibiliFragment = new BilibiliFragment();
        fragments = new ArrayList<>();
            fragments.add(bilibiliFragment);
        getChildFragmentManager().beginTransaction()
                .add(R.id.container, bilibiliFragment)
                .show(bilibiliFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                changeFragmentIndex(item, 0);
                return true;

            case R.id.item_download:
                // 离线缓存
                // startActivity(new Intent(getContext(), OffLineDownloadActivity.class));
                return true;

            case R.id.item_vip:
                //大会员
                //startActivity(new Intent(getContext(), VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                changeFragmentIndex(item, 1);
                return true;

            case R.id.item_history:
                // 历史记录
                changeFragmentIndex(item, 2);
                return true;

            case R.id.item_group:
                // 关注的人
                changeFragmentIndex(item, 3);
                return true;

            case R.id.item_tracker:
                // 我的钱包
                changeFragmentIndex(item, 4);
                return true;

            case R.id.item_theme:
                // 主题选择
                // CardPickerDialog dialog = new CardPickerDialog();
                // dialog.setClickListener(this);
                // dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
                return true;

            case R.id.item_app:
                // 应用推荐

                return true;

            case R.id.item_settings:
                // 设置中心
                changeFragmentIndex(item, 5);
                return true;
        }

        return false;

    }

    /**
     * Fragment切换
     */
    private void switchFragment() {

        FragmentTransaction trx = getChildFragmentManager().beginTransaction();
        trx.hide(fragments.get(currentTabIndex));
        if (!fragments.get(index).isAdded()) {
            trx.add(R.id.container, fragments.get(index));
        }
        trx.show(fragments.get(index)).commit();
        currentTabIndex = index;
    }


    /**
     * 切换Fragment的下标
     */
    private void changeFragmentIndex(MenuItem item, int currentIndex) {

        index = currentIndex;
        switchFragment();
        item.setChecked(true);
    }



    /**
     * 双击退出App
     */
    private void exitApp() {

        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showShort("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            PreferenceUtil.remove(Constants.SWITCH_MODE_KEY);
            getActivity().finish();
        }
    }


    /**
     * 解决App重启后导致Fragment重叠的问题
     */


}
