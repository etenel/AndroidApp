package com.androidapp.product.model.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.HomeHouseBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/7.
 */

public class ProductMessageAdapter extends BaseQuickAdapter<HomeHouseBean.DataBean.ItemsBean, BaseViewHolder> {

    public ProductMessageAdapter(@LayoutRes int layoutResId, @Nullable List<HomeHouseBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeHouseBean.DataBean.ItemsBean item) {
        helper.setText(R.id.tv_msg, item.getGoods_name());
        helper.setText(R.id.tv_author, item.getBrand_info().getBrand_name());
        helper.setText(R.id.number, item.getLike_count());
        helper.setText(R.id.tv_price, "￥" + item.getPrice());
        Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.im_pro_msg));
    }
}
