package com.androidapp.maganize.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.androidapp.R;
import com.androidapp.activity.MaginazeActivity;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.maganize.adapter.AuthorAdapter;
import com.androidapp.maganize.bean.AuthorBean;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;

import java.util.List;

import butterknife.BindView;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by etenel on 2017/7/9.
 */

public class AuthorFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private AuthorAdapter adapter;
    private List<AuthorBean.DataBean.ItemsBean> datas;
    private String[] urls = Constants.MAGINZE_AUTHORS;

    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.MAGAZINE_LIST_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                AuthorBean authorBean = parseObject(response, AuthorBean.class);
                datas = authorBean.getData().getItems();
                adapter = new AuthorAdapter(R.layout.item_author, datas);
                recycle.setAdapter(adapter);
                recycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    if (position < urls.length) {
                        String url = urls[position];
                        Intent intent = new Intent(getContext(), MaginazeActivity.class);
                        intent.putExtra(Constants.MAGINIZEdetail, url);
                        intent.putExtra(Constants.MAGAUTHORNAME, datas.get(position).getAuthor_name());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                        } else {
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getContext(), "isnull", Toast.LENGTH_SHORT).show();
                    }

                });
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public int getlayoutid() {
        return R.layout.author;
    }


}
