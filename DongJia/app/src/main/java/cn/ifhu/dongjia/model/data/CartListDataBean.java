package cn.ifhu.dongjia.model.data;

import java.util.List;

public class CartListDataBean {

    /**
     * row_count : 3
     * page_count : 1
     * list : [{"cart_id":144,"goods_id":19,"goods_name":"开荒保洁 企事业单位 外墙翻新清洗外墙防水","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/e8/e81075be10e8efe6cb29e70a06d25b3b336c3106.jpg","num":1,"attr_list":[{"attr_group_name":"颜色","attr_name":"白色"},{"attr_group_name":"上门时间","attr_name":"下午"}],"price":100,"unitPrice":100,"max_num":100,"disabled":false}]
     * mch_list : [{"id":4,"name":"箭牌卫浴","logo":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/09/092d926f9df2e61fd024772ad71735335fba212f.jpg","list":[{"cart_id":143,"goods_id":102,"goods_name":"黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png","num":1,"attr_list":[{"attr_group_name":"规格","attr_name":"默认"}],"price":1899.2,"unitPrice":1899.2,"max_num":59989,"disabled":false,"mch_id":4},{"cart_id":145,"goods_id":77,"goods_name":"安全防盗门 智能门家庭进户门 入户门指纹密码锁门 莱茵河系列","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png","num":1,"attr_list":[{"attr_group_name":"颜色","attr_name":"机械锁【左右开向】"}],"price":2488,"unitPrice":2488,"max_num":995,"disabled":false,"mch_id":4}]}]
     */

    private String row_count;
    private int page_count;
    private List<ListBean> list;
    private List<MchListBean> mch_list;

    public String getRow_count() {
        return row_count;
    }

    public void setRow_count(String row_count) {
        this.row_count = row_count;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<MchListBean> getMch_list() {
        return mch_list;
    }

    public void setMch_list(List<MchListBean> mch_list) {
        this.mch_list = mch_list;
    }

    public static class ListBean {
        /**
         * cart_id : 144
         * goods_id : 19
         * goods_name : 开荒保洁 企事业单位 外墙翻新清洗外墙防水
         * goods_pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/e8/e81075be10e8efe6cb29e70a06d25b3b336c3106.jpg
         * num : 1
         * attr_list : [{"attr_group_name":"颜色","attr_name":"白色"},{"attr_group_name":"上门时间","attr_name":"下午"}]
         * price : 100
         * unitPrice : 100
         * max_num : 100
         * disabled : false
         */

        private int cart_id;
        private int goods_id;
        private String goods_name;
        private String goods_pic;
        private int num;
        private int price;
        private int unitPrice;
        private int max_num;
        private boolean disabled;
        private List<AttrListBean> attr_list;

        public int getCart_id() {
            return cart_id;
        }

        public void setCart_id(int cart_id) {
            this.cart_id = cart_id;
        }

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getMax_num() {
            return max_num;
        }

        public void setMax_num(int max_num) {
            this.max_num = max_num;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public List<AttrListBean> getAttr_list() {
            return attr_list;
        }

        public void setAttr_list(List<AttrListBean> attr_list) {
            this.attr_list = attr_list;
        }

        public static class AttrListBean {
            /**
             * attr_group_name : 颜色
             * attr_name : 白色
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

    public static class MchListBean {
        /**
         * id : 4
         * name : 箭牌卫浴
         * logo : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/09/092d926f9df2e61fd024772ad71735335fba212f.jpg
         * list : [{"cart_id":143,"goods_id":102,"goods_name":"黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png","num":1,"attr_list":[{"attr_group_name":"规格","attr_name":"默认"}],"price":1899.2,"unitPrice":1899.2,"max_num":59989,"disabled":false,"mch_id":4},{"cart_id":145,"goods_id":77,"goods_name":"安全防盗门 智能门家庭进户门 入户门指纹密码锁门 莱茵河系列","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/aa/aa86fc4feb44657a80f92c6dc5b705c56cdbebd9.png","num":1,"attr_list":[{"attr_group_name":"颜色","attr_name":"机械锁【左右开向】"}],"price":2488,"unitPrice":2488,"max_num":995,"disabled":false,"mch_id":4}]
         */

        private int id;
        private String name;
        private String logo;
        private List<ListBeanX> list;
        private boolean isSelect_shop;      //店铺是否在购物车中被选中

        public boolean getIsSelect_shop() {
            return isSelect_shop;
        }

        public void setIsSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }

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

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * cart_id : 143
             * goods_id : 102
             * goods_name : 黑色弧扇形淋浴房整体扇形浴屏卫生间隔断玻璃门简易干湿分离浴室
             * goods_pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/30/302c4262c1e7e2d546048b9e77b634effd1907ca.png
             * num : 1
             * attr_list : [{"attr_group_name":"规格","attr_name":"默认"}]
             * price : 1899.2
             * unitPrice : 1899.2
             * max_num : 59989
             * disabled : false
             * mch_id : 4
             */

            private int cart_id;
            private int goods_id;
            private String goods_name;
            private String goods_pic;
            private int num;
            private double price;
            private double unitPrice;
            private int max_num;
            private boolean disabled;
            private int mch_id;
            private List<AttrListBeanX> attr_list;

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
            }

            public int getMax_num() {
                return max_num;
            }

            public void setMax_num(int max_num) {
                this.max_num = max_num;
            }

            public boolean isDisabled() {
                return disabled;
            }

            public void setDisabled(boolean disabled) {
                this.disabled = disabled;
            }

            public int getMch_id() {
                return mch_id;
            }

            public void setMch_id(int mch_id) {
                this.mch_id = mch_id;
            }

            public List<AttrListBeanX> getAttr_list() {
                return attr_list;
            }

            public void setAttr_list(List<AttrListBeanX> attr_list) {
                this.attr_list = attr_list;
            }

            public static class AttrListBeanX {
                /**
                 * attr_group_name : 规格
                 * attr_name : 默认
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
}
