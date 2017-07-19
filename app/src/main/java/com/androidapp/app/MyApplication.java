package com.androidapp.app;

import android.database.sqlite.SQLiteDatabase;

import com.androidapp.BuildConfig;
import com.androidapp.util.BaseApplication;
import com.androidapp.util.CrashUtils;
import com.androidapp.util.LogUtils;
import com.androidapp.util.Utils;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.mob.MobSDK;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * Created by etenel on 2017/7/10.
 */

public class MyApplication extends BaseApplication {
    private SQLiteDatabase db;
    private static MyApplication myApplication;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        ZXingLibrary.initDisplayOpinion(this);
        setDatabase();
        Utils.init(this);
        initLog();
        initCrash();
        MobSDK.init(this, this.a(), this.b());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        //初始化环信sdk
        EMOptions options = new EMOptions();//配置一些功能
        //是否自动接受群组邀请
        options.setAutoAcceptGroupInvitation(false);
        //是否自动接受邀请
        options.setAcceptInvitationAlways(false);
        EaseUI.getInstance().init(this,options);
        JPushInterface.init(this);
    }
public static MyApplication getApplication(){
        return myApplication;
    }
    private void setDatabase() {
        // 通过DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为greenDAO 已经帮你做了。
        // 注意：默认的DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper=new DaoMaster.DevOpenHelper(this,"shop_cart.db",null);
        db=mHelper.getWritableDatabase();
        // 注意：该数据库连接属于DaoMaster，所以多个 Session 指的是相同的数据库连接
        mDaoMaster=new DaoMaster(db);
        mDaoSession=mDaoMaster.newSession();
    }
public DaoSession getmDaoSession(){
    return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }
    public static void initLog() {
        LogUtils.Builder builder = new LogUtils.Builder()
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(LogUtils.V);// log文件过滤器，和logcat过滤器同理，默认Verbose
        LogUtils.d(builder.toString());
    }

    private void initCrash() {
        CrashUtils.init();
    }
}
