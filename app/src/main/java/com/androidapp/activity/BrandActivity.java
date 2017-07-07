package com.androidapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.BrandDetailAdapter;
import com.androidapp.product.model.bean.BrandDetailBean;
import com.androidapp.util.ImageUtils;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandActivity extends AppCompatActivity {

    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.brand_logo)
    ImageView brandLogo;
    @BindView(R.id.brand_name)
    TextView brandName;
    @BindView(R.id.brand_story_tv)
    TextView brandStoryTv;
    @BindView(R.id.brand_goods_tv)
    TextView brandGoodsTv;
    @BindView(R.id.brand_story_content_tv)
    TextView brandStoryContentTv;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.right)
    ImageButton right;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private String url;
    private List<BrandDetailBean.DataBean.ItemsBean> datas;
    private String imageurl;
    private BrandDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(Constants.BRANDDETAIL);
        imageurl = getIntent().getStringExtra(Constants.BRANDIMAGE);
        LogUtils.e("TAG", url);
        initData();
        initView();

    }

    private void initdetail() {
        if (datas != null && datas.size() > 0) {
            adapter = new BrandDetailAdapter(R.layout.item_proc_msg, datas);
            recycle.setAdapter(adapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(BrandActivity.this, 2, GridLayoutManager.VERTICAL, false);
            recycle.setLayoutManager(gridLayoutManager);
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
                            outRect.right = 40;

                            break;
                        case 1:
                            //位于右侧的item
                            outRect.left = 40;

                            break;
                        default:
                            break;
                    }
                    //设置底部边距
                    outRect.bottom = 20;
                    outRect.top = 20;
                }
            });
            adapter.setOnItemClickListener((adapter1, view, position) -> {
                Intent intent = new Intent(BrandActivity.this, WebViewActivity.class);
                intent.putExtra(Constants.WEBURL, datas.get(position).getGoods_id());
                startActivity(intent);
            });
        }

    }

    private void initData() {
        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                BrandDetailBean brandDetailBean = JSON.parseObject(response, BrandDetailBean.class);
                datas = brandDetailBean.getData().getItems();
                LogUtils.e("TAG", brandDetailBean.getMeta().getAccount_id());
                LogUtils.e("TAG", brandDetailBean.getData().getNum_items());
                if (datas.size() > 0) {
                    initdetail();
                    brandStoryContentTv.setText(datas.get(0).getBrand_info().getBrand_desc());
                }
                Glide.with(BrandActivity.this).asBitmap().load(imageurl).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Bitmap bitmap = ImageUtils.toRound(resource);
                        brandLogo.setImageBitmap(bitmap);
                    }
                });


            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void initView() {
        left.setImageResource(R.drawable.ease_back);
        right.setVisibility(View.GONE);
    }

    @OnClick(R.id.left)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.brand_story_tv, R.id.brand_goods_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.brand_story_tv:
                brandStoryContentTv.setVisibility(View.VISIBLE);
                recycle.setVisibility(View.GONE);
                break;
            case R.id.brand_goods_tv:
                brandStoryContentTv.setVisibility(View.GONE);
                recycle.setVisibility(View.VISIBLE);
                break;
        }
    }
}
