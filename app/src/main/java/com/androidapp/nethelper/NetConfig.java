package com.androidapp.nethelper;


public class NetConfig {

    //"http://mobile.iliangcang.com/goods/goodsList?app_key=Android&count=10&list_id=7&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0&page=1";
    public static String BASE_URL = "http://mobile.iliangcang.com/";
    //分类总url
    public static String CATEGORY_URL = BASE_URL + "goods/goodsCategory?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    //家具url
    public static String FURNITURE_URL = BASE_URL + "goods/goodsShare?app_key=Android&cat_code=0055&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //家居
    public static String HOME_HOUSE_URL = BASE_URL + "goods/goodsShare?app_key=Android&cat_code=0045&count=10&coverId=1&page=1&sig=3D3968703BE211CC6D931C9D4F737FB4%7C290216330933368&v=1.0";
    //文具
    public static String STATIONERY_URL = BASE_URL + "goods/goodsShare?app_key=Android&cat_code=0062&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //数码
    public static String DIGITAL_URL = BASE_URL + "goods/goodsShare?app_key=Android&cat_code=0069&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //玩乐
    public static String LIBERTINISM_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0077&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //厨卫
    public static String KITCHEN_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0082&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //美食
    public static String CATE_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0092&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //男装
    public static String MEN_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0101&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //女装
    public static String WOMEN_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0112&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //童装
    public static String CHILDREN_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0125&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //鞋包
    public static String SHOES_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0129&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //配饰
    public static String ACCESSORY_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0141&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //美护
    public static String BEAUTYCARE_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0154&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //户外
    public static String OUTDOORS_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0166&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //植物
    public static String PLANT_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0172&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //图书
    public static String BOOKS_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0182&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //商店 礼物
    public static String GIFT_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0190&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //商店 推荐
    public static String RECOMMEND_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0198&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //艺术
    public static String ART_URL = BASE_URL +
            "goods/goodsShare?app_key=Android&cat_code=0214&count=10&coverId=1&page=1&sig=6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
    //品牌
    public static String BRAND_URL = BASE_URL +
            "brand/brandList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0";
    //品牌 item详情url brand_id需要动态传入
    public static String BRAND_DETAILS_URL = BASE_URL +
            "brand/brandShopList?app_key=Android&count=20&page=1&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0&brand_id=";
    //商品详情 url goods_id需要动态传入
    public static String BRAND_GOODS_DETAILS_URL = BASE_URL +
            "goods/goodsDetail?app_key=Android&sig=430BD99E6C913B8B8C3ED109737ECF15%7C830952120106768&v=1.0&goods_id=";

    //首页 url  详情url为topic_url
    public static String HOME_PAGER_URL = BASE_URL +
            "goods/newShopHome?app_key=Android&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0";
    //专题页面  page动态输入, 页数的增加用于刷新
    public static String TOPIC_URL = BASE_URL +
            "goods/shopSpecial?app_key=Android&count=10&sig=3780CB0808528F7CE99081D295EE8C0F%7C116941220826768&uid=626138098&user_token=0516ed9429352c8e1e3bd11c63ba6f54&v=1.0&page=1";

    public static int getGiftorder() {
        return giftorder;
    }

    public static void setGiftorder(int giftorder) {
        NetConfig.giftorder = giftorder;
    }

    private static int giftorder = 7;
    //礼物header详情 url
    public static String gift_url = BASE_URL +
            "goods/goodsList?app_key=Android&count=10&list_id=";
    public static String gift_url2 = "&sig=73760B2740FA36D5A273523FBC9295FE%7C285269230036268&v=1.0&page=1";
    //    杂志 作者列表接口
    public static String MAGAZINE_LIST_URL = BASE_URL +
            "topic/magazineAuthorList?app_key=Android&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    杂志_良仓 接口(topic_url 是详情链接)
    public static String LIANGCANG_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=1&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    VOICER 接口
    public static String VOICER_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=3&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    新视线 接口
    public static String NLINE_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=4&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    Amcreative 接口
    public static String AMCREATIVE_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=5&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    凹凸帮 接口
    public static String AOTUBANG_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=6&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    摩根族 接口
    public static String MOGAN_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=7&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    理想生活实验室
    public static String ILLAB_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=8&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    普向工业设计小站
    public static String DESIGHN_STATION_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=11&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    24HOURS
    public static String TFHOURS_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=12&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    顶尖文案
    public static String TOPTS_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=23&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    山行
    public static String MTRIP_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=13&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //    食帖
    public static String WITHEATING_URL = BASE_URL +
            "topic/magazineList?app_key=Android&author_id=14&sig=2FA0974FFF1BC3DFA562AA63C8B5A84F%7C118265010131868&v=1.0";
    //达人 首页列表 默认推荐 url
    public static String EXPERT_RECOMMEND_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0&page=1";

    //达人详情页面 喜欢url owner_id为urse_id 动态输入
    public static String EXPERT_LIKE_URL = BASE_URL +
            "user/masterLike?app_key=Android&count=10&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0&page=1&owner_id=";
    //  达人详情页面  推荐：owner_id为urse_id 动态输入
    public static String EXPERT_INFR_URL = BASE_URL +
            "user/masterListInfo?app_key=Android&count=10&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0&page=1&owner_id=";

    public static int getAttentions() {
        return attentions;
    }

    public static void setAttentions(int attentions) {
        NetConfig.attentions = attentions;
    }

    public static int attentions = 85;
    //  达人详情页面关注：
    public static String EXPERT_ATTENTION_URL = BASE_URL +
            "user/masterFollow?app_key=Android&count=12&owner_id=";
    public static String ATTENTION_2 = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";

    //  达人详情页面粉丝：
    public static String EXPERT_FANS_URL = BASE_URL +
            "user/masterFollowed?app_key=Android&count=12&owner_id=";
    public static String FANS2 = "&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";
    //    最多推荐：
    public static String EXPERT_MENU_MOSTR_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&orderby=goods_sum&page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    //    最受欢迎：
    public static String EXPERT_MENU_WELCOME_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&orderby=followers&page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";
    //    最新推荐：　
    public static String EXPERT_MENU_NEWR_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&orderby=action_time&page=1&sig=CD0E234053E25DD6111E3DBD450A4B85%7C954252010968868&v=1.0";

    //    最新加入：　
    public static String EXPERT_MENU_NEWJ_URL = BASE_URL +
            "user/masterList?app_key=Android&count=18&orderby=reg_time&page=1&sig=79F01B94B8EBEFAC8EEB344EE2B20AA2%7C383889010803768&v=1.0";

}
