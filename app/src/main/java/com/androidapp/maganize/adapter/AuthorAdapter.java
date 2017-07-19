package com.androidapp.maganize.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.androidapp.R;
import com.androidapp.maganize.bean.AuthorBean;
import com.androidapp.util.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/9.
 */

public class AuthorAdapter extends BaseQuickAdapter<AuthorBean.DataBean.ItemsBean, BaseViewHolder> {
    public AuthorAdapter(@LayoutRes int layoutResId, @Nullable List<AuthorBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AuthorBean.DataBean.ItemsBean item) {
        Glide.with(mContext).load(item.getThumb()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Bitmap bitmap = ImageUtils.toRound(resource);
                helper.setImageBitmap(R.id.talent_author_iv, bitmap);
            }
        });
        helper.setText(R.id.topic_author_name, item.getAuthor_name());
        helper.setText(R.id.topic_author_desc, item.getNote());
    }
}
