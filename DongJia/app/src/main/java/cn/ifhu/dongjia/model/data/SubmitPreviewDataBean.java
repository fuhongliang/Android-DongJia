package cn.ifhu.dongjia.model.data;

import java.io.Serializable;
import java.util.List;

public class SubmitPreviewDataBean {

    /**
     * total_price : 0
     * goods_info : {"goods_id":"117","attr":[{"attr_group_id":13,"attr_group_name":"yibd","attr_id":118,"attr_name":"guhd"},{"attr_group_id":14,"attr_group_name":"guhduu","attr_id":119,"attr_name":"fhbd"}],"num":1}
     * list : []
     * address : {"id":"37","name":"ssss","mobile":"18319699581","province_id":"2","province":"北京市","city_id":"3","city":"北京市","district_id":"4","district":"东城区","detail":"xxxx","is_default":"0"}
     * express_price : 0
     * integral : 0
     * goods_card_list : []
     * mch_list : [{"id":11,"name":"南洋胡氏家居","logo":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/a5/a50f041c5c8c6cbdc5505ce216a54be6ef425698.png","express_price":0,"total_price":3,"list":[{"goods_id":117,"goods_name":"yuhd","goods_pic":"http://yiwuyimei-test.oss-cn-beijing.aliyuncs.com/app/upload/pic/k1/3003e216f8add8db23fba36b7cbb1b1d..jpg","num":1,"price":"3.00","single_price":3,"attr_list":[{"attr_group_name":"yibd","attr_name":"guhd"},{"attr_group_name":"guhduu","attr_name":"fhbd"}],"give":0,"freight":0,"integral":null,"weight":6,"full_cut":"{\"pieces\":\"6\",\"forehead\":\"6\"}","cat_id":25,"mch_id":11}]}]
     * offer_rule : {"is_allowed":0,"total_price":0,"msg":"不是商城订单"}
     * coupon_list : []
     * shop_list : [{"address":"M485路,881路","mobile":"18899902929","id":"1","name":"一门旗舰店","longitude":"114.159298","latitude":"22.644968","distance":-1}]
     * is_shop : {"id":"1","store_id":"4","name":"一门旗舰店","mobile":"18899902929","address":"M485路,881路","is_delete":"0","addtime":"1568621231","longitude":"114.159298","latitude":"22.644968","score":"5","cover_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/2e/2e0b97f0139dfa2e75da1afcaed9ffefde9032b9.jpg","pic_url":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/2e/2e0b97f0139dfa2e75da1afcaed9ffefde9032b9.jpg","shop_time":"","content":"&amp;lt;p&amp;gt;门店介绍&amp;lt;/p&amp;gt;","is_default":"1"}
     * level : null
     * send_type : 0
     * form : {"is_form":0,"list":[]}
     * is_payment : {"wechat":"1","balance":"1"}
     * pay_type_list : [{"name":"微信支付","payment":0,"icon":"https://testjiaju.ifhu.cn/statics/images/recharge/icon-online.png"},{"name":"账户余额支付","payment":3,"icon":"https://testjiaju.ifhu.cn/statics/images/recharge/icon-balance.png"}]
     * is_area : 0
     */

    private double total_price;
    private GoodsInfoBean goods_info;
    private AddressBean address;
    private double express_price;
    private OfferRuleBean offer_rule;
    private IsShopBean is_shop;
    private Object level;
    private int send_type;
    private FormBean form;
    private IsPaymentBean is_payment;
    private int is_area;
    private List<?> list;
    private List<?> goods_card_list;
    private List<MchListBean> mch_list;
    private List<?> coupon_list;
    private List<ShopListBean> shop_list;
    private List<PayTypeListBean> pay_type_list;

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public GoodsInfoBean getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(GoodsInfoBean goods_info) {
        this.goods_info = goods_info;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public double getExpress_price() {
        return express_price;
    }

    public void setExpress_price(double express_price) {
        this.express_price = express_price;
    }

    public OfferRuleBean getOffer_rule() {
        return offer_rule;
    }

    public void setOffer_rule(OfferRuleBean offer_rule) {
        this.offer_rule = offer_rule;
    }

    public IsShopBean getIs_shop() {
        return is_shop;
    }

    public void setIs_shop(IsShopBean is_shop) {
        this.is_shop = is_shop;
    }

    public Object getLevel() {
        return level;
    }

    public void setLevel(Object level) {
        this.level = level;
    }

    public int getSend_type() {
        return send_type;
    }

    public void setSend_type(int send_type) {
        this.send_type = send_type;
    }

    public FormBean getForm() {
        return form;
    }

    public void setForm(FormBean form) {
        this.form = form;
    }

    public IsPaymentBean getIs_payment() {
        return is_payment;
    }

    public void setIs_payment(IsPaymentBean is_payment) {
        this.is_payment = is_payment;
    }

    public int getIs_area() {
        return is_area;
    }

    public void setIs_area(int is_area) {
        this.is_area = is_area;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public List<?> getGoods_card_list() {
        return goods_card_list;
    }

    public void setGoods_card_list(List<?> goods_card_list) {
        this.goods_card_list = goods_card_list;
    }

    public List<MchListBean> getMch_list() {
        return mch_list;
    }

    public void setMch_list(List<MchListBean> mch_list) {
        this.mch_list = mch_list;
    }

    public List<?> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<?> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public List<ShopListBean> getShop_list() {
        return shop_list;
    }

    public void setShop_list(List<ShopListBean> shop_list) {
        this.shop_list = shop_list;
    }

    public List<PayTypeListBean> getPay_type_list() {
        return pay_type_list;
    }

    public void setPay_type_list(List<PayTypeListBean> pay_type_list) {
        this.pay_type_list = pay_type_list;
    }

    public static class GoodsInfoBean {
        /**
         * goods_id : 117
         * attr : [{"attr_group_id":13,"attr_group_name":"yibd","attr_id":118,"attr_name":"guhd"},{"attr_group_id":14,"attr_group_name":"guhduu","attr_id":119,"attr_name":"fhbd"}]
         * num : 1
         */

        private String goods_id;
        private int num;
        private List<AttrBean> attr;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<AttrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<AttrBean> attr) {
            this.attr = attr;
        }

        public static class AttrBean {
            /**
             * attr_group_id : 13
             * attr_group_name : yibd
             * attr_id : 118
             * attr_name : guhd
             */

            private int attr_group_id;
            private String attr_group_name;
            private int attr_id;
            private String attr_name;

            public int getAttr_group_id() {
                return attr_group_id;
            }

            public void setAttr_group_id(int attr_group_id) {
                this.attr_group_id = attr_group_id;
            }

            public String getAttr_group_name() {
                return attr_group_name;
            }

            public void setAttr_group_name(String attr_group_name) {
                this.attr_group_name = attr_group_name;
            }

            public int getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(int attr_id) {
                this.attr_id = attr_id;
            }

            public String getAttr_name() {
                return attr_name;
            }

            public void setAttr_name(String attr_name) {
                this.attr_name = attr_name;
            }
        }
    }


    public static class OfferRuleBean {
        /**
         * is_allowed : 0
         * total_price : 0
         * msg : 不是商城订单
         */

        private int is_allowed;
        private int total_price;
        private String msg;

        public int getIs_allowed() {
            return is_allowed;
        }

        public void setIs_allowed(int is_allowed) {
            this.is_allowed = is_allowed;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class IsShopBean {
        /**
         * id : 1
         * store_id : 4
         * name : 一门旗舰店
         * mobile : 18899902929
         * address : M485路,881路
         * is_delete : 0
         * addtime : 1568621231
         * longitude : 114.159298
         * latitude : 22.644968
         * score : 5
         * cover_url : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/2e/2e0b97f0139dfa2e75da1afcaed9ffefde9032b9.jpg
         * pic_url : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/2e/2e0b97f0139dfa2e75da1afcaed9ffefde9032b9.jpg
         * shop_time :
         * content : &amp;lt;p&amp;gt;门店介绍&amp;lt;/p&amp;gt;
         * is_default : 1
         */

        private String id;
        private String store_id;
        private String name;
        private String mobile;
        private String address;
        private String is_delete;
        private String addtime;
        private String longitude;
        private String latitude;
        private String score;
        private String cover_url;
        private String pic_url;
        private String shop_time;
        private String content;
        private String is_default;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getShop_time() {
            return shop_time;
        }

        public void setShop_time(String shop_time) {
            this.shop_time = shop_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }
    }

    public static class FormBean {
        /**
         * is_form : 0
         * list : []
         */

        private int is_form;
        private List<?> list;

        public int getIs_form() {
            return is_form;
        }

        public void setIs_form(int is_form) {
            this.is_form = is_form;
        }

        public List<?> getList() {
            return list;
        }

        public void setList(List<?> list) {
            this.list = list;
        }
    }

    public static class IsPaymentBean {
        /**
         * wechat : 1
         * balance : 1
         */

        private String wechat;
        private String balance;

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }

    public static class MchListBean {
        /**
         * id : 11
         * name : 南洋胡氏家居
         * logo : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/a5/a50f041c5c8c6cbdc5505ce216a54be6ef425698.png
         * express_price : 0
         * total_price : 3
         * list : [{"goods_id":117,"goods_name":"yuhd","goods_pic":"http://yiwuyimei-test.oss-cn-beijing.aliyuncs.com/app/upload/pic/k1/3003e216f8add8db23fba36b7cbb1b1d..jpg","num":1,"price":"3.00","single_price":3,"attr_list":[{"attr_group_name":"yibd","attr_name":"guhd"},{"attr_group_name":"guhduu","attr_name":"fhbd"}],"give":0,"freight":0,"integral":null,"weight":6,"full_cut":"{\"pieces\":\"6\",\"forehead\":\"6\"}","cat_id":25,"mch_id":11}]
         */

        private int id;
        private String name;
        private String logo;
        private double express_price;
        private double total_price;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public double getExpress_price() {
            return express_price;
        }

        public void setExpress_price(double express_price) {
            this.express_price = express_price;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goods_id : 117
             * goods_name : yuhd
             * goods_pic : http://yiwuyimei-test.oss-cn-beijing.aliyuncs.com/app/upload/pic/k1/3003e216f8add8db23fba36b7cbb1b1d..jpg
             * num : 1
             * price : 3.00
             * single_price : 3
             * attr_list : [{"attr_group_name":"yibd","attr_name":"guhd"},{"attr_group_name":"guhduu","attr_name":"fhbd"}]
             * give : 0
             * freight : 0
             * integral : null
             * weight : 6
             * full_cut : {"pieces":"6","forehead":"6"}
             * cat_id : 25
             * mch_id : 11
             */

            private int goods_id;
            private String goods_name;
            private String goods_pic;
            private int num;
            private String price;
            private double single_price;
            private int give;
            private int freight;
            private Object integral;
            private int weight;
            private String full_cut;
            private int cat_id;
            private int mch_id;
            private List<AttrListBean> attr_list;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_pic() {
                return goods_pic;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public double getSingle_price() {
                return single_price;
            }

            public void setSingle_price(double single_price) {
                this.single_price = single_price;
            }

            public int getGive() {
                return give;
            }

            public void setGive(int give) {
                this.give = give;
            }

            public int getFreight() {
                return freight;
            }

            public void setFreight(int freight) {
                this.freight = freight;
            }

            public Object getIntegral() {
                return integral;
            }

            public void setIntegral(Object integral) {
                this.integral = integral;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public String getFull_cut() {
                return full_cut;
            }

            public void setFull_cut(String full_cut) {
                this.full_cut = full_cut;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public int getMch_id() {
                return mch_id;
            }

            public void setMch_id(int mch_id) {
                this.mch_id = mch_id;
            }

            public List<AttrListBean> getAttr_list() {
                return attr_list;
            }

            public void setAttr_list(List<AttrListBean> attr_list) {
                this.attr_list = attr_list;
            }

            public static class AttrListBean {
                /**
                 * attr_group_name : yibd
                 * attr_name : guhd
                 */

                private String attr_group_name;
                private String attr_name;

                public String getAttr_group_name() {
                    return attr_group_name;
                }

                public void setAttr_group_name(String attr_group_name) {
                    this.attr_group_name = attr_group_name;
                }

                public String getAttr_name() {
                    return attr_name;
                }

                public void setAttr_name(String attr_name) {
                    this.attr_name = attr_name;
                }
            }
        }
    }

    public static class ShopListBean {
        /**
         * address : M485路,881路
         * mobile : 18899902929
         * id : 1
         * name : 一门旗舰店
         * longitude : 114.159298
         * latitude : 22.644968
         * distance : -1
         */

        private String address;
        private String mobile;
        private String id;
        private String name;
        private String longitude;
        private String latitude;
        private int distance;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

    public static class PayTypeListBean {
        /**
         * name : 微信支付
         * payment : 0
         * icon : https://testjiaju.ifhu.cn/statics/images/recharge/icon-online.png
         */

        private String name;
        private int payment;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
