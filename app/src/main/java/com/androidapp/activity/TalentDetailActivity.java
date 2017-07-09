package com.androidapp.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.talent.adapter.TalentAttentAdapter;
import com.androidapp.talent.adapter.TalentFansAdapter;
import com.androidapp.talent.adapter.TalentLikeAdapter;
import com.androidapp.talent.adapter.TalentRecommendAdapter;
import com.androidapp.talent.bean.AttentionsBean;
import com.androidapp.talent.bean.FansBean;
import com.androidapp.talent.bean.LikeBean;
import com.androidapp.talent.bean.RecommendBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalentDetailActivity extends AppCompatActivity {


    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.bar)
    RelativeLayout bar;
    @BindView(R.id.talent_author_iv)
    ImageView talentAuthorIv;
    @BindView(R.id.topic_author_name)
    TextView topicAuthorName;
    @BindView(R.id.topic_author_desc)
    TextView topicAuthorDesc;
    @BindView(R.id.ll_like)
    RadioButton llLike;
    @BindView(R.id.ll_recommend)
    RadioButton llRecommend;
    @BindView(R.id.ll_attention)
    RadioButton llAttention;
    @BindView(R.id.ll_fans)
    RadioButton llFans;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.rgp)
    RadioGroup rgp;
    private LikeBean.DataBean.ItemsBean data;
    private String url;
    private TalentLikeAdapter likeAdapter;
    private TalentRecommendAdapter recommendAdapter;
    private TalentAttentAdapter attentAdapter;
    private TalentFansAdapter fansAdapter;
    private String useid;
    public LikeBean likeBean;
    public RecommendBean recommendBean;
    public AttentionsBean attentionsBean;
    public FansBean fansBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_detail);
        ButterKnife.bind(this);
        getData();
        initData();

    }

    private void getData() {
        useid = (String) getIntent().getSerializableExtra(Constants.TALENTDETAIL);
        NetUtils.getInstance().get(NetConfig.EXPERT_LIKE_URL + useid, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                LikeBean bean = JSON.parseObject(response, LikeBean.class);
                data = bean.getData().getItems();
                mid.setText(data.getUser_name());
                topicAuthorName.setText(data.getUser_name());
                if (!TextUtils.isEmpty(data.getUser_desc())) {
                    topicAuthorDesc.setText(data.getUser_desc());
                }
                Glide.with(TalentDetailActivity.this).load(data.getUser_image().getOrig()).into(talentAuthorIv);
                llRecommend.setText(String.format(llRecommend.getText().toString(), data.getRecommendation_count()));
                llFans.setText(String.format(llFans.getText().toString(), data.getFollowed_count()));
                llAttention.setText(String.format(llAttention.getText().toString(), data.getFollowing_count()));
                llLike.setText(String.format(llLike.getText().toString(), data.getLike_count()));
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void initData() {
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                //获得当前item的位置
                int position = parent.getChildAdapterPosition(view);
                //根据position确定item需要留出的位置
                switch (position % 2) {
                    case 0:
                        //位于左侧的item
                        outRect.right = 20;
                        outRect.left = 20;
                        break;
                    case 1:
                        //位于右侧的item
                        outRect.left = 20;
                        outRect.right = 20;
                        break;
                    default:
                        break;
                }
                //设置底部边距
                outRect.bottom = 20;
                outRect.top = 20;
            }
        });
        rgp.setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.ll_like:
                    url = NetConfig.EXPERT_LIKE_URL + useid;
                    NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String response) {
                            likeBean = JSON.parseObject(response, LikeBean.class);
                            int i = Integer.parseInt(likeBean.getData().getItems().getLike_count());
                            if (i != 0) {
                                LogUtils.e("TAG", "like=" + i);
                                List<LikeBean.DataBean.ItemsBean.GoodsBean> goods = likeBean.getData().getItems().getGoods();
                                likeAdapter = new TalentLikeAdapter(R.layout.item_like, goods);
                                recycle.setAdapter(likeAdapter);
                                recycle.setLayoutManager(new GridLayoutManager(TalentDetailActivity.this, 3, GridLayoutManager.VERTICAL, false));
                                likeAdapter.setOnItemClickListener((adapter, view12, position) -> {
                                    Intent intent = new Intent(TalentDetailActivity.this, WebViewActivity.class);
                                    intent.putExtra(Constants.WEBURL, goods.get(position).getGoods_id());
                                    startActivity(intent);
                                });
                            } else {
                                return;
                            }
                        }

                        @Override
                        public void onFailure(String message) {

                        }
                    });
                    break;
                case R.id.ll_recommend:
                    url = NetConfig.EXPERT_INFR_URL + useid;
                    NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String response) {
                            recommendBean = JSON.parseObject(response, RecommendBean.class);
                            int i = Integer.parseInt(recommendBean.getData().getItems().getRecommendation_count());
                            if (i != 0) {
                                LogUtils.e("TAG", "recommend" + i);
                                List<RecommendBean.DataBean.ItemsBean.GoodsBean> goods = recommendBean.getData().getItems().getGoods();
                                recommendAdapter = new TalentRecommendAdapter(R.layout.item_like, goods);
                                recycle.setAdapter(recommendAdapter);
                                recycle.setLayoutManager(new GridLayoutManager(TalentDetailActivity.this, 3, GridLayoutManager.VERTICAL, false));
                                recommendAdapter.setOnItemClickListener((adapter, view13, position) -> {
                                    Intent intent = new Intent(TalentDetailActivity.this, WebViewActivity.class);
                                    intent.putExtra(Constants.WEBURL, goods.get(position).getGoods_id());
                                    startActivity(intent);
                                });
                            } else {
                                return;
                            }
                        }

                        @Override
                        public void onFailure(String message) {

                        }
                    });
                    break;
                case R.id.ll_attention:
                    url = NetConfig.EXPERT_ATTENTION_URL + useid + NetConfig.ATTENTION_2;
                    NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String response) {
                            attentionsBean = JSON.parseObject(response, AttentionsBean.class);
                            int i = Integer.parseInt(attentionsBean.getData().getItems().getFollowing_count());
                            if (i != 0) {
                                List<AttentionsBean.DataBean.ItemsBean.UsersBean> users = attentionsBean.getData().getItems().getUsers();
                                attentAdapter = new TalentAttentAdapter(R.layout.item_like, users);
                                recycle.setAdapter(attentAdapter);
                                recycle.setLayoutManager(new GridLayoutManager(TalentDetailActivity.this, 3, GridLayoutManager.VERTICAL, false));
                                attentAdapter.setOnItemClickListener((adapter, view1, position) -> {
                                    Intent intent = new Intent(TalentDetailActivity.this, TalentDetailActivity.class);
                                    intent.putExtra(Constants.TALENTDETAIL, users.get(position).getUser_id());
                                    startActivity(intent);
                                });
                            } else {
                                return;
                            }
                        }

                        @Override
                        public void onFailure(String message) {

                        }
                    });
                    break;
                case R.id.ll_fans:
                    url = NetConfig.EXPERT_FANS_URL + useid + NetConfig.FANS2;
                    NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String response) {
                            fansBean = JSON.parseObject(response, FansBean.class);
                            int i = Integer.parseInt(fansBean.getData().getItems().getFollowed_count());
                            if (i != 0) {
                                List<FansBean.DataBean.ItemsBean.UsersBean> users = fansBean.getData().getItems().getUsers();
                                fansAdapter = new TalentFansAdapter(R.layout.item_like, users);
                                recycle.setAdapter(fansAdapter);
                                recycle.setLayoutManager(new GridLayoutManager(TalentDetailActivity.this, 3, GridLayoutManager.VERTICAL, false));
                                fansAdapter.setOnItemClickListener((adapter, view, position) -> {
                                    Intent intent = new Intent(TalentDetailActivity.this, TalentDetailActivity.class);
                                    intent.putExtra(Constants.TALENTDETAIL, users.get(position).getUser_id());
                                    startActivity(intent);
                                });
                            } else {
                                return;
                            }
                        }

                        @Override
                        public void onFailure(String message) {

                        }
                    });
                    break;
            }
        });
        rgp.check(R.id.ll_recommend);
    }

    @OnClick(R.id.left)
    public void onViewClicked() {
        finish();
    }
}
