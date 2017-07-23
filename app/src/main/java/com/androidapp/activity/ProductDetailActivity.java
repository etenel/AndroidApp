package com.androidapp.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.androidapp.GoodsInfo;
import com.androidapp.R;
import com.androidapp.app.MyApplication;
import com.androidapp.base.FlowRadioGroup;
import com.androidapp.cn.sharesdk.onekeyshare.OnekeyShare;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetConfig;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.product.model.adapter.ProductDetailAdapter;
import com.androidapp.product.model.bean.GoodsBean;
import com.androidapp.util.BarUtils;
import com.androidapp.util.LogUtils;
import com.androidapp.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.greendao.gen.GoodsInfoDao;
import com.rd.PageIndicatorView;
import com.rd.utils.DensityUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.shop_cart)
    ImageView shopCart;

    private Button qrbt;
    private Button share_I;
    private Button share;

    private Button exit;
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


    private View popuview;

    private String url;
    public GoodsBean.DataBean.ItemsBean items;
    public List<GoodsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info;
    private ProductDetailAdapter adapter;


    private GoodsInfoDao dao;
    private GoodsInfo goodsInfo = new GoodsInfo();

    public List<GoodsBean.DataBean.ItemsBean.SkuInvBean> sku_inv;
    public String image;
    public String gooddesc;
    public int counts;
    public double price;
    public double disprice;
    private HashMap<String, String> sku_goods = new HashMap<>();
    private HashMap<String, String> attr_keys = new HashMap<>();
    public String attr_id = "";
    public GoodsBean.DataBean.ItemsBean.SkuInvBean skuInvBean;
    public boolean isliked = false;
    public PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        dao = MyApplication.getApplication().getmDaoSession().getGoodsInfoDao();
        BarUtils.hideNotificationBar(this);
        popuview = View.inflate(this, R.layout.popuview, null);
        ButterKnife.bind(this);
        initdata();

    }

    private void initDetail() {
        if (items != null) {
            goods_info = items.getGoods_info();
            brandNameTv.setText(items.getBrand_info().getBrand_name());
            goodNameTv.setText(items.getGoods_name());
            // priceTv.setText(TextUtils.isEmpty(items.getDiscount_price()) ? "￥" + items.getPrice() : "￥" + items.getDiscount_price());
            List<GoodsBean.DataBean.ItemsBean.SkuInfoBean> sku_info = items.getSku_info();
            sku_inv = items.getSku_inv();

            for (int i = 0; i < sku_info.size(); i++) {
                FlowRadioGroup sku_attr = new FlowRadioGroup(this);
                TextView skv_type1_name = new TextView(this);
                skv_type1_name.setText(sku_info.get(i).getType_name());
                skv_type1_name.setTextColor(Color.parseColor("#ffcccccc"));
                skv_type1_name.setTextSize(12);
                skv_type1_name.setPadding(0, 10, 20, 10);
                sku_goods.put(skv_type1_name.getText().toString(), "");
                attr_keys.put(String.valueOf(i), "");
                // skv_type1_name.setBackgroundColor(Color.parseColor("#ff424950"));
                skuLl.addView(skv_type1_name);
                if (sku_info.get(i).getAttrList() != null && sku_info.get(i).getAttrList().size() > 0) {
                    sku_attr.setOrientation(FlowRadioGroup.HORIZONTAL);
                    int size = sku_info.get(i).getAttrList().size();
                    for (int i1 = 0; i1 < size; i1++) {
                        List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = sku_info.get(i).getAttrList();
                        RadioButton skv_attr_name = new RadioButton(this);
                        FlowRadioGroup.LayoutParams layoutParams = new FlowRadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        int margin = DensityUtils.dpToPx(10);
                        layoutParams.setMargins(0, margin, margin, margin);
                        skv_attr_name.setText(attrList.get(i1).getAttr_name());
                        skv_attr_name.setPadding(20, 10, 20, 10);
                        skv_attr_name.setId(i1);
                        skv_attr_name.setTextSize(12);
                        skv_attr_name.setBackground(getResources().getDrawable(R.drawable.attr_bg_check));
                        skv_attr_name.setTextColor(Color.parseColor("#ffcccccc"));
                        int finalI = i;
                        skv_attr_name.setOnCheckedChangeListener((compoundButton, b) -> {
                            if (b) {
                                //选中
                                skv_attr_name.setBackground(getResources().getDrawable(R.drawable.attr_bg_checked));
                                sku_goods.put(skv_type1_name.getText().toString(), skv_attr_name.getText().toString());


                                //设置种类组合，根据组合的attr_id获取价格
                                attr_keys.put(String.valueOf(finalI), attrList.get(skv_attr_name.getId()).getAttr_id());
                                //LogUtils.e(attrList.get(skv_attr_name.getId()).getAttr_id());
                                //LogUtils.e(skv_type1_name.getText().toString() + ":" + skv_attr_name.getText().toString());
                                for (int i2 = 0; i2 < attr_keys.size(); i2++) {
                                    String s = attr_keys.get(String.valueOf(i2));
                                    if (i2 == 0) {
                                        attr_id = s;
                                    } else {
                                        attr_id = attr_id + "," + s;
                                    }
                                }
                                LogUtils.e(":" + attr_id.toString());

                                //设置显示图片
                                LogUtils.e(skv_attr_name.getId());
                                LogUtils.e(attrList.get(skv_attr_name.getId()).getImg_path());
                                if (!TextUtils.isEmpty(attrList.get(skv_attr_name.getId()).getImg_path())) {
                                    Glide.with(this).load(attrList.get(skv_attr_name.getId()).getImg_path()).into(skuIv);
                                    image = attrList.get(skv_attr_name.getId()).getImg_path();
                                } else {
                                    Glide.with(ProductDetailActivity.this).load(items.getImages_item().get(0)).into(skuIv);
                                    image = items.getImages_item().get(0);
                                }
                                //设置价格
                                for (int i2 = 0; i2 < sku_inv.size(); i2++) {
                                    // LogUtils.e(sku_inv.get(i2).getAttr_keys());
                                    skuInvBean = sku_inv.get(i2);
                                    if (skuInvBean.getAttr_keys().equals(attr_id)) {
                                        if (!TextUtils.isEmpty(skuInvBean.getDiscount_price())) {
                                            priceTv.setText(skuInvBean.getDiscount_price());
                                        } else {
                                            priceTv.setText(skuInvBean.getPrice());
                                        }
                                        return;
                                    }
                                }
                            } else {
                                skv_attr_name.setBackground(getResources().getDrawable(R.drawable.attr_bg_check));

                            }
                        });
//                        if (i1 == 0) {
//                            skv_attr_name.setBackground(getResources().getDrawable(R.drawable.attr_bg_checked));
//                        }
                        skv_attr_name.setButtonDrawable(null);
                        skv_attr_name.setOnClickListener(view -> ToastUtils.showShortSafe(skv_attr_name.getText().toString()));
                        // sku_attr.addView(skv_attr_name, layoutParams);
                        sku_attr.addView(skv_attr_name, layoutParams);

                    }
                    skuLl.addView(sku_attr);

                }
                sku_attr.check(0);
            }

        }

    }

    private void initdata() {
        url = NetConfig.BRAND_GOODS_DETAILS_URL + getIntent().getStringExtra(Constants.WEBURL);
        LogUtils.e("TAG", url);
        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                GoodsBean goodsBean = JSON.parseObject(response, GoodsBean.class);
                items = goodsBean.getData().getItems();
                adapter = new ProductDetailAdapter(goods_info);
                initDetail();
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
        hSize_choose_rl.setOnClickListener(view -> {
            sellBarLl.setVisibility(View.GONE);
            cartPupmenu.setVisibility(View.VISIBLE);
        });
        hPage_indicator.setViewPager(hItem_viewpager);
        //设置进入时位置
        // hPage_indicator.setSelection(1);
        //设置从左还是从右滑动
        //hPage_indicator.setRtlMode(RtlMode.Off);
        hItem_detail_love.setOnClickListener(view -> {
            if (isliked == false) {
                Glide.with(this).load(R.drawable.like_big).into(hItem_detail_love);
                hItem_detail_likedCount.setText((Integer.parseInt(hItem_detail_likedCount.getText().toString()) + 1) + "");
                isliked = true;
            } else {
                Glide.with(this).load(R.drawable.like_not_big).into(hItem_detail_love);
                hItem_detail_likedCount.setText((Integer.parseInt(hItem_detail_likedCount.getText().toString()) - 1) + "");
                isliked = false;
            }

        });
        //分享
        hShare_iv.setOnClickListener(view -> {
            popupWindow = new PopupWindow(popuview, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            popupWindow.setOutsideTouchable(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Transition explode = null;
                explode = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
                popupWindow.setEnterTransition(explode);
            }
            popupWindow.showAsDropDown(cartRl);
            Toast.makeText(ProductDetailActivity.this, "pupwindows", Toast.LENGTH_SHORT).show();
            qrbt = (Button) popuview.findViewById(R.id.qrbt);

            qrbt.setOnClickListener(qrview -> {
                Intent intentqr = new Intent(this, QRActicity.class);
                intentqr.putExtra(Constants.REQUEST_QR, items.getGoods_image());
                startActivity(intentqr);
            });
            share = popuview.findViewById(R.id.share);
            share.setOnClickListener(view1 -> {
                showShare();
                popupWindow.dismiss();
            });
            share_I = popuview.findViewById(R.id.sharebyi);
            share_I.setOnClickListener(v1 -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + items.getGoods_name());
                startActivity(Intent.createChooser(intent, items.getGoods_desc()));
                popupWindow.dismiss();
            });
            exit = popuview.findViewById(R.id.exit);
            exit.setOnClickListener(viewe -> popupWindow.dismiss());
        });
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

    @OnClick({R.id.into_cart, R.id.buy_directly, R.id.left_back_iv, R.id.cart_rl, R.id.custom_service_fl, R.id.close, R.id.minus_iv, R.id.plus_iv, R.id.bottom_action_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_cart:
                Intent intent = new Intent(this, ShopCartActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
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
            case R.id.minus_iv:
                //减小数量
                String count = amountTv.getText().toString();
                int anInt = Integer.parseInt(count);
                if (anInt == 1) {
                    minusIv.setEnabled(false);
                } else {
                    anInt--;
                    amountTv.setText(anInt + "");
                }

                break;
            case R.id.plus_iv:
                //增加数量
                int addcount = Integer.parseInt(amountTv.getText().toString()) + 1;
                amountTv.setText(addcount + "");
                if (addcount > 1) {
                    minusIv.setEnabled(true);
                }

                break;
            case R.id.bottom_action_bar:
                /**
                 *  private Long id;
                 private String image;
                 private String gooddesc;
                 private String sku_goods;
                 private int count;
                 private double price;
                 private double disprice;
                 */
                gooddesc = items.getBrand_info().getBrand_name() + items.getGoods_name();
                counts = Integer.parseInt(amountTv.getText().toString());
                price = Double.parseDouble(priceTv.getText().toString());
                disprice = 0.00;
                if (items.getDiscount_price() != null && !TextUtils.isEmpty(items.getDiscount_price())) {
                    disprice = Double.parseDouble(items.getDiscount_price().toString());
                }
                String content = "";
                //iterator遍历
                Iterator<Map.Entry<String, String>> iterator = sku_goods.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    content = content + entry.getKey() + ":" + entry.getValue() + ";";
                    LogUtils.e(content);
                }
//                Iterator<Map.Entry<String, String>> attr = attr_keys.entrySet().iterator();
//                while (attr.hasNext()) {
//                    Map.Entry<String, String> entry = attr.next();
//                    LogUtils.e(entry.getKey() + ":" + entry.getValue());
//                }
                // 需要更新的数据
                goodsInfo = new GoodsInfo(image, gooddesc, content, counts, price, disprice);
                List<GoodsInfo> list = dao.queryBuilder()
                        .where(GoodsInfoDao.Properties.Gooddesc.eq(gooddesc))
                        .where(GoodsInfoDao.Properties.Image.eq(image))
                        .list();
                //大于0说明数据库中有该类型的数据
                if (list.size() > 0) {
                    LogUtils.e(list.size());

                    // 根据需要更新数据的主键去数据库进行查询
                    GoodsInfo load = dao.load(list.get(0).getId());
                    if (load != null) {
                        // 说明数据库中存在此条数据，则进行数据更新
                        load.setCount((load.getCount() + goodsInfo.getCount()));
                        dao.update(load);
                        Toast.makeText(ProductDetailActivity.this, "商品已存在，只填加数量", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dao.insert(goodsInfo);
                    Toast.makeText(ProductDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                sellBarLl.setVisibility(View.VISIBLE);
                cartPupmenu.setVisibility(View.GONE);
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


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(items.getGoods_url());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(items.getGoods_name());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(items.getGoods_image());//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(items.getGoods_url());

        // 启动分享GUI
        oks.show(this);
    }
}
