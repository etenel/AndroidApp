package com.androidapp.maganize.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.maganize.bean.MagnizeBean;
import com.androidapp.util.DateChange;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/9.
 */

public class MagnizeAdapter extends BaseQuickAdapter<MagnizeBean.Data.Items.ProductBean, BaseViewHolder> {


    private final List<MagnizeBean.Data.Items.ProductBean> data;

    public MagnizeAdapter(@LayoutRes int layoutResId, @Nullable List<MagnizeBean.Data.Items.ProductBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MagnizeBean.Data.Items.ProductBean item) {
        Glide.with(mContext).load(item.getCover_img()).into((ImageView) helper.getView(R.id.image));
        helper.setText(R.id.title, item.getTopic_name());
        helper.setText(R.id.type, "——" + item.getCat_name() + "——");
        String addtime = item.getAddtime().substring(0, 10);
        int adapterPosition = helper.getAdapterPosition();
        String s = DateChange.dateFormat(addtime.substring(5, 7));
        LogUtils.e("TAG", addtime.substring(5, 7));

        helper.setText(R.id.tv_time, "--" + s + addtime.substring(7, 10) + "--");
        LogUtils.e("TAG", adapterPosition);

        if (adapterPosition <= (data.size() - 2)) {
            String next = data.get(adapterPosition + 1).getAddtime().substring(0, 10);
            // String current = data.get(adapterPosition).getAddtime().substring(0, 10);
            String data = item.getAddtime().substring(0, 10);
            if (!next.equals(data)) {
                helper.setVisible(R.id.tv_time, true);
            } else {
                helper.setVisible(R.id.tv_time, false);
            }
        }
    }
}
