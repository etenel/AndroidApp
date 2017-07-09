package com.androidapp.talent.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.talent.bean.LikeBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/8.
 */

public class TalentLikeAdapter extends BaseQuickAdapter<LikeBean.DataBean.ItemsBean.GoodsBean, BaseViewHolder> {


    public TalentLikeAdapter(@LayoutRes int layoutResId, @Nullable List<LikeBean.DataBean.ItemsBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LikeBean.DataBean.ItemsBean.GoodsBean item) {
        Glide.with(mContext).load(item.getGoods_image()).into((ImageView) helper.getView(R.id.talent_image));
        helper.setVisible(R.id.talent_career, false);
        helper.setVisible(R.id.talent_name, false);
    }

}
