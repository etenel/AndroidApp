package com.androidapp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by etenel on 2017/7/12.
 */
@Entity
public class GoodsInfo {
    @Id(autoincrement = true)
    private Long id;
    private String image;
    private String gooddesc;
    private String content;
    private int count;
    private double price;
    private double disprice;
    public boolean ischecked = true;

    public boolean ischecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    @Generated(hash = 1667412391)
    public GoodsInfo(Long id, String image, String gooddesc, String content, int count, double price,
                     double disprice, boolean ischecked) {
        this.id = id;
        this.image = image;
        this.gooddesc = gooddesc;
        this.content = content;
        this.count = count;
        this.price = price;
        this.disprice = disprice;
        this.ischecked = ischecked;
    }

    @Generated(hash = 1227172248)
    public GoodsInfo() {
    }

    public GoodsInfo(String image, String gooddesc, String content, int count, double price, double disprice) {
        this.image = image;
        this.gooddesc = gooddesc;
        this.content = content;
        this.count = count;
        this.price = price;
        this.disprice = disprice;
        this.ischecked = true;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGooddesc() {
        return this.gooddesc;
    }

    public void setGooddesc(String gooddesc) {
        this.gooddesc = gooddesc;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDisprice() {
        return this.disprice;
    }

    public void setDisprice(double disprice) {
        this.disprice = disprice;
    }

    public boolean getIschecked() {
        return this.ischecked;
    }
}
