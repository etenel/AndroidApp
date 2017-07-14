package com.androidapp.share.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.R;
import com.androidapp.constant.Constants;
import com.androidapp.share.bean.BSRecommendBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by etenel on 2017/7/11.
 */

public class BsRecommendAdapter extends BaseQuickAdapter<BSRecommendBean.ListBean, BaseViewHolder> {


    public BsRecommendAdapter(@Nullable List<BSRecommendBean.ListBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<BSRecommendBean.ListBean>() {


            @Override
            protected int getItemType(BSRecommendBean.ListBean listBean) {
                int position = 0;
                switch (listBean.getType()) {
                    case "image":
                        position = 0;
                        break;
                    case "gif":
                        position = 1;
                        break;
                    case "video":
                        position = 2;
                        break;
                    case "text":
                        position = 3;
                        break;
                    case "html":
                        position = 4;
                        break;
                }

                return Constants.BSRECOMMEND_TYPE[position];
            }
        });
        getMultiTypeDelegate().registerItemType(0, R.layout.all_image_item)
                .registerItemType(1, R.layout.all_gif_item)
                .registerItemType(2, R.layout.all_video_item)
                .registerItemType(3, R.layout.all_text_item)
                .registerItemType(4, R.layout.all_html_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, BSRecommendBean.ListBean item) {
        //公共部分
        helper.setText(R.id.tv_name, item.getU().getName());
        helper.setText(R.id.tv_time_refresh, item.getPasstime());
        if (item.getTags() != null && !TextUtils.isEmpty(item.getTags().get(0).getName())) {
            helper.setText(R.id.tv_video_kind_text, item.getTags().get(0).getName());
        }
        helper.setText(R.id.tv_shenhe_ding_number, item.getUp());
        // helper.setText(R.id.tv_shenhe_cai_number, item.getDown());
        Glide.with(mContext).load(item.getU().getHeader().get(0)).into((ImageView) helper.getView(R.id.iv_headpic));
        helper.setText(R.id.tv_posts_number, item.getShare_url());
        helper.setText(R.id.tv_download_number, item.getComment());

        //添加评论
        LinearLayout helperView = (LinearLayout) helper.getView(R.id.comment_content);
        helperView.removeAllViews();
        if (item.getTop_comments() != null && item.getTop_comments().size() > 0) {
            for (int i = 0; i < item.getTop_comments().size(); i++) {
                TextView textView = new TextView(mContext);
                String string = item.getTop_comments().get(i).getU().getName() + "：" + item.getTop_comments().get(i).getContent();
                Spannable spannable = new SpannableString(string);
                //设置字体大小为12（单位为物理像素），设置字体为蓝色，
//                spannable.setSpan(new AbsoluteSizeSpan(25), 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, item.getTop_comments().get(i).getU().getName().length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannable);
                helperView.addView(textView);
            }

        }
        switch (helper.getItemViewType()) {
            case 0:
                helper.setText(R.id.tv_context, item.getText());
                if (item.getImage().getThumbnail_small() != null && item.getImage().getThumbnail_small().size() > 0) {
                    LogUtils.e(item.getImage().getThumbnail_small().get(0));
                  Glide.with(mContext).load(item.getImage().getThumbnail_small().get(0)).into((ImageView)helper.getView(R.id.iv_image_icon));
                }
                break;
            case 1:
                helper.setText(R.id.tv_context, item.getText());
                Glide.with(mContext).asGif().load(item.getGif().getImages().get(0)).into((ImageView) helper.getView(R.id.iv_image_gif));

                break;
            case 2:
                helper.setText(R.id.tv_context, item.getText());
                // helper.setText(R.id.tv_play_nums, item.getVideo().getPlaycount());
                int i = item.getVideo().getDuration() % 60;
                int j = item.getVideo().getDuration() / 60;
                helper.setText(R.id.tv_video_duration, j + ":" + i);
                LogUtils.e("TAG", item.getVideo().getDuration());
                JCVideoPlayerStandard player = helper.getView(R.id.jcv_videoplayer);
                player.setUp(item.getVideo().getVideo().get(0), "");
                Glide.with(mContext).load(item.getVideo().getThumbnail().get(0)).into(player.ivThumb);

                break;
            case 3:
                helper.setText(R.id.tv_context, item.getText());

                break;
            case 4:
                helper.setText(R.id.tv_context, item.getHtml().getDesc());
                WebView view = helper.getView(R.id.webview);
                view.loadUrl(item.getHtml().getSource_url());
                break;
        }
    }

}
