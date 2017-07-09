package com.androidapp.maganize.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.maganize.bean.MagnizeBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/9.
 */

public class MagnizeAdapter extends BaseQuickAdapter<MagnizeBean.Data.Items.ProductBean, BaseViewHolder> {

    public MagnizeAdapter(@LayoutRes int layoutResId, @Nullable List<MagnizeBean.Data.Items.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MagnizeBean.Data.Items.ProductBean item) {
        helper.setText(R.id.title, item.getTopic_name());
        helper.setText(R.id.type, "——" + item.getCat_name() + "——");
        Glide.with(mContext).load(item.getCover_img()).into((ImageView) helper.getView(R.id.image));
    }
}
