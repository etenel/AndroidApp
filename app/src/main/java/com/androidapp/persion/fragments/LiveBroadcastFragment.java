package com.androidapp.persion.fragments;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.persion.adapter.LiveAdapter;
import com.androidapp.persion.bean.LiveBean;
import com.androidapp.persion.view.CustomEmptyView;
import com.androidapp.util.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/20.
 */

public class LiveBroadcastFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    @BindView(R.id.swiperefersh)
    SwipeRefreshLayout swiperefersh;


    //headView
    private com.youth.banner.Banner item_live_banner;
    private LinearLayout ll_attention;
    private ImageView attent_image;
    private TextView attent_text;
    private LinearLayout ll_live;
    private ImageView live_image;
    private TextView live_text;
    private LinearLayout ll_search;
    private ImageView search_image;
    private TextView search_text;
    private LinearLayout ll_all_type;
    private ImageView alltype_image;
    private TextView alltype_text;

    private LiveAdapter adapter;
    private List<LiveBean.DataBean.PartitionsBean> datas;

    @Override
    public void initData() {
        swiperefersh.setOnRefreshListener(this);
    }

    @Override
    public void initview() {
        loadData();
    }


    private void initEmptyView() {
//
        swiperefersh.setRefreshing(false);
        emptyLayout.setVisibility(View.VISIBLE);
        recycle.setVisibility(View.GONE);
        emptyLayout.setEmptyImage(R.drawable.img_tips_error_load_error);
        emptyLayout.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        SnackbarUtils.with(recycle).setMessage("数据加载失败,请重新加载或者检查网络是否链接").show();
    }

    public void hideEmptyView() {
        emptyLayout.setVisibility(View.GONE);
        recycle.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        NetUtils.getInstance().get(NetConfig.BILIBILILIVE_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                swiperefersh.setRefreshing(false);
                LiveBean liveBean = JSON.parseObject(response, LiveBean.class);
                List<LiveBean.DataBean.BannerBean> banner = liveBean.getData().getBanner();
                datas = liveBean.getData().getPartitions();
                List<String> images = new ArrayList<>();
                if(banner!=null&&banner.size()>0) {
                    for (int i = 0; i < banner.size(); i++) {
                        images.add(banner.get(i).getImg());
                    }
                }

                hideEmptyView();
                adapter = new LiveAdapter(getContext(),images);
                adapter.setLiveInfo(liveBean);
                recycle.setAdapter(adapter);
                initRecycleView();
                recycle.scrollToPosition(0);
            }

            @Override
            public void onFailure(String message) {
                initEmptyView();
            }
        });
    }


    private void initRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 12);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getSpanSize(position);
            }
        });
        recycle.setLayoutManager(layoutManager);
    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_lives;
    }

    @Override
    public void onRefresh() {
        swiperefersh.setColorSchemeResources(R.color.colorAccent);
        swiperefersh.setOnRefreshListener(this::loadData);
        swiperefersh.post(() -> {
            swiperefersh.setRefreshing(false);
        });
    }
}
