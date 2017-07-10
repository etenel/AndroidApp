package com.androidapp.maganize.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by etenel on 2017/7/9.
 */

public class MagnizeBean {
    private Meta meta;
    private int version;
    private Data data;

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public static class Meta {
        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private String errdata;
        private String errmsg;

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getCost() {
            return cost;
        }

        public void setErrdata(String errdata) {
            this.errdata = errdata;
        }

        public String getErrdata() {
            return errdata;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public String getErrmsg() {
            return errmsg;
        }
    }

    public static class Data {
        private boolean has_more;
        private int num_items;
        private Items items;

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public boolean getHas_more() {
            return has_more;
        }

        public void setNum_items(int num_items) {
            this.num_items = num_items;
        }

        public int getNum_items() {
            return num_items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public Items getItems() {
            return items;
        }


        public static class Items {
            private ArrayList keys;

            private HashMap infos;

            public List<ProductBean> getDatas() {
                datas = new ArrayList<>();
                for (int i = 0; i < keys.size(); i++) {
                    String response = infos.get(keys.get(i).toString()).toString();

                    List<ProductBean> o = new Gson().fromJson(response, new TypeToken<ArrayList<ProductBean>>() {
                    }.getType());
                    for (int i1 = 0; i1 < o.size(); i1++) {
                        datas.add(o.get(i1));
                    }

                }
                return datas;
            }

            public void setDatas(List<ProductBean> datas) {
                this.datas = datas;
            }

            private List<ProductBean> datas;

            public ArrayList getKeys() {

                return keys;
            }

            public void setKeys(ArrayList keys) {
                this.keys = keys;
            }

            public HashMap getInfos() {
                return infos;
            }

            public void setInfos(HashMap infos) {
                this.infos = infos;
            }

            public static class ProductBean {
                /**
                 * access_url : http://www.iliangcang.com/i/topicapp/201701091815
                 * taid : 1692
                 * cat_name : 美食
                 * cover_img_new : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/1692_.jpg?_t=1483946343
                 * hit_number : 488
                 * topic_url : http://www.iliangcang.com/i/topicapp/201701091815
                 * cover_img : http://imgs-qn.iliangcang.com/ware/appimg/topic/cover/1692_.jpg?_t=1483946343
                 * nav_title : 良仓杂志
                 * topic_name : 这几种咖啡冷门喝法，只有极少数人试过
                 * cat_id : 9
                 * content :
                 * addtime : 2017-01-08 15:18:55
                 * author_id : 1
                 * author_name : 良仓
                 */

                private String access_url;
                private String taid;
                private String cat_name;
                private String cover_img_new;
                private int hit_number;
                private String topic_url;
                private String cover_img;
                private String nav_title;
                private String topic_name;
                private String cat_id;
                private String content;
                private String addtime;
                private String author_id;
                private String author_name;

                public String getAccess_url() {
                    return access_url;
                }

                public void setAccess_url(String access_url) {
                    this.access_url = access_url;
                }

                public String getTaid() {
                    return taid;
                }

                public void setTaid(String taid) {
                    this.taid = taid;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
                }

                public String getCover_img_new() {
                    return cover_img_new;
                }

                public void setCover_img_new(String cover_img_new) {
                    this.cover_img_new = cover_img_new;
                }

                public int getHit_number() {
                    return hit_number;
                }

                public void setHit_number(int hit_number) {
                    this.hit_number = hit_number;
                }

                public String getTopic_url() {
                    return topic_url;
                }

                public void setTopic_url(String topic_url) {
                    this.topic_url = topic_url;
                }

                public String getCover_img() {
                    return cover_img;
                }

                public void setCover_img(String cover_img) {
                    this.cover_img = cover_img;
                }

                public String getNav_title() {
                    return nav_title;
                }

                public void setNav_title(String nav_title) {
                    this.nav_title = nav_title;
                }

                public String getTopic_name() {
                    return topic_name;
                }

                public void setTopic_name(String topic_name) {
                    this.topic_name = topic_name;
                }

                public String getCat_id() {
                    return cat_id;
                }

                public void setCat_id(String cat_id) {
                    this.cat_id = cat_id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public String getAuthor_id() {
                    return author_id;
                }

                public void setAuthor_id(String author_id) {
                    this.author_id = author_id;
                }

                public String getAuthor_name() {
                    return author_name;
                }

                public void setAuthor_name(String author_name) {
                    this.author_name = author_name;
                }
            }
        }
    }
}
