package com.androidapp.persion.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.persion.bean.Bilibili;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/24.
 */

public class ChaseAdapter extends BaseQuickAdapter<Bilibili.ListBean, BaseViewHolder> {
    public ChaseAdapter(@LayoutRes int layoutResId, @Nullable List<Bilibili.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bilibili.ListBean item) {
        helper.setText(R.id.item_live_title, item.getTitle());
        helper.setText(R.id.item_live_user, item.getAuthor());
        Glide.with(mContext).load("http:"+item.getPic()).into((ImageView) helper.getView(R.id.item_live_user_cover));
        Glide.with(mContext).load("http:"+item.getPic()).into((ImageView) helper.getView(R.id.item_live_cover));
        helper.getView(R.id.item_live_cover).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse("https://www.bilibili.com/video/av" + item.getAid() + "/");

                intent.setData(content_url);
               mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.about_introduce)));
            }
        });
    }
}
