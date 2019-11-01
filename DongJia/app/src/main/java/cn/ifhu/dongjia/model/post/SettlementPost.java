package cn.ifhu.dongjia.model.post;

import java.util.List;

public class SettlementPost {

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
