package com.androidapp.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.ProductMessageAdapter;
import com.androidapp.product.model.bean.HomeHouseBean;
import com.androidapp.util.LogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListActivity extends AppCompatActivity {

    @BindView(R.id.recycle_msg)
    RecyclerView recycleMsg;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.price_selector)
    LinearLayout priceSelector;
    private List<HomeHouseBean.DataBean.ItemsBean> datas;
    private ProductMessageAdapter PAdapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_product_message);
        ButterKnife.bind(this);
        left.setImageResource(R.drawable.ease_back);
        initData();
    }

    private void initData() {
        url = getIntent().getStringExtra(Constants.PRODUCT_MSG);
        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {

            private GridLayoutManager gridLayoutManager;

            @Override
            public void onSuccess(String response) {
                LogUtils.e("TAG", "response" + response);
                HomeHouseBean homeHouseBean = JSON.parseObject(response, HomeHouseBean.class);
                datas = homeHouseBean.getData().getItems();
                Log.e("TAG", "uri=" + url);
                PAdapter = new ProductMessageAdapter(R.layout.item_proc_msg, datas);
                recycleMsg.setAdapter(PAdapter);

                gridLayoutManager = new GridLayoutManager(ProductListActivity.this, 2, GridLayoutManager.VERTICAL, false);
                //gridLayoutManager.setMeasuredDimension(10, 10);
                recycleMsg.setLayoutManager(gridLayoutManager);

                PAdapter.setOnItemClickListener((adapter, view, position) -> {
                    Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                    intent.putExtra(Constants.WEBURL, datas.get(position).getGoods_id());
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(String message) {

            }
        });
        recycleMsg.addItemDecoration(new RecyclerView.ItemDecoration() {

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

    }

    @OnClick(R.id.left)
    public void onViewClicked() {
        finish();
    }
}
