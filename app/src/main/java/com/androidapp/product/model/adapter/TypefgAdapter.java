package com.androidapp.product.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidapp.R;
import com.androidapp.product.model.bean.Classification;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by etenel on 2017/7/6.
 */

public class TypefgAdapter extends RecyclerView.Adapter<TypefgAdapter.ViewHolder> implements View.OnClickListener {
    private final List<Classification.DataBean.ItemsBean> datas;
    private final Context context;

    public TypefgAdapter(List<Classification.DataBean.ItemsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_t, null);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(datas.get(position).getNew_cover_img()).into(holder.imageType);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_type)
        ImageView imageType;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public void onClick(View view) {
        if (mOnitemClickListenr != null) {
            mOnitemClickListenr.onItemClick(view);
        }
    }

    private onItemClickListener mOnitemClickListenr;

    public interface onItemClickListener {
        void onItemClick(View view);
    }

    public void setmOnitemClickListenr(onItemClickListener listenr) {
        mOnitemClickListenr = listenr;
    }

}
