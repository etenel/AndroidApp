package com.androidapp.talent;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidapp.R;
import com.androidapp.activity.TalentDetailActivity;
import com.androidapp.base.BaseFragment;
import com.androidapp.constant.Constants;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.talent.adapter.TalentAdapter;
import com.androidapp.talent.bean.TalentBean;
import com.androidapp.util.LogUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by etenel on 2017/7/6.
 */

public class TalentFragment extends BaseFragment {


    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.rb_moren)
    RadioButton rbMoren;
    @BindView(R.id.rb_zuiduo)
    RadioButton rbZuiduo;
    @BindView(R.id.rb_like)
    RadioButton rbLike;
    @BindView(R.id.rb_new)
    RadioButton rbNew;
    @BindView(R.id.rb_newjoin)
    RadioButton rbNewjoin;
    @BindView(R.id.rg_groups)
    RadioGroup rgGroups;
    @BindView(R.id.ll_pup)
    LinearLayout llPup;
    @BindView(R.id.left)
    ImageView left;
    @BindView(R.id.mid)
    TextView mid;
    @BindView(R.id.right)
    ImageButton right;
    @BindView(R.id.bar)
    RelativeLayout bar;
    private boolean isshowpup = false;
    private int position;
    private TalentAdapter adapter;
    private List<TalentBean.DataBean.ItemsBean> datas;

    @Override
    public void initData() {
        if (isshowpup) {
            Glide.with(getContext()).load(R.drawable.ic_pack_close).into(right);
        } else {
            Glide.with(getContext()).load(R.drawable.actionbar_navigation_menu).into(right);
        }
        rgGroups.setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id) {
                case R.id.rb_moren:
                    position = 0;
                    netConnnect(position);

                    break;
                case R.id.rb_zuiduo:
                    position = 1;
                    netConnnect(position);
                    break;
                case R.id.rb_like:
                    position = 2;
                    netConnnect(position);
                    break;
                case R.id.rb_new:
                    position = 3;
                    netConnnect(position);
                    break;
                case R.id.rb_newjoin:
                    position = 4;
                    netConnnect(position);
                    break;
            }
        });
        rgGroups.check(R.id.rb_moren);
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                //获得当前item的位置
                int position = parent.getChildAdapterPosition(view);
                //根据position确定item需要留出的位置
                switch (position % 3) {
                    case 0:
                        //位于左侧的item
                        outRect.right = 25;
                        outRect.left = 25;
                        break;
                    case 1:
                        //位于右侧的item
                        outRect.right = 25;
                        outRect.left = 25;
                        break;
                    case 2:
                        //位于中间的item
                        outRect.right = 25;
                        outRect.left = 25;
                        break;
                    default:
                        break;
                }
                //设置底部边距
                outRect.bottom = 20;
                outRect.top = 20;
            }

        });
    }

    private void netConnnect(int position) {
        NetUtils.getInstance().get(Constants.Talents[position], new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {
                LogUtils.e("TAG", Constants.Talents[position]);
                TalentBean talentBean = JSON.parseObject(response, TalentBean.class);
                datas = talentBean.getData().getItems();
                adapter = new TalentAdapter(R.layout.talent_item, datas);
                recycle.setAdapter(adapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
                recycle.setLayoutManager(gridLayoutManager);
                adapter.setOnItemClickListener((adapter1, view, position1) -> {
                    Intent intent = new Intent(getContext(), TalentDetailActivity.class);
                    intent.putExtra(Constants.TALENTDETAIL, datas.get(position1).getUid());
                    startActivity(intent);
                });
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_talent;
    }

    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                break;
            case R.id.right:
                if (isshowpup) {
                    isshowpup = false;
                    llPup.setVisibility(View.GONE);
                    Glide.with(getContext()).load(R.drawable.actionbar_navigation_menu).into(right);
                } else {
                    isshowpup = true;
                    llPup.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(R.drawable.ic_pack_close).into(right);
                }
                break;

        }
    }

}
