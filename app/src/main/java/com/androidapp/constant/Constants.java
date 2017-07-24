package com.androidapp.constant;

import com.androidapp.nethelper.NetConfig;

/**
 * Created by etenel on 2017/7/6.
 */

public class Constants {
    public static final String[] product_tag = {
            "分类", "品牌", "首页", "专题", "礼物"
    };
    public static final String[] MAGINIZE_TAG = {
            "分类", "作者"
    };
    public static final String PRODUCT_MSG = "product_message";
    public static final String WEBURL = "weburl";
    public static final String BRANDDETAIL = "branddetail";
    public static final String BRANDIMAGE = "image_brand";
    public static final String TOPIC = "topic";
    public static final String[] Talents = {
            NetConfig.EXPERT_RECOMMEND_URL, NetConfig.EXPERT_MENU_MOSTR_URL, NetConfig.EXPERT_MENU_WELCOME_URL,
            NetConfig.EXPERT_MENU_NEWR_URL, NetConfig.EXPERT_MENU_NEWJ_URL
    };
    public static final String TALENTDETAIL = "talentdetail";
    public static final String[] MAGINZE_AUTHORS = {
            NetConfig.LIANGCANG_URL, NetConfig.VOICER_URL, NetConfig.NLINE_URL,
            NetConfig.AMCREATIVE_URL, NetConfig.AOTUBANG_URL, NetConfig.MOGAN_URL,
            NetConfig.ILLAB_URL, NetConfig.DESIGHN_STATION_URL, NetConfig.TFHOURS_URL,
            NetConfig.TOPTS_URL, NetConfig.MTRIP_URL, NetConfig.WITHEATING_URL
    };
    public static final String MAGAUTHORNAME = "authorname";
    public static final String SWITCH_MODE_KEY = "mode_key";
    public static final String EXTRA_CID = "cid";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ONLINE = "online";
    public static final String EXTRA_FACE = "face";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_MID = "mid";
    public static final String EXTRA_PLAYURL = "playurl";
    public static final String AID = "aid";
    public static String MAGINIZEdetail = "MAGIN";
    public static int[] BSRECOMMEND_TYPE = new int[]{0, 1, 2, 3, 4};


    public static String REQUEST_QR = "request_qr";
}
