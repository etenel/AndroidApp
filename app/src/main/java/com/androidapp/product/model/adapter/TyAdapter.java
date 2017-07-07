package com.androidapp.product.model.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.Classification;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/7.
 */

public class TyAdapter extends BaseQuickAdapter<Classification.DataBean.ItemsBean, BaseViewHolder> {


    public TyAdapter(@LayoutRes int layoutResId, @Nullable List<Classification.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Classification.DataBean.ItemsBean item) {
        Glide.with(mContext).load(item.getNew_cover_img()).into((ImageView) helper.getView(R.id.image_type));
    }


}
