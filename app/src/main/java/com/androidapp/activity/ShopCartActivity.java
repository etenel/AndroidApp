package com.androidapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.androidapp.R;
import com.androidapp.util.BarUtils;

public class ShopCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        setContentView(R.layout.activity_shop_cart);
    }
}
