package com.androidapp.product.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.HomeAdapter;
import com.androidapp.product.model.bean.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by etenel on 2017/7/6.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.fl_ab)
    FloatingActionButton flAb;

    private HomeAdapter adapter;
    private List<HomeBean.DataBean.ItemsBean.ListBean> datas;

    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.HOME_PAGER_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                if (response != null && response.length() > 0) {
                    HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
                    datas = homeBean.getData().getItems().getList();
                    adapter = new HomeAdapter(datas);
                    recycle.setAdapter(adapter);
                    recycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public int getlayoutid() {
        return R.layout.item_home;
    }

    @OnClick(R.id.fl_ab)
    public void onViewClicked() {
        recycle.scrollToPosition(0);
    }
}
