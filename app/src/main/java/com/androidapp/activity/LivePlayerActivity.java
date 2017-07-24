package com.androidapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.persion.view.BiliDanmukuCompressionTools;
import com.androidapp.persion.view.CircleImageView;
import com.androidapp.persion.view.DanmakuParser;
import com.androidapp.persion.view.LoveLikeLayout;
import com.androidapp.util.BarUtils;
import com.androidapp.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.ui.widget.DanmakuView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class LivePlayerActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    SurfaceView videoView;

    @BindView(R.id.bili_anim)
    ImageView mAnimView;

    @BindView(R.id.right_play)
    ImageView mRightPlayBtn;

    @BindView(R.id.bottom_layout)
    RelativeLayout mBottomLayout;

    @BindView(R.id.bottom_play)
    ImageView mBottomPlayBtn;

    @BindView(R.id.bottom_fullscreen)
    ImageView mBottomFullscreen;

    @BindView(R.id.video_start_info)
    TextView mLoadTv;

    @BindView(R.id.user_pic)
    CircleImageView mUserPic;

    @BindView(R.id.user_name)
    TextView mUserName;

    @BindView(R.id.live_num)
    TextView mLiveNum;

    @BindView(R.id.love_layout)
    LoveLikeLayout mLoveLikeLayout;

    @BindView(R.id.bottom_love)
    ImageView mlove;
    @BindView(R.id.live_layout)
    FrameLayout liveLayout;
    @BindView(R.id.user_info_layout)
    RelativeLayout userInfoLayout;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.danmaku)
    DanmakuView danmaku;

    private IjkMediaPlayer ijkMediaPlayer;

    private SurfaceHolder holder;

    private int flag = 0;

    private boolean isPlay = false;

    private AnimationDrawable mAnimViewBackground;

    private int cid;

    private String title;

    private int online;

    private String face;

    private String name;

    private int mid;
    public String playurl;
    public int height;
    public int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.hideStatusBar(this);
        setContentView(R.layout.activity_live_player);
        ButterKnife.bind(this);

        initViews();
    }


    public void initViews() {

        Intent intent = getIntent();
        if (intent != null) {
            cid = intent.getIntExtra(Constants.EXTRA_CID, 0);
            title = intent.getStringExtra(Constants.EXTRA_TITLE);
            online = intent.getIntExtra(Constants.EXTRA_ONLINE, 0);
            face = intent.getStringExtra(Constants.EXTRA_FACE);
            name = intent.getStringExtra(Constants.EXTRA_NAME);
            mid = intent.getIntExtra(Constants.EXTRA_MID, 0);
            playurl = intent.getStringExtra(Constants.EXTRA_PLAYURL);
        }
        startAnim();
        initVideo();
        initUserInfo();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DownloadxmlUtils.DownloadFiles();
//            }
//        }).start();


    }


    /**
     * 设置用户信息
     */
    private void initUserInfo() {
        Glide.with(LivePlayerActivity.this)
                .load(face)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ico_user_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mUserPic);

        mUserName.setText(name);
        mLiveNum.setText(String.valueOf(online));
    }


    private void startAnim() {

        mAnimViewBackground = (AnimationDrawable) mAnimView.getBackground();
        mAnimViewBackground.start();
    }


    private void stopAnim() {
        mAnimViewBackground = (AnimationDrawable) mAnimView.getBackground();
        mAnimViewBackground.stop();
        mAnimView.setVisibility(View.GONE);
        mLoadTv.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initVideo() {

        holder = videoView.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
        playVideo(playurl);
    }

    private void playVideo(String uri) {

        try {

            ijkMediaPlayer.setDataSource(this, Uri.parse(uri));
            ijkMediaPlayer.setDisplay(holder);
            holder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }


                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    ijkMediaPlayer.setDisplay(holder);
                }


                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
            stopAnim();
            videoView.setVisibility(View.VISIBLE);
            isPlay = true;
            mRightPlayBtn.setImageResource(R.drawable.ic_tv_stop);
            mBottomPlayBtn.setImageResource(R.drawable.ic_portrait_stop);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ijkMediaPlayer.setKeepInBackground(false);
    }


    private void startBottomShowAnim() {

        mBottomLayout.setVisibility(View.VISIBLE);
        mRightPlayBtn.setVisibility(View.VISIBLE);
    }


    private void startBottomHideAnim() {

        mBottomLayout.setVisibility(View.GONE);
        mRightPlayBtn.setVisibility(View.GONE);
    }


    public static void launch(Activity activity, int cid, String title, int online, String face, String name, int mid, String playurl) {

        Intent mIntent = new Intent(activity, LivePlayerActivity.class);
        mIntent.putExtra(Constants.EXTRA_CID, cid);
        mIntent.putExtra(Constants.EXTRA_TITLE, title);
        mIntent.putExtra(Constants.EXTRA_ONLINE, online);
        mIntent.putExtra(Constants.EXTRA_FACE, face);
        mIntent.putExtra(Constants.EXTRA_NAME, name);
        mIntent.putExtra(Constants.EXTRA_MID, mid);
        mIntent.putExtra(Constants.EXTRA_PLAYURL, playurl);
        activity.startActivity(mIntent);
    }


    @OnClick(R.id.right_play)
    void rightPlay() {

        ControlVideo();
    }


    @OnClick(R.id.bottom_play)
    void bottomPlay() {

        ControlVideo();
    }

    private boolean isFullScreen = false;

    @OnClick(R.id.bottom_fullscreen)
    void fullScreen() {
        if (!isFullScreen) {
            height = videoView.getHeight();
            width = videoView.getWidth();
            int screenHeigh = ScreenUtils.getScreenHeight();
            int screenWidth = ScreenUtils.getScreenWidth();
            ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
            layoutParams.height = screenWidth;
            //llLayout.setVisibility(View.GONE);
            layoutParams.width = screenHeigh;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            // svDanmaku.setVisibility(View.GONE);
            userInfoLayout.setVisibility(View.GONE);
            // svDanmakuVertical.setVisibility(View.VISIBLE);
            setDanmaku();
            danmaku.show();
            danmaku.setVisibility(View.VISIBLE);

            view.setVisibility(View.GONE);
            isFullScreen = true;
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isFullScreen = false;
            view.setVisibility(View.VISIBLE);
//            llLayout.setVisibility(View.VISIBLE);
//            svDanmaku.setVisibility(View.VISIBLE);
            userInfoLayout.setVisibility(View.VISIBLE);
            //  svDanmakuVertical.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = width;
            danmaku.hide();
            danmaku.stop();
            danmaku.setVisibility(View.GONE);
        }
    }

    private void setDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://comment.bilibili.tv/20458.xml";
                    HttpConnection.Response rsp = (HttpConnection.Response)
                            Jsoup.connect(url).timeout(20000).execute();
                    InputStream stream = new ByteArrayInputStream(BiliDanmukuCompressionTools.
                            decompressXML(rsp.bodyAsBytes()));

                    danmaku.prepare(createParser(stream), DanmakuContext.create());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // danmaku.prepare(createParser(this.getResources().openRawResource(R.raw.comments)),DanmakuContext.create());

        danmaku.enableDanmakuDrawingCache(true);
        danmaku.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                danmaku.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
    }


    @OnClick(R.id.video_view)
    void showBottomLayout() {

        if (flag == 0) {
            startBottomShowAnim();
            flag = 1;
        } else {
            startBottomHideAnim();
            flag = 0;
        }
    }

    @OnClick(R.id.bottom_love)
    void clickLove() {

        mLoveLikeLayout.addLove();
    }


    private void ControlVideo() {

        if (isPlay) {
            ijkMediaPlayer.pause();
            isPlay = false;
            mRightPlayBtn.setImageResource(R.drawable.ic_tv_play);
            mBottomPlayBtn.setImageResource(R.drawable.ic_portrait_play);
        } else {
            ijkMediaPlayer.start();
            isPlay = true;
            mRightPlayBtn.setImageResource(R.drawable.ic_tv_stop);
            mBottomPlayBtn.setImageResource(R.drawable.ic_portrait_stop);
        }
    }

    private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new DanmakuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

//    private BaseDanmakuParser createParser(String url) {
//
//        if (url == null) {
//            return new BaseDanmakuParser() {
//
//                @Override
//                protected Danmakus parse() {
//                    return new Danmakus();
//                }
//            };
//        }
//
//        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
//
//        try {
//            loader.load(url);
//        } catch (IllegalDataException e) {
//            e.printStackTrace();
//        }
//        BaseDanmakuParser parser = new DanmakuParser();
//        IDataSource<?> dataSource = loader.getDataSource();
//        parser.load(dataSource);
//        return parser;
//
//    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        ijkMediaPlayer.release();
        if (danmaku != null) {
            danmaku.release();
            danmaku = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmaku != null && danmaku.isPrepared()) {
            danmaku.pause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmaku != null && danmaku.isPrepared() && danmaku.isPaused()) {
            danmaku.stop();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (danmaku != null) {
            danmaku.release();
            danmaku = null;
        }
    }
}
