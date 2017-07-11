package com.androidapp.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.ProductDetailAdapter;
import com.androidapp.product.model.bean.GoodsBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.rd.PageIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends AppCompatActivity {
    @BindView(R.id.custom_service_fl)
    FrameLayout customServiceFl;
    @BindView(R.id.into_cart)
    FrameLayout intoCart;
    @BindView(R.id.buy_directly)
    FrameLayout buyDirectly;
    @BindView(R.id.sell_bar_ll)
    LinearLayout sellBarLl;
    @BindView(R.id.content_lv)
    RecyclerView contentLv;
    @BindView(R.id.left_back_iv)
    ImageView leftBackIv;
    @BindView(R.id.cart_count_tv)
    TextView cartCountTv;
    @BindView(R.id.cart_rl)
    RelativeLayout cartRl;
    @BindView(R.id.confirm_tv)
    TextView confirmTv;
    @BindView(R.id.into_cart_tv)
    TextView intoCartTv;
    @BindView(R.id.buy_directly_tv)
    TextView buyDirectlyTv;
    @BindView(R.id.bottom_action_bar)
    LinearLayout bottomActionBar;
    @BindView(R.id.brand_name_tv)
    TextView brandNameTv;
    @BindView(R.id.good_name_tv)
    TextView goodNameTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.sku_ll)
    LinearLayout skuLl;
    @BindView(R.id.amount_title_tv)
    TextView amountTitleTv;
    @BindView(R.id.minus_iv)
    ImageView minusIv;
    @BindView(R.id.amount_tv)
    TextView amountTv;
    @BindView(R.id.plus_iv)
    ImageView plusIv;
    @BindView(R.id.dark_rl)
    RelativeLayout darkRl;
    @BindView(R.id.sku_iv)
    ImageView skuIv;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.cart_pupmenu)
    RelativeLayout cartPupmenu;
    //尾部局
    private LinearLayout shop_note_ll;
    private TextView item_detail_goodsDesc;
    private TextView rec_reason_tv;
    private TextView item_detail_brandname_top_bottom;
    //头布局
    private ViewPager hItem_viewpager;
    private PageIndicatorView hPage_indicator;
    private ImageView hPromotion_img;
    private RelativeLayout hHeader_head_rl;
    private RelativeLayout hLove_layout;
    private ImageView hItem_detail_love;
    private TextView hItem_detail_likedCount;
    private ImageView hShare_iv;
    private TextView hItem_detail_brandname_top;
    private TextView hItem_detail_goodsName;
    private TextView hItem_promotionnote;
    private RelativeLayout hPrice_rl;
    private TextView hItem_detail_price;
    private TextView hItem_detail_origin_price;
    private LinearLayout hShip_ll;
    private ImageView hShipping_str_iv;
    private TextView hShipping_str;
    private RelativeLayout hSize_choose_rl;
    private ImageView hSize_choose_iv;
    private RelativeLayout hBrand_rl;
    private ImageView hItem_detail_brand_image;
    private TextView hItem_detail_brand_name;
    private LinearLayout hTab_shop_tip;
    private TextView hGood_detail_choose_tv;
    private TextView hShopping_note_choose_tv;
    private View hPadding_top_for_listview;


    private String url;
    public GoodsBean.DataBean.ItemsBean items;
    public List<GoodsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info;
    private ProductDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        url = NetConfig.BRAND_GOODS_DETAILS_URL + getIntent().getStringExtra(Constants.WEBURL);
        LogUtils.e("TAG", url);
        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                GoodsBean goodsBean = JSON.parseObject(response, GoodsBean.class);
                items = goodsBean.getData().getItems();
                goods_info = items.getGoods_info();
                adapter = new ProductDetailAdapter(goods_info);
                addFootview();
                addHeadview();
                contentLv.setAdapter(adapter);
                contentLv.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    //设置头布局
    private void addHeadview() {
        View v = View.inflate(ProductDetailActivity.this, R.layout.item_shop_detail_layout_header, null);
        bindViews(v);
        hItem_viewpager.setAdapter(new MyPagerAdapter());
        if (items.getPromotion_imgurl() != null && !TextUtils.isEmpty(items.getPromotion_imgurl())) {
            Glide.with(this).load(items.getPromotion_imgurl()).into(hPromotion_img);
        }
        hItem_detail_brand_name.setText(items.getBrand_info().getBrand_name());
        hItem_detail_brandname_top.setText(items.getBrand_info().getBrand_name());
        hItem_detail_goodsName.setText(items.getGoods_name());
        if (items.getPromotion_note() != null && !TextUtils.isEmpty(items.getPromotion_note())) {
            hItem_promotionnote.setText(items.getPromotion_note());
        }
        hItem_detail_likedCount.setText(items.getLike_count());
        if (items.getDiscount_price() != null && !TextUtils.isEmpty(items.getDiscount_price())) {
            hItem_detail_price.setText("￥" + items.getDiscount_price());
            hItem_detail_origin_price.setText("￥" + items.getPrice());
            hItem_detail_origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            hItem_detail_price.setText("￥" + items.getPrice());
        }
        hShipping_str.setText(items.getShipping_str());
        Glide.with(this).load(items.getBrand_info().getBrand_logo()).into(hItem_detail_brand_image);
        hBrand_rl.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDetailActivity.this, BrandActivity.class);
            intent.putExtra(Constants.BRANDIMAGE, items.getBrand_info().getBrand_logo());
            intent.putExtra(Constants.BRANDDETAIL, NetConfig.BRAND_DETAILS_URL + items.getBrand_info().getBrand_id());
            startActivity(intent);
        });
        hPage_indicator.setViewPager(hItem_viewpager);
        //设置进入时位置
        // hPage_indicator.setSelection(1);
        //设置从左还是从右滑动
        //hPage_indicator.setRtlMode(RtlMode.Off);

        adapter.addHeaderView(v);

        //商品详情
        hGood_detail_choose_tv.setOnClickListener(view -> {
            shop_note_ll.setVisibility(View.GONE);
            hGood_detail_choose_tv.setBackgroundResource(R.drawable.gray1);
            hShopping_note_choose_tv.setBackgroundResource(R.drawable.black1);
        });
        //购物须知
        hShopping_note_choose_tv.setOnClickListener(view -> {
            shop_note_ll.setVisibility(View.VISIBLE);
            hShopping_note_choose_tv.setBackgroundResource(R.drawable.gray1);
            hGood_detail_choose_tv.setBackgroundResource(R.drawable.black1);
        });

    }


    private void addFootview() {
        //设置尾部局
        View v = View.inflate(ProductDetailActivity.this, R.layout.item_shop_detail_layout_footer, null);
        shop_note_ll = v.findViewById(R.id.shop_note_ll);
        item_detail_goodsDesc = v.findViewById(R.id.item_detail_goodsDesc);
        item_detail_goodsDesc.setText(items.getGoods_desc());
        rec_reason_tv = v.findViewById(R.id.rec_reason_tv);
        rec_reason_tv.setText(items.getRec_reason());
        item_detail_brandname_top_bottom = v.findViewById(R.id.item_detail_brandname_top_bottom);
        item_detail_brandname_top_bottom.setText(items.getBrand_info().getBrand_desc());

        adapter.addFooterView(v);
    }

    @OnClick({R.id.into_cart, R.id.buy_directly, R.id.left_back_iv, R.id.cart_rl, R.id.custom_service_fl, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.into_cart:
                sellBarLl.setVisibility(View.GONE);
                cartPupmenu.setVisibility(View.VISIBLE);
                break;
            case R.id.buy_directly:
                break;
            case R.id.left_back_iv:
                finish();
                break;
            case R.id.cart_rl:

                break;
            case R.id.custom_service_fl:
                Toast.makeText(ProductDetailActivity.this, "客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.close:
                cartPupmenu.setVisibility(View.GONE);
                sellBarLl.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void bindViews(View v) {

        hItem_viewpager = (ViewPager) v.findViewById(R.id.item_viewpager);
        hPage_indicator = (PageIndicatorView) v.findViewById(R.id.page_indicator);
        hPromotion_img = (ImageView) v.findViewById(R.id.promotion_img);
        hHeader_head_rl = (RelativeLayout) v.findViewById(R.id.header_head_rl);
        hLove_layout = (RelativeLayout) v.findViewById(R.id.love_layout);
        hItem_detail_love = (ImageView) v.findViewById(R.id.item_detail_love);
        hItem_detail_likedCount = (TextView) v.findViewById(R.id.item_detail_likedCount);
        hShare_iv = (ImageView) v.findViewById(R.id.share_iv);
        hItem_detail_brandname_top = (TextView) v.findViewById(R.id.item_detail_brandname_top);
        hItem_detail_goodsName = (TextView) v.findViewById(R.id.item_detail_goodsName);
        hItem_promotionnote = (TextView) v.findViewById(R.id.item_promotionnote);
        hPrice_rl = (RelativeLayout) v.findViewById(R.id.price_rl);
        hItem_detail_price = (TextView) v.findViewById(R.id.item_detail_price);
        hItem_detail_origin_price = (TextView) v.findViewById(R.id.item_detail_origin_price);
        hShip_ll = (LinearLayout) v.findViewById(R.id.ship_ll);
        hShipping_str_iv = (ImageView) v.findViewById(R.id.shipping_str_iv);
        hShipping_str = (TextView) v.findViewById(R.id.shipping_str);
        hSize_choose_rl = (RelativeLayout) v.findViewById(R.id.size_choose_rl);
        hSize_choose_iv = (ImageView) v.findViewById(R.id.size_choose_iv);
        hBrand_rl = (RelativeLayout) v.findViewById(R.id.brand_rl);
        hItem_detail_brand_image = (ImageView) v.findViewById(R.id.item_detail_brand_image);
        hItem_detail_brand_name = (TextView) v.findViewById(R.id.item_detail_brand_name);
        hTab_shop_tip = (LinearLayout) v.findViewById(R.id.tab_shop_tip);
        hGood_detail_choose_tv = (TextView) v.findViewById(R.id.good_detail_choose_tv);
        hShopping_note_choose_tv = (TextView) v.findViewById(R.id.shopping_note_choose_tv);
        hPadding_top_for_listview = (View) v.findViewById(R.id.padding_top_for_listview);
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return items.getImages_item() == null ? 0 : items.getImages_item().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ProductDetailActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(ProductDetailActivity.this).load(items.getImages_item().get(position)).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
