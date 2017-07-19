package com.androidapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.GoodsInfo;
import com.androidapp.R;
import com.androidapp.app.MyApplication;
import com.androidapp.shopping_cart.adapter.CartAdapter;
import com.androidapp.util.BarUtils;
import com.greendao.gen.GoodsInfoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartActivity extends AppCompatActivity {
    @BindView(R.id.reach_discount_price_tv)
    TextView reachDiscountPriceTv;
    @BindView(R.id.reach_discount_fl)
    FrameLayout reachDiscountFl;
    @BindView(R.id.discount_discount_price_tv)
    TextView discountDiscountPriceTv;
    @BindView(R.id.discount_discount_fl)
    FrameLayout discountDiscountFl;
    @BindView(R.id.pack_price_tv)
    TextView packPriceTv;
    @BindView(R.id.pack_fl)
    FrameLayout packFl;
    @BindView(R.id.ship_price_tv)
    TextView shipPriceTv;
    @BindView(R.id.ship_fl)
    FrameLayout shipFl;
    @BindView(R.id.pay_tv)
    TextView payTv;
    @BindView(R.id.all_check_select_iv)
    ImageView allCheckSelectIv;
    @BindView(R.id.add_check_select_display_tv)
    TextView addCheckSelectDisplayTv;
    @BindView(R.id.pay_fee_tv)
    TextView payFeeTv;
    @BindView(R.id.save_fee_tv)
    TextView saveFeeTv;
    @BindView(R.id.saving_ll)
    LinearLayout savingLl;
    @BindView(R.id.bottom_bar_ll)
    LinearLayout bottomBarLl;
    @BindView(R.id.cart_lv)
    RecyclerView cartLv;
    private GoodsInfoDao dao;
    public List<GoodsInfo> infos;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            explode = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(explode);
        }
        BarUtils.setTranslucentForCoordinatorLayout(this, 0);
        dao = MyApplication.getApplication().getmDaoSession().getGoodsInfoDao();
        setContentView(R.layout.activity_shop_cart);
        ButterKnife.bind(this);
        initData();


    }

    private void initData() {
        infos = dao.queryBuilder().list();
        showdatas();
    }

    private void showdatas() {
        if (infos != null && infos.size() > 0) {
            adapter = new CartAdapter(R.layout.item_cart, infos, allCheckSelectIv);
            cartLv.setAdapter(adapter);
            cartLv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            showTotalPrice();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showdatas();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showdatas();
    }

    public void checkall() {
        if (infos != null && infos.size() > 0) {
            int number = 0;
            for (int i = 0; i < infos.size(); i++) {
                GoodsInfo goodsBean = infos.get(i);
                if (!goodsBean.ischecked()) {
                    goodsBean.ischecked = true;
                } else {
                    number++;
                }
            }
            if (infos.size() == number) {
                allCheckSelectIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_cart_selected));
            }
        } else {
            allCheckSelectIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_cart_unselected));
        }
        adapter.notifyDataSetChanged();
    }

    public void showTotalPrice() {
        double total = 0;
        if (infos != null && infos.size() > 0) {
            for (int i = 0; i < infos.size(); i++) {
                GoodsInfo goodsBean = infos.get(i);
                if (goodsBean.ischecked()) {
                    if (goodsBean.getDisprice() > 0) {
                        total += (goodsBean.getDisprice()) * (goodsBean.getCount());
                    } else {
                        total += goodsBean.getPrice() * goodsBean.getCount();
                    }

                }
            }
            payFeeTv.setText("￥" + total);
        }
    }

    private boolean isall_checked = true;

    @OnClick(R.id.all_check_select_iv)
    public void onViewClicked() {
        if (infos.size() > 0) {
            if (isall_checked == true) {
                showTotalPrice();
                isall_checked = false;
                checkall();
                adapter.notifyDataSetChanged();
                showTotalPrice();
            } else {
                isall_checked = true;
                payFeeTv.setText("￥" + "0.00");
                for (int i = 0; i < infos.size(); i++) {
                    infos.get(i).ischecked = false;
                }
                adapter.notifyDataSetChanged();
            }

        }
    }
}
