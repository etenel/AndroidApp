package com.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.UserInfo;
import com.androidapp.app.MyApplication;
import com.androidapp.mainactivity.MainActivity;
import com.bumptech.glide.Glide;
import com.greendao.gen.UserInfoDao;
import com.hyphenate.chat.EMClient;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
    private ImageView image;
    private Disposable disposable;
    private UserInfoDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image = (ImageView) findViewById(R.id.image);
        dao = MyApplication.getApplication().getmDaoSession().getUserInfoDao();
        initView();

    }

    private void initView() {
        Glide.with(this).load(R.drawable.loading_start).asGif().into(image);
        startLogin();
    }
    private void startLogin() {

        Observable.timer(4700, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(aLong -> selectChageActivity());
    }

    private void selectChageActivity() {
        Observable.just(EMClient.getInstance().isLoggedInBefore())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        Log.e("TAG",aBoolean+"");
                        if (aBoolean == true) {
                            String currentUser= EMClient.getInstance().getCurrentUser();
                        dao.insert(new UserInfo(currentUser,currentUser));
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        } else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }
                        finish();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
