package com.androidapp.product.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.activity.WebViewActivity2;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.TopicAdapter;
import com.androidapp.product.model.bean.TopicBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/6.
 */

public class TopicFragment extends BaseFragment {
    @BindView(R.id.recycle_list)
    RecyclerView recycleList;
    private List<TopicBean.DataBean.ItemsBean> datas;
    private TopicAdapter adapter;

    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.TOPIC_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                if (response != null && !response.isEmpty()) {
                    TopicBean topicBean = JSON.parseObject(response, TopicBean.class);
                    datas = topicBean.getData().getItems();
                    adapter = new TopicAdapter(R.layout.topic_item, datas);
                    recycleList.setAdapter(adapter);
                    recycleList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapter.setOnItemClickListener((adapter1, view, position) -> {
                        Intent intent = new Intent(getContext(), WebViewActivity2.class);
                        intent.putExtra(Constants.TOPIC, datas.get(position).getTopic_url());
                        startActivity(intent);
                    });
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public int getlayoutid() {

        return R.layout.item_topic;
    }

}
