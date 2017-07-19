package com.androidapp.shopping_cart.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.GoodsInfo;
import com.androidapp.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by etenel on 2017/7/17.
 */

public class CartAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {
    private final ImageView checlall;

    public CartAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsInfo> data, ImageView allCheckSelectIv) {
        super(layoutResId, data);
        this.checlall=allCheckSelectIv;

    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfo item) {
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.good_image_iv));
        helper.setText(R.id.good_name_tv, item.getGooddesc());
        helper.setText(R.id.good_name_size, item.getContent());
        if (item.getDisprice() > 0) {
            helper.setText(R.id.good_price_size, "￥" + item.getDisprice());
        } else {
            {
                helper.setText(R.id.good_price_size, "￥" + item.getPrice());
            }
        }
        helper.setText(R.id.good_amount_tv, "X" + item.getCount());
        // 判断 记住的状态
        if (item.ischecked()) {
            helper.setChecked(R.id.cb_gov, true);
        } else {
            helper.setChecked(R.id.cb_gov, false);
        }

        // 设置复选框的点击事件
        helper.getView(R.id.cb_gov).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.ischecked = !item.ischecked;
                if (item.ischecked()) {
//                            list.add(bean);
                    Toast.makeText(mContext, "选中", Toast.LENGTH_SHORT).show();
                } else {
//                            list.remove(bean);
                    Toast.makeText(mContext, "取消", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setchecked() {

    }


}