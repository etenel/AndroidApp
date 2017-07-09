package com.androidapp.talent.bean;

import java.util.List;

/**
 * Created by etenel on 2017/7/8.
 */

public class RecommendBean {

    /**
     * meta : {"status":0,"server_time":"2017-07-08 14:02:02","account_id":0,"cost":0.017391920089722,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":true,"num_items":"12","items":{"user_id":"1502","user_name":"张悦然","is_daren":"1","email":"karmakarma@qq.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/1/1502.jpg?t=1499493722","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/1/1502.jpg?t=1499493722","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/1/1502.jpg?t=1499493722"},"user_desc":"作家","friend":"0","like_count":"49","recommendation_count":"12","following_count":"26","followed_count":"2444","template_id":"0","goods":[{"goods_id":"6098","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/6/6098.jpg?t=1392521211","goods_name":"Harris Tweed翻毛搭扣小钱包 ","price":"889.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"78","liked":"0"},{"goods_id":"10928","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10928.jpg?t=1392521208","goods_name":"45rpm靛青二重织拔染裙衣","price":"3985.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"44","liked":"0"},{"goods_id":"10929","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10929.jpg?t=1392521208","goods_name":"Henry Cuir Beguelin Barneys Vachetta Embroidered Card Wallet","price":"1208.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"49","liked":"0"},{"goods_id":"11050","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11050.jpg?t=1392521208","goods_name":"DODOcase for Kindle","price":"500.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"51","liked":"0"},{"goods_id":"11123","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11123.jpg?t=1392521208","goods_name":"奥朵姆水曲柳床头灯","price":"499.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"41","liked":"0"},{"goods_id":"11269","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11269.jpg?t=1392521208","goods_name":"Lollia Imagine Foaming Bubble Bath","price":"597.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"85","liked":"0"},{"goods_id":"11449","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11449.jpg?t=1392521207","goods_name":"DEATHLY SCARY SHADOW SHOWER死亡阴影浴帘","price":"275.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"129","liked":"0"},{"goods_id":"11450","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11450.jpg?t=1392521207","goods_name":"Vintage Scale Clock","price":"483.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"55","liked":"0"},{"goods_id":"11608","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11608.jpg?t=1392521207","goods_name":"东京日和","price":"41.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"99","liked":"0"},{"goods_id":"12224","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/12/12224.jpg?t=1392521207","goods_name":" 奈良美智Gummi Girl 糖果盒","price":"780.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"86","liked":"0"}]}}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-08 14:02:02
         * account_id : 0
         * cost : 0.017391920089722
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : true
         * num_items : 12
         * items : {"user_id":"1502","user_name":"张悦然","is_daren":"1","email":"karmakarma@qq.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/1/1502.jpg?t=1499493722","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/1/1502.jpg?t=1499493722","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/1/1502.jpg?t=1499493722"},"user_desc":"作家","friend":"0","like_count":"49","recommendation_count":"12","following_count":"26","followed_count":"2444","template_id":"0","goods":[{"goods_id":"6098","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/6/6098.jpg?t=1392521211","goods_name":"Harris Tweed翻毛搭扣小钱包 ","price":"889.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"78","liked":"0"},{"goods_id":"10928","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10928.jpg?t=1392521208","goods_name":"45rpm靛青二重织拔染裙衣","price":"3985.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"44","liked":"0"},{"goods_id":"10929","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10929.jpg?t=1392521208","goods_name":"Henry Cuir Beguelin Barneys Vachetta Embroidered Card Wallet","price":"1208.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"49","liked":"0"},{"goods_id":"11050","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11050.jpg?t=1392521208","goods_name":"DODOcase for Kindle","price":"500.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"51","liked":"0"},{"goods_id":"11123","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11123.jpg?t=1392521208","goods_name":"奥朵姆水曲柳床头灯","price":"499.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"41","liked":"0"},{"goods_id":"11269","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11269.jpg?t=1392521208","goods_name":"Lollia Imagine Foaming Bubble Bath","price":"597.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"85","liked":"0"},{"goods_id":"11449","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11449.jpg?t=1392521207","goods_name":"DEATHLY SCARY SHADOW SHOWER死亡阴影浴帘","price":"275.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"129","liked":"0"},{"goods_id":"11450","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11450.jpg?t=1392521207","goods_name":"Vintage Scale Clock","price":"483.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"55","liked":"0"},{"goods_id":"11608","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11608.jpg?t=1392521207","goods_name":"东京日和","price":"41.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"99","liked":"0"},{"goods_id":"12224","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/12/12224.jpg?t=1392521207","goods_name":" 奈良美智Gummi Girl 糖果盒","price":"780.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"86","liked":"0"}]}
         */

        private boolean has_more;
        private String num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getNum_items() {
            return num_items;
        }

        public void setNum_items(String num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * user_id : 1502
             * user_name : 张悦然
             * is_daren : 1
             * email : karmakarma@qq.com
             * user_image : {"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/1/1502.jpg?t=1499493722","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/1/1502.jpg?t=1499493722","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/1/1502.jpg?t=1499493722"}
             * user_desc : 作家
             * friend : 0
             * like_count : 49
             * recommendation_count : 12
             * following_count : 26
             * followed_count : 2444
             * template_id : 0
             * goods : [{"goods_id":"6098","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/6/6098.jpg?t=1392521211","goods_name":"Harris Tweed翻毛搭扣小钱包 ","price":"889.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"78","liked":"0"},{"goods_id":"10928","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10928.jpg?t=1392521208","goods_name":"45rpm靛青二重织拔染裙衣","price":"3985.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"44","liked":"0"},{"goods_id":"10929","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/10/10929.jpg?t=1392521208","goods_name":"Henry Cuir Beguelin Barneys Vachetta Embroidered Card Wallet","price":"1208.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"49","liked":"0"},{"goods_id":"11050","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11050.jpg?t=1392521208","goods_name":"DODOcase for Kindle","price":"500.00","owner_id":"1502","comment_count":"2","is_outter":"1","like_count":"51","liked":"0"},{"goods_id":"11123","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11123.jpg?t=1392521208","goods_name":"奥朵姆水曲柳床头灯","price":"499.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"41","liked":"0"},{"goods_id":"11269","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11269.jpg?t=1392521208","goods_name":"Lollia Imagine Foaming Bubble Bath","price":"597.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"85","liked":"0"},{"goods_id":"11449","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11449.jpg?t=1392521207","goods_name":"DEATHLY SCARY SHADOW SHOWER死亡阴影浴帘","price":"275.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"129","liked":"0"},{"goods_id":"11450","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11450.jpg?t=1392521207","goods_name":"Vintage Scale Clock","price":"483.00","owner_id":"1502","comment_count":"0","is_outter":"1","like_count":"55","liked":"0"},{"goods_id":"11608","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/11/11608.jpg?t=1392521207","goods_name":"东京日和","price":"41.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"99","liked":"0"},{"goods_id":"12224","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/12/12224.jpg?t=1392521207","goods_name":" 奈良美智Gummi Girl 糖果盒","price":"780.00","owner_id":"1502","comment_count":"1","is_outter":"1","like_count":"86","liked":"0"}]
             */

            private String user_id;
            private String user_name;
            private String is_daren;
            private String email;
            private UserImageBean user_image;
            private String user_desc;
            private String friend;
            private String like_count;
            private String recommendation_count;
            private String following_count;
            private String followed_count;
            private String template_id;
            private List<GoodsBean> goods;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getIs_daren() {
                return is_daren;
            }

            public void setIs_daren(String is_daren) {
                this.is_daren = is_daren;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public UserImageBean getUser_image() {
                return user_image;
            }

            public void setUser_image(UserImageBean user_image) {
                this.user_image = user_image;
            }

            public String getUser_desc() {
                return user_desc;
            }

            public void setUser_desc(String user_desc) {
                this.user_desc = user_desc;
            }

            public String getFriend() {
                return friend;
            }

            public void setFriend(String friend) {
                this.friend = friend;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getRecommendation_count() {
                return recommendation_count;
            }

            public void setRecommendation_count(String recommendation_count) {
                this.recommendation_count = recommendation_count;
            }

            public String getFollowing_count() {
                return following_count;
            }

            public void setFollowing_count(String following_count) {
                this.following_count = following_count;
            }

            public String getFollowed_count() {
                return followed_count;
            }

            public void setFollowed_count(String followed_count) {
                this.followed_count = followed_count;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class UserImageBean {
                /**
                 * self_img : 1
                 * orig : http://imgs-qn.iliangcang.com/ware/userhead/orig/2/1/1502.jpg?t=1499493722
                 * mid : http://imgs-qn.iliangcang.com/ware/userhead/mid/2/1/1502.jpg?t=1499493722
                 * tmb : http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/1/1502.jpg?t=1499493722
                 */

                private String self_img;
                private String orig;
                private String mid;
                private String tmb;

                public String getSelf_img() {
                    return self_img;
                }

                public void setSelf_img(String self_img) {
                    this.self_img = self_img;
                }

                public String getOrig() {
                    return orig;
                }

                public void setOrig(String orig) {
                    this.orig = orig;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getTmb() {
                    return tmb;
                }

                public void setTmb(String tmb) {
                    this.tmb = tmb;
                }
            }

            public static class GoodsBean {
                /**
                 * goods_id : 6098
                 * goods_image : http://imgs-qn.iliangcang.com/ware/goods/big/2/6/6098.jpg?t=1392521211
                 * goods_name : Harris Tweed翻毛搭扣小钱包
                 * price : 889.00
                 * owner_id : 1502
                 * comment_count : 0
                 * is_outter : 1
                 * like_count : 78
                 * liked : 0
                 */

                private String goods_id;
                private String goods_image;
                private String goods_name;
                private String price;
                private String owner_id;
                private String comment_count;
                private String is_outter;
                private String like_count;
                private String liked;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOwner_id() {
                    return owner_id;
                }

                public void setOwner_id(String owner_id) {
                    this.owner_id = owner_id;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getIs_outter() {
                    return is_outter;
                }

                public void setIs_outter(String is_outter) {
                    this.is_outter = is_outter;
                }

                public String getLike_count() {
                    return like_count;
                }

                public void setLike_count(String like_count) {
                    this.like_count = like_count;
                }

                public String getLiked() {
                    return liked;
                }

                public void setLiked(String liked) {
                    this.liked = liked;
                }
            }
        }
    }
}
