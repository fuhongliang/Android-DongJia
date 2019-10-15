package cn.ifhu.dongjia.model.data;

import java.util.List;

public class MeDataBean {

    /**
     * order_count : {"all":"0","status_0":"0","status_1":"0","status_2":"0","status_3":"0"}
     * show_customer_service : 1
     * contact_tel : 4000088759
     * share_setting : {"id":"5","first":"0.00","second":"0.00","third":"0.00","store_id":"4","level":"0","condition":"0","share_condition":"0","content":null,"pay_type":"0","min_money":"1.00","agree":null,"first_name":null,"second_name":null,"third_name":null,"pic_url_1":null,"pic_url_2":null,"price_type":"0","bank":null,"remaining_sum":"0","rebate":"0.00","is_rebate":"0","work_type":["请选择工种","水电师傅","油漆师傅","设计师","瓦工师傅","电工师傅"]}
     * user_info : {"nickname":"测试账号","binding":null,"avatar_url":"http://thirdwx.qlogo.cn/mmopen/vi_32/AFVJP1c6HQbJib1dOmW1gJKRXqPT7wYoXricQJBnMyibk83HD1hREewuKRTRZpMZfY6RSKYHibVxkPOQJxCeuVVISw/132","is_distributor":0,"parent":"总店","id":21,"is_clerk":0,"level":-1,"level_name":"普通用户","integral":0,"money":"0.00","is_share":false}
     * next_level : {"id":"1","store_id":"4","level":"1","name":"1级会员","money":"100.00","discount":"9.5","status":"1","is_delete":"0","addtime":"1540203537","image":"http://tuzhuangjia.zxrweb.com/web/uploads/image/0e/0e2c035dd891c93140371ab12cded95c8f414fc6.png","price":"300.00","detail":"会员权益提示，会员权益提示，会员权益提示。","buy_prompt":"会员购买提示\r\n会员购买提示\r\n会员购买提示\r\n"}
     * menu_list : [{"is_show":1,"name":"我的拼团","icon":"/images/pt-my-group.png","open_type":"navigator","url":"/pages/pt/order/order","tel":""},{"is_show":1,"name":"分销中心","icon":"/images/icon-user-fx.png","open_type":"navigator","url":"/pages/share/index","tel":""},{"is_show":1,"name":"我的卡券","icon":"/images/icon-user-card.png","open_type":"navigator","url":"/pages/card/card","tel":""},{"is_show":1,"name":"我的优惠券","icon":"/images/icon-user-yhq.png","open_type":"navigator","url":"/pages/coupon/coupon","tel":""},{"is_show":1,"name":"领券中心","icon":"/images/icon-user-lingqu.png","open_type":"navigator","url":"/pages/coupon-list/coupon-list","tel":""},{"is_show":1,"name":"我的收藏","icon":"/images/icon-user-sc.png","open_type":"navigator","url":"/pages/favorite/favorite","tel":""},{"is_show":1,"name":"在线客服","icon":"/images/icon-user-kf.png","open_type":"contact","url":"","tel":""},{"is_show":1,"name":"联系我们","icon":"/images/icon-user-lx.png","open_type":"tel","url":"","tel":"4000088759"},{"is_show":1,"name":"服务中心","icon":"/images/icon-help.png","open_type":"navigator","url":"/pages/article-list/article-list?id=2","tel":""},{"is_show":1,"name":"关于我们","icon":"/images/icon-about-us.png","open_type":"navigator","url":"/pages/article-detail/article-detail?id=about_us","tel":""},{"is_show":1,"name":"我的预约","icon":"/images/icon-about-us.png","open_type":"navigator","url":"/pages/book/order/order","tel":""}]
     * user_center_bg : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/68/6863a2ef5d6dee3a3f9c3fab962c2e3555aa3302.png
     * orders : {"status_0":{"text":"待付款","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/83/83280e0bd08422aa3068ac722badaa8e74ea6d0e.png"},"status_1":{"text":"待发货","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/9f/9f04be4e6adf50b92fe95d9097417eb30f865cd6.png"},"status_2":{"text":"待收货","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/ae/ae68d4e0410b802d32a15498dd97189fcd1f8382.png"},"status_3":{"text":"已完成","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/29/29532635159c847126bf4fe7b074a6706866f0a7.png"},"status_4":{"text":"售后","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/62/6218fb9cf1bb202d8ee7a74c610346d3cda7116a.png"}}
     * menus : [{"id":"miaosha","name":"秒杀订单","icon":"https://testjiaju.ifhu.cn/statics/images/user-center/icon-user-ms.png","open_type":"navigator","url":"/pages/miaosha/order/order","tel":""},{"id":"youhuiquan","name":"我的优惠券","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/2c/2ced11de1f31bd5341d281c0bf07c81f38a49196.png","open_type":"navigator","url":"/subpages/my-evaluate/my-evaluate","tel":""},{"id":"address","name":"收货地址","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/ea/eac5f97bf4793b4879f758d578e6ed62e1cbe025.png","open_type":"navigator","url":"/address/address/address","tel":""},{"id":"shoucang","name":"我的收藏","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/b6/b61d6bb21570fe8e6aaa8f1a203c5cd77a6f20d9.png","open_type":"navigator","url":"/subpages/favorite/favorite","tel":""},{"id":"kefu","name":"在线客服","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/0d/0d9fc5ab37c33d96000c54c8e4e5c4ea87e9da98.png","open_type":"contact","url":"","tel":""},{"id":"guanyu","name":"关于我们","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/67/6742393c744d6b93be9ac4097c9b2cb7d2f3bc06.png","open_type":"navigator","url":"/subpages/article-detail/article-detail?id=about_us","tel":""}]
     * copyright : {"text":"","icon":"","url":"","open_type":"","is_phone":0,"phone":""}
     * wallet : {"integral":1,"re":"1","is_wallet":"1"}
     * style : {"menu":"0","top":"0"}
     * setting : {"is_order":"1"}
     */

    private OrderCountBean order_count;
    private int show_customer_service;
    private String contact_tel;
    private ShareSettingBean share_setting;
    private UserInfoBean user_info;
    private NextLevelBean next_level;
    private String user_center_bg;
    private OrdersBean orders;
    private CopyrightBean copyright;
    private WalletBean wallet;
    private StyleBean style;
    private SettingBean setting;
    private List<MenuListBean> menu_list;
    private List<MenusBean> menus;

    public OrderCountBean getOrder_count() {
        return order_count;
    }

    public void setOrder_count(OrderCountBean order_count) {
        this.order_count = order_count;
    }

    public int getShow_customer_service() {
        return show_customer_service;
    }

    public void setShow_customer_service(int show_customer_service) {
        this.show_customer_service = show_customer_service;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public ShareSettingBean getShare_setting() {
        return share_setting;
    }

    public void setShare_setting(ShareSettingBean share_setting) {
        this.share_setting = share_setting;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public NextLevelBean getNext_level() {
        return next_level;
    }

    public void setNext_level(NextLevelBean next_level) {
        this.next_level = next_level;
    }

    public String getUser_center_bg() {
        return user_center_bg;
    }

    public void setUser_center_bg(String user_center_bg) {
        this.user_center_bg = user_center_bg;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public CopyrightBean getCopyright() {
        return copyright;
    }

    public void setCopyright(CopyrightBean copyright) {
        this.copyright = copyright;
    }

    public WalletBean getWallet() {
        return wallet;
    }

    public void setWallet(WalletBean wallet) {
        this.wallet = wallet;
    }

    public StyleBean getStyle() {
        return style;
    }

    public void setStyle(StyleBean style) {
        this.style = style;
    }

    public SettingBean getSetting() {
        return setting;
    }

    public void setSetting(SettingBean setting) {
        this.setting = setting;
    }

    public List<MenuListBean> getMenu_list() {
        return menu_list;
    }

    public void setMenu_list(List<MenuListBean> menu_list) {
        this.menu_list = menu_list;
    }

    public List<MenusBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusBean> menus) {
        this.menus = menus;
    }

    public static class OrderCountBean {
        /**
         * all : 0
         * status_0 : 0
         * status_1 : 0
         * status_2 : 0
         * status_3 : 0
         */

        private String all;
        private String status_0;
        private String status_1;
        private String status_2;
        private String status_3;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getStatus_0() {
            return status_0;
        }

        public void setStatus_0(String status_0) {
            this.status_0 = status_0;
        }

        public String getStatus_1() {
            return status_1;
        }

        public void setStatus_1(String status_1) {
            this.status_1 = status_1;
        }

        public String getStatus_2() {
            return status_2;
        }

        public void setStatus_2(String status_2) {
            this.status_2 = status_2;
        }

        public String getStatus_3() {
            return status_3;
        }

        public void setStatus_3(String status_3) {
            this.status_3 = status_3;
        }
    }

    public static class ShareSettingBean {
        /**
         * id : 5
         * first : 0.00
         * second : 0.00
         * third : 0.00
         * store_id : 4
         * level : 0
         * condition : 0
         * share_condition : 0
         * content : null
         * pay_type : 0
         * min_money : 1.00
         * agree : null
         * first_name : null
         * second_name : null
         * third_name : null
         * pic_url_1 : null
         * pic_url_2 : null
         * price_type : 0
         * bank : null
         * remaining_sum : 0
         * rebate : 0.00
         * is_rebate : 0
         * work_type : ["请选择工种","水电师傅","油漆师傅","设计师","瓦工师傅","电工师傅"]
         */

        private String id;
        private String first;
        private String second;
        private String third;
        private String store_id;
        private String level;
        private String condition;
        private String share_condition;
        private Object content;
        private String pay_type;
        private String min_money;
        private Object agree;
        private Object first_name;
        private Object second_name;
        private Object third_name;
        private Object pic_url_1;
        private Object pic_url_2;
        private String price_type;
        private Object bank;
        private String remaining_sum;
        private String rebate;
        private String is_rebate;
        private List<String> work_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getThird() {
            return third;
        }

        public void setThird(String third) {
            this.third = third;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getShare_condition() {
            return share_condition;
        }

        public void setShare_condition(String share_condition) {
            this.share_condition = share_condition;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public Object getAgree() {
            return agree;
        }

        public void setAgree(Object agree) {
            this.agree = agree;
        }

        public Object getFirst_name() {
            return first_name;
        }

        public void setFirst_name(Object first_name) {
            this.first_name = first_name;
        }

        public Object getSecond_name() {
            return second_name;
        }

        public void setSecond_name(Object second_name) {
            this.second_name = second_name;
        }

        public Object getThird_name() {
            return third_name;
        }

        public void setThird_name(Object third_name) {
            this.third_name = third_name;
        }

        public Object getPic_url_1() {
            return pic_url_1;
        }

        public void setPic_url_1(Object pic_url_1) {
            this.pic_url_1 = pic_url_1;
        }

        public Object getPic_url_2() {
            return pic_url_2;
        }

        public void setPic_url_2(Object pic_url_2) {
            this.pic_url_2 = pic_url_2;
        }

        public String getPrice_type() {
            return price_type;
        }

        public void setPrice_type(String price_type) {
            this.price_type = price_type;
        }

        public Object getBank() {
            return bank;
        }

        public void setBank(Object bank) {
            this.bank = bank;
        }

        public String getRemaining_sum() {
            return remaining_sum;
        }

        public void setRemaining_sum(String remaining_sum) {
            this.remaining_sum = remaining_sum;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getIs_rebate() {
            return is_rebate;
        }

        public void setIs_rebate(String is_rebate) {
            this.is_rebate = is_rebate;
        }

        public List<String> getWork_type() {
            return work_type;
        }

        public void setWork_type(List<String> work_type) {
            this.work_type = work_type;
        }
    }

    public static class UserInfoBean {
        /**
         * nickname : 测试账号
         * binding : null
         * avatar_url : http://thirdwx.qlogo.cn/mmopen/vi_32/AFVJP1c6HQbJib1dOmW1gJKRXqPT7wYoXricQJBnMyibk83HD1hREewuKRTRZpMZfY6RSKYHibVxkPOQJxCeuVVISw/132
         * is_distributor : 0
         * parent : 总店
         * id : 21
         * is_clerk : 0
         * level : -1
         * level_name : 普通用户
         * integral : 0
         * money : 0.00
         * is_share : false
         */

        private String nickname;
        private Object binding;
        private String avatar_url;
        private int is_distributor;
        private String parent;
        private int id;
        private int is_clerk;
        private int level;
        private String level_name;
        private int integral;
        private String money;
        private boolean is_share;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getBinding() {
            return binding;
        }

        public void setBinding(Object binding) {
            this.binding = binding;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getIs_distributor() {
            return is_distributor;
        }

        public void setIs_distributor(int is_distributor) {
            this.is_distributor = is_distributor;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_clerk() {
            return is_clerk;
        }

        public void setIs_clerk(int is_clerk) {
            this.is_clerk = is_clerk;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public boolean isIs_share() {
            return is_share;
        }

        public void setIs_share(boolean is_share) {
            this.is_share = is_share;
        }
    }

    public static class NextLevelBean {
        /**
         * id : 1
         * store_id : 4
         * level : 1
         * name : 1级会员
         * money : 100.00
         * discount : 9.5
         * status : 1
         * is_delete : 0
         * addtime : 1540203537
         * image : http://tuzhuangjia.zxrweb.com/web/uploads/image/0e/0e2c035dd891c93140371ab12cded95c8f414fc6.png
         * price : 300.00
         * detail : 会员权益提示，会员权益提示，会员权益提示。
         * buy_prompt : 会员购买提示
         会员购买提示
         会员购买提示

         */

        private String id;
        private String store_id;
        private String level;
        private String name;
        private String money;
        private String discount;
        private String status;
        private String is_delete;
        private String addtime;
        private String image;
        private String price;
        private String detail;
        private String buy_prompt;

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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getBuy_prompt() {
            return buy_prompt;
        }

        public void setBuy_prompt(String buy_prompt) {
            this.buy_prompt = buy_prompt;
        }
    }

    public static class OrdersBean {
        /**
         * status_0 : {"text":"待付款","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/83/83280e0bd08422aa3068ac722badaa8e74ea6d0e.png"}
         * status_1 : {"text":"待发货","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/9f/9f04be4e6adf50b92fe95d9097417eb30f865cd6.png"}
         * status_2 : {"text":"待收货","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/ae/ae68d4e0410b802d32a15498dd97189fcd1f8382.png"}
         * status_3 : {"text":"已完成","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/29/29532635159c847126bf4fe7b074a6706866f0a7.png"}
         * status_4 : {"text":"售后","icon":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/62/6218fb9cf1bb202d8ee7a74c610346d3cda7116a.png"}
         */

        private Status0Bean status_0;
        private Status1Bean status_1;
        private Status2Bean status_2;
        private Status3Bean status_3;
        private Status4Bean status_4;

        public Status0Bean getStatus_0() {
            return status_0;
        }

        public void setStatus_0(Status0Bean status_0) {
            this.status_0 = status_0;
        }

        public Status1Bean getStatus_1() {
            return status_1;
        }

        public void setStatus_1(Status1Bean status_1) {
            this.status_1 = status_1;
        }

        public Status2Bean getStatus_2() {
            return status_2;
        }

        public void setStatus_2(Status2Bean status_2) {
            this.status_2 = status_2;
        }

        public Status3Bean getStatus_3() {
            return status_3;
        }

        public void setStatus_3(Status3Bean status_3) {
            this.status_3 = status_3;
        }

        public Status4Bean getStatus_4() {
            return status_4;
        }

        public void setStatus_4(Status4Bean status_4) {
            this.status_4 = status_4;
        }

        public static class Status0Bean {
            /**
             * text : 待付款
             * icon : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/83/83280e0bd08422aa3068ac722badaa8e74ea6d0e.png
             */

            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class Status1Bean {
            /**
             * text : 待发货
             * icon : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/9f/9f04be4e6adf50b92fe95d9097417eb30f865cd6.png
             */

            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class Status2Bean {
            /**
             * text : 待收货
             * icon : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/ae/ae68d4e0410b802d32a15498dd97189fcd1f8382.png
             */

            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class Status3Bean {
            /**
             * text : 已完成
             * icon : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/29/29532635159c847126bf4fe7b074a6706866f0a7.png
             */

            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class Status4Bean {
            /**
             * text : 售后
             * icon : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/62/6218fb9cf1bb202d8ee7a74c610346d3cda7116a.png
             */

            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }

    public static class CopyrightBean {
        /**
         * text :
         * icon :
         * url :
         * open_type :
         * is_phone : 0
         * phone :
         */

        private String text;
        private String icon;
        private String url;
        private String open_type;
        private int is_phone;
        private String phone;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }

        public int getIs_phone() {
            return is_phone;
        }

        public void setIs_phone(int is_phone) {
            this.is_phone = is_phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class WalletBean {
        /**
         * integral : 1
         * re : 1
         * is_wallet : 1
         */

        private int integral;
        private String re;
        private String is_wallet;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getRe() {
            return re;
        }

        public void setRe(String re) {
            this.re = re;
        }

        public String getIs_wallet() {
            return is_wallet;
        }

        public void setIs_wallet(String is_wallet) {
            this.is_wallet = is_wallet;
        }
    }

    public static class StyleBean {
        /**
         * menu : 0
         * top : 0
         */

        private String menu;
        private String top;

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }
    }

    public static class SettingBean {
        /**
         * is_order : 1
         */

        private String is_order;

        public String getIs_order() {
            return is_order;
        }

        public void setIs_order(String is_order) {
            this.is_order = is_order;
        }
    }

    public static class MenuListBean {
        /**
         * is_show : 1
         * name : 我的拼团
         * icon : /images/pt-my-group.png
         * open_type : navigator
         * url : /pages/pt/order/order
         * tel :
         */

        private int is_show;
        private String name;
        private String icon;
        private String open_type;
        private String url;
        private String tel;

        public int getIs_show() {
            return is_show;
        }

        public void setIs_show(int is_show) {
            this.is_show = is_show;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class MenusBean {
        /**
         * id : miaosha
         * name : 秒杀订单
         * icon : https://testjiaju.ifhu.cn/statics/images/user-center/icon-user-ms.png
         * open_type : navigator
         * url : /pages/miaosha/order/order
         * tel :
         */

        private String id;
        private String name;
        private String icon;
        private String open_type;
        private String url;
        private String tel;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
