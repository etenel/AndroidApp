package com.androidapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRActicity extends AppCompatActivity {

    @BindView(R.id.qr_image)
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qracticity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String image = getIntent().getStringExtra(Constants.REQUEST_QR);
        LogUtils.e(image);
        Glide.with(this).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Bitmap bitmap = CodeUtils.createImage(image, 800, 800, resource);
                qrImage.setImageBitmap(bitmap);
            }
        });
    }
}
