package com.androidapp.talent.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.talent.bean.TalentBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/8.
 */

public class TalentAdapter extends BaseQuickAdapter<TalentBean.DataBean.ItemsBean,BaseViewHolder> {
    public TalentAdapter(@LayoutRes int layoutResId, @Nullable List<TalentBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TalentBean.DataBean.ItemsBean item) {
        Glide.with(mContext).load(item.getUser_images().getOrig()).into((ImageView)helper.getView(R.id.talent_image));
        helper.setText(R.id.talent_name,item.getUsername());
        helper.setText(R.id.talent_career,item.getDuty());
    }
}
