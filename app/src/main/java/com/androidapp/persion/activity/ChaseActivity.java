package com.androidapp.persion.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChaseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public String aid;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.swiperefersh)
    SwipeRefreshLayout swiperefersh;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chase);
        ButterKnife.bind(this);
        initview();
        loadata();
    }

    private void loadata() {
        WebSettings settings = webview.getSettings();
        webview.requestFocusFromTouch();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        LogUtils.e(url);
        webview.loadUrl(url);
    }

    private void initview() {
        aid = getIntent().getStringExtra(Constants.AID);
        url="http://share.acg.tv/flash.swf?aid="+aid;
        swiperefersh.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        swiperefersh.setColorSchemeResources(R.color.colorAccent);
        swiperefersh.setOnRefreshListener(this::loadata);
        swiperefersh.post(() -> {
            swiperefersh.setRefreshing(true);
        });
    }


}
