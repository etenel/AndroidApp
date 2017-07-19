package com.androidapp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by etenel on 2017/7/1.
 */
@Entity
public class UserInfo {
    private String name;
    private String hxid;
    private String nick;
    private String photo;

    @Generated(hash = 417340442)
    public UserInfo(String name, String hxid, String nick, String photo) {
        this.name = name;
        this.hxid = hxid;
        this.nick = nick;
        this.photo = photo;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public UserInfo(String name, String hxid) {
        this.name = name;
        this.hxid = hxid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHxid() {
        return this.hxid;
    }

    public void setHxid(String hxid) {
        this.hxid = hxid;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
