package com.androidapp.activity;

import android.os.Bundle;
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

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initWeb();
    }

    private void initWeb() {
        //url = NetConfig.BRAND_GOODS_DETAILS_URL+getIntent().getStringExtra(Constants.WEBURL);
        url = "http://www.iliangcang.com/i/goods/?id="+getIntent().getStringExtra(Constants.WEBURL);
        LogUtils.e("TAG", url);
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
       // http://www.iliangcang.com/i/goods/?id=257430
        webview.loadUrl(url);
    }
}
