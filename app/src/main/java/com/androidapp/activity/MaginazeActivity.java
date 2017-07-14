package com.androidapp.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.maganize.adapter.MagnizeAdapter;
import com.androidapp.maganize.bean.MagnizeBean;
import com.androidapp.nethelper.NetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.androidapp.util.Utils.getContext;


public class MaginazeActivity extends AppCompatActivity {

    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.bar)
    RelativeLayout bar;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private MagnizeAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_maginaze);
        ButterKnife.bind(this);
        initTitle();

        initData();

    }

    private void initTitle() {
        String name = getIntent().getStringExtra(Constants.MAGAUTHORNAME);
        mid.setText("杂志-" + name);
        left.setVisibility(View.GONE);
    }

    private void initData() {
        url = getIntent().getStringExtra(Constants.MAGINIZEdetail);
        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                MagnizeBean magnizeBean = JSON.parseObject(response, MagnizeBean.class);
                List<MagnizeBean.Data.Items.ProductBean> datas = magnizeBean.getData().getItems().getDatas();
                LinearLayoutManager layoutMgr = new LinearLayoutManager(MaginazeActivity.this, LinearLayoutManager.VERTICAL, false);
                adapter = new MagnizeAdapter(R.layout.item_mag, datas);
                recycle.setAdapter(adapter);
                recycle.setLayoutManager(layoutMgr);
                adapter.setOnItemClickListener((adapter1, view, position) -> {
                    Intent intent = new Intent(MaginazeActivity.this, WebViewActivity2.class);
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
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
