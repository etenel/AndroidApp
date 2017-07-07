package com.androidapp.product.model.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.BrandBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/7.
 */

public class BrandAdapter extends BaseQuickAdapter<BrandBean.DataBean.ItemsBean, BaseViewHolder> {
    public BrandAdapter(@LayoutRes int layoutResId, @Nullable List<BrandBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandBean.DataBean.ItemsBean item) {
        helper.setText(R.id.brand_name, item.getBrand_name());
        Glide.with(mContext).load(item.getBrand_logo()).into((ImageView) helper.getView(R.id.brand_image));
    }
}
