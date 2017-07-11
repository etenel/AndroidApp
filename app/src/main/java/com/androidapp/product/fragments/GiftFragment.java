package com.androidapp.product.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.activity.ProductListActivity;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.product.model.adapter.ProductMessageAdapter;
import com.androidapp.product.model.bean.HomeHouseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by etenel on 2017/7/6.
 */

public class GiftFragment extends BaseFragment {
    @BindView(R.id.present_7_iv)
    ImageView present7Iv;
    @BindView(R.id.present_1_iv)
    ImageView present1Iv;
    @BindView(R.id.present_2_iv)
    ImageView present2Iv;
    @BindView(R.id.present_3_iv)
    ImageView present3Iv;
    @BindView(R.id.present_4_iv)
    ImageView present4Iv;
    @BindView(R.id.present_5_iv)
    ImageView present5Iv;
    @BindView(R.id.present_6_iv)
    ImageView present6Iv;
    @BindView(R.id.set_gift_remind)
    ImageView setGiftRemind;
    private List<HomeHouseBean.DataBean.ItemsBean> datas;
    private ProductMessageAdapter PAdapter;

    @Override
    public void initData() {

    }

    @Override
    public int getlayoutid() {
        return R.layout.item_gift;
    }


    @OnClick({R.id.present_7_iv, R.id.present_1_iv, R.id.present_2_iv, R.id.present_3_iv, R.id.present_4_iv, R.id.present_5_iv, R.id.present_6_iv, R.id.set_gift_remind})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getContext(), ProductListActivity.class);
        switch (view.getId()) {

            case R.id.present_7_iv:
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_1_iv:
                NetConfig.setGiftorder(1);
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_2_iv:
                NetConfig.setGiftorder(2);
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_3_iv:
                NetConfig.setGiftorder(3);
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_4_iv:
                NetConfig.setGiftorder(4);
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_5_iv:
                NetConfig.setGiftorder(5);
                intent.putExtra(Constants.PRODUCT_MSG, NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.present_6_iv:
                NetConfig.setGiftorder(6);
                intent.putExtra(Constants.PRODUCT_MSG,NetConfig.gift_url+NetConfig.getGiftorder()+NetConfig.gift_url2);
                startActivity(intent);
                break;
            case R.id.set_gift_remind:
                break;
        }
    }
}
