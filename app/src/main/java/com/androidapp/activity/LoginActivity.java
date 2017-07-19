package com.androidapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.R;
import com.androidapp.UserInfo;
import com.androidapp.app.MyApplication;
import com.androidapp.mainactivity.MainActivity;
import com.androidapp.util.ToastUtils;
import com.greendao.gen.UserInfoDao;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.douban)
    ImageView douban;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.weixin)
    ImageView weixin;
    private UserInfoDao dao;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.bt_login_regist)
    Button btLoginRegist;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dao = MyApplication.getApplication().getmDaoSession().getUserInfoDao();
    }

    @OnClick({R.id.bt_login_regist, R.id.bt_login_login, R.id.douban, R.id.qq, R.id.weibo, R.id.weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_regist:
                String reusername = etLoginName.getText().toString().trim();
                String repwd = etLoginPwd.getText().toString().trim();
                //校验
                if (TextUtils.isEmpty(repwd) || TextUtils.isEmpty(reusername)) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(reusername, repwd);
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show());
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                        }
                    }
                }).start();


                break;
            case R.id.bt_login_login:
                String username = etLoginName.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                //校验
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    ToastUtils.showShort("用户名或或者密码不能为空");
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().login(username, pwd, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                String currentUser = EMClient.getInstance().getCurrentUser();
                                dao.insert(new UserInfo(currentUser, currentUser));
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                runOnUiThread(() -> {
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                });

                            }

                            @Override
                            public void onError(int i, String s) {
                                runOnUiThread(() -> ToastUtils.showShort(s));
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }

                        });
                    }
                }).start();
                break;
            case R.id.douban:
                Toast.makeText(LoginActivity.this, "weixin", Toast.LENGTH_SHORT).show();

                break;
            case R.id.qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
            case R.id.weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weibo.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
            case R.id.weixin:
Toast.makeText(LoginActivity.this, "weixin", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
