package com.androidapp.product.model.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.TopicBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/7.
 */

public class TopicAdapter extends BaseQuickAdapter<TopicBean.DataBean.ItemsBean, BaseViewHolder> {
    public TopicAdapter(@LayoutRes int layoutResId, @Nullable List<TopicBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBean.DataBean.ItemsBean item) {
        Glide.with(mContext).load(item.getCover_img()).into((ImageView) helper.getView(R.id.topic_image));
        LogUtils.e("TAG",item.getCover_img());
        helper.setText(R.id.topic_title, item.getTopic_name());
    }
}
