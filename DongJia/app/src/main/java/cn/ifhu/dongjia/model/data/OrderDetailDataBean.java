package cn.ifhu.dongjia.model.data;

import java.util.List;

public class OrderDetailDataBean {

    /**
     * status_code : 1
     * mch : {"id":4,"name":"箭牌卫浴","logo":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/09/092d926f9df2e61fd024772ad71735335fba212f.jpg"}
     * order_id : 307
     * is_pay : 1
     * is_send : 0
     * is_confirm : 0
     * status : 订单待发货
     * express :
     * express_no :
     * name : ssss
     * mobile : 18319699581
     * address : 北京市北京市东城区xxxx
     * order_no : 20191107103806850672
     * addtime : 2019-11-07 10:38
     * total_price : 4299
     * express_price : 0
     * goods_total_price : 4299
     * coupon_sub_price : 0.00
     * pay_price : 4299.00
     * num : 1
     * goods_list : [{"goods_id":"77","order_detail_id":"258","name":"安全防盗门 智能门家庭进户门 入户门指纹密码锁门 莱茵河系列","attr":[{"attr_id":"74","attr_group_name":"颜色","attr_name":"K8指纹锁【左右开向】","no":""}],"num":"1","total_price":"4299.00","pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png","is_order_refund":0,"order_refund_enable":0}]
     * is_offline : 0
     * content :
     * before_update :
     * money :
     * shop : null
     * discount : 10.00
     * user_coupon_id : null
     * words : null
     * pay_type : 3
     */

    private int status_code;
    private MchBean mch;
    private int order_id;
    private int is_pay;
    private int is_send;
    private int is_confirm;
    private String status;
    private String express;
    private String express_no;
    private String name;
    private String mobile;
    private String address;
    private String order_no;
    private String addtime;
    private double total_price;
    private double express_price;
    private double goods_total_price;
    private String coupon_sub_price;
    private String pay_price;
    private int num;
    private int is_offline;
    private String content;
    private String before_update;
    private String money;
    private Object shop;
    private String discount;
    private Object user_coupon_id;
    private Object words;
    private int pay_type;
    private List<GoodsListBean> goods_list;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public MchBean getMch() {
        return mch;
    }

    public void setMch(MchBean mch) {
        this.mch = mch;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getIs_send() {
        return is_send;
    }

    public void setIs_send(int is_send) {
        this.is_send = is_send;
    }

    public int getIs_confirm() {
        return is_confirm;
    }

    public void setIs_confirm(int is_confirm) {
        this.is_confirm = is_confirm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpress_no() {
        return express_no;
    }

    public void setExpress_no(String express_no) {
        this.express_no = express_no;
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

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public double getExpress_price() {
        return express_price;
    }

    public void setExpress_price(double express_price) {
        this.express_price = express_price;
    }

    public double getGoods_total_price() {
        return goods_total_price;
    }

    public void setGoods_total_price(double goods_total_price) {
        this.goods_total_price = goods_total_price;
    }

    public String getCoupon_sub_price() {
        return coupon_sub_price;
    }

    public void setCoupon_sub_price(String coupon_sub_price) {
        this.coupon_sub_price = coupon_sub_price;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIs_offline() {
        return is_offline;
    }

    public void setIs_offline(int is_offline) {
        this.is_offline = is_offline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBefore_update() {
        return before_update;
    }

    public void setBefore_update(String before_update) {
        this.before_update = before_update;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Object getShop() {
        return shop;
    }

    public void setShop(Object shop) {
        this.shop = shop;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Object getUser_coupon_id() {
        return user_coupon_id;
    }

    public void setUser_coupon_id(Object user_coupon_id) {
        this.user_coupon_id = user_coupon_id;
    }

    public Object getWords() {
        return words;
    }

    public void setWords(Object words) {
        this.words = words;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class MchBean {
        /**
         * id : 4
         * name : 箭牌卫浴
         * logo : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/09/092d926f9df2e61fd024772ad71735335fba212f.jpg
         */

        private int id;
        private String name;
        private String logo;

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
    }

    public static class GoodsListBean {
        /**
         * goods_id : 77
         * order_detail_id : 258
         * name : 安全防盗门 智能门家庭进户门 入户门指纹密码锁门 莱茵河系列
         * attr : [{"attr_id":"74","attr_group_name":"颜色","attr_name":"K8指纹锁【左右开向】","no":""}]
         * num : 1
         * total_price : 4299.00
         * pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png
         * goods_pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png
         * is_order_refund : 0
         * order_refund_enable : 0
         */

        private String goods_id;
        private String order_detail_id;
        private String name;
        private String num;
        private String total_price;
        private String pic;
        private String goods_pic;
        private int is_order_refund;
        private int order_refund_enable;
        private List<AttrBean> attr;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getOrder_detail_id() {
            return order_detail_id;
        }

        public void setOrder_detail_id(String order_detail_id) {
            this.order_detail_id = order_detail_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public int getIs_order_refund() {
            return is_order_refund;
        }

        public void setIs_order_refund(int is_order_refund) {
            this.is_order_refund = is_order_refund;
        }

        public int getOrder_refund_enable() {
            return order_refund_enable;
        }

        public void setOrder_refund_enable(int order_refund_enable) {
            this.order_refund_enable = order_refund_enable;
        }

        public List<AttrBean> getAttr() {
            return attr;
        }

        public void setAttr(List<AttrBean> attr) {
            this.attr = attr;
        }

        public static class AttrBean {
            /**
             * attr_id : 74
             * attr_group_name : 颜色
             * attr_name : K8指纹锁【左右开向】
             * no :
             */

            private String attr_id;
            private String attr_group_name;
            private String attr_name;
            private String no;

            public String getAttr_id() {
                return attr_id;
            }

            public void setAttr_id(String attr_id) {
                this.attr_id = attr_id;
            }

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

            public String getNo() {
                return no;
            }

            public void setNo(String no) {
                this.no = no;
            }
        }
    }
}
