package com.androidapp.persion.fragments;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.persion.PersionFragment;
import com.androidapp.persion.view.CircleImageView;
import com.androidapp.persion.view.NoScrollViewPager;
import com.androidapp.product.model.adapter.ViewPagerAdapter;
import com.androidapp.util.ToastUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/19.
 */

public class BilibiliFragment extends BaseFragment {
    @BindView(R.id.toolbar_user_avatar)
    CircleImageView toolbarUserAvatar;
    @BindView(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sliding_tabs)
    TabLayout slidingTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private ViewPagerAdapter viewPagerAdapter;
    private List<Fragment> fragments;
    private LiveBroadcastFragment liveFragment;
    private RecommendFragment recommendFragment;
    private ChaseFragment chaseFragment;
    private DynamicFragment dynamicFragment;
    private PartitionFragment partitionFragment;
    private FindFragment findFragment;
    private String[] tags = new String[]{
            "直播", "推荐", "追番", "分区", "动态", "发现"
    };
    private PersionFragment persionFragment = new PersionFragment();

    @Override
    public void initview() {
        super.initview();
        //初始化searchbar
        searchView.setVoiceSearch(true);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void initData() {
        initFragments();
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, tags);
        viewPager.setAdapter(viewPagerAdapter);
        slidingTabs.setupWithViewPager(viewPager);
        slidingTabs.setTabTextColors(Color.LTGRAY, Color.WHITE);
        slidingTabs.setSelectedTabIndicatorColor(Color.WHITE);
    }

    private void initFragments() {
        liveFragment = new LiveBroadcastFragment();
        chaseFragment = new ChaseFragment();
        dynamicFragment = new DynamicFragment();
        recommendFragment = new RecommendFragment();
        partitionFragment = new PartitionFragment();
        findFragment = new FindFragment();
        fragments = new ArrayList<>();
        fragments.add(liveFragment);
        fragments.add(recommendFragment);
        fragments.add(chaseFragment);
        fragments.add(partitionFragment);
        fragments.add(dynamicFragment);
        fragments.add(findFragment);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.id_action_search);
        searchView.setMenuItem(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_game:
                //游戏中心
                //  startActivity(new Intent(getActivity(), GameCentreActivity.class));
                ToastUtils.showShort("游戏中心");
                break;

            case R.id.id_action_download:
                //离线缓存
                //startActivity(new Intent(getActivity(), OffLineDownloadActivity.class));
                ToastUtils.showShort("离线缓存");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_bili;
    }


    public boolean isOpenSearchView() {

        return searchView.isSearchOpen();
    }


    public void closeSearchView() {

        searchView.closeSearch();
    }
}
