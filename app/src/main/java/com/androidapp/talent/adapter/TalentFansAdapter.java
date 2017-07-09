package com.androidapp.talent.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.talent.bean.FansBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/8.
 */

public class TalentFansAdapter extends BaseQuickAdapter<FansBean.DataBean.ItemsBean.UsersBean, BaseViewHolder> {

    public TalentFansAdapter(@LayoutRes int layoutResId, @Nullable List<FansBean.DataBean.ItemsBean.UsersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FansBean.DataBean.ItemsBean.UsersBean item) {
        if (!TextUtils.isEmpty(item.getUser_image().getOrig())) {
            Glide.with(mContext).load(item.getUser_image().getOrig()).into((ImageView) helper.getView(R.id.talent_image));
        }
        helper.setText(R.id.talent_name, item.getUser_name());
        helper.setVisible(R.id.talent_career, false);
    }
}
