package com.androidapp.product.model.adapter;

import android.content.Intent;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.activity.WebViewActivity2;
import com.androidapp.constant.Constants;
import com.androidapp.product.model.bean.HomeBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

/**
 * Created by etenel on 2017/7/7.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeBean.DataBean.ItemsBean.ListBean, BaseViewHolder> {


    public HomeAdapter(List<HomeBean.DataBean.ItemsBean.ListBean> datas) {
        super(datas);
        setMultiTypeDelegate(new MultiTypeDelegate<HomeBean.DataBean.ItemsBean.ListBean>() {
            @Override
            protected int getItemType(HomeBean.DataBean.ItemsBean.ListBean listBean) {
                return Integer.parseInt(listBean.getHome_type());
            }
        });
        getMultiTypeDelegate().registerItemType(1, R.layout.home_item1)
                .registerItemType(6, R.layout.home_item1)
                .registerItemType(2, R.layout.home_item2)
                .registerItemType(4, R.layout.home_item4);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ItemsBean.ListBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                Glide.with(mContext).load(item.getOne().getPic_url()).into((ImageView) helper.getView(R.id.image_home));
                LogUtils.e("TAG", "url=" + item.getOne().getTopic_url());
                LogUtils.json("TAG", item.getOne().getTopic_name());
                helper.getView(R.id.image_home).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getOne().getTopic_url());

                    mContext.startActivity(intent);
                });
                break;
            case 6:
                Glide.with(mContext).load(R.drawable.brand_bg).into((ImageView) helper.getView(R.id.image_home));
//                LogUtils.e("TAG", "url=" + item.getOne().getTopic_url());
//                helper.getView(R.id.image_home).setOnClickListener(view -> {
//                    Intent intent = new Intent(mContext, WebViewActivity2.class);
//                    intent.putExtra(Constants.TOPIC, item.getOne().getTopic_url());
//                    mContext.startActivity(intent);
//                });
                break;
            case 2:
                Glide.with(mContext).load(item.getOne().getPic_url()).into((ImageView) helper.getView(R.id.home2_image1));
                Glide.with(mContext).load(item.getTwo().getPic_url()).into((ImageView) helper.getView(R.id.home2_image2));
                helper.getView(R.id.home2_image1).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getOne().getTopic_url());
                    mContext.startActivity(intent);
                });
                helper.getView(R.id.home2_image2).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getTwo().getTopic_url());
                    mContext.startActivity(intent);
                });

                break;
            case 4:
                Glide.with(mContext).load(item.getOne().getPic_url()).into((ImageView) helper.getView(R.id.home4_image1));
                Glide.with(mContext).load(item.getTwo().getPic_url()).into((ImageView) helper.getView(R.id.home4_image2));
                Glide.with(mContext).load(item.getThree().getPic_url()).into((ImageView) helper.getView(R.id.home4_image3));
                Glide.with(mContext).load(item.getFour().getPic_url()).into((ImageView) helper.getView(R.id.home4_image4));
                helper.getView(R.id.home4_image1).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getOne().getTopic_url());
                    mContext.startActivity(intent);
                });
                helper.getView(R.id.home4_image2).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getTwo().getTopic_url());
                    mContext.startActivity(intent);
                });
                helper.getView(R.id.home4_image3).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getThree().getTopic_url());
                    mContext.startActivity(intent);
                });
                helper.getView(R.id.home4_image4).setOnClickListener(view -> {
                    Intent intent = new Intent(mContext, WebViewActivity2.class);
                    intent.putExtra(Constants.TOPIC, item.getFour().getTopic_url());
                    mContext.startActivity(intent);
                });
                break;

        }

    }

}
