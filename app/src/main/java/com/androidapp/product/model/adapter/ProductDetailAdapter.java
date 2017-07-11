package com.androidapp.product.model.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.GoodsBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

/**
 * Created by etenel on 2017/7/10.
 */

public class ProductDetailAdapter extends BaseQuickAdapter<GoodsBean.DataBean.ItemsBean.GoodsInfoBean, BaseViewHolder> {
    private GoodsBean.DataBean.ItemsBean items;

    public ProductDetailAdapter(@Nullable List<GoodsBean.DataBean.ItemsBean.GoodsInfoBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<GoodsBean.DataBean.ItemsBean.GoodsInfoBean>() {
            @Override
            protected int getItemType(GoodsBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean) {
                return goodsInfoBean.getType();

            }
        });
        getMultiTypeDelegate().registerItemType(0, R.layout.item_shop_detail_type0)
                .registerItemType(1, R.layout.item_shop_detail_type1)
                .registerItemType(2, R.layout.item_shop_detail_type2);


    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean.ItemsBean.GoodsInfoBean item) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.text_tv, item.getContent().getText());
                break;
            case 1:
                Glide.with(mContext).load(item.getContent().getImg()).into((ImageView) helper.getView(R.id.img_iv));
                break;
            case 2:
                helper.setText(R.id.text_tv, item.getContent().getText());
                break;
//            case 3:
//                LogUtils.e("TAG", items.getGoods_desc());
//                helper.setText(R.id.text_tv, items.getGoods_desc());
//                break;
        }
    }
}
