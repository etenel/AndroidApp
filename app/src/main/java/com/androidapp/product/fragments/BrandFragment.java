package com.androidapp.product.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.activity.BrandActivity;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.BrandAdapter;
import com.androidapp.product.model.bean.BrandBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/6.
 */

public class BrandFragment extends BaseFragment {
    private BrandAdapter adapter;
    private List<BrandBean.DataBean.ItemsBean> datas;

    @BindView(R.id.recycle_brand)
    RecyclerView recycleBrand;


    @Override
    public void initData() {
        NetUtils.getInstance().get(NetConfig.BRAND_URL, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                if (response != null && !TextUtils.isEmpty(response)) {
                    BrandBean brandBean = JSON.parseObject(response, BrandBean.class);
                    datas = brandBean.getData().getItems();
                    if(datas!=null&&datas.size()>0) {
                        adapter = new BrandAdapter(R.layout.brand_list_item, datas);
                        recycleBrand.setAdapter(adapter);
                        recycleBrand.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        adapter.setOnItemClickListener((adapter1, view, position) -> {
                            Intent intent = new Intent(getContext(), BrandActivity.class);
                            intent.putExtra(Constants.BRANDIMAGE,datas.get(position).getBrand_logo());
                            intent.putExtra(Constants.BRANDDETAIL, NetConfig.BRAND_DETAILS_URL + datas.get(position).getBrand_id());
                            startActivity(intent);
                        });
                    }

                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public int getlayoutid() {
        return R.layout.item_brand;
    }


}
