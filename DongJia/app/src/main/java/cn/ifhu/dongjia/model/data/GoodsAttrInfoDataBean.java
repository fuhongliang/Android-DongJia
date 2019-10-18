package cn.ifhu.dongjia.model.data;

import java.util.List;

public class GoodsAttrInfoDataBean {

    /**
     * attr_list : [{"attr_id":64,"attr_name":"床+床头柜*2"},{"attr_id":61,"attr_name":"1800*2000"}]
     * num : 9999
     * price : 3480
     * no :
     * pic :
     * miaosha : null
     */

    private int num;
    private String price;
    private String no;
    private String pic;
    private Object miaosha;
    private List<AttrListBean> attr_list;

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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Object getMiaosha() {
        return miaosha;
    }

    public void setMiaosha(Object miaosha) {
        this.miaosha = miaosha;
    }

    public List<AttrListBean> getAttr_list() {
        return attr_list;
    }

    public void setAttr_list(List<AttrListBean> attr_list) {
        this.attr_list = attr_list;
    }

    public static class AttrListBean {
        /**
         * attr_id : 64
         * attr_name : 床+床头柜*2
         */

        private int attr_id;
        private String attr_name;

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
