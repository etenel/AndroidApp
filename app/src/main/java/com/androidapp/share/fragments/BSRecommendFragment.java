package com.androidapp.share.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.share.adapter.BsRecommendAdapter;
import com.androidapp.share.bean.BSRecommendBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/11.
 */

public class BSRecommendFragment extends BaseFragment {
    @BindView(R.id.recycle_re)
    RecyclerView recycleRe;
    private BsRecommendAdapter adapter;

    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.BAISIRECOMMEND_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                BSRecommendBean bsRecommendBean = JSON.parseObject(response, BSRecommendBean.class);
                List<BSRecommendBean.ListBean> list = bsRecommendBean.getList();
                adapter = new BsRecommendAdapter(list);
                recycleRe.setAdapter(adapter);
                recycleRe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public int getlayoutid() {

        return R.layout.fragment_bsrecommend;
    }

}
