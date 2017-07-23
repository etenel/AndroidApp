package com.androidapp.persion.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by etenel on 2017/7/21.
 */

public class Mysection extends SectionEntity<LiveBean.DataBean.PartitionsBean> {
    private LiveBean.DataBean.PartitionsBean.PartitionBeanX partition;
    private List<LiveBean.DataBean.PartitionsBean.LivesBean> lives;

    public Mysection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public Mysection(boolean isHeader, String header, LiveBean.DataBean.PartitionsBean.PartitionBeanX partition, List<LiveBean.DataBean.PartitionsBean.LivesBean> lives) {
        super(isHeader, header);
        this.partition = partition;
        this.lives = lives;
    }

    public Mysection(LiveBean.DataBean.PartitionsBean partitionsBean) {
        super(partitionsBean);
    }
}
