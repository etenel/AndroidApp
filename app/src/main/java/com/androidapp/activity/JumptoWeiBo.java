package com.androidapp.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidapp.R;

public class JumptoWeiBo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        //知道要跳转应用的包名、类名
        ComponentName componentName = new ComponentName("com.sina.weibo", "com.sina.weibo.SplashActivity");
        intent.setComponent(componentName);
        startActivity(intent);
        finish();
        setContentView(R.layout.activity_jumpto_wei_bo);
    }
}
