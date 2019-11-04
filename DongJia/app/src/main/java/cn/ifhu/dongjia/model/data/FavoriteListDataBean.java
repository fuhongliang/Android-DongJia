package cn.ifhu.dongjia.model.data;

import java.util.List;

public class FavoriteListDataBean {

    /**
     * row_count : 2
     * page_count : 1
     * list : [{"goods_id":74,"name":"木月床 北欧简约双人床高箱储物床布艺软靠床婚床 雅致 高箱床 1.5*2.0米","price":"1599.00","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/b9/b97ac0c769c14ca2496964dee75c80b8f30ba9c3.png"},{"goods_id":59,"name":"箭牌卫浴（ARROW） 整体淋浴房弧扇形钢化玻璃简易淋浴房","price":"2649.00","goods_pic":"http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/23/23282cc6bb9f5ae3d976066146dbdbd8266575fc.png"}]
     */

    private String row_count;
    private int page_count;
    private List<ListBean> list;

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

    public static class ListBean {
        /**
         * goods_id : 74
         * name : 木月床 北欧简约双人床高箱储物床布艺软靠床婚床 雅致 高箱床 1.5*2.0米
         * price : 1599.00
         * goods_pic : http://yiwuyimei.oss-cn-beijing.aliyuncs.com/web/uploads/image/b9/b97ac0c769c14ca2496964dee75c80b8f30ba9c3.png
         */

        private int goods_id;
        private String name;
        private String price;
        private String goods_pic;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }
    }
}
