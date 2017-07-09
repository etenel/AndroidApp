package com.androidapp.maganize;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.activity.Maganizetype;
import com.androidapp.activity.WebViewActivity2;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.maganize.adapter.MagnizeAdapter;
import com.androidapp.maganize.bean.MagnizeBean;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by etenel on 2017/7/6.
 */

public class MaganizeFragment extends BaseFragment {


    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.bar)
    RelativeLayout bar;
    @BindView(R.id.recycle)
    RecyclerView recycle;


    private MagnizeAdapter adapter;

    @Override
    public int getlayoutid() {
        return R.layout.fragment_maganize;
    }

    @Override
    public void initTitle() {
        super.initTitle();

    }

    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.LIANGCANG_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                MagnizeBean magnizeBean = JSON.parseObject(response, MagnizeBean.class);
                List<MagnizeBean.Data.Items.ProductBean> datas = magnizeBean.getData().getItems().getDatas();
                ArrayList keys = magnizeBean.getData().getItems().getKeys();
                LinearLayoutManager layoutMgr = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                adapter = new MagnizeAdapter(R.layout.item_mag, datas);
                recycle.setAdapter(adapter);
                recycle.setLayoutManager(layoutMgr);
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    Intent intent = new Intent(getContext(), WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, datas.get(position).getTopic_url());
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @OnClick(R.id.bar)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), Maganizetype.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }

    }
}
